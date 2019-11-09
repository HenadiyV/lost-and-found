function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var userDetalisApi= Vue.resource('/admin/user/detalis{/id}');

Vue.component('user-form', {
    props: ['userList', 'userAttr'],
    data: function() {
        return {
            active:'',
            email:'',
            name: '',
            id: ''
        }
    },
    watch: {
       userAttr: function(newVal, oldVal) {
            this.active = newVal.active;
            this.email = newVal.email;
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
    '<div>' +'' +
    '<input type="text" th:placeholder="#{lang.hello}" v-model="email" />' +
    '<input type="text" placeholder="Write name" v-model="name" /><br/>' +
    '<label>Active</label><input type="checkbox"  v-model="active" /><br/>' +
    '<input type="button" value="Save" @click="save" />' +
    '</div>',
    methods: {
        save: function() {
            var user = { name: this.name,
                email: this.email,
                active:this.active,
            };

            if (this.id) {
                userDetalisApi.update({id: this.id}, user).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.userList, data.id);
                        this.userList.splice(index, 1, data);
                        this.email=''
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                userDetalisApi.save({}, user).then(result =>
                    result.json().then(data => {
                        this.userList.push(data);
                        this.email=''
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('user-row',{
    props:['user','editMethod','userList'],
    template:'<div>'+
        '<div><i>({{user.id}})</i> {{user.name}} {{user.email}}  {{user.active}}'+
   '<span style="position: absolute; right: -50%">' +
        '<input type="button" value="Edit" @click="edit" />' +
    '<input type="button" value="delete" @click="del" />' +
    '</span></div>' +
    '</div>',
    methods:{
        edit: function(){
            this.editMethod(this.user);
        },
        del: function() {
            userDetalisApi.remove({id: this.user.id}).then(result => {
                if (result.ok) {
                    this.userList.splice(this.userList.indexOf(this.user), 1)
                }
            })
        }
    }
});

Vue.component('user-list', {
    props: ['userList'],
    data: function() {
        return {
            user: null
        }
    },
    template:
    '<div style="position: relative; width: 300px;">' +
    '<user-form :userList="userList" :userAttr="user" />' +
    '<user-row v-for="user in userList" :key="user.id" :user="user" ' +
    ':editMethod="editMethod" :userList="userList" />' +
    '</div>',
    created: function() {
        userDetalisApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => this.userList.push(user))
            )
        )
    },
    methods: {
        editMethod: function(user) {
            this.user = user;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<user-list :userList="userList" />',
    data: {
        userList: []
    }
});