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
const ITEMS_TABLE_QUANTITY_CELL_CLASS = "items-table-quantity-cell";
const ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS = "items-table-quantity-cell-input";

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
    $("#add-order-form").submit(function() {

            // let isAllQuantitiesInputAreEmpty = true;
            // let itemsTableQuantityCellsInputs = document.getElementsByClassName(ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS);
            // for (let input of itemsTableQuantityCellsInputs) {
            //     if (!input.value.isEmptyObject()) {
            //         isAllQuantitiesInputAreEmpty = false;
            //     }
            // }
            // if (isAllQuantitiesInputAreEmpty) {
            //     let addOrderMsgLabel = document.getElementById("add-order-msg-label");
            //     addOrderMsgLabel.text = "To add order ....";
            //     console.log(addOrderMsgLabel);
            // }
            // else {
            //     let itemsTableQuantityCellsInputs = document.getElementsByClassName(ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS);

                // itemsTableQuantityCellsInputs.appendTo("#add-order-form");

                let parameters = $(this).serialize();

                // let itemsQuantities = {};
                // // for (let input of itemsTableQuantityCellsInputs) {
                // let rows = document.getElementById(ITEMS_TABLE_ID).rows;
                // for (let i = 0; i < itemsTableQuantityCellsInputs.length; i++) {
                //     let row = rows[i];
                //     let input = itemsTableQuantityCellsInputs[i];
                //     let itemQuantity = input.value;
                //     if (itemQuantity) {
                //         let itemId = row.cells[row.cells.length -5];
                //         itemsQuantities[itemId] = itemQuantity;
                //     }
                // }
                // let str = "&limor=panther";
                // parameters.concat(str);
                // = itemsQuantities;
                console.log(parameters);

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
            // }

            // return value of the submit operation
            // by default - we'll always return false so it doesn't redirect the user.
            return false;
        });
    // }
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
    let itemTableQuantityCells = document.getElementsByClassName(ITEMS_TABLE_QUANTITY_CELL_CLASS);
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
                for (let cell of itemTableQuantityCells) {
                    if (cell.innerHTML === "") {
                        let rowIndex = cell.closest('tr').rowIndex;
                        let row = itemsTable.rows[rowIndex];
                        let itemId = row.cells[0].textContent;
                        setQuantityCellContentToInput(row, cell, itemId);
                    }
                }
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


function setQuantityCellContentToInput(row, quantityCell, itemId) {
    quantityCell.innerHTML = `<input id=${itemId} 
                                         class=${ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS} 
                                         type="number" min="0.1" step=".01"
                                         form="add-order-form">`;
    let purchaseCategory = row.cells[row.cells.length-3].textContent;
    if (purchaseCategory === "quantity") {
        let input = document.getElementById(itemId);
        input.removeAttribute("step");
        input.setAttribute("min", "1");
    }
}


function addCellsToPriceColumn(prices) {
    const notAvailableStr = "Not Available";
    let itemsTableBody = document.getElementById(ITEMS_TABLE_BODY_ID);
    let priceCell;
    let cellContent;
    let itemId;
    for (let row of itemsTableBody.rows) {
        cellContent = notAvailableStr;
        itemId = row.cells[0].textContent;
        // if (isStrInJsonArrayKeys(prices, itemId)) {
        //     cellContent = prices[itemId];
        // }
        // else {
        //     let quantityCell = row.cells[row.cells.length-1];
        //     quantityCell.innerHTML = "";
        // }
        let quantityCell = row.cells[row.cells.length-1];
        let isItemInStore = false;
        Object.keys(prices).forEach(function(key) {
            if (itemId === key) {
                cellContent = prices[key];
                isItemInStore = true;
                setQuantityCellContentToInput(row, quantityCell, itemId);
            }
        });
        if (!isItemInStore) {
            quantityCell.innerHTML = "";
        }

        priceCell = row.cells[row.cells.length-2];
        if (!priceCell.classList.contains(ITEMS_TABLE_PRICE_CELL_CLASS)) {
            priceCell.classList.add(ITEMS_TABLE_PRICE_CELL_CLASS);
        }
        priceCell.textContent = cellContent;
    }
}


function addPriceColumnToItemsTable(index) {
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

    addCellsToPriceColumn(prices);
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


function addQuantityCellsInputs() {
    let itemsTableBody = document.getElementById(ITEMS_TABLE_BODY_ID);
    for (let row of itemsTableBody.rows) {
        let quantityCell = row.insertCell();
        quantityCell.classList.add(ITEMS_TABLE_CELL_CLASS);
        quantityCell.classList.add(ITEMS_TABLE_QUANTITY_CELL_CLASS);
        let itemId = row.cells[0].textContent;
        setQuantityCellContentToInput(row, quantityCell, itemId);
    }
}


function setItemsTableData() {
    $.each(items || [], function(index, item) {
        addElemToTable(item, ITEMS_TABLE_BODY_ID, ITEMS_TABLE_CELL_CLASS);
    });

    addQuantityCellsInputs();
}


$(function() {
    $.when(ajaxSetStores(), ajaxItemsTable()).then(function() {
        configLocationInputs();
        addStoresToStoresSelect();
        setItemsTableData();
        configStoresSelectForStaticOrder();
        configOrderCategoryRadioButtons();
    });

    ajaxAddOrder();

    // $.when(ajaxAddOrder()).then(function() {
    // });
});