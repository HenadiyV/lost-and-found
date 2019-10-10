function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var subcategoryApi = Vue.resource('/admin/subcategory{/id}');

Vue.component('subcategory-form', {
    props: ['subcategorys', 'subcategoryAttr'],
    data: function() {
        return {
            name: '',
            id: ''
        }
    },
    watch: {
        subcategoryAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },

    template:
    ' <form class="col-md-12">'+
    '<div >' +
    '<input type="text"  v-model="name" placeholder="Назва категорії" />' +
    '<input class="btn green accent-2" type="button" value="Зберегти" @click="save" />' +
    // '<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"  />' +
    '</div></form>',
    methods: {
        save: function() {
            var subcategory = { name: this.name };

            if (this.id) {
                subcategoryApi.update({id: this.id}, subcategory).then(result =>
                result.json().then(data => {
                    var index = getIndex(this.subcategorys, data.id);
                this.subcategorys.splice(index, 1, data);
                this.name = ''
                this.id = ''
            })
            )
            } else {
                subcategoryApi.save({}, subcategory).then(result =>
                result.json().then(data => {
                    this.subcategorys.push(data);
                this.name = ''
            })
            )
            }
        }
    }

});

Vue.component('subcategory-row', {
    props: ['subcategory', 'editMethod', 'subcategorys'],
    template: '<div>' +
    '<i>({{ subcategory.id }})</i> {{ subcategory.name }}' +
    '<span style="position: absolute;  right: 0">' +
    '<input class="waves-effect waves-light btn" type="button" value="Змінити" @click="edit" />' +
    '<input class="waves-effect btn red darken-1" type="button" value="Видалити" @click="del" />' +
    '</span>' +
    '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.subcategory);
        },
        del: function() {
            subcategoryApi.remove({id: this.subcategory.id}).then(result => {
                if (result.ok) {
                this.subcategorys.splice(this.categorys.indexOf(this.subcategory), 1)
            }
        })
        }
    }


});

Vue.component('subcategorys-list', {
    props: ['subcategorys'],
    data: function() {
        return {
            subcategory: null
        }
    },
    template:
    '<div class="collection" style="position: relative; width: 640px;">' +
    '<subcategory-form :subcategorys="subcategorys" :subcategoryAttr="subcategory" />' +
    '<subcategory-row class="collection-item" v-for="subcategory in subcategorys" :key="subcategory.id" :category="subcategory" ' +
    ':editMethod="editMethod" :subcategorys="subcategorys" />' +
    '<a class="btn blue darken-1" href="/admin">Адмінка</a></div>',
    created: function() {
        subcategoryApi.get().then(result =>
        result.json().then(data =>
        data.forEach(category => this.subcategorys.push(subcategory))
    )
    )
    },
    methods: {
        editMethod: function(subcategory) {
            this.subcategory = subcategory;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<subcategorys-list :subcategorys="subcategorys" />',
    data: {
        subcategorys: []
    }
});
