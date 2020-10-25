const NOTIFICATIONS_AREA_ID = "notifications-area";

let storeNotificationVersion = 0;
let storeFeedbackNotificationVersion = 0;
let orderNotificationVersion = 0;

// const REFRESH_RATE = 2000; //milli seconds
const REFRESH_RATE = 60000; //milli seconds

const STORE_NOTIFICATION_URL_RESOURCE = "storeNotifications";
let STORE_NOTIFICATION_URL = buildUrlWithContextPath(STORE_NOTIFICATION_URL_RESOURCE);
const STORE_FEEDBACK_NOTIFICATION_URL_RESOURCE = "storeFeedbackNotifications";
let STORE_FEEDBACK_NOTIFICATION_URL = buildUrlWithContextPath(STORE_FEEDBACK_NOTIFICATION_URL_RESOURCE);
const ORDER_NOTIFICATION_URL_RESOURCE = "orderNotifications";
let ORDER_NOTIFICATION_URL = buildUrlWithContextPath(ORDER_NOTIFICATION_URL_RESOURCE);


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


function appendOrderToNotificationsArea(orderNotifications) {
    $.each(orderNotifications || [], appendOrderNotification);

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

function appendOrderNotification(index, notification) {
    let notificationElement = createOrderNotification(notification);
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


function createOrderNotification(OrderNotification) {
    let orderId = OrderNotification["orderId"];
    let storeName = OrderNotification["storeName"];
    let customerName = OrderNotification["customerName"];
    let totalItems = OrderNotification["totalItems"];
    let itemsCost = OrderNotification["itemsCost"];
    let deliveryCost = OrderNotification["deliveryCost"];

    return $("<span class=\"success\">").append(
        `You have one new order from your store ${storeName}:
        Order ID: ${orderId} |
        Customer Name: ${customerName} | 
        Total Items: ${totalItems} | 
        Items Cost: ${itemsCost} | 
        Delivery Cost: ${deliveryCost}`
    );
}


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


function ajaxOrderNotifications() {
    $.ajax({
        url: ORDER_NOTIFICATION_URL,
        data: "orderNotificationVersion=" + orderNotificationVersion,
        dataType: 'json',
        success: function(newOrderNotifications) {
            if (newOrderNotifications.version !== orderNotificationVersion) {
                orderNotificationVersion = newOrderNotifications.version;
                appendOrderToNotificationsArea(newOrderNotifications.entries);
            }
            triggerAjaxOrderNotifications();
        },
        error: function() {
            triggerAjaxOrderNotifications();
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
    setTimeout(ajaxOrderNotifications, REFRESH_RATE);
}


$(function() {
    triggerAjaxStoreNotifications();
    triggerAjaxStoreFeedbackNotifications();
    triggerAjaxOrderNotifications();
})

