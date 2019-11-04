$b(function () {

    $b('input#lang1').autocomplete({
        source: function (request, response) {
            // data()
            $b.getJSON('/view/city', {filter: $b('input#lang1').val()}, function (data) {
                var e = document.getElementById('City');
                while (e.firstChild) {
                    e.removeChild(e.firstChild);
                }
                // var div= document.getElementById('listCity');
                if(data.length>0){

                   e.style.display ='block';
                  //  console.log("ok");
                } else{
                    e.style.display  ='none';}

               // var suggestions = []; // массив для хранения результатов
                $b.each(data, function (key, val) {
                    //console.log(data);
                    //  suggestions.push(); // добавляем все элементы
                    //console.log(val.idCity);
                    var option = document.createElement('option');

                    option.value = val.idCity;
                    option.text = val.nameCity;
                    option.className = "City";
                    e.appendChild(option);
                    //suggestions.push(val.idCity, val.nameCity); // добавляем все элементы
                });

                // mySelect(suggestions);
            });
        }
    });


});

function clearInput() {
    // var i= document.getElementById('#lang1').val("");
    var city=document.getElementById('City');
    var name=city.options[city.selectedIndex].text;
    // alert(name);
    $b('#lang1').val(name);

}
function cityIdInput() {
    // var i= document.getElementById('#lang1').val("");
    var city=document.getElementById('City');
    var id=city.options[city.selectedIndex].value;
    var name=city.options[city.selectedIndex].text;
    // alert(name);
    $b('#lang1 ').val(name);

}


function clearElement() {

    var city=document.getElementById('City');
    city.options[city.selectedIndex]=0;

    $b('#lang1 ').val('');
    var subcategory=document.getElementById('subcategory');
    subcategory.options[subcategory.selectedIndex]=0;
}

function alertFilename() {
    var elem = document.getElementById('fp1');
    elem.style.display = "none";
    var fi = document.getElementById('file');

    var f = files.value;
    var fin = f.lastIndexOf("\\") + 1;
    var fs = f.substr(fin, f.length).toLowerCase();
    var idxDot = f.lastIndexOf(".") + 1;
    var extFile = f.substr(idxDot, f.length).toLowerCase();
    if (extFile == "jpg" || extFile == "jpeg" || extFile == "png") {
        document.getElementById('fp').innerHTML = fs;

    } else {
        elem.style.display = "block";
    }

}
function subcategoryList(){
    var id= $('#category').val();
    console.log(id);
    $.ajax({
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        url: '/view/subcategory',
        // dataType: 'json',
        // cache: false,

        data:{id},
        success: function (result) {
            console.log(result);
// var usId='U'+Id;
//             document.getElementById(usId).style.display='none';

        }

    });

    // слушатель
// $b('#contract').change(function() {
//     if(!this.checked){
//     $b('submit').disabled=true;}
//     console.log("ok");
// });
// function mycontract(){
//     $('#submit').disabled=true;
//     console.log("ok");
// }
// $i(function () {
//
//     $i('input#lang1').autocomplete({
//         source: function (request, response) {
//             // data()
//             $i.getJSON('/view/city', {filter: $i('input#lang1').val()}, function (data) {
//                 var e = document.getElementById('City');
//                 while (e.firstChild) {
//                     e.removeChild(e.firstChild);
//                 }
//                 var suggestions = []; // массив для хранения результатов
//                 $i.each(data, function (key, val) {
//                     //console.log(data);
//                     //  suggestions.push(); // добавляем все элементы
//                     //console.log(val.idCity);
//                     var option = document.createElement('option');
//
//                     option.value = val.idCity;
//                     option.text = val.nameCity;
//                     option.className = "City";
//                     e.appendChild(option);
//                     //suggestions.push(val.idCity, val.nameCity); // добавляем все элементы
//                 });
//                 // mySelect(suggestions);
//             });
//         }
//     });
//
//
// });
//
// function clearInput() {
//     // var i= document.getElementById('#lang1').val("");
//     var city=document.getElementById('City');
//     var name=city.options[city.selectedIndex].text;
//     // alert(name);
//     $i('#lang1').val(name);
//
// }
// function cityIdInput() {
//     // var i= document.getElementById('#lang1').val("");
//     var city=document.getElementById('City');
//     var id=city.options[city.selectedIndex].value;
//     var name=city.options[city.selectedIndex].text;
//     // alert(name);
//     $i('#lang1 ').val(name);
//
// }
// // слушатель
// $('#clear').click(() => {
//     $('select').prop('selectedIndex',0);
// });
//
// function clearElement() {
//
//     var city=document.getElementById('City');
//    city.options[city.selectedIndex]=0;
//
//     $i('#lang1 ').val('');
//     var subcategory=document.getElementById('subcategory');
//     subcategory.options[subcategory.selectedIndex]=0;
// }


    // $.getJSON('/view/subcategory', {id: $('#category').val()}, function (data) {
    //     console.log(data);
    //     // var e = document.getElementById('subcategory');
    //     // while (e.firstChild) {
    //     //     e.removeChild(e.firstChild);
    //     // }
    //     // var suggestions = []; // массив для хранения результатов
    //     // $.each(data, function (key, val) {
    //     //     console.log(data);
    //     //     //  suggestions.push(); // добавляем все элементы
    //     //     //console.log(val.idCity);
    //     //     var option = document.createElement('option');
    //     //
    //     //     option.value = val.id;
    //     //     option.text = val.name;
    //     //
    //     //     e.appendChild(option);
    //     //     //suggestions.push(val.idCity, val.nameCity); // добавляем все элементы
    //     // });
    //     // $('#subcategory').autocomplete({
    //     //     source: function (suggestions) {
    //     //         // data()
    //     //         console.log("ok");
    //     //     }
    //     // });
    //     // mySelect(suggestions);
    // });


}