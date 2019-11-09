// <--Admin-->
//var $=jQuery.noConflict();
function toggle_show_message(id,mesId) {
    document.getElementById(id).style.display = document.getElementById(id).style.display == 'none' ? 'block' : 'none';

  hiddenPost(mesId);
}

function hiddenPost(Id){
    var id=Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/message/hidden',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {
           // alert("ok");

        //   document.getElementById(id).style.display='none';

        }

    });
}
function deletePost(Id){
    var id=Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/message/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {

            document.getElementById(id).style.display='none';

        }

    });
}
// function deleteUser(Id){
// //     var id=Id;
// //     $.ajax({
// //         type: "POST",
// //         contentType: 'application/json; charset=utf-8',
// //         url: '/admin/user/delete',
// //         dataType: 'json',
// //         cache: false,
// //
// //         data:JSON.stringify(id),
// //         success: function (result) {
// //
// //             document.getElementById(id).style.display='none';
// //
// //         }
// //
// //     });
// // }
// // function banUser(Id){
// //     var id=Id;
// //     $.ajax({
// //         type: "POST",
// //         contentType: 'application/json; charset=utf-8',
// //         url: '/admin/user/ban',
// //         dataType: 'json',
// //         cache: false,
// //
// //         data:JSON.stringify(id),
// //         success: function (result) {
// //
// //             document.getElementById(id).style.display='none';
// //
// //         }
// //
// //     });
// // }
// // function editSubcategory(Id,cotgoryId){
// //     var t='I'+Id;
// //     var label='L'+Id;
// //     var n=document.getElementById(t).value;
// //
// //     var sub={
// //         id:Id,
// //         name:n,
// //         category:cotgoryId
// //     }
// //
// //     if(n!="") {
// //         $.ajax({
// //             type: "POST",
// //             contentType: 'application/json; charset=utf-8',
// //             url: '/subcategory/edit',
// //             dataType: 'json',
// //             cache: false,
// //
// //             data: JSON.stringify(sub),
// //
// //             success: function (result) {
// //
// //
// //
// //
// //                 document.getElementById(label).innerText = n;
// //                 // var n1 = document.getElementById(t).value = "";
// //                 document.getElementById(t).value = "";
// //                 $('#Id').val('');
// //             }
// //         });
// //     }
// // }
// // function adminModal(Id){
// //     $("#adminModal").modal('show');
// //     $("#idCat").val(Id);
// //
// // }
// // function addSubCategory(){
// //
// //     var addCat={
// //         category :$('#idCat').val(),
// //         name:$('#subcategory').val()
// //     }
// //
// //     if(addCat.name!=null&&addCat.name!=""){
// //         $.ajax({
// //             type: "POST",
// //             contentType: 'application/json; charset=utf-8',
// //             url: '/subcategory/add',
// //             dataType: 'json',
// //             cache: false,
// //
// //             data:JSON.stringify(addCat),
// //             success: function (result) {
// //                 var tempCat='T'+addCat.category;
// //                 var tab=document.getElementById('sub');
// //                 var div= document.getElementById(tempCat);//
// //
// //                 // var s=addCat.name;
// //                 var elem = document.createElement("h3");
// //                 var elemText = document.createTextNode(addCat.name);
// //
// //                 elem.appendChild(elemText);
// //                 div.appendChild(elem);
// //
// //                 $('#subcategory').val('');
// //                 $('#idCat').val('');
// //             },error : function() {
// //                 alert('adding component failed with status');
// //             }
// //         });
// //     }
// //     else{
// //         alert("Bad");
// //     }
// //
// // }