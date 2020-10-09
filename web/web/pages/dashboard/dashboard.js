const SELL_ZONES_TABLE_BODY_ID = "sell-zones-table-body";
const SELL_ZONES_TABLE_CELL_ID = "sell-zones-table-cell";
const ACCOUNT_TABLE_BODY_ID = "account-table-body";
const ACCOUNT_TABLE_CELL_ID = "account-table-cell";
const USER_LIST_URL_RESOURCE = "userslist";
const SELL_ZONES_TABLE_URL_RESOURCE = "sellZonesTable";
const ACCOUNT_TABLE_URL_RESOURCE = "accountTable";

let refreshRate = 2000; //milli seconds
let USER_LIST_URL = buildUrlWithContextPath(USER_LIST_URL_RESOURCE);
let SELL_ZONES_TABLE_URL = buildUrlWithContextPath(SELL_ZONES_TABLE_URL_RESOURCE);
let ACCOUNT_TABLE_URL = buildUrlWithContextPath(ACCOUNT_TABLE_URL_RESOURCE);


//users = a list of users, essentially an array of javascript strings:
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


function addElemToTable(elem, tableBodyId) {
    let tableBody = document.getElementById(tableBodyId);
    let row = tableBody.insertRow();
    Object.keys(elem).forEach(function(key) {
        let cell = row.insertCell();
        cell.classList.add(SELL_ZONES_TABLE_CELL_ID);
        cell.textContent = elem[key];
    })
}

//zones = a list of zones, essentially an array of javascript strings:
//[{"ownerName":"shalom","zoneName":"Galil Maarvi","totalDifferentItems":5,"totalStores":2,"totalOrders":0,"totalOrdersCostAverageWithoutDelivery":0.0},{"ownerName":"shalom","zoneName":"Haifa","totalDifferentItems":8,"totalStores":4,"totalOrders":0,"totalOrdersCostAverageWithoutDelivery":0.0},{"ownerName":"shalom","zoneName":"Hasharon","totalDifferentItems":10,"totalStores":4,"totalOrders":0,"totalOrdersCostAverageWithoutDelivery":0.0}]
function refreshSellZoneTable(zones) {
    //clear all current table
    $("#sell-zones-table-body").empty();

    // rebuild the table of sell zones: scan all zones and add them to the table of sell zones
    $.each(zones || [], function(index, zone) {
        addElemToTable(zone, SELL_ZONES_TABLE_BODY_ID);
    });
}


function refreshAccountTable(zones) {
    //clear all current table
    $("#account-table-body").empty();

    // rebuild the table of sell zones: scan all zones and add them to the table of sell zones
    $.each(zones || [], function(index, account) {
        addElemToTable(account, ACCOUNT_TABLE_BODY_ID);
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


function ajaxAccountTable() {
    $.ajax({
        url: ACCOUNT_TABLE_URL,
        success: function(account) {
            refreshAccountTable(account);
        }
    });
}

//activate the timer calls after the page is loaded
$(function() {

    //The users list is refreshed automatically every second
    setInterval(ajaxUsersList, refreshRate);

    //The sell zones table content is refreshed automatically every second
    setInterval(ajaxSellZonesTable, refreshRate);

    //The account table content is refreshed automatically every second
    setInterval(ajaxAccountTable, refreshRate);
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
                $("#upload-file-msg-label").text("Failed to get result from server " + r);
            },
            success: function(r) {
                $("#upload-file-msg-label").text(r);
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})


$(function() {
    $("#charge-credit-form").submit(function() {
        let parameters = $(this).serialize();

        $.ajax({
            data: parameters,
            url: this.action,
            timeout: 2000,
            error: function() {
                console.error("Failed to submit");
                $("#charge-credit-msg-label").text("Failed to get result from server");
            },
            success: function(r) {
                $("#charge-credit-msg-label").text(r);
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})