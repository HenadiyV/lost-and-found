// <--Admin-->
var ban;
var unban;
var del;
function deleteUser(Id,str,btnBan,btnUnban,btnDel){
    var result = confirm(str);
    ban=btnBan;
    unban=btnUnban;
    del=btnDel;
    if(result) {


    var id=Id;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/delete',
        dataType: 'json',
        cache: false,

        data:JSON.stringify(id),
        success: function (result) {

             getSchedule(ban, unban, del);
        }

    });
    }else alert("NOT");
}
function lockUser(Id,btnBan,btnUnban,btnDel) {
    var id = Id;
     ban=btnBan;
     unban=btnUnban;
     del=btnDel;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/lock',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(id),
        success: function (result) {

            getSchedule(ban, unban, del);
        }

    });
}
function unlockUser(Id,btnBan,btnUnban,btnDel) {
    var id = Id;
    ban=btnBan;
    unban=btnUnban;
    del=btnDel;
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url: '/admin/user/unlock',
        dataType: 'json',
        cache: false,

        data: JSON.stringify(id),
        success: function (result) {
            getSchedule(ban, unban, del);

        }

    });
}
function getSchedule(btnBan,btnUnban,btnDel) {
    var s=btnBan;
    ban=btnBan;
    unban=btnUnban;
    del=btnDel;
    var activ='';
    var yes=$('#yes').val();
    var no=$('#no').val();
    $.ajax({
        type: "GET",
        url: '/admin/user/test',
        dataType: "json",
        success: [

            function (response) {
                // console.log(response);
                $("#tableBan").find("tr:not(:first)").remove();
                var trHTML = '';
                var phon='';
                //var obj = $.parseJSON(response.responseText);
                for (var i = 0; i < response.length; i++) {
                    if(response[i].phone!=null){phon=response[i].phone}else{
                        phon='';
                    }
                    if(response[i].active){
                        activ=yes;
                    }else{activ=no;}
                    trHTML += '<tr><td><label>' + response[i].name+ '</label></td>' +
                        // '<td>' +response[i].name + '</td>'+
                        '<td>' +response[i].email + '</td>'+
                        '<td>' +phon + '</td>'+
                        // '<td>' +response[i].nameSocial + '</td>'+
                        '<td>' +activ + '</td>' +
                        '<td> <input type="button"  onclick="lockUser('+ response[i].id +', ban,unban , del)"  value='+ ban +'  > </input></td>'+

                        '<td> <input type="button"  onclick="unlockUser('+response[i].id+', ban,unban , del)" value='+ unban +' > </input></td>'+
                        '<td> <input type="button" onclick="deleteUser('+response[i].id+', ban,unban , del)" value='+ del +' > </input></td>'+

                        '</tr>';

                }

                $("#tableBan tbody").append(trHTML);

            }

        ]
    });

}

function getDetalis() {
    $.ajax({
        type: "GET",
        url: '/admin/user-detalis',
        dataType: "json",
        success: [

            function (response) {
                // console.log(response);
                $("#tableDetalis").find("tr:not(:first)").remove();
                var trHTML = '';
                //var obj = $.parseJSON(response.responseText);
               // trHTML='tr<td>'
                for (var i = 0; i < response.length; i++) {
                    trHTML += '<tr><td><label>' + response[i].id+ '</label></td>' +
                         '<td>' +response[i].name + '</td>'+
                        '<td>' +response[i].email + '</td>'+
                        '<td>' +response[i].phone + '</td>'+
                         '<td>' +response[i].nameSocial + '</td>'+
                        '<td>' +response[i].active + '</td>' +
                       // '<td> <button  onclick="lockUser('+response[i].id+')" >Lock</button></td>'+
                        '<td> <button  onclick="deleteUser('+response[i].id+')" >Delete</button></td>'+
                        // '<td> <button  onclick="deleteUser('+response[i].id+')" >Delete</button></td>'+

                        '</tr>';

                }
                $("#tableDetalis tbody").append(trHTML);
            }
        ]
    });
}

function ConfirmDelete(mess)
{
    var x = confirm(mess);
    if (x)
        return true;
    else
        return false;
}

function search(){
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
document.addEventListener("DOMContentLoaded", function() {
   var user_count=$('#countUser').val();
   var advt_count=$('#countAdvt').val();
   var messag_count=$('#countMess').val();
   console.log(messag_count);
   if(user_count>0){
    $('#usr').animate({ usr: user_count - 0.3/* - начало + '%'*/ }, {
        duration: 5000,
        step: function (usr){
            this.innerHTML = (usr + 0.3).toFixed(0) ;
        }
    });
   }
   if(advt_count>0){
    $('#advt').animate({ advt: advt_count - 0.3/* - начало + '%'*/ }, {
        duration: 4000,
        step: function (advt){
            this.innerHTML = (advt + 0.3).toFixed(0) ;
        }
    });
   }
   if(messag_count>0){
    $('#mes').animate({ mes: messag_count - 0.1/* - начало + '%'*/ }, {
        duration: 5000,
        step: function (mes){
            this.innerHTML = (mes + 0.1).toFixed(0) ;
        }
    });
   }

});

