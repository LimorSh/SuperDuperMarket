const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_ID = "items-table-cell";

const STORES_CONTAINER_ACCORDION_ID = "stores-container-accordion";
const STORE_ITEMS_TABLE_ID = "store-items-table";
const STORE_ITEMS_TABLE_BODY_ID = "store-items-table-body";
const STORE_ITEMS_TABLE_COL = "store-items-table-col";
const STORE_ITEMS_TABLE_CELL_ID = "store-items-table-cell";

const STORE_ITEMS_TABLE_HEADERS = ["ID", "Name", "Purchase Category", "Price", "Total Sells"];

const SET_TITLE_URL_RESOURCE = "setTitle";
let SET_TITLE_TABLE_URL = buildUrlWithContextPath(SET_TITLE_URL_RESOURCE);
const ITEMS_TABLE_URL_RESOURCE = "itemsTable";
let ITEMS_TABLE_URL = buildUrlWithContextPath(ITEMS_TABLE_URL_RESOURCE);
const STORES_URL_RESOURCE = "stores";
let STORES_URL = buildUrlWithContextPath(STORES_URL_RESOURCE);


function ajaxSetTitle() {
    $.ajax({
        url: SET_TITLE_TABLE_URL,
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

function addItemsTableToStore(items) {
    let itemsTable = document.createElement("table");
    itemsTable.id = STORE_ITEMS_TABLE_ID;
    let thead = document.createElement("thead");
    itemsTable.appendChild(thead);
    let itemsTableBody = document.createElement("tbody");
    itemsTableBody.id = STORE_ITEMS_TABLE_BODY_ID;
    itemsTable.appendChild(itemsTableBody);

    for (let i = 0; i < STORE_ITEMS_TABLE_HEADERS.length; i++) {
        let header = document.createElement("th");
        header.class = STORE_ITEMS_TABLE_COL;
        let headerText = document.createTextNode(STORE_ITEMS_TABLE_HEADERS[i]);
        header.appendChild(headerText);
        thead.appendChild(header);
    }
    for (let i = 0; i < items.length; i++) {
        let item = items[i]

        // let tableBody = document.getElementById(tableBodyId);
        let row = itemsTableBody.insertRow();
        Object.keys(item).forEach(function(key) {
            let cell = row.insertCell();
            cell.classList.add(STORE_ITEMS_TABLE_CELL_ID);
            cell.textContent = item[key];
        })
        // addElemToTable(item, STORE_ITEMS_TABLE_BODY_ID, STORE_ITEMS_TABLE_CELL_ID);
    }
    return itemsTable;
}

function addStore(store) {
    let id = store["id"];
    let name = store["name"];
    let ownerName = store["ownerName"];
    let xLocation = store["xLocation"];
    let yLocation = store["yLocation"];
    let ppk = store["ppk"];
    let totalDeliveriesRevenue = store["totalDeliveriesRevenue"];
    let numberOfOrders = store["numberOfOrders"];
    let totalItemsCost = store["totalItemsCost"];
    let items = store["storeItemsDto"];

    let storesContainerAccordion = document.getElementById(STORES_CONTAINER_ACCORDION_ID);
    let button = document.createElement("button");
    button.classList.add("accordion");
    let buttonText = document.createTextNode(name);
    button.appendChild(buttonText);
    let div = document.createElement("div");
    div.classList.add("panel");
    let label = document.createElement("label");
    let str = `ID: ${id}, Owner Name: ${ownerName}, Location: (${xLocation},${yLocation}),
                PPK: ${ppk}, Total Deliveries Revenue: ${totalDeliveriesRevenue},
                Number of Orders: ${numberOfOrders}, Total Items Cost: ${totalItemsCost}`;
    let labelText = document.createTextNode(str);
    label.appendChild(labelText);

    let itemsTable = addItemsTableToStore(items);

    div.appendChild(label);
    div.appendChild(itemsTable);
    storesContainerAccordion.appendChild(button);
    storesContainerAccordion.appendChild(div);
}

function addToStoresEventListeners() {
    let acc = document.getElementsByClassName("accordion");
    let i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            this.classList.toggle("active");
            let panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }
}


function refreshStores(stores) {
    $("#stores-container").empty();

    // rebuild the stores accordion: scan all stores and add them to the accordion of stores
    $.each(stores || [], function(index, store) {
        addStore(store);
    });

    addToStoresEventListeners();
}


function ajaxItemsTable() {
    $.ajax({
        url: ITEMS_TABLE_URL,
        success: function(items) {
            refreshItemsTable(items);
        }
    });
}


function ajaxStores() {
    $.ajax({
        url: STORES_URL,
        success: function(stores) {
            refreshStores(stores);
        }
    });
}


function ajaxAddOrder() {
    $("#add-order-form").submit(function() {
        let parameters = $(this).serialize();

        $.ajax({
            data: parameters,
            url: this.action,
            timeout: 2000,
            error: function() {
                console.error("Failed to submit");
                $("#error-msg").text("Failed to get result from server");
            },
            success: function(r) {
                console.log(r);
                // if (r.length > 0) {
                //     $("#error-msg").text(r);
                // }
                // else {
                //     pageRedirect();
                // }
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
}


$(function() {
    ajaxSetTitle();

    //The items table content is refreshed automatically every second
    // setInterval(ajaxItemsTable, refreshRate);
    ajaxItemsTable();

    //The stores content is refreshed automatically every second
    // setInterval(ajaxStores, refreshRate);
    ajaxStores();

    ajaxAddOrder();
});
