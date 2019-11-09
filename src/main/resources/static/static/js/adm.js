// <--Admin-->
var ban;
var unban;
var del;
function deleteUser(Id,str,btnBan,btnUnban,btnDel){
    var result = confirm(str);
    ban=btnBan;
    unban=btnUnban;
    del=btnDel;
    if(result) {


    var id=Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {
            // alert("ok");
            //
             getSchedule(ban, unban, del);
        }

    });
    }else alert("NOT");
}
function lockUser(Id,btnBan,btnUnban,btnDel) {
    var id = Id;
     ban=btnBan;
     unban=btnUnban;
     del=btnDel;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/lock',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(id),
        success: function (result) {

            getSchedule(ban, unban, del);
        }

    });
}
function unlockUser(Id,btnBan,btnUnban,btnDel) {
    var id = Id;
    ban=btnBan;
    unban=btnUnban;
    del=btnDel;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/unlock',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(id),
        success: function (result) {
            getSchedule(ban, unban, del);

        }

    });
}
function getSchedule(btnBan,btnUnban,btnDel) {
    var s=btnBan;
    ban=btnBan;
    unban=btnUnban;
    del=btnDel;
    var activ='';
    var yes=$('#yes').val();
    var no=$('#no').val();
    $.ajax({
        type: "GET",
        url: '/admin/user/test',
        dataType: "json",
        success: [

            function (response) {
                // console.log(response);
                $("#tableBan").find("tr:not(:first)").remove();
                var trHTML = '';
                var phon='';
                //var obj = $.parseJSON(response.responseText);
                for (var i = 0; i < response.length; i++) {
                    if(response[i].phone!=null){phon=response[i].phone}else{
                        phon='';
                    }
                    if(response[i].active){
                        activ=yes;
                    }else{activ=no;}
                    trHTML += '<tr><td><label>' + response[i].name+ '</label></td>' +
                        // '<td>' +response[i].name + '</td>'+
                        '<td>' +response[i].email + '</td>'+

                        '<td>' +phon + '</td>'+

                        // '<td>' +response[i].nameSocial + '</td>'+
                        '<td>' +activ + '</td>' +
                        '<td> <input type="button"  onclick="lockUser('+ response[i].id +', ban,unban , del)"  value='+ ban +'  > </input></td>'+

                        '<td> <input type="button"  onclick="unlockUser('+response[i].id+', ban,unban , del)" value='+ unban +' > </input></td>'+
                        '<td> <input type="button" onclick="deleteUser('+response[i].id+', ban,unban , del)" value='+ del +' > </input></td>'+

                        '</tr>';

                }
               // nameS(btnBan);
                $("#tableBan tbody").append(trHTML);

            }

        ]
    });

}

function getDetalis() {
    $.ajax({
        type: "GET",
        url: '/admin/user-detalis',
        dataType: "json",
        success: [

            function (response) {
                // console.log(response);
                $("#tableDetalis").find("tr:not(:first)").remove();
                var trHTML = '';
                //var obj = $.parseJSON(response.responseText);
               // trHTML='tr<td>'
                for (var i = 0; i < response.length; i++) {
                    trHTML += '<tr><td><label>' + response[i].id+ '</label></td>' +
                         '<td>' +response[i].name + '</td>'+
                        '<td>' +response[i].email + '</td>'+
                        '<td>' +response[i].phone + '</td>'+
                         '<td>' +response[i].nameSocial + '</td>'+
                        '<td>' +response[i].active + '</td>' +
                       // '<td> <button  onclick="lockUser('+response[i].id+')" >Lock</button></td>'+
                        '<td> <button  onclick="deleteUser('+response[i].id+')" >Delete</button></td>'+
                        // '<td> <button  onclick="deleteUser('+response[i].id+')" >Delete</button></td>'+

                        '</tr>';

                }
                $("#tableDetalis tbody").append(trHTML);
            }
        ]
    });
}

function ConfirmDelete(mess)
{
    var x = confirm(mess);
    if (x)
        return true;
    else
        return false;
}

function search(){
    var v=$('#filter').val();
    if (v.keyCode == 27 || v == '') {
        //if esc is pressed we want to clear the value of search box
        $('#filter').val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else {
        filter('tbody tr', v);
    }
}

//filter results based on query
function filter(selector, query) {
    query	=	$.trim(query); //trim white space
    query = query.replace(/ /gi, '|'); //add OR for regex

    $(selector).each(function() {
        ($(this).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
    });
}
document.addEventListener("DOMContentLoaded", function() {
   var user_count=$('#countUser').val();
   var advt_count=$('#countAdvt').val();
   var messag_count=$('#countMess').val();
   console.log(messag_count);
   if(user_count>0){
    $('#usr').animate({ usr: user_count - 0.3/* - начало + '%'*/ }, {
        duration: 5000,
        step: function (usr){
            this.innerHTML = (usr + 0.3).toFixed(0) ;
        }
    });
   }
   if(advt_count>0){
    $('#advt').animate({ advt: advt_count - 0.3/* - начало + '%'*/ }, {
        duration: 4000,
        step: function (advt){
            this.innerHTML = (advt + 0.3).toFixed(0) ;
        }
    });
   }
   if(messag_count>0){
    $('#mes').animate({ mes: messag_count - 0.1/* - начало + '%'*/ }, {
        duration: 5000,
        step: function (mes){
            this.innerHTML = (mes + 0.1).toFixed(0) ;
        }
    });
   }

});

//function nameS(s)
// // {
// //     var links = document.getElementsByTagName('button');
// //     for (var i = 0, _i = links.length; i < _i; i++) {
// //         if (links[i].innerHTML == 'Pages') {
// //             links[i].innerHTML = s;
// //            // break;
// //             console.log(links[i]);
// //         }
// //     }
// //
// // } ;
//function advtUser(Id){
//
// var idUser=Id;
//
//     $.ajax({
//         type: "GET",
//         contentType: 'application/json; charset=utf-8',
//         url: '/admin/user-advt',
//         dataType: 'text',
//         //cache: false,JSON.stringify
//
//         data: (idUser),
//         success: function (result) {
//             //alert('ok');
//
//         },error : function() {
//             alert('errors');
//         }
//
//     });
// }
//function mySearch(){
//     var name=$('#appendedInputButtons').val();
//
//
//     $.ajax({
//         type: "GET",
//         contentType: 'application/json; charset=utf-8',
//         url: '/admin/search-city',
//         dataType: 'text',
//         //cache: false,JSON.stringify
//
//         data: (name),
//         success: function (result) {
//             alert('ok');
//
//         },error : function() {
//             alert('adding component failed with status');
//         }
//
//     });
// }
// function editSubcategory(Id,cotgoryId){
// var t='I'+Id;
//     var label='L'+Id;
//     var n=document.getElementById(t).value;
//
//     var sub={
//         id:Id,
//         name:n,
//         category:cotgoryId
//     }
//
//     if(n!="") {
//         $.ajax({
//             type: "POST",
//             contentType: 'application/json; charset=utf-8',
//             url: '/subcategory/edit',
//             dataType: 'json',
//             cache: false,
//
//             data: JSON.stringify(sub),
//
//             success: function (result) {
//
//
//
//
//                 document.getElementById(label).innerText = n;
//                // var n1 = document.getElementById(t).value = "";
//                 document.getElementById(t).value = "";
//                 $('#Id').val('');
//             }
//         });
//     }
// }
// function adminModal(Id){
//     $("#adminModal").modal('show');
//     $("#idCat").val(Id);
//
// }
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
//                var div= document.getElementById(tempCat);//
//
//                // var s=addCat.name;
//                 var elem = document.createElement("h3");
//                 var elemText = document.createTextNode(addCat.name);
//
// elem.appendChild(elemText);
// div.appendChild(elem);
// // alert(tempCat);
//                 $('#subcategory').val('');
//             $('#idCat').val('');
//             },error : function() {
//              alert('adding component failed with status');
//       }
//         });
//     }
//     else{
//         alert("Bad");
//     }
//
// }
// function deleteSubcategory(Id){
// var id=Id;
//     $.ajax({
//         type: "POST",
//         contentType: 'application/json; charset=utf-8',
//         url: '/subcategory/delete',
//         dataType: 'json',
//         cache: false,
//
//         data:JSON.stringify(id),
//         success: function (result) {
//             var text='T'+Id;
//             var tr='TR'+Id;
// var lab='L'+Id;
// var butE= 'E'+Id;
// var butD= 'D'+Id;
//             document.getElementById(tr).hidden=true;
// //             document.getElementById(text).hidden=true;
// // document.getElementById(lab).hidden=true;
// //             document.getElementById(butE).hidden=true;
// //             document.getElementById(butD).hidden=true;
//         }
//
// });
// }
//////////////////////
// if(n==""){
// var ed='E'+Id;
// var ne=document.getElementById(ed).disabled=true;}


// alert(addCat.category);addCat.name!=nullable&&
//  div.innerHTML=addCat.name;
//tab.appendChild(div,content);
//tab.before(s);
//  var str=document.createElement('p');
//    str=innerHTML=addCat.name;
// div.prepend(str);
// $('#tempCat').html();
// $('#tempCat').append('<p>'+addCat.name+'</p>');
//    alert(tempCat);
//   alert('<b><p>name</p></b>');xhr, status, errorThrownstatus + errorThrown
////////////////////////////////////
// var usId='U'+Id;
//             document.getElementById(usId).style.display='none';

//  var usId='U'+Id;
// $('#usId').html()==result.toString();
// alert(result.toString());
// var usId='U'+Id;
// document.getElementById(usId).style.display='none';

// $('#tableID').bootstrap.t('load', result);
//  console.log(result);
// var usId='U'+Id;
// $('#usId').html()==result.toString();
// //document.getElementById(usId).style.display='none';
// alert(result.valueOf().toString());

// var d=document.getElementsByName("del");
// d.values(s);.value="eee"
// $('button.del').val(s);
//console.log(d);
//window.document.tableBan.btn.value='Ваш текст:'+document.getElementById("txt").value+'.';

// for ( var i = 0; i < result.length; i++) {
//     html += '<option value="' + result[i].nume + '">'
//         + result[i].nume + '</option>';
// }
// html += '</option>';
// $('#agency').html(html);

// for ( var i = 0; i < result.length; i++) {
//     html += '<option value="' + result[i].nume + '">'
//         + result[i].nume + '</option>';
// }
// html += '</option>';
// $('#agency').html(html);