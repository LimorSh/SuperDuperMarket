const NOTIFICATIONS_AREA_ID = "notifications-area";

let storeNotificationVersion = 0;

const REFRESH_RATE = 2000; //milli seconds
const STORE_NOTIFICATION_URL_RESOURCE = "storeNotifications";
let STORE_NOTIFICATION_URL = buildUrlWithContextPath(STORE_NOTIFICATION_URL_RESOURCE);


function appendToNotificationsArea(notifications) {
//    $("#chatarea").children(".success").removeClass("success");

    // add the relevant entries
    $.each(notifications || [], appendNotification);

    // handle the scroller to auto scroll to the end of the chat area
    let scroller = $(`#${NOTIFICATIONS_AREA_ID}`);
    let height = scroller[0].scrollHeight - $(scroller).height();
    $(scroller).stop().animate({ scrollTop: height }, "slow");
}

function appendNotification(index, notification){
    let notificationElement = createNotification(notification);
    $(`#${NOTIFICATIONS_AREA_ID}`).append(notificationElement).append("<br>");
}


function createNotification(notification){
    let storeOwnerName = notification["ownerName"];
    let storeName = notification["storeName"];
    let location = notification["locationStr"];
    let itemsRatio = notification["itemsRatio"];

    return $("<span class=\"success\">").append(
        `New store was opened in your zone: 
        Store Owner Name: ${storeOwnerName}
        Store Name: ${storeName}
        Location: ${location}
        Items Ratio: ${itemsRatio}`
    );
}


function ajaxNotifications() {
    $.ajax({
        url: STORE_NOTIFICATION_URL,
        data: "storeNotificationVersion=" + storeNotificationVersion,
        dataType: 'json',
        success: function(data) {
            /*
             data will arrive in the next form:
             {
                "entries": [
                    {
                        "ownerName":"Limor",
                        "storeName":"super-baba",
                        "locationStr":(3,5),
                        "itemsRatio":10/15
                    },
                ],
                "version":1
             }
             */
            console.log("Server version: " + data.version + ", Current version: " + storeNotificationVersion);
            if (data.version !== storeNotificationVersion) {
                storeNotificationVersion = data.version;
                appendToNotificationsArea(data.entries);
            }
            triggerAjaxNotifications();
        },
        error: function() {
            triggerAjaxNotifications();
        }
    });
}


function triggerAjaxNotifications() {
    setTimeout(ajaxNotifications, REFRESH_RATE);
}


$(function() {
    triggerAjaxNotifications();
})

