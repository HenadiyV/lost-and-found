var $=jQuery.noConflict();
function toggle_show(id) {
    document.getElementById(id).style.display = document.getElementById(id).style.display == 'none' ? 'block' : 'none';
}

function myModal() {
    $("#myModal").modal();
}

function myModalMessage(id,usId) {
    window.$("#myModalMessage").modal();
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
    document.getElementById('btn_msg').disabled=document.getElementById('btn_msg').disabled==false  ?true  : false;
    }
}

$(document).ready(function () {

    $("#mess-form").submit(function (event) {
        event.preventDefault();
        searchText();
    });
});

function addMessage(str,err) {
    var search = {
        idAdver: $('#idAdver').val(),
        userId: $('#userId').val(),
        contact: $('#contact').val(),
        textMessage: $('#textMessage').val(),
    }
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/view/add_message',
        dataType: 'json',
        cache: false,
        data: JSON.stringify(search), // Note it is important
        success: function (result) {
            console.log(result)
            if(result){
                  $('#idAdver').val(""),
                $('#userId').val(""),
                $('#contact').val(""),
            $('#textMessage').val("");
            $('#capcha').attr('checked',false);
            //cbx_chng("capcha");
            alert(str);
            }else{alert(err);}
        },error : function() {
            alert("Not");
        }
    });
}
function cbx_chng(cbx) {
    if (document.getElementById(cbx).checked) {
        document.getElementById(elem).checked = "false";
    } else {
        document.getElementById(elem).checked = "true";
    }
}

$(function() {
    $("input[type='checkbox']:checked").change(function() {
        var byt=document.getElementById('submit1');
        if(this.checked){
        byt.disabled=false;
            // console.log("ok");
        }else{

            byt.disabled=true;
        // console.log("no");
        }
    })
});

// function chekMy(str) {
//     alert(str);
// }

function hideMess(idAdvt,idMess){
    $.ajax({
        url : 'hide',
       data:({'idAdvt': idAdvt,'idMess':idMess}),
        success:function (data) {
        }
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
