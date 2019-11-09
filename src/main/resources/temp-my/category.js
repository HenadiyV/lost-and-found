function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var categoryApi =Vue.resource('/category{/id}');


Vue.component('category-form', {
    props: ['categoryList', 'categoryAttr'],
    data: function() {
        return {

            name: '',
            id: ''
        }
    },
    watch: {
        categoryAttr: function(newVal, oldVal) {

            this.name = newVal.name;
            this.id = newVal.id;
        }
    },

    template:
    ' <form class="col-md-12">'+
    '<div>' +

    '<input type="text" placeholder="Назва категории" v-model="name" />' +
    '<input class="btn green accent-2" type="button" value="Зберегти" @click="save" />' +
    '</div> </form>',
    methods: {
        save: function() {
            var category = { name: this.name };

            if (this.id) {
                categoryApi.update({id: this.id}, category).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.categoryList, data.id);
                        this.categoryList.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                categoryApi.save({}, category).then(result =>
                    result.json().then(data => {
                        this.categoryList.push(data);
                        this.name = ''

                    })
                )
            }
        }
    }

});

Vue.component('category-row', {
    props: ['category', 'editMethod', 'categoryList'],
    template: '<div>' +
    '<i>({{ category.id }})</i> {{ category.name }} ' +
    '<span style="position: absolute; right: 0">' +
    '<input class="waves-effect waves-light btn" type="button" value="Змінити" @click="edit" />' +
    '<input class="waves-effect btn red darken-1" type="button" value="Видалити" @click="del" />' +
    '</span>' +
    '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.category);
        },
        del: function() {
            categoryApi.remove({id: this.category.id}).then(result => {
                if (result.ok) {
                    this.categoryList.splice(this.categoryList.indexOf(this.category), 1)
                }
            })
        }
    }


});

Vue.component('categoryList-list', {
    props: ['categoryList'],
    data: function() {
        return {
            category: null
        }
    },
    template:
    '<div class="collection" style="position: relative; width: 640px;">' +
    '<a class="btn blue darken-1" href="/admin">Адмінка</a>'+
    '<category-form :categoryList="categoryList" :categoryAttr="category" />' +
    '<category-row class="collection-item" v-for="category in categoryList" :key="category.id" :category="category" ' +
    ':editMethod="editMethod" :categoryList="categoryList" />' +

    '</div>',
    created: function() {
        categoryApi.get().then(result =>
            result.json().then(data =>
                data.forEach(category => this.categoryList.push(category))
            )
        )
    },
    methods: {
        editMethod: function(category) {
            this.category = category;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<categoryList-list :categoryList="categoryList" />',
    data: {
        categoryList: []
    }
});

