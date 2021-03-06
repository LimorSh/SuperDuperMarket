const ACTIONS_BAR_CUSTOMER_CONTAINER = "actions-bar-customer-container";
const ACTIONS_BAR_SELLER_CONTAINER = "actions-bar-seller-container";
const NOTIFICATIONS_CONTAINER = "notifications-container";

const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_ID = "items-table-cell";

const STORES_CONTAINER_ID = "stores-container";
const STORE_CONTAINER_ID = "store-container";
const STORES_HEADER_ID = "stores-headers";
const STORE_ITEMS_TABLE_ID = "store-items-table";
const STORE_ITEMS_TABLE_BODY_ID = "store-items-table-body";
const STORE_ITEMS_TABLE_COL_CLASS = "store-items-table-col";
const STORE_ITEMS_TABLE_CELL_CLASS = "store-items-table-cell";
const STORE_ITEMS_TABLE_HEADERS = ["ID", "Name", "Purchase Category", "Price", "Total Sells"];
const STORE_DETAILS_CONTAINER_CLASS = "store-details-container";
const STORE_DETAILS_P_CLASS = "store-details-p";

let refreshRate = 2000; //milli seconds
const SET_TITLE_URL_RESOURCE = "setTitle";
let SET_TITLE_TABLE_URL = buildUrlWithContextPath(SET_TITLE_URL_RESOURCE);


function ajaxSetTitle() {
    $.ajax({
        url: SET_TITLE_TABLE_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(r) {
            $("#header").text(r + " - Sell Zone");
        }
    });
}


function refreshItemsTable(items) {
    $("#items-table-body").empty();

    // rebuild the table of items: scan all items and add them to the table of items
    $.each(items || [], function(index, item) {
        addElemToTable(item, ITEMS_TABLE_BODY_ID, ITEMS_TABLE_CELL_ID);
    });
}


function addHeadersToItemsTable(thead) {
    for (let i = 0; i < STORE_ITEMS_TABLE_HEADERS.length; i++) {
        let header = document.createElement("th");
        header.classList.add(STORE_ITEMS_TABLE_COL_CLASS);
        header.innerHTML = STORE_ITEMS_TABLE_HEADERS[i];
        thead.appendChild(header);
    }
}


function addItemsToItemsTable(items, itemsTableBody) {
    for (let i = 0; i < items.length; i++) {
        let item = items[i]

        let row = itemsTableBody.insertRow();
        Object.keys(item).forEach(function(key) {
            let cell = row.insertCell();
            cell.classList.add(STORE_ITEMS_TABLE_CELL_CLASS);
            cell.textContent = item[key];
        })
    }
}


function addItemsTableToStore(items) {
    let itemsTable = document.createElement("table");
    itemsTable.id = STORE_ITEMS_TABLE_ID;
    let thead = document.createElement("thead");
    itemsTable.appendChild(thead);
    let itemsTableBody = document.createElement("tbody");
    itemsTableBody.id = STORE_ITEMS_TABLE_BODY_ID;
    itemsTable.appendChild(itemsTableBody);
    addHeadersToItemsTable(thead);
    addItemsToItemsTable(items, itemsTableBody);
    return itemsTable;
}


function addStoreDetails(store) {
    let id = store["id"];
    let ownerName = store["ownerName"];
    let xLocation = store["xLocation"];
    let yLocation = store["yLocation"];
    let ppk = store["ppk"];
    let totalDeliveriesRevenue = store["totalDeliveriesRevenue"];
    let numberOfOrders = store["numberOfOrders"];
    let totalItemsCost = store["totalItemsCost"];

    let storeDetailsDiv = document.createElement("div");
    storeDetailsDiv.classList.add(STORE_DETAILS_CONTAINER_CLASS);

    let storeDetailsP = document.createElement("p");
    storeDetailsP.classList.add(STORE_DETAILS_P_CLASS);
    storeDetailsP.innerHTML = `ID: ${id}${TAB}Owner Name: ${ownerName}${TAB}
                Location: (${xLocation},${yLocation})${TAB}PPK: ${ppk}${NEW_LINE}
                Total Deliveries Revenue: ${totalDeliveriesRevenue}${TAB}
                Number of Orders: ${numberOfOrders}${TAB}Total Items Cost: ${totalItemsCost}`;
    storeDetailsDiv.appendChild(storeDetailsP);
    return storeDetailsDiv;
}


function addStore(store) {
    let name = store["name"];
    let items = store["storeItemsDto"];

    let storesContainer = document.getElementById(STORES_CONTAINER_ID);
    let storeHeader = document.createElement("h3");
    storeHeader.classList.add(STORES_HEADER_ID);
    storeHeader.textContent = name;
    let storeDiv = document.createElement("div");
    storeDiv.classList.add(STORE_CONTAINER_ID);

    let itemsTable = addItemsTableToStore(items);
    let storeDetailsDiv = addStoreDetails(store);
    let divider = document.createElement("hr");

    storeDiv.appendChild(divider);
    storeDiv.appendChild(storeHeader);
    storeDiv.appendChild(storeDetailsDiv);
    storeDiv.appendChild(itemsTable);
    storesContainer.appendChild(storeDiv);
}


function refreshStores(stores) {
    $("#stores-container").empty();

    // rebuild the stores accordion: scan all stores and add them to the accordion of stores
    $.each(stores || [], function(index, store) {
        addStore(store);
    });
}


function ajaxItemsTable() {
    $.ajax({
        url: ITEMS_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(items) {
            refreshItemsTable(items);
        }
    });
}


function ajaxStores() {
    $.ajax({
        url: STORES_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(stores) {
            refreshStores(stores);
        }
    });
}


function showElementsByUserType(currUserType) {
    let actionsBarCustomerContainer = document.getElementById(ACTIONS_BAR_CUSTOMER_CONTAINER);
    let actionsBarSellerContainer = document.getElementById(ACTIONS_BAR_SELLER_CONTAINER);
    let notificationsContainer = document.getElementById(NOTIFICATIONS_CONTAINER);

    if (currUserType === USER_TYPE_CUSTOMER_STR) {
        actionsBarSellerContainer.style.display = "none";
        notificationsContainer.style.display = "none";
    }
    else {
        actionsBarCustomerContainer.style.display = "none";
    }
}


function ajaxGetUserType() {
    $.ajax({
        url: GET_USER_TYPE_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(currUserType) {
            showElementsByUserType(currUserType);
        }
    });
}


$(function() {
    // get user type
    ajaxGetUserType();

    ajaxSetTitle();

    //The items table content is refreshed automatically every second
    // setInterval(ajaxItemsTable, refreshRate);
    setInterval(ajaxItemsTable, refreshRate);

    //The stores content is refreshed automatically every second
    // setInterval(ajaxStores, refreshRate);
    setInterval(ajaxStores, refreshRate);
});




