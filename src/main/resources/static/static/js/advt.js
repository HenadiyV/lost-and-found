$b(function () {

    $b('input#lang1').autocomplete({
        source: function (request, response) {

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


}
function activ() {
    var cat=$('#lang1').val();
    var subcat=$('#subcategory').val();
    console.log(cat);
    console.log(subcat);
    var butSearch=document.getElementById('btnSearch');
    if(cat!=null||subcat!=null){
        butSearch.disabled=false;
        console.log("ok");
        // document.getElementById('butSearch').disabled = document.getElementById('butSearch').disabled=false;
     }else{ butSearch.disabled=true;console.log("no");}//document.getElementById('butSearch').disabled=document.getElementById('butSearch').disabled=true;
}