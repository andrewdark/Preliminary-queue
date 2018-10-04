function submitRegistrationForm(){

    var userId = 0;
    var userName = jQuery("#user").val();
    var encryptedPassword = jQuery("#pwd").val();
    var enabled = true;
    var UserCommand = {userId:userId, userName:userName, encryptedPassword:encryptedPassword, enabled:enabled};

    $.ajax({
        type: "post",
        url: "Submit", //your valid url
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(UserCommand), //json object or array of json objects
        success: function(result) {
//do nothing
        },
        error: function(){
            alert(userName + 'failure');
        }
    });
}
