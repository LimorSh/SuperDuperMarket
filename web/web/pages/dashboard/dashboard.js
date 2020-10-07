let refreshRate = 2000; //milli seconds
let USER_LIST_URL = buildUrlWithContextPath("userslist");


//users = a list of user, essentially an array of javascript strings:
//[{"id":1,"name":"david","userType":"CUSTOMER"},{"id":2,"name":"rachel","userType":"SELLER"}]
function refreshUsersList(users) {
    //clear all current users
    $("#users-list").empty();

    // rebuild the list of users: scan all users and add them to the list of users
    $.each(users || [], function(index, user) {
        let userStr = `id: ${user["id"]}, name: ${user["name"]}, type: ${user["userType"]}`;
        $('<li>' + userStr + '</li>').appendTo($("#users-list"));
    });
}


function ajaxUsersList() {
    $.ajax({
        url: USER_LIST_URL,
        success: function(users) {
            refreshUsersList(users);
        }
    });
}


//activate the timer calls after the page is loaded
$(function() {

    //The users list is refreshed automatically every second
    setInterval(ajaxUsersList, refreshRate);
});