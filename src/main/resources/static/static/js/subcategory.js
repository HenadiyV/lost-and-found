
function editSubcategory(Id,categoryId){
    var t='I'+Id;
    var label='L'+Id;
    var n=document.getElementById(t).value;

    var sub={
        id:Id,
        name:n,
        category:categoryId
    }

    if(n!="") {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: '/subcategory/edit',
            dataType: 'json',
            cache: false,
            data: JSON.stringify(sub),
            success: function (result) {
                document.getElementById(label).innerText = n;
                document.getElementById(t).value = "";
                $('#Id').val('');
            }
        });
    }
}
function adminModal(Id){
    $("#adminModal").modal('show');
    $("#idCat").val(Id);
    var cat='C'+Id;
    var n= document.getElementById(cat);
    //  var n= $("#cat").val();
    var s=n.value;
    var nc= document.getElementById("nameCat").innerHTML=(s);
    //$("#nameCat").innerHTML=(s);
    console.log(s);

}
function addSubCategory(){

    var addCat={
        category :$('#idCat').val(),
        name:$('#subcategory').val()
    }

    if(addCat.name!=null&&addCat.name!=""){
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: '/subcategory/add',
            dataType: 'json',
            cache: false,

            data:JSON.stringify(addCat),
            success: function (result) {
                var tempCat='T'+addCat.category;
                var td=document.createElement("b");
                td.append(addCat.name);
                var div= document.getElementById(tempCat);
                div.appendChild(td);
                div.insertAdjacentHTML("beforeend", '<br/>');
            },error : function() {
                alert('adding component failed with status');
            }
        });
    }
    else{
        alert("Bad");
    }

}

function deleteSubcategory(Id){
    var id=Id;
    var tr='TR'+Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/subcategory/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {

            document.getElementById(tr).hidden=true;
        }

    });
}
function getSubcategory(categoryId) {
    $.ajax({
        type: "GET",
        url: '/admin/subcategory',
        dataType: "json",
        success: [

            function (response) {

                $("#tableSub").find("tr:not(:first)").remove();
                var trHTML = '';

                for (var i = 0; i < response.length; i++) {
                    trHTML += '<tr><td><label>' + response[i].name+ '</label></td>' +

                        '<td> <button  onclick="editSubcategory('+response[i].id,categoryId+')" >Edit</button></td>'+
                        '<td> <button  onclick="deleteSubcategory('+response[i].id+')" >Delete</button></td>'+

                        '</tr>';

                }
                $("#tableSub tbody").append(trHTML);
            }
        ]
    });
}

// function addSubCategory(){
//
//     var addCat={
//         category :$('#idCat').val(),
//         name:$('#subcategory').val()
//     }
//
//     if(addCat.name!=null&&addCat.name!=""){
//         $.ajax({
//             type: "POST",
//             contentType: 'application/json; charset=utf-8',
//             url: '/subcategory/add',
//             dataType: 'json',
//             cache: false,
//
//             data:JSON.stringify(addCat),
//             success: function (result) {
//                 var tempCat='T'+addCat.category;
//                 var tab=document.getElementById('sub');
//                 var div= document.getElementById(tempCat);//
//
//                 // var s=addCat.name;
//                 var elem = document.createElement("p");
//                 var elemText = document.createTextNode(addCat.name);
//
//                 elem.appendChild(elemText);
//                 div.appendChild(elem);
// // alert(tempCat);
//                 $('#subcategory').val('');
//                 $('#idCat').val('');
//             },error : function() {
//                 alert('adding component failed with status');
//             }
//         });
//     }
//     else{
//         alert("Bad");
//     }
//
// }

///////////////////////////////////

// function getIndex(list, id) {
//     for (var i = 0; i < list.length; i++ ) {
//         if (list[i].id === id) {
//             return i;
//         }
//     }
//
//     return -1;
// }
//
// var subcategoryApi =Vue.resource('/sub{/id}');
//
//
// Vue.component('sub-form', {
//     props: ['subList', 'subAttr'],
//     data: function() {
//         return {
//            category: '',
//             name: '',
//             id: ''
//         }
//     },
//     watch: {
//         subAttr: function(newVal, oldVal) {
//             this.category = newVal.category;
//             this.name = newVal.name;
//             this.id = newVal.id;
//         }
//     },
//
//     template:
//     ' <form class="col-md-12">'+
//     '<div>' +
//     '<input type="text" placeholder="Назва категории" v-model="category" />' +
//     '<input type="text" placeholder="Назва subкатегории" v-model="name" />' +
//     '<input class="btn green accent-2" type="button" value="Зберегти" @click="save" />' +
//     '</div> </form>',
//     methods: {
//         save: function() {
//             var sub = { name: this.name, category: this.category };
//
//             if (this.id) {
//                 subcategoryApi.update({id: this.id}, sub).then(result =>
//                     result.json().then(data => {
//                         var index = getIndex(this.subList, data.id);
//                         this.subList.splice(index, 1, data);
//                         this.category=''
//                         this.name = ''
//                         this.id = ''
//                     })
//                 )
//             } else {
//                 subcategoryApi.save({}, sub).then(result =>
//                     result.json().then(data => {
//                         this.subList.push(data);
//                         this.name = '',
//                         this.category = ''
//                     })
//                 )
//             }
//         }
//     }
//
// });
//
// Vue.component('sub-row', {
//     props: ['sub', 'editMethod', 'subList'],
//     template: '<div>' +
//     '<i>({{ sub.id }})</i> {{ sub.name }} {{sub.category}}' +
//     '<span style="position: absolute; right: 0">' +
//     '<input class="waves-effect waves-light btn" type="button" value="Змінити" @click="edit" />' +
//     '<input class="waves-effect btn red darken-1" type="button" value="Видалити" @click="del" />' +
//     '</span>' +
//     '</div>',
//     methods: {
//         edit: function() {
//             this.editMethod(this.sub);
//         },
//         del: function() {
//             subcategoryApi.remove({id: this.sub.id}).then(result => {
//                 if (result.ok) {
//                     this.subList.splice(this.subList.indexOf(this.sub), 1)
//                 }
//             })
//         }
//     }
//
//
// });
//
// Vue.component('subList-list', {
//     props: ['subList'],
//     data: function() {
//         return {
//             sub: null
//         }
//     },
//     template:
//     '<div class="collection" style="position: relative; width: 640px;">' +
//     '<a class="btn blue darken-1" href="/admin">Адмінка</a>'+
//     '<sub-form :subList="subList" :subAttr="sub" />' +
//     '<sub-row class="collection-item" v-for="sub in subList" :key="sub.id" :sub="sub" ' +
//     ':editMethod="editMethod" :subList="subList" />' +
//
//     '</div>',
//     created: function() {
//         subcategoryApi.get().then(result =>
//             result.json().then(data =>
//                 data.forEach(sub => this.subList.push(sub))
//             )
//         )
//     },
//     methods: {
//         editMethod: function(sub) {
//             this.sub = sub;
//         }
//     }
// });
//
// var app = new Vue({
//     el: '#app',
//     template: '<subList-list :subList="subList" />',
//     data: {
//         subList: []
//     }
// });
//


// var td1=document.createElement("td");
// td1.append('<input type="text"   id="+addCat.category+100+" ></input>');
// var td2=document.createElement("td");
// td2.append('<td> <input  type="button" id="+addCat.category+200" value="Змінити"   onclick="editSubcategory(addCat.category+100,addCat.category)" ></input></td>');
// var td3=document.createElement("td");
// td3.append('<td> <input  type="button"  id="+addCat.category+300" value="delete"  onclick="deleteSubcategory(addCat.category+100) ></input></td>');
// var tem='<td>addCat.name</td><td><input type="text"   id="+addCat.category+100" ></input></td>'+
//     '<td> <input  type="button" id="+addCat.category+200" value="Змінити"   onclick="editSubcategory(addCat.category+100,addCat.category)" ></input></td>'+
//     '<td> <input  type="button"  id="+addCat.category+300" value="delete"  onclick="deleteSubcategory(addCat.category+100) ></input></td>'
//                 var elemText = document.createTextNode(tem);
// var d= document.getElementById('tempCat');
// alert(d.innerHTML);

// div.insertAdjacentHTML("afterbegin", '<input type="text"   id="idNew" ></input>');
// div.insertAdjacentHTML("afterbegin", '<input type="text"   id={idNew1}></input>');
// div.appendChild(td2);
// div.appendChild(td3);
//d.appendChild(tem);

//  $('#tempCat').innerHTML='<td>1</td><td>2</td>';
//document.getElementById("tempCat").innerHTML = '<td>1</td><td>2</td>';
// document.getElementById('tableSub').insertRow(-1).innerHTML = '<td>1</td><td>2</td>';
//         var tab=        var div= document.getElementById(tempCat);//
//
//                 // var s=addCat.name;
//                 var elem = document.createElement("p");
//                 var elemText = document.createTextNode(addCat.name);
//
//                 elem.appendChild(elemText);
//                 div.appendChild(elem);
// // alert(tempCat);
//                 $('#subcategory').val('');
//                 $('#idCat').val('');


// var text='T'+Id;
// var tr='TR'+Id;
// var lab='L'+Id;
// var butE= 'E'+Id;
// var butD= 'D'+Id;
// document.getElementById(tr).hidden=true;
//             document.getElementById(text).hidden=true;
// document.getElementById(lab).hidden=true;
//             document.getElementById(butE).hidden=true;
//             document.getElementById(butD).hidden=true;



// '<td>' +response[i].name + '</td>'+
// '<td>' +response[i].email + '</td>'+
// '<td>' +response[i].phone + '</td>'+
// // '<td>' +response[i].nameSocial + '</td>'+
// '<td>' +response[i].active + '</td>' +