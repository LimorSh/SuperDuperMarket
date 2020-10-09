const ITEMS_TABLE_URL_RESOURCE = "itemsTable";
let ITEMS_TABLE_URL = buildUrlWithContextPath(ITEMS_TABLE_URL_RESOURCE);



function addElemToTable(elem, tableBodyId, tableCellId) {
    let tableBody = document.getElementById(tableBodyId);
    let row = tableBody.insertRow();
    Object.keys(elem).forEach(function(key) {
        let cell = row.insertCell();
        cell.classList.add(tableCellId);
        cell.textContent = elem[key];
    })
}


function refreshItemsTable(transactions) {
    $("#items-table-body").empty();

    // rebuild the table of sell zones: scan all zones and add them to the table of sell zones
    // $.each(transactions || [], function(index, transaction) {
    //     addElemToTable(transaction, ACCOUNT_TABLE_BODY_ID, ACCOUNT_TABLE_CELL_ID);
    // });
}


function ajaxItemsTable() {
    $.ajax({
        url: ITEMS_TABLE_URL,
        // success: function(users) {
        //     refreshUsersList(users);
        // }
        success: function(r) {
            console.log(r);
        }
    });
}


$(function() {
    //The items table content is refreshed automatically every second
    // setInterval(ajaxItemsTable, refreshRate);
    ajaxItemsTable();
});