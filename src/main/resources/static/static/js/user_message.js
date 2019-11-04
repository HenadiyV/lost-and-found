function userMessageModal(result){
    $("#userMessageModal").modal('show');
    $("#textMess").value(result.getDat());
    $("#datMess").value(result.getText());
  //
   // var id=Id;

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
           // alert("ok");
        }
    });
}