function submitRegistrationForm() {

    var userId = 0;
    var userName = jQuery("#user").val();
    var encryptedPassword = jQuery("#pwd").val();
    var enabled = true;
    var UserCommand = {userId: userId, userName: userName, encryptedPassword: encryptedPassword, enabled: enabled};

    $.ajax({
        type: "post",
        url: "/admin/api/users", //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(UserCommand), //json object or array of json objects
        success: function (result) {
            $(".information").append("<p>" + userName + " has just created. Status: " + result.enabled + "</p>");
            jQuery("#user").val('');
            jQuery("#pwd").val('');
            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function updateUser() {

}

function addRoleToUser() {
    var appUser = {userId: jQuery("#userId").html(), userName: null, encryptedPassword: null, enabled: null};
    var appRole = {roleId: jQuery("#roles option:selected").val(), roleName: null};
    var roleUserCommand = {id: 0, appUser: appUser, appRole: appRole};

    $.ajax({
        type: "post",
        url: "/admin/api/user_role", //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(roleUserCommand), //json object or array of json objects
        success: function (result) {

            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}
function deleteRoleFromUser(roleId) {
    alert(roleId);
    var appUser = {userId: jQuery("#userId").html(), userName: null, encryptedPassword: null, enabled: null};
    var appRole = {roleId: roleId, roleName: null};
    var roleUserCommand = {id: 0, appUser: appUser, appRole: appRole};

    $.ajax({
        type: "delete",
        url: "/admin/api/user_role", //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(roleUserCommand), //json object or array of json objects
        success: function (result) {

            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}