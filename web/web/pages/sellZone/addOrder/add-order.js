const ORDER_CATEGORY_RADIO_BUTTON_CLASS = "order-category-radio-button";
const ORDER_CATEGORY_INPUT_ID = "order-category-input";
const STORES_SELECT_CONTAINER_ID = "stores-select-container";
const STORES_SELECT_ID = "stores-select";
const ORDER_CATEGORY_STATIC_STR = "static";


let stores;
let storeItems;
let orderCategory;
let storesIds = [];

const STORES_URL_RESOURCE = "stores";
let STORES_URL = buildUrlWithContextPath(STORES_URL_RESOURCE);


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


function ajaxStoreItemsTable() {

}


function configStoresSelectForStaticOrder() {
    let storesSelect = document.getElementById(STORES_SELECT_ID);
    storesSelect.addEventListener('change', storeWasChosenForStaticOrder);
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
        console.log(storeId);
        ajaxStoreItemsTable();
    }
}


function configOrderCategoryRadioButtons() {
    console.log(stores);

    let radios = document.getElementsByClassName(ORDER_CATEGORY_RADIO_BUTTON_CLASS);
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            // document.getElementById("login").disabled = false;
            orderCategory = radio.value;
            document.getElementById(ORDER_CATEGORY_INPUT_ID).value = orderCategory;
            let storesSelect = document.getElementById(STORES_SELECT_CONTAINER_ID);
            if (radio.value === ORDER_CATEGORY_STATIC_STR) {
                storesSelect.style.visibility = "visible";
            }
            else {
                storesSelect.style.visibility = "hidden";
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


$(function() {
    $.when(ajaxSetStores()).then(function( data, textStatus, jqXHR ) {
        // alert( jqXHR.status ); // Alerts 200
        configOrderCategoryRadioButtons();
        addStoresToStoresSelect();
        configStoresSelectForStaticOrder();
    });
    // ajaxSetStores();

    $.when(ajaxAddOrder()).then(function( data, textStatus, jqXHR ) {
        // alert( jqXHR.status ); // Alerts 200
    });
    // ajaxAddOrder();
});