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
            url: '/city/'+Id,
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
            url: '/city',
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
        url: '/city/'+Id,
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

// $с(document).ready(function(){
//
// //default each row to visible
//     $с('tbody tr').addClass('visible');
//
//     //overrides CSS display:none property
//     //so only users w/ JS will see the
//     //filter box
//     $с('#search').show();
//
//     $с('#filter').keyup(function(event) {
//         //if esc is pressed or nothing is entered
//         if (event.keyCode == 27 || $с(this).val() == '') {
//             //if esc is pressed we want to clear the value of search box
//             $с(this).val('');
//
//             //we want each row to be visible because if nothing
//             //is entered then all rows are matched.
//             $с('tbody tr').removeClass('visible').show().addClass('visible');
//         }
//
//         //if there is text, lets filter
//         else {
//             filter('tbody tr', $с(this).val());
//         }
// }
//     )}
//     );
$с(document).ready(function() {
    $с('tbody tr').addClass('visible');
});
//overrides CSS display:none property
//so only users w/ JS will see the
//filter box
// $с('#search').show();

$с('#filter').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $с(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $с(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $с('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else {
        filter('tbody tr', $с(this).val());
    }
});
//filter results based on query
function filter(selector, query) {
    query	=	$с.trim(query); //trim white space
    query = query.replace(/ /gi, '|'); //add OR for regex

    $с(selector).each(function() {
        ($с(this).text().search(new RegExp(query, "i")) < 0) ? $с(this).hide().removeClass('visible') : $с(this).show().addClass('visible');
    });
}

// t.value=sub.name;
//document.getElementById(label).innerText = sub.name;

// document.getElementById(label).innerText = n;
// category :$('#idCat').val(),&&cityAdd.name!=""

// $(document).ready(function() {
//     zebraRows('tbody tr:odd td', 'odd');
//     $('tbody tr').hover(function(){
//         $(this).find('td').addClass('hovered');
//     }, function(){
//         $(this).find('td').removeClass('hovered');
//     });
//
//
//     });
// function mySearch(){
// //По умолчанию каждый ряд видимый
// $('tbody tr').addClass('visible');
// $('#search').show();
// $('#filter').keyup(function(event) {
//     //Если нажата клавиша esc или ничего не введено в поле поиска
//     if (event.keyCode == 27 || $(this).val() == '') {
//         //при нажатии клавиши esc поле поиска очищается
//         $(this).val('');
//
//         //нужно, чтобы каждый ряд был видимым раз ничего так и не введено
//
//         $('tbody tr').removeClass('visible').show().addClass('visible');
//     }
//
//     //если есть текст, включаем фильтр
//     else {
//         filter('tbody tr', $(this).val());
//     }
//
//
//     //снова активируем режим 'зебра'
//     $('.visible td').removeClass('odd');
//     zebraRows('.visible:odd td', 'odd');
// }
// //используется для того, чтобы задать чередование стилей строк
// function zebraRows(selector, className)
// {
//     $(selector).removeClass(className).addClass(className);
// }
// function filter(selector, query) {
//     query =   $.trim(query); //отрезаем пробелы
//     query = query.replace(/ /gi, '|'); //добавляем разделитель слов
//
//     $(selector).each(function() {
//         ($(this).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
//     });
// }
// function getSubcategory(categoryId) {
// //     $.ajax({
// //         type: "GET",
// //         url: '/admin/category',
// //         dataType: "json",
// //         success: [
// //
// //             function (response) {
// //
// //                 $("#tableCat").find("tr:not(:first)").remove();
// //                 var trHTML = '';
// //
// //                 for (var i = 0; i < response.length; i++) {
// //                     trHTML += '<tr><td><label>' + response[i].name+ '</label></td>' +
// //
// //                         '<td> <button  onclick="editSubcategory('+response[i].id,categoryId+')" >Edit</button></td>'+
// //                     '<td> <button  onclick="deleteSubcategory('+response[i].id+')" >Delete</button></td>'+
// //
// //                     '</tr>';
// //
// //                 }
// //                 $("#tableSub tbody").append(trHTML);
// //             }
// //         ]
// //     });
// // }