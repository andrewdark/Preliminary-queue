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
    var userId = jQuery("#userId").html();
    var userName = null;
    var userPwd = null;
    var enabled = null;
    if (jQuery("#userNameE").val().length > 1) userName = jQuery("#userNameE").val();
    if (jQuery("#userPwdE").val().length > 5) userPwd = jQuery("#userPwdE").val();
    var url = "/admin/api/users/" + userId;
    var appUserCommand = {userId: userId, userName: userName, encryptedPassword: userPwd, enabled: enabled};

    $.ajax({
        type: "put",
        url: url, //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        data: JSON.stringify(appUserCommand), //json object or array of json objects
        success: function (result) {
            location.reload();
            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
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
            location.reload();
            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function deleteRoleFromUser(userId, roleId) {
    var url = "/admin/api/user_role/" + userId + "/" + roleId;
    var isDelete = confirm("Дійсно бажаєте видалити?");
    if (!isDelete) {
        return;
    }
    $.ajax({
        type: "delete",
        url: url, //your valid url (/url)
        contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
        //data: JSON.stringify(roleUserCommand), //json object or array of json objects
        success: function (result) {
            location.reload();

            //do nothing
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}