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
            $(".information").append("<p>"  + "Client has just created." + "</p>");
            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(".information").append(Client);
            alert('hernya: '+jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

// Search clients when Calendar was clicked

function calendarClick() {

    var dataStart = "2017-01-01 06:00:00";
    var dataEnd = "2019-01-01 15:00:00";
    var loc = 1;

    $.ajax({
        type: "get",
        url:"/api/clients/date/"+dataStart+"/"+dataEnd+"/locations/"+loc+"",
        contentType: "application/json",
        //data: JSON.stringify(),
        success: function (result) {
            for (j = 0; j <= result.length; j++)
            {
                $(".information").append("id:" +result[j].id +" firstName:"+ result[j].firstName + "<br />");
            }


        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(".information").append(Client);
            alert('hernya: '+jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}