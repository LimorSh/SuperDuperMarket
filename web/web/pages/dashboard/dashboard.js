let refreshRate = 2000; //milli seconds
let USER_LIST_URL = buildUrlWithContextPath("userslist");


//users = a list of usernames, essentially an array of javascript strings:
// ["moshe","nachum","nachche"...]
function refreshUsersList(users) {
    //clear all current users
    $("#users-list").empty();

    // rebuild the list of users: scan all users and add them to the list of users
    $.each(users || [], function(index, username) {
        console.log("Adding user #" + index + ": " + username);
        //create a new <option> tag with a value in it and
        //appeand it to the #userslist (div with id=userslist) element
        $('<li>' + username + '</li>').appendTo($("#users-list"));
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