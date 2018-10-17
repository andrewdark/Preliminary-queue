$(document).ready(function () {
    $("#popup1").hide();
});

function submitClientObj() {

    var id = 0;
    var firstName = jQuery("#firstName").val();
    var lastName = jQuery("#lastName").val();
    var middleName = jQuery("#middleName").val();
    var action = jQuery("#action option:selected").val();
    var location = jQuery("#location option:selected").val();
    var meeting = jQuery("#meeting").val();
    var userId = 0;
    //var encryptedPassword = jQuery("#pwd").val();

    var Client = {
        "id": 0,
        "firstName": firstName,
        "lastName": lastName,
        "middleName": middleName,
        "action": action,
        "location": {
            "id": location,
            "tscNumber": 0000,
            "address": ""
        },
        "meeting": meeting,
        "userId": 0
    };

    $.ajax({
        type: "post",
        url: "/api/clients", //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(Client), //json object or array of json objects
        success: function (result) {
            $(".client_create_info").append("<p>" + "Client has just created." + "</p>");
            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(".information").append(Client);
            alert('hernya: ' + jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

// Search clients when Calendar was clicked

function calendarClick() {

    jQuery("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
    //var testdate = jQuery("#datepicker").datepicker( "getDate" );
    var date = $("#datepicker").datepicker({dateFormat: "yy-mm-dd"}).val();
    //var dateStart = date + " 06:00:00";
    //var dateEnd = date +" 15:00:00";
    var loc = 1;
    var url = "/api/clients/date/"+date+"/locations/"+loc;

    $.ajax({
        type: "get",
        //url:"/api/clients/date/"+dateStart+"/"+dateEnd+"/locations/"+loc+"",
        url: url,
        contentType: "application/json",
        //data: JSON.stringify(),
        success: function (result) {
            $(".information").empty();
            ;
            for (j = 0; j <= result.length; j++) {
                $(".information").append("id:" + result[j].id + " firstName:" + result[j].firstName + "<br />");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(".information").append(Client);
            alert('hernya: ' + jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function closePopUp() {
    $("#popup1").hide();
}
function openPopUp() {
    $("#popup1").show();
}