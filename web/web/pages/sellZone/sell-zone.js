const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_ID = "items-table-cell";

const ITEMS_TABLE_URL_RESOURCE = "itemsTable";
let ITEMS_TABLE_URL = buildUrlWithContextPath(ITEMS_TABLE_URL_RESOURCE);
const STORES_URL_RESOURCE = "stores";
let STORES_URL = buildUrlWithContextPath(STORES_URL_RESOURCE);

function refreshItemsTable(items) {
    $("#items-table-body").empty();

    // rebuild the table of items: scan all items and add them to the table of items
    $.each(items || [], function(index, item) {
        addElemToTable(item, ITEMS_TABLE_BODY_ID, ITEMS_TABLE_CELL_ID);
    });
}


function refreshStores(stores) {
    $("#stores-container").empty();

    // rebuild the stores accordion: scan all stores and add them to the accordion of stores
    $.each(stores || [], function(index, store) {

    });
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
    //The items table content is refreshed automatically every second
    // setInterval(ajaxItemsTable, refreshRate);
    ajaxItemsTable();

    //The stores content is refreshed automatically every second
    // setInterval(ajaxStores, refreshRate);
    ajaxStores();
});