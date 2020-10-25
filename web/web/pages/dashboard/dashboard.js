const NOTIFICATIONS_CONTAINER = "notifications-container";
const UPLOAD_FILE_CONTAINER = "upload-file-container";
const CHARGE_CREDIT_CONTAINER = "charge-credit-container";

const SELL_ZONES_TABLE_BODY_ID = "sell-zones-table-body";
const SELL_ZONES_TABLE_CELL_ID = "sell-zones-table-cell";
const SELL_ZONE_TABLE_LINK_CELL_CLASS = "sell-zone-link-cell-class";
const ACCOUNT_TABLE_BODY_ID = "account-table-body";
const ACCOUNT_TABLE_CELL_ID = "account-table-cell";

let refreshRate = 2000; //milli seconds
const GET_USER_TYPE_URL_RESOURCE = "getUserType";
let GET_USER_TYPE_URL = buildUrlWithContextPath(GET_USER_TYPE_URL_RESOURCE);
const USER_LIST_URL_RESOURCE = "userslist";
let USER_LIST_URL = buildUrlWithContextPath(USER_LIST_URL_RESOURCE);
const SELL_ZONES_TABLE_URL_RESOURCE = "sellZonesTable";
let SELL_ZONES_TABLE_URL = buildUrlWithContextPath(SELL_ZONES_TABLE_URL_RESOURCE);
const ACCOUNT_TABLE_URL_RESOURCE = "accountTable";
let ACCOUNT_TABLE_URL = buildUrlWithContextPath(ACCOUNT_TABLE_URL_RESOURCE);
const SELL_ZONE_URL_RESOURCE = "pages/sellZone/sell-zone.html";
let SELL_ZONE_URL = buildUrlWithContextPath(SELL_ZONE_URL_RESOURCE);
const SELL_ZONE_CHOSEN_URL_RESOURCE = "sellZoneChosen";
let SELL_ZONE_CHOSEN_URL = buildUrlWithContextPath(SELL_ZONE_CHOSEN_URL_RESOURCE);


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


function zoneWasChosen(zoneName) {
    $.ajax({
        data: {
            "zoneName": zoneName,
        },
        url: SELL_ZONE_CHOSEN_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(r) {
            console.log(r);
        }
    });
}

function addZoneLinksToTable(zone) {
    let zoneName = zone["zoneName"];
    let link = document.createElement("a");
    link.setAttribute("href", SELL_ZONE_URL);
    // let linkIcon = document.createElement("i");
    // linkIcon.setAttribute("class", "fas fa-bars");
    // let linkIcon = document.createElement("img");
    // linkIcon.setAttribute("src", "../../common/images/cart-icon.jpg");
    // link.append('<i class="fas fa-bars"></i>');
    link.className = SELL_ZONE_TABLE_LINK_CELL_CLASS;

    link.innerHTML = zoneName;
    link.addEventListener("click", () => {
        zoneWasChosen(zoneName);
    });

    let tableBody = document.getElementById(SELL_ZONES_TABLE_BODY_ID);
    for (let i = 0; i < tableBody.rows.length; i++) {
        let row = tableBody.rows[i];
        let cell = row.insertCell();
        cell.classList.add(SELL_ZONES_TABLE_CELL_ID);
        cell.appendChild(link);
    }
}

//zones = a list of zones, essentially an array of javascript strings:
//[{"ownerName":"shalom","zoneName":"Galil Maarvi","totalDifferentItems":5,"totalStores":2,"totalOrders":0,"totalOrdersCostAverageWithoutDelivery":0.0},{"ownerName":"shalom","zoneName":"Haifa","totalDifferentItems":8,"totalStores":4,"totalOrders":0,"totalOrdersCostAverageWithoutDelivery":0.0},{"ownerName":"shalom","zoneName":"Hasharon","totalDifferentItems":10,"totalStores":4,"totalOrders":0,"totalOrdersCostAverageWithoutDelivery":0.0}]
function refreshSellZoneTable(zones) {
    //clear all current table
    $("#sell-zones-table-body").empty();

    // rebuild the table of sell zones: scan all zones and add them to the table of sell zones
    $.each(zones || [], function(index, zone) {
        addElemToTable(zone, SELL_ZONES_TABLE_BODY_ID, SELL_ZONES_TABLE_CELL_ID);
        addZoneLinksToTable(zone);
    });
}


//transactions = a list of transactions, essentially an array of javascript strings:
//[{"type":"CHARGE","date":"Oct 9, 2020 11:42:58 AM","amount":30.0,"balanceBefore":0.0,"balanceAfter":30.0},{"type":"CHARGE","date":"Oct 9, 2020 11:43:36 AM","amount":46.0,"balanceBefore":30.0,"balanceAfter":76.0},{"type":"CHARGE","date":"Oct 9, 2020 11:44:02 AM","amount":42.0,"balanceBefore":76.0,"balanceAfter":118.0}]
function refreshAccountTable(transactions) {
    //clear all current table
    $("#account-table-body").empty();

    // rebuild the table of sell zones: scan all zones and add them to the table of sell zones
    $.each(transactions || [], function(index, transaction) {
        addElemToTable(transaction, ACCOUNT_TABLE_BODY_ID, ACCOUNT_TABLE_CELL_ID);
    });
}


function ajaxUsersList() {
    $.ajax({
        url: USER_LIST_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(users) {
            refreshUsersList(users);
        }
    });
}


function ajaxSellZonesTable() {
    $.ajax({
        url: SELL_ZONES_TABLE_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(zones) {
            refreshSellZoneTable(zones);
        }
    });
}


function ajaxAccountTable() {
    $.ajax({
        url: ACCOUNT_TABLE_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(account) {
            refreshAccountTable(account);
        }
    });
}


function ajaxGetUserType() {
    $.ajax({
        url: GET_USER_TYPE_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(userType) {
            let notificationsContainer = document.getElementById(NOTIFICATIONS_CONTAINER);
            let uploadFileContainer = document.getElementById(UPLOAD_FILE_CONTAINER);
            let chargeCreditContainer = document.getElementById(CHARGE_CREDIT_CONTAINER);

            if (userType === USER_TYPE_CUSTOMER_STR) {
                notificationsContainer.style.display = "none";
                uploadFileContainer.style.display = "none";
            }
            else {
                chargeCreditContainer.style.display = "none";
            }
        }
    });
}


//activate the timer calls after the page is loaded
$(function() {

    // get user type
    ajaxGetUserType();

    //The users list is refreshed automatically every second
    setInterval(ajaxUsersList, refreshRate);

    //The sell zones table content is refreshed automatically every second
    setInterval(ajaxSellZonesTable, refreshRate);

    //The account transactions table content is refreshed automatically every second
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
            headers: {
                'cache-control': 'no-store,no-cache',
            },
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
            headers: {
                'cache-control': 'no-store,no-cache',
            },
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