const ORDERS_CONTAINER_ID = "orders-container";
const STORES_ORDERS_CONTAINER_ID = "stores-orders-container";
const STORE_ORDERS_CONTAINER_ID = "store-orders-container";
const STORES_SELECT_CONTAINER_ID = "stores-select-container";
const STORES_SELECT_ID = "stores-select";
const STORE_SELECT_DEFAULT_OPTION_ID = "stores-select-default-option";

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


function showOrders(orders) {
    console.log(orders);
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
        success: function(orders) {
            if (orders.length > 0) {
                showOrders(orders);
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