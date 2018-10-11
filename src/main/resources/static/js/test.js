function test() {

    var id = jQuery("#actionId").val();
    var actionName = jQuery("#actionName").val();
    var ActionCommand = {id: id, userName: actionName};

    $.ajax({
        type: "post",
        url: "/api/actions", //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(ActionCommand), //json object or array of json objects
        success: function (result) {
            // $(".information").append("<p>"+ " has just created. Status: " + "</p>");
            // jQuery("#actionId").val('');
            // jQuery("#actionName").val('');
            //do nothing

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}