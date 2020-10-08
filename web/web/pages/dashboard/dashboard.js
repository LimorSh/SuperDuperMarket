const SELL_ZONES_TABLE_ID = "sell-zones-table";
const SELL_ZONES_TABLE_BODY_ID = "sell-zones-table-body";
const SELL_ZONES_TABLE_CELL_ID = "sell-zones-table-cell"
const USER_LIST_URL_RESOURCE = "userslist";
const SELL_ZONES_TABLE_URL_RESOURCE = "sellZonesTable";

let refreshRate = 2000; //milli seconds
let USER_LIST_URL = buildUrlWithContextPath(USER_LIST_URL_RESOURCE);
let SELL_ZONES_TABLE_URL = buildUrlWithContextPath(SELL_ZONES_TABLE_URL_RESOURCE);


//users = a list of user, essentially an array of javascript strings:
//[{"id":1,"name":"david","userType":"CUSTOMER"},{"id":2,"name":"rachel","userType":"SELLER"}]
function refreshUsersList(users) {
    //clear all current users
    $("#users-list").empty();

    // rebuild the list of users: scan all users and add them to the list of users
    $.each(users || [], function(index, user) {
        let userStr = `id: ${user.id}, name: ${user.name}, type: ${user["userType"]}`;
        $('<li>' + userStr + '</li>').appendTo($("#users-list"));
    });
}


function addZoneToSellZonesTable(zone) {
    let tableBody = document.getElementById(SELL_ZONES_TABLE_BODY_ID);
    let row = tableBody.insertRow();
    Object.keys(zone).forEach(function(key) {
        let cell = row.insertCell();
        cell.classList.add(SELL_ZONES_TABLE_CELL_ID);
        cell.textContent = zone[key];
    })
}


function refreshSellZoneTable(zones) {
    //clear all current table
    $("#sell-zones-table-body").empty();

    // rebuild the table of sell zones: scan all zones and add them to the table of sell zones
    $.each(zones || [], function(index, zone) {
        addZoneToSellZonesTable(zone);
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

function ajaxSellZonesTable() {
    $.ajax({
        url: SELL_ZONES_TABLE_URL,
        success: function(zones) {
            refreshSellZoneTable(zones);
        }
    });
}


//activate the timer calls after the page is loaded
$(function() {

    //The users list is refreshed automatically every second
    setInterval(ajaxUsersList, refreshRate);

    //The sell zones table content is refreshed automatically every second
    setInterval(ajaxSellZonesTable, refreshRate);
});


// upload file
$(function() {
    $("#upload-file").submit(function() {
        let file = this[0].files[0];
        let formData = new FormData();
        formData.append("file", file);

        $.ajax({
            method:'POST',
            data: formData,
            url: this.action,
            processData: false, // Don't process the files
            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
            timeout: 4000,
            error: function(r) {
                // console.error("Failed to submit");
                $("#error-msg").text("Failed to get result from server " + r);
            },
            success: function(r) {
                $("#error-msg").text(r);
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})