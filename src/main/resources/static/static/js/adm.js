// <--Admin-->
function editSubcategory(Id,cotgoryId){
var t='I'+Id;
    var label='L'+Id;
    var n=document.getElementById(t).value;

    var sub={
        id:Id,
        name:n,
        category:cotgoryId
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
               // var n1 = document.getElementById(t).value = "";
                document.getElementById(t).value = "";
                $('#Id').val('');
            }
        });
    }
}
function adminModal(Id){
    $("#adminModal").modal('show');
    $("#idCat").val(Id);

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
                var tab=document.getElementById('sub');
               var div= document.getElementById(tempCat);//

               // var s=addCat.name;
                var elem = document.createElement("h3");
                var elemText = document.createTextNode(addCat.name);

elem.appendChild(elemText);
div.appendChild(elem);
// alert(tempCat);
                $('#subcategory').val('');
            $('#idCat').val('');
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
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/subcategory/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {
            var text='T'+Id;
            var tr='TR'+Id;
var lab='L'+Id;
var butE= 'E'+Id;
var butD= 'D'+Id;
            document.getElementById(tr).hidden=true;
//             document.getElementById(text).hidden=true;
// document.getElementById(lab).hidden=true;
//             document.getElementById(butE).hidden=true;
//             document.getElementById(butD).hidden=true;
        }

});
}
function deleteUser(Id){
    var id=Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {
// var usId='U'+Id;
//             document.getElementById(usId).style.display='none';
            getSchedule();
        }

    });
}
function lockUser(Id) {
    var id = Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/lock',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(id),
        success: function (result) {
          //  var usId='U'+Id;
           // $('#usId').html()==result.toString();
           // alert(result.toString());
            // var usId='U'+Id;
            // document.getElementById(usId).style.display='none';
            getSchedule();
        }

    });
}
function unlockUser(Id) {
    var id = Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/unlock',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(id),
        success: function (result) {
            getSchedule();
           // $('#tableID').bootstrap.t('load', result);
           //  console.log(result);
            // var usId='U'+Id;
            // $('#usId').html()==result.toString();
            // //document.getElementById(usId).style.display='none';
            // alert(result.valueOf().toString());
        }

    });
}
function getSchedule() {
    $.ajax({
        type: "GET",
        url: '/admin/user/test',
        dataType: "json",
        success: [

            function (response) {
                // console.log(response);
                $("#tableID").find("tr:not(:first)").remove();
                var trHTML = '';
                //var obj = $.parseJSON(response.responseText);
                for (var i = 0; i < response.length; i++) {
                    trHTML += '<tr><td><label>' + response[i].name+ '</label></td>' +
                        // '<td>' +response[i].name + '</td>'+
                        '<td>' +response[i].email + '</td>'+
                        '<td>' +response[i].phone + '</td>'+
                        // '<td>' +response[i].nameSocial + '</td>'+
                        '<td>' +response[i].active + '</td>' +
                        '<td> <button  onclick="lockUser('+response[i].id+')" >Lock</button></td>'+
                        '<td> <button  onclick="unlockUser('+response[i].id+')" >UnLock</button></td>'+
                        // '<td> <button  onclick="deleteUser('+response[i].id+')" >Delete</button></td>'+

                        '</tr>';

                }
                $("#tableID tbody").append(trHTML);
            }
        ]
    });
}
function getDetalis() {
    $.ajax({
        type: "GET",
        url: '/admin/user/test',
        dataType: "json",
        success: [

            function (response) {
                // console.log(response);
                $("#tableID").find("tr:not(:first)").remove();
                var trHTML = '';
                //var obj = $.parseJSON(response.responseText);
               // trHTML='tr<td>'
                for (var i = 0; i < response.length; i++) {
                    trHTML += '<tr><td><label>' + response[i].id+ '</label></td>' +
                        // '<td>' +response[i].name + '</td>'+
                        '<td>' +response[i].email + '</td>'+
                        '<td>' +response[i].phone + '</td>'+
                        // '<td>' +response[i].nameSocial + '</td>'+
                        '<td>' +response[i].active + '</td>' +
                        '<td> <button  onclick="lockUser('+response[i].id+')" >Lock</button></td>'+
                        '<td> <button  onclick="unlockUser('+response[i].id+')" >UnLock</button></td>'+
                        // '<td> <button  onclick="deleteUser('+response[i].id+')" >Delete</button></td>'+

                        '</tr>';

                }
                $("#tableID tbody").append(trHTML);
            }
        ]
    });
}
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