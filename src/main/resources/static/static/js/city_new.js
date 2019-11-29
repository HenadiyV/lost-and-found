function editCity(Id,mess){
    var t='ICity'+Id;
    var lab='LCity'+Id;
    var n=document.getElementById(t).value;
var tex=document.getElementById(lab);
    var sub={id:Id, name:n }

    if(n!="") {
        $.ajax({
            type: "PUT",
            contentType: 'application/json; charset=utf-8',
            url: '/admin/city/'+Id,
            dataType: 'json',
            cache: false,
            data: JSON.stringify(sub),
            success: function (result) {

              tex.innerText=sub.name;

                document.getElementById(t).value =sub.name;
                $('#Id').val('');
            }
        });

}else{alert("Please insert name")}
}

function adminModal(Id){
    $("#adminModalCat").modal('show');
     $("#idCat").val(Id);

}

function addCity(Id){
var id=Id;
    var cityAdd={

        name:$('#cityNew').val()
    }

    if(cityAdd.name!=null){
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: '/admin/city',
            dataType: 'json',
            cache: false,

            data:JSON.stringify(cityAdd),
            success: function (result) {
                var tempCit='TCity'+id;
                var td=document.createElement("b");
                td.append(cityAdd.name);
                var div= document.getElementById(tempCit);
                div.appendChild(td);
                div.insertAdjacentHTML("beforeend", '<br/>');
                $('#cityNew').val('');

            },error : function() {
                alert('Not adding city ');
            }
        });
    }
    else{
        alert("Error");
    }

}

function deleteCity(Id,del,deleting,error){
    var tr='TRCity'+Id;
    var id=Id;


    if(ConfirmDelete(del)){
    $.ajax({
        type: "DELETE",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/city/'+Id,
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {

alert(deleting);
document.getElementById(tr).hidden=true;

        },error : function() {
            alert(error);
        }
    });
    }
}


function searchCity(){
    var v=$('#filter').val();
    if (v.keyCode == 27 || v == '') {
        //if esc is pressed we want to clear the value of search box
        $('#filter').val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else {
        filter('tbody tr', v);
    }
}

//filter results based on query
function filter(selector, query) {
    query	=	$.trim(query); //trim white space
    query = query.replace(/ /gi, '|'); //add OR for regex

    $(selector).each(function() {
        ($(this).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
    });
}
