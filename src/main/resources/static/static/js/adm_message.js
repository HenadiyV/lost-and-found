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
