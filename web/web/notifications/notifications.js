const NOTIFICATIONS_AREA_ID = "notifications-area";

let storeNotificationVersion = 0;
let storeFeedbackNotificationVersion = 0;

const REFRESH_RATE = 200; //milli seconds

const STORE_NOTIFICATION_URL_RESOURCE = "storeNotifications";
let STORE_NOTIFICATION_URL = buildUrlWithContextPath(STORE_NOTIFICATION_URL_RESOURCE);
const STORE_FEEDBACK_NOTIFICATION_URL_RESOURCE = "storeFeedbackNotifications";
let STORE_FEEDBACK_NOTIFICATION_URL = buildUrlWithContextPath(STORE_FEEDBACK_NOTIFICATION_URL_RESOURCE);


function appendStoreNotificationToNotificationsArea(storeNotifications) {
    $.each(storeNotifications || [], appendStoreNotification);

    let scroller = $(`#${NOTIFICATIONS_AREA_ID}`);
    let height = scroller[0].scrollHeight - $(scroller).height();
    $(scroller).stop().animate({ scrollTop: height }, "slow");
}


function appendStoreFeedbackToNotificationsArea(storeFeedbackNotifications) {
    $.each(storeFeedbackNotifications || [], appendStoreFeedbackNotification);

    let scroller = $(`#${NOTIFICATIONS_AREA_ID}`);
    let height = scroller[0].scrollHeight - $(scroller).height();
    $(scroller).stop().animate({ scrollTop: height }, "slow");
}


function appendStoreNotification(index, notification) {
    let notificationElement = createStoreNotification(notification);
    $(`#${NOTIFICATIONS_AREA_ID}`).append(notificationElement).append("<br>");
}


function appendStoreFeedbackNotification(index, notification) {
    let notificationElement = createFeedbackStoreNotification(notification);
    $(`#${NOTIFICATIONS_AREA_ID}`).append(notificationElement).append("<br>");
}


// function createNotification(notification) {
//     if (notification.type === NOTIFICATION_TYPE_NEW_STORE_STR) {
//         return createStoreNotification(notification);
//     }
//     else if (notification.type === NOTIFICATION_TYPE_NEW_STORE_FEEDBACK_STR) {
//         return createFeedbackStoreNotification(notification);
//     }
// }

function createFeedbackStoreNotification(storeFeedbackNotification) {
    let storeName = storeFeedbackNotification["storeName"];
    let customerName = storeFeedbackNotification["customerName"];
    let rate = storeFeedbackNotification["rate"];

    return $("<span class=\"success\">").append(
        `You have one new feedback on your store ${storeName}:
        Customer Name: ${customerName} | 
        Rate: ${rate}`
    );
}

function createStoreNotification(storeNotification) {
    let storeOwnerName = storeNotification["storeOwnerName"];
    let storeName = storeNotification["storeName"];
    let location = storeNotification["locationStr"];
    let itemsRatio = storeNotification["itemsRatio"];

    return $("<span class=\"success\">").append(
        `New store was opened in your zone: 
        Store Owner Name: ${storeOwnerName} | 
        Store Name: ${storeName} | 
        Location: ${location} | 
        Items Ratio: ${itemsRatio}`
    );
}


function ajaxStoreNotifications() {
    $.ajax({
        url: STORE_NOTIFICATION_URL,
        data: "storeNotificationVersion=" + storeNotificationVersion,
        dataType: 'json',
        success: function(newStoreNotifications) {
            if (newStoreNotifications.version !== storeNotificationVersion) {
                storeNotificationVersion = newStoreNotifications.version;
                appendStoreNotificationToNotificationsArea(newStoreNotifications.entries);
            }
            triggerAjaxStoreNotifications();
        },
        error: function() {
            triggerAjaxStoreNotifications();
        }
    });
}


function ajaxStoreFeedbackNotifications() {
    $.ajax({
        url: STORE_FEEDBACK_NOTIFICATION_URL,
        data: "storeFeedbackNotificationVersion=" + storeFeedbackNotificationVersion,
        dataType: 'json',
        success: function(newStoreFeedbackNotifications) {
            if (newStoreFeedbackNotifications.version !== storeFeedbackNotificationVersion) {
                storeFeedbackNotificationVersion = newStoreFeedbackNotifications.version;
                appendStoreFeedbackToNotificationsArea(newStoreFeedbackNotifications.entries);
            }
            triggerAjaxStoreFeedbackNotifications();
        },
        error: function() {
            triggerAjaxStoreFeedbackNotifications();
        }
    });
}


function triggerAjaxStoreNotifications() {
    setTimeout(ajaxStoreNotifications, REFRESH_RATE);
}


function triggerAjaxStoreFeedbackNotifications() {
    setTimeout(ajaxStoreFeedbackNotifications, REFRESH_RATE);
}


function triggerAjaxOrderNotifications() {
    // setTimeout(ajaxOrderNotifications, REFRESH_RATE);
}


$(function() {
    triggerAjaxStoreNotifications();
    triggerAjaxStoreFeedbackNotifications();
    // triggerAjaxOrderNotifications();
})

