
function editSubcategory(Id,categoryId){
    var t='I'+Id;
    var label='L'+Id;
    var n=document.getElementById(t).value;

    var sub={
        id:Id,
        name:n,
        category:categoryId
    }

    if(n!="") {
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: '/admin/subcategory/edit',
            dataType: 'json',
            cache: false,
            data: JSON.stringify(sub),
            success: function (result) {
                document.getElementById(label).innerText = n;
                document.getElementById(t).value = "";
                $('#Id').val('');
            }
        });
    }
}
function adminModal(Id){
    $("#adminModal").modal('show');
    $("#idCat").val(Id);
    var cat='C'+Id;
    var n= document.getElementById(cat);
    //  var n= $("#cat").val();
    var s=n.value;
    var nc= document.getElementById("nameCat").innerHTML=(s);
    //$("#nameCat").innerHTML=(s);
    console.log(s);

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
            url: '/admin/subcategory/add',
            dataType: 'json',
            cache: false,

            data:JSON.stringify(addCat),
            success: function (result) {
                var tempCat='T'+addCat.category;
                var td=document.createElement("b");
                td.append(addCat.name);
                var div= document.getElementById(tempCat);
                div.appendChild(td);
                div.insertAdjacentHTML("beforeend", '<br/>');
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
    var tr='TR'+Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/subcategory/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {

            document.getElementById(tr).hidden=true;
        }

    });
}
function getSubcategory(categoryId) {
    $.ajax({
        type: "GET",
        url: '/admin/subcategory',
        dataType: "json",
        success: [

            function (response) {

                $("#tableSub").find("tr:not(:first)").remove();
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

