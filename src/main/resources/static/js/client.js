function submitClientObj() {

    var id = 0;
    var firstName = jQuery("#firstName").val();
    var lastName = jQuery("#lastName").val();
    var middleName = jQuery("#middleName").val();
    var action = jQuery("#action").val();
    var location = jQuery("#location").val();
    var meeting = jQuery("#meeting").val();
    var userId = 0;
    //var encryptedPassword = jQuery("#pwd").val();

    var Client = {
        "id": 0,
        "firstName": firstName,
        "lastName": lastName,
        "middleName": middleName,
        "action": {
            "id": 2,
            "actionName": "ololo2"
        },
        "location": {
            "id": 1,
            "tscNumber": 5555,
            "address": "adr1"
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