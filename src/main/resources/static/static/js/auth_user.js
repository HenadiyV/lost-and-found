

var app = new Vue({
    el: '#auth_user',
    template:'<div>'+
    '<div v-if="profile">{{profile}}</div>'+
    '</div>',
    data: {

       profile:frontendData.profile
    }
})
// '<div>{{message}}</div>'+
// // message: 'Привет, Vue!',