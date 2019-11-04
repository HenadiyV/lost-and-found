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
            url: '/category/'+Id,
            dataType: 'json',
            cache: false,
            data: JSON.stringify(sub),
            success: function (result) {
              //  alert(lab);
              //  console.dir(tex);
              tex.innerText=sub.name;
                //document.getElementById(label).innerText = sub.name;

                // document.getElementById(label).innerText = n;
                document.getElementById(t).value = "";
                $('#Id').val('');
            }
        });

}else{alert("Please insert name")}
}
function adminModal(Id){
    $("#adminModalCat").modal('show');
     $("#idCat").val(Id);

}
function addCategory(Id){
var id=Id;
    var addCat={
       // category :$('#idCat').val(),
        name:$('#catNew').val()
    }

    if(addCat.name!=null&&addCat.name!=""){
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: '/category',
            dataType: 'json',
            cache: false,

            data:JSON.stringify(addCat),
            success: function (result) {
                var tempCat='TCat'+id;
                var td=document.createElement("b");
                td.append(addCat.name);
                var div= document.getElementById(tempCat);
                div.appendChild(td);
                div.insertAdjacentHTML("beforeend", '<br/>');
                $('#catNew').val('');
            },error : function() {
                alert('adding component failed with status');
            }
        });
    }
    else{
        alert("Bad");
    }

}

function deleteCategory(Id){
    var tr='TRCat'+Id;
    var id=Id;
    $.ajax({
        type: "DELETE",
        contentType: 'application/json; charset=utf-8',
        url: '/category/'+Id,
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {

alert("delete");
document.getElementById(tr).hidden=true;
        }

    });
}
function getSubcategory(categoryId) {
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
                    '<td> <button  onclick="deleteSubcategory('+response[i].id+')" >Delete</button></td>'+

                    '</tr>';

                }
                $("#tableSub tbody").append(trHTML);
            }
        ]
    });
}