function toggle_show(id) {
    document.getElementById(id).style.display = document.getElementById(id).style.display == 'none' ? 'block' : 'none';

}
function myModal() {
    $("#myModal").modal();
}
function myModalMessage(id,usId) {
    $("#myModalMessage").modal();

    $('#idAdver').val(id);
    $('#userId').val(usId);
    $('#textMessage').val("");
    $('#corectValue').html("100 ");

}
function message_show(id) {

    document.getElementById(id).style.display = document.getElementById(id).style.display == 'none' ? 'block' : 'none';

}
function doAjax() {
    $.ajax({
        url : 'message1',
        data:({textMessage: $('#textMessage').val()}),
        success:function (data) {
            $('#corectValue').html(data);

        }
    });
}

function doMessage() {
    var t=$('#textMessage').val();
    if(t!=""){
    //document.getElementById('btn_msg').style.display = document.getElementById('btn_msg').style.disabled == 'true' ? 'false' : 'true';
    document.getElementById('btn_msg').disabled=  document.getElementById('btn_msg').disabled==false  ?true  : false;}

}

$(document).ready(function () {

    $("#mess-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

       // fire__ajax__submit();
        searchText();
       // closeModal();
    });

});
function searchText() {
    var search = {
        idAdver: $('#idAdver').val(),
        userId: $('#userId').val(),
        contact: $('#contact').val(),
        textMessage: $('#textMessage').val(),

    }
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: 'add_message',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(search), // Note it is important
        success: function (result) {
            // do what ever you want with data
           // ;

            $('#idAdver').val(""),
                $('#userId').val(""),
                $('#contact').val(""),
            $('#textMessage').val("");
        }
    });
}

function chekMy(str) {

    alert(str);
}
function hideMess(idAdvt,idMess){
    $.ajax({
        url : 'hide',
       data:({'idAdvt': idAdvt,'idMess':idMess}),
        success:function (data) {
           // $('#corectValue').html(data);
alert("ok");
        }
    });
}

/////////////////////
// function doMess() {
//     // var mes={
//     // idAdver:$('#idAdver').val(),
//     // userId:$('#userId').val(),
//     // contact:$('#contact').val(),
//     // textMessage:$('#textMessage').val(),
//     // url:$('#url').val()}
//    var idAdver=$('#idAdver').val();
//    var userId=$('#userId').val();
//     var contact=$('#contact').val();
//     var textMessage=$('#textMessage').val();
//     var url=$('#url').val();
//     $.ajax({
//
//         // dataType: 'json',
//         // contentType: 'application/json',
//         url : 'add_message',
//         data:{"par1":idAdver,"par2":userId,"contact":contact,"textMessage":textMessage,"url":url },//JSON.stringify(mes),
//         type: "POST",
//         success:function (data) {
//
// alert("ok!");
//         }, error : function(xhr, status, errorThrown) {
//             alert('adding component failed with status: ' + status + ". "
//                 + errorThrown);
//         }
//     });
// }
//////////////////////////

// function submitForm() {
//
//     var idAdver= $('#idAdver').val();
//     var userId= $('#userId').val();
//     var contact= $('#contact').val();
//     var  textMessage= $('#textMessage').val();
//
//     var object = {idAdver:idAdver,userId:userId,contact:contact,textMessage:textMessage};
//
//     $.ajax("/view/add_message",
//         {
//             type:"POST",
//             data:JSON.stringify(object)
//         });
// }