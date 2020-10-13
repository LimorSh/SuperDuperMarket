const X_LOCATION_INPUT_ID = "location-x";
const Y_LOCATION_INPUT_ID = "location-y";
const ORDER_CATEGORY_RADIO_BUTTON_CLASS = "order-category-radio-button";
const ORDER_CATEGORY_INPUT_ID = "order-category-input";
const STORES_SELECT_CONTAINER_ID = "stores-select-container";
const STORES_SELECT_ID = "stores-select";
const STORE_SELECT_DEFAULT_OPTION_ID = "stores-select-default-option";
const ORDER_CATEGORY_STATIC_STR = "static";
const STORE_DELIVERY_COST_LABEL_CONTAINER_ID = "store-delivery-cost-label-container";

const ITEMS_TABLE_CONTAINER_ID = "items-table-container";
const ITEMS_TABLE_ID = "items-table";
const ITEMS_TABLE_THEAD_ID = "items-table-thead";
const ITEMS_TABLE_PRICE_TH_ID = "items-table-price-th";
const ITEMS_TABLE_COL = "items-table-col";
const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_CLASS = "items-table-cell";
const ITEMS_TABLE_PRICE_CELL_CLASS = "items-table-price-cell";
const ITEMS_TABLE_QUANTITY_CELL_INPUT_ID = "items-table-quantity-cell-input";

const SET_STORE_DELIVERY_COST_URL_RESOURCE = "setStoreDeliveryCost";
let SET_STORE_DELIVERY_COST_URL = buildUrlWithContextPath(SET_STORE_DELIVERY_COST_URL_RESOURCE);

let stores = [];
let items = [];
let orderCategory = "";
let storesIds = [];
let xLocation;
let yLocation;


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
            "price": "",
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


function configLocationInputs() {
    let xLocationInput = document.getElementById(X_LOCATION_INPUT_ID);
    let yLocationInput = document.getElementById(Y_LOCATION_INPUT_ID);

    xLocationInput.onchange = function() {
        xLocation = xLocationInput.value;
    }
    yLocationInput.onchange = function() {
        yLocation = yLocationInput.value;
    }
}


function configOrderCategoryRadioButtons() {
    let storesSelectContainer = document.getElementById(STORES_SELECT_CONTAINER_ID);
    let radios = document.getElementsByClassName(ORDER_CATEGORY_RADIO_BUTTON_CLASS);
    let storeDeliveryCostLabelContainer = document.getElementById(STORE_DELIVERY_COST_LABEL_CONTAINER_ID);
    let itemsTableContainer = document.getElementById(ITEMS_TABLE_CONTAINER_ID);
    let itemsTable = document.getElementById(ITEMS_TABLE_ID);
    let itemTablePriceHeader = document.getElementById(ITEMS_TABLE_PRICE_TH_ID);
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            itemsTableContainer.style.display = "inline-block";

            // document.getElementById("login").disabled = false;
            orderCategory = radio.value;
            document.getElementById(ORDER_CATEGORY_INPUT_ID).value = orderCategory;
            if (radio.value === ORDER_CATEGORY_STATIC_STR) {
                storesSelectContainer.style.display = "inline-block";
                storeDeliveryCostLabelContainer.style.display = "inline-block";
                itemTablePriceHeader.style.display = "inline-block";
                $(".items-table-price-cell").show();
            }
            else {
                storeDeliveryCostLabelContainer.style.display = "none";
                storesSelectContainer.style.display = "none";
                itemTablePriceHeader.style.display = "none";
                $(".items-table-price-cell").hide();
            }
        }
    }
}


function addStoresToStoresSelect() {
    let storesSelect = document.getElementById(STORES_SELECT_ID);

    let storesValues = [];
    let storeValues = "";
    let currStore;
    let currStoreId;
    for (let j = 0; j < stores.length; j++) {
        currStore = stores[j];
        currStoreId = currStore["id"];
        storeValues = `${currStoreId} | ${currStore["name"]} | (${currStore["xLocation"]},${currStore["yLocation"]})`;
        storesValues[j] = (storeValues);
        storesIds[j] = currStoreId;
    }

    let option;
    for (const val of storesValues) {
        option = document.createElement("option");
        option.value = val;
        option.text = val;
        storesSelect.appendChild(option);
    }
}


function ajaxGetStoreDeliveryCost(storeId) {
    let parameters = {
        "storeId": storeId,
        "location-x": xLocation,
        "location-y": yLocation,
    };

    $.ajax({
        data: parameters,
        url: SET_STORE_DELIVERY_COST_URL,
        timeout: 2000,
        error: function() {
            console.error("Failed to submit");
            $("#error-msg").text("Failed to get result from server");
        },
        success: function(storeDeliveryCost) {
            $("#store-delivery-cost-label").text("Delivery Cost: " + storeDeliveryCost);
            $("#store-delivery-cost-label-container").visibility = "visible";
        }
    });
}


function addPriceColumnToItemsTable(index) {
    let itemsTableBody = document.getElementById(ITEMS_TABLE_BODY_ID);
    let priceCell;

    let prices = {};
    let store = stores[index];
    let items = store["storeItemsDto"];
    let id;
    let price;
    for (let item of items) {
        id = item["id"];
        price = item["price"];
        prices[id] = price;
    }

    let cellContent;
    let itemId;
    for (let row of itemsTableBody.rows) {
        cellContent = "Not Available";
        itemId = row.cells[0].textContent;
        Object.keys(prices).forEach(function(key) {
            if (itemId === key) {
                cellContent = prices[key];
            }
        });

        priceCell = row.cells[row.cells.length-2];
        if (!priceCell.classList.contains(ITEMS_TABLE_PRICE_CELL_CLASS)) {
            priceCell.classList.add(ITEMS_TABLE_PRICE_CELL_CLASS);
        }
        priceCell.textContent = cellContent;
    }
}


function storeWasChosenForStaticOrder() {
    let index;
    let storeId;

    document.getElementById(STORE_SELECT_DEFAULT_OPTION_ID).disabled = true;

    index = this.selectedIndex - 1;
    for (let i = 0; i < storesIds.length; i++) {
        if (i === index) {
            storeId = storesIds[i];
            break;
        }
    }
    ajaxGetStoreDeliveryCost(storeId);
    addPriceColumnToItemsTable(index);
}


function configStoresSelectForStaticOrder() {
    let storesSelect = document.getElementById(STORES_SELECT_ID);
    storesSelect.addEventListener("change", storeWasChosenForStaticOrder);
}


function setItemsTableData() {
    $.each(items || [], function(index, item) {
        addElemToTable(item, ITEMS_TABLE_BODY_ID, ITEMS_TABLE_CELL_CLASS);
    });

    let itemsTableBody = document.getElementById(ITEMS_TABLE_BODY_ID);
    for (let row of itemsTableBody.rows) {
        let quantityCell = row.insertCell();
        quantityCell.classList.add(ITEMS_TABLE_CELL_CLASS);
        quantityCell.innerHTML = `<input id=${ITEMS_TABLE_QUANTITY_CELL_INPUT_ID} type="number" min="0.1" step=".01">`;
        let purchaseCategoryCell = row.cells[row.cells.length-3].textContent;
        if (purchaseCategoryCell === "quantity") {
            let input = document.getElementById(ITEMS_TABLE_QUANTITY_CELL_INPUT_ID);
            input.removeAttribute("step");
            input.setAttribute("min", "1");
        }
    }
}


$(function() {
    $.when(ajaxSetStores(), ajaxItemsTable()).then(function() {
        configLocationInputs();
        addStoresToStoresSelect();
        configStoresSelectForStaticOrder();
        configOrderCategoryRadioButtons();
        setItemsTableData();
    });

    $.when(ajaxAddOrder()).then(function() {
    });
});