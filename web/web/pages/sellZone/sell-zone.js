const TAB = "&emsp;&emsp;&emsp;"
const NEW_LINE = "<br/>"

const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_ID = "items-table-cell";

const STORES_CONTAINER_ACCORDION_ID = "stores-container-accordion";
const STORE_ITEMS_TABLE_ID = "store-items-table";
const STORE_ITEMS_TABLE_BODY_ID = "store-items-table-body";
const STORE_ITEMS_TABLE_COL = "store-items-table-col";
const STORE_ITEMS_TABLE_CELL_ID = "store-items-table-cell";
const STORE_ITEMS_TABLE_HEADERS = ["ID", "Name", "Purchase Category", "Price", "Total Sells"];
const STORE_DETAILS_CONTAINER_ID = "store-details-container";
const STORE_DETAILS_P_CLASS = "store-details-p";

const SET_TITLE_URL_RESOURCE = "setTitle";
let SET_TITLE_TABLE_URL = buildUrlWithContextPath(SET_TITLE_URL_RESOURCE);


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


function addHeadersToItemsTable(thead) {
    for (let i = 0; i < STORE_ITEMS_TABLE_HEADERS.length; i++) {
        let header = document.createElement("th");
        header.class = STORE_ITEMS_TABLE_COL;
        header.innerHTML = STORE_ITEMS_TABLE_HEADERS[i];
        thead.appendChild(header);
    }
}


function addItemsToItemsTable(items, itemsTableBody) {
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
    storeDetailsDiv.id = STORE_DETAILS_CONTAINER_ID;

    let storeDetailsP = document.createElement("p");
    storeDetailsP.class = STORE_DETAILS_P_CLASS;
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

    let storesContainerAccordion = document.getElementById(STORES_CONTAINER_ACCORDION_ID);
    let button = document.createElement("button");
    button.classList.add("accordion");
    let buttonText = document.createTextNode(name);
    button.appendChild(buttonText);
    let storeDiv = document.createElement("div");
    storeDiv.classList.add("panel");

    let itemsTable = addItemsTableToStore(items);
    let storeDetailsDiv = addStoreDetails(store);

    storeDiv.appendChild(storeDetailsDiv);
    storeDiv.appendChild(itemsTable);
    storesContainerAccordion.appendChild(button);
    storesContainerAccordion.appendChild(storeDiv);
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


$(function() {
    ajaxSetTitle();

    //The items table content is refreshed automatically every second
    // setInterval(ajaxItemsTable, refreshRate);
    ajaxItemsTable();

    //The stores content is refreshed automatically every second
    // setInterval(ajaxStores, refreshRate);
    ajaxStores();
});




