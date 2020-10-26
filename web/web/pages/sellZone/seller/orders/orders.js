const ORDERS_CONTAINER_ID = "orders-container";
const STORES_ORDERS_CONTAINER_ID = "stores-orders-container";
const STORE_ORDERS_CONTAINER_ID = "store-orders-container";
const STORES_SELECT_CONTAINER_ID = "stores-select-container";
const STORES_SELECT_ID = "stores-select";
const STORE_SELECT_DEFAULT_OPTION_ID = "stores-select-default-option";

const ORDERS_ACCORDION_CONTAINER_ID = "orders-accordion-container";
const ORDER_ACCORDION_BUTTON_CLASS = "order-accordion-button";
const ORDER_ACCORDION_BUTTON_ACTIVE_CLASS = "order-accordion-button-active";
const ORDER_PANEL_CONTAINER_CLASS = "order-panel-container";

const ORDER_ITEMS_TABLE_ID = "order-items-table";
const ORDER_ITEMS_TABLE_BODY_ID = "order-items-table-body";
const ORDER_ITEMS_TABLE_COL_CLASS = "order-items-table-col";
const ORDER_ITEMS_TABLE_CELL_CLASS = "order-items-table-cell";
const ORDER_ITEMS_TABLE_HEADERS = ["ID", "Name", "Purchase Category", "Quantity",
    "Price", "Total Cost", "Discount"];

const NO_STORES_P_ID = "no-stores-p";
const NO_STORES_P_TEXT_CONTENT = "You don't have any stores in this zone.";
const NO_STORE_ORDERS_P_ID = "no-store-orders-p";
const NO_STORE_ORDERS_P_TEXT_CONTENT = "There are no orders from this store yet.";

const OWNER_STORES_URL_RESOURCE = "ownerStores";
let OWNER_STORES_URL = buildUrlWithContextPath(OWNER_STORES_URL_RESOURCE);
const OWNER_STORE_ORDERS_URL_RESOURCE = "ownerStoreOrders";
let OWNER_STORE_ORDERS_URL = buildUrlWithContextPath(OWNER_STORE_ORDERS_URL_RESOURCE);

let stores;
let storeId;
let storesIds = [];


function addEventListenersToStoreOrders() {
    let ordersAccordionButtons = document.getElementsByClassName(ORDER_ACCORDION_BUTTON_CLASS);

    for (let orderAccordionButton of ordersAccordionButtons) {
        orderAccordionButton.addEventListener("click", function() {
            this.classList.toggle(ORDER_ACCORDION_BUTTON_ACTIVE_CLASS);
            let panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }
}


function addHeadersToPurchasedItemsTable(thead) {
    for (let i = 0; i < ORDER_ITEMS_TABLE_HEADERS.length; i++) {
        let header = document.createElement("th");
        header.class = ORDER_ITEMS_TABLE_COL_CLASS;
        header.innerHTML = ORDER_ITEMS_TABLE_HEADERS[i];
        thead.appendChild(header);
    }
}


function addItemsToPurchasedItemsTable(items, itemsTableBody) {
    for (let item of items) {
        let row = itemsTableBody.insertRow();
        Object.keys(item).forEach(function(key) {
            let cell = row.insertCell();
            cell.classList.add(ORDER_ITEMS_TABLE_CELL_CLASS);
            cell.textContent = item[key];
        })
    }
}


function addPurchasedItemsTableToOrder(items) {
    let itemsTable = document.createElement("table");
    itemsTable.id = ORDER_ITEMS_TABLE_ID;
    let thead = document.createElement("thead");
    itemsTable.appendChild(thead);
    let itemsTableBody = document.createElement("tbody");
    itemsTableBody.id = ORDER_ITEMS_TABLE_BODY_ID;
    itemsTable.appendChild(itemsTableBody);
    addHeadersToPurchasedItemsTable(thead);
    addItemsToPurchasedItemsTable(items, itemsTableBody);
    return itemsTable;
}


function addStoreOrder(storeOrder) {
    let id = storeOrder["orderId"];
    let date = storeOrder["dateStr"];
    let customerName = storeOrder["customerName"];
    let customerLocation = storeOrder["customerLocation"];
    let totalItems = storeOrder["totalItems"];
    let itemsCost = storeOrder["itemsCost"];
    let deliveryCost = storeOrder["deliveryCost"];
    let totalCost = storeOrder["totalCost"];
    let purchasedItems = storeOrder["purchasedItemsStoreOrderDto"];

    let ordersAccordionContainer = document.getElementById(ORDERS_ACCORDION_CONTAINER_ID);
    let orderAccordionButton = document.createElement("button");
    orderAccordionButton.classList.add(ORDER_ACCORDION_BUTTON_CLASS);
    orderAccordionButton.innerHTML = `ID: ${id}${TAB}
                                   Date: ${date}${TAB}
                                   Customer Name: ${customerName}${TAB}
                                   Customer Location: ${customerLocation}${TAB}
                                   ${NEW_LINE}
                                   Total Items: ${totalItems}${TAB}
                                   Items Cost: ${itemsCost}${TAB}
                                   Delivery Cost: ${deliveryCost}${TAB}
                                   Total Cost: ${totalCost}${TAB}
                                   `;
    let orderPanelContainer = document.createElement("div");
    orderPanelContainer.classList.add(ORDER_PANEL_CONTAINER_CLASS);
    let purchasedItemsTable = addPurchasedItemsTableToOrder(purchasedItems);
    orderPanelContainer.appendChild(purchasedItemsTable);
    ordersAccordionContainer.appendChild(orderAccordionButton);
    ordersAccordionContainer.appendChild(orderPanelContainer);
}


function showStoreOrders(storeOrders) {
    $(`#${ORDERS_ACCORDION_CONTAINER_ID}`).empty();

    $.each(storeOrders || [], function(index, storeOrder) {
        addStoreOrder(storeOrder);
    });

    addEventListenersToStoreOrders();
        // console.log(orders);
}


function showNoContentMsg(containerId, noContentPId, noContentPText) {
    let container = document.getElementById(containerId);
    let noContentP = document.createElement("p");
    noContentP.id = noContentPId;
    noContentP.textContent = noContentPText;
    container.appendChild(noContentP);
}


function ajaxGetStoreOrders() {
    $.ajax({
        url: OWNER_STORE_ORDERS_URL,
        data: {"storeId": storeId},
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function() {
            console.error("Failed to submit");
        },
        success: function(storeOrders) {
            let ordersContainer = document.getElementById(ORDERS_CONTAINER_ID);

            if (storeOrders.length > 0) {
                let ordersAccordionContainer = document.createElement("div");
                ordersAccordionContainer.id = ORDERS_ACCORDION_CONTAINER_ID;
                ordersContainer.appendChild(ordersAccordionContainer);
                showStoreOrders(storeOrders);
            }
            else {
                showNoContentMsg(STORE_ORDERS_CONTAINER_ID, NO_STORE_ORDERS_P_ID,
                    NO_STORE_ORDERS_P_TEXT_CONTENT);
            }
        }
    });
}


function storeWasChosen() {
    document.getElementById(STORE_SELECT_DEFAULT_OPTION_ID).disabled = true;
    let storesSelect = document.getElementById(STORES_SELECT_ID);

    let index = storesSelect.selectedIndex - 1;
    for (let i = 0; i < storesIds.length; i++) {
        if (i === index) {
            storeId = storesIds[i];
            break;
        }
    }
    ajaxGetStoreOrders(storeId);
}


function addOwnerStoresToStoresSelect() {
    let storesSelect = document.getElementById(STORES_SELECT_ID);

    let storesValues = [];
    for (let store of stores) {
        let storeId = store["id"];
        let storeValues = `${storeId} | ${store["name"]} | (${store["xLocation"]},${store["yLocation"]})`;
        storesValues.push(storeValues);
        storesIds.push(storeId);
    }

    let option;
    for (const val of storesValues) {
        option = document.createElement("option");
        option.value = val;
        option.text = val;
        storesSelect.appendChild(option);
    }
}


function ajaxSetOwnerStores() {
    $.ajax({
        url: OWNER_STORES_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function() {
            console.error("Failed to submit");
        },
        success: function(allStores) {
            let storesOrdersContainer = document.getElementById(STORES_ORDERS_CONTAINER_ID);
            if (allStores.length > 0) {
                storesOrdersContainer.style.display = "block";
                stores = allStores;
                addOwnerStoresToStoresSelect();
            }
            else {
                showNoContentMsg(ORDERS_CONTAINER_ID, NO_STORES_P_ID, NO_STORES_P_TEXT_CONTENT);
            }
        }
    });
}


$(function() {
    ajaxSetOwnerStores()
});