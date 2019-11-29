function userMessageModal(result){
    $("#userMessageModal").modal('show');
    $("#textMess").value(result.getDat());
    $("#datMess").value(result.getText());
}

function viewAdvt(Id){
var mess={
    'idMess':Id
}
    $.ajax({
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        url: '/view/advt',
        dataType: 'json',
        cache: false,
         data: (mess),
        success: function (result) {
            userMessageModal(result);
        }
    });
}