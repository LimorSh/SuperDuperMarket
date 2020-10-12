const ORDER_CATEGORY_RADIO_BUTTON_CLASS = "order-category-radio-button";
const ORDER_CATEGORY_INPUT_ID = "order-category-input";
const STORES_SELECT_CONTAINER_ID = "stores-select-container";
const STORES_SELECT_ID = "stores-select";
const ORDER_CATEGORY_STATIC_STR = "static";
const ITEMS_TABLE_CONTAINER_ID = "items-table-container";
const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_ID = "items-table-cell";

let stores = [];
let items = [];
let orderCategory = "";
let storesIds = [];


function ajaxSetStores() {
    return $.ajax({
        url: STORES_URL,
        timeout: 2000,
        error: function() {
            console.error("Failed to submit");
            $("#error-msg").text("Failed to get result from server");
        },
        success: function(allStores) {
            stores = allStores;
        }
    });
}


function setItemsArray(allItems) {
    let id;
    let name;
    let purchaseCategory;
    let newItem = {};
    $.each(allItems || [], function(index, item) {
        id = item["id"];
        name = item["name"];
        purchaseCategory = item["purchaseCategory"];
        newItem = {
            "id": id,
            "name": name,
            "purchaseCategory": purchaseCategory,
        };
        items.push(newItem);
    });
}


function ajaxItemsTable() {
    return $.ajax({
        url: ITEMS_TABLE_URL,
        timeout: 2000,
        error: function() {
            console.error("Failed to submit");
            $("#error-msg").text("Failed to get result from server");
        },
        success: function(allItems) {
            setItemsArray(allItems);
        }
    });
}


function ajaxAddOrder() {
    return $("#add-order-form").submit(function() {
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


function ajaxStoreItemsTable() {

}


function configStoresSelectForStaticOrder() {
    let storesSelect = document.getElementById(STORES_SELECT_ID);
    storesSelect.addEventListener("change", storeWasChosenForStaticOrder);
}


function storeWasChosenForStaticOrder() {
    let index;
    if (this.selectedIndex) {
        index = this.selectedIndex;

        let storeId;
        for (let i = 0; i < storesIds.length; i++) {
            if (i === index) {
                storeId = storesIds[i];
                break;
            }
        }
        ajaxStoreItemsTable();
    }
}


function configOrderCategoryRadioButtons() {
    let radios = document.getElementsByClassName(ORDER_CATEGORY_RADIO_BUTTON_CLASS);
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            let itemsTableContainer = document.getElementById(ITEMS_TABLE_CONTAINER_ID);
            itemsTableContainer.style.visibility = "visible";

            // document.getElementById("login").disabled = false;
            orderCategory = radio.value;
            document.getElementById(ORDER_CATEGORY_INPUT_ID).value = orderCategory;
            let storesSelectContainer = document.getElementById(STORES_SELECT_CONTAINER_ID);
            if (radio.value === ORDER_CATEGORY_STATIC_STR) {
                storesSelectContainer.style.visibility = "visible";
            }
            else {
                storesSelectContainer.style.visibility = "hidden";
            }
        }
    }
}


function addStoresToStoresSelect() {
    let storesSelect = document.getElementById(STORES_SELECT_ID);

    let storesValues = [];
    let storeValues = "";
    for (let j = 0; j < stores.length; j++) {
        let store = stores[j];
        let storeId = store["id"];
        storeValues = `${storeId} | ${store["name"]} | (${store["xLocation"]},${store["yLocation"]})`;
        storesValues[j] = (storeValues);
        storesIds[j] = storeId;
    }

    for (const val of storesValues) {
        let option = document.createElement("option");
        option.value = val;
        option.text = val;
        storesSelect.appendChild(option);
    }
}


function setItemsTableData() {
    $.each(items || [], function(index, item) {
        addElemToTable(item, ITEMS_TABLE_BODY_ID, ITEMS_TABLE_CELL_ID);
    });
}


$(function() {
    $.when(ajaxSetStores(), ajaxItemsTable()).then(function() {
        configOrderCategoryRadioButtons();
        addStoresToStoresSelect();
        configStoresSelectForStaticOrder();
        setItemsTableData();
    });

    $.when(ajaxAddOrder()).then(function() {
    });
});