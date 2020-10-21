const ORDERS_CONTAINER_ID = "orders-container";
const ORDERS_ACCORDION_CONTAINER_ID = "orders-accordion-container";
const ORDER_ACCORDION_BUTTON_CLASS = "order-accordion-button";
const ORDER_ACCORDION_BUTTON_ACTIVE_CLASS = "order-accordion-button-active";
const ORDER_PANEL_CONTAINER_CLASS = "order-panel-container";
const ORDER_ITEMS_TABLE_ID = "order-items-table";
const ORDER_ITEMS_TABLE_BODY_ID = "order-items-table-body";
const ORDER_ITEMS_TABLE_COL_CLASS = "order-items-table-col";
const ORDER_ITEMS_TABLE_CELL_CLASS = "order-items-table-cell";
const ORDER_ITEMS_TABLE_HEADERS = ["ID", "Name", "Purchase Category", "Store ID", "Store Name",
                                    "Quantity", "Price", "Total Cost", "Discount"];
const NO_ORDERS_P_ID = "no-orders-p";
const NO_ORDERS_P_TEXT_CONTENT = "You didn't order yet :)";


const ORDER_HISTORY_URL_RESOURCE = "getOrderHistory";
let ORDER_HISTORY_URL = buildUrlWithContextPath(ORDER_HISTORY_URL_RESOURCE);


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


function addOrder(order) {
    let id = order["id"];
    let date = order["dateStr"];
    let customerXLocation = order["customerXLocation"];
    let customerYLocation = order["customerYLocation"];
    let totalStores = order["totalStores"];
    let totalItems = order["totalItems"];
    let itemsCost = order["itemsCost"];
    let deliveryCost = order["deliveryCost"];
    let totalCost = order["totalCost"];
    let purchasedItems = order["purchasedItemsDto"];

    let ordersAccordionContainer = document.getElementById(ORDERS_ACCORDION_CONTAINER_ID);
    let orderAccordionButton = document.createElement("button");
    orderAccordionButton.classList.add(ORDER_ACCORDION_BUTTON_CLASS);
    orderAccordionButton.innerHTML = `ID: ${id}${TAB}
                                   Date: ${date}${TAB}
                                   Destination: (${customerXLocation},${customerYLocation})${TAB}
                                   ${NEW_LINE}
                                   Total Stores: ${totalStores}${TAB}
                                   Total Items: ${totalItems}${TAB}
                                   ${NEW_LINE}
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


function addEventListenersToOrders() {
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


function showOrderHistory(orders) {
    $.each(orders || [], function(index, order) {
        addOrder(order);
    });

    addEventListenersToOrders();
}


function ajaxOrderHistory() {
    $.ajax({
        url: ORDER_HISTORY_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(orders) {
            let ordersContainer = document.getElementById(ORDERS_CONTAINER_ID);
            if (orders.length > 0) {
                let ordersAccordionContainer = document.createElement("div");
                ordersAccordionContainer.id = ORDERS_ACCORDION_CONTAINER_ID;
                ordersContainer.appendChild(ordersAccordionContainer);
                showOrderHistory(orders);
            }
            else {
                let noOrdersP = document.createElement("p");
                noOrdersP.id = NO_ORDERS_P_ID;
                noOrdersP.textContent = NO_ORDERS_P_TEXT_CONTENT;
                ordersContainer.appendChild(noOrdersP);
            }
        }
    });
}


$(function() {
    ajaxOrderHistory();
});

