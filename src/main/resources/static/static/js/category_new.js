function editCategory(Id){
    var t='ICat'+Id;
    var lab='LCat'+Id;
    var n=document.getElementById(t).value;
var tex=document.getElementById(lab);
    var sub={
        id:Id,
        name:n,

    }

    if(n!="") {
        $.ajax({
            type: "PUT",
            contentType: 'application/json; charset=utf-8',
            url: 'category/'+Id,
            dataType: 'json',
            cache: false,
            data: JSON.stringify(sub),
            success: function (result) {

              tex.innerText=sub.name;

                // document.getElementById(t).value = "";
                // $('#Id').val('');
            }
        });

}else{alert("Please insert name")}
}
function adminModal(Id){
    $("#adminModalCat").modal('show');
     $("#idCat").val(Id);

}

function getCategory(categoryId) {
    $.ajax({
        type: "GET",
        url: '/admin/category',
        dataType: "json",
        success: [

            function (response) {

                $("#tableCat").find("tr:not(:first)").remove();
                var trHTML = '';

                for (var i = 0; i < response.length; i++) {
                    trHTML += '<tr><td><label>' + response[i].name+ '</label></td>' +

                        '<td> <button  onclick="editSubcategory('+response[i].id,categoryId+')" >Edit</button></td>'+
                    // '<td> <button  onclick="deleteSubcategory('+response[i].id+')" >Delete</button></td>'+

                    '</tr>';

                }
                $("#tableSub tbody").append(trHTML);
            }
        ]
    });
}
