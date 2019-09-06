function toggle_show(id) {
    document.getElementById(id).style.display = document.getElementById(id).style.display == 'none' ? 'block' : 'none';

}
function myModal() {
    $("#myModal").modal();
}
function myModalMessage(id) {
    $("#myModalMessage").modal();

    $('#idAdver').val(id);
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
    //document.getElementById('btn_msg').style.display = document.getElementById('btn_msg').style.disabled == 'true' ? 'false' : 'true';
    document.getElementById('btn_msg').disabled=  document.getElementById('btn_msg').disabled==false  ?true  : false;

}