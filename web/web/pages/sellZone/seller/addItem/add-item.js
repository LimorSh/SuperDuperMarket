const ADD_ITEM_CONTAINER_ID = "add-item-container";
const ADD_ITEM_FORM_CONTAINER_ID = "add-item-form-container";
const ADD_ITEM_FORM_ID = "add-item-form";
const ADD_ITEM_FORM_BUTTON_ID = "add-item";
const ADD_ITEM_MSG_LABEL_ID = "add-item-msg-label";
const ADD_ITEM_MSG_SUCCESS = "The item was added successfully!";
const ADD_ITEM_MSG_EMPTY_ITEMS = "Please choose at least one store and enter the item price.";
const NO_STORES_P_ID = "no-stores-p";
const NO_STORES_P_TEXT_CONTENT = "You don't have any stores yet.";
const ITEM_PURCHASE_CATEGORY_RADIO_BUTTON = "item-purchase-category-radio-button";
const CHOSEN_PURCHASE_CATEGORY_INPUT = "chosen-purchase-category-input";

const STORES_TABLE_CONTAINER_ID = "stores-table-container";
const STORES_TABLE_ID = "stores-table";
const STORES_TABLE_THEAD_ID = "stores-table-thead";
const STORES_TABLE_PRICE_COL_ID = "stores-table-price-col";
const STORES_TABLE_COL = "stores-table-col";
const STORES_TABLE_BODY_ID = "stores-table-body";
const STORES_TABLE_CELL_CLASS = "stores-table-cell";
const STORES_TABLE_PRICE_CELL_CLASS = "stores-table-price-cell";
const STORES_TABLE_PRICE_CELL_INPUT_CLASS = "stores-table-price-cell-input";

const GO_BACK_BUTTON_ID = "go-back-button";
const ADD_ITEM_BUTTONS_CLASS = "add-item-buttons";

let storesIdsAndPrices = {};


function setPriceCellContentToInput(row, priceCell, storeId) {
    priceCell.innerHTML = `<input id=${storeId} name="storeId-${storeId}" 
                                         class=${STORES_TABLE_PRICE_CELL_INPUT_CLASS} 
                                         type="number" min="0.1" step=".01"
                                         form=${ADD_ITEM_FORM_ID}>`;
}


function addPriceCellsInputs() {
    let storesTableBody = document.getElementById(STORES_TABLE_BODY_ID);
    for (let row of storesTableBody.rows) {
        let priceCell = row.insertCell();
        priceCell.classList.add(STORES_TABLE_CELL_CLASS);
        priceCell.classList.add(STORES_TABLE_PRICE_CELL_INPUT_CLASS);
        let storeId = row.cells[0].textContent;
        setPriceCellContentToInput(row, priceCell, storeId);
    }
}


function setStoresTableData(stores) {
    $.each(stores || [], function(index, store) {
        addElemToTable(store, STORES_TABLE_BODY_ID, STORES_TABLE_CELL_CLASS);
    });

    addPriceCellsInputs();
}

$(function() {
    $.ajax({
        url: OWNER_BASIC_STORES_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function () {
            console.error("Failed to submit");
            $(`#${ADD_ITEM_MSG_LABEL_ID}`).text("Failed to get result from server");
        },
        success: function (stores) {
            if (stores.length > 0) {
                let addItemFormContainer = document.getElementById(ADD_ITEM_FORM_CONTAINER_ID);
                addItemFormContainer.style.display = "block";
                setStoresTableData(stores) ;
            }
            else {
                let addItemContainer = document.getElementById(ADD_ITEM_CONTAINER_ID);
                let noStoresP = document.createElement("p");
                noStoresP.id = NO_STORES_P_ID;
                noStoresP.textContent = NO_STORES_P_TEXT_CONTENT;
                addItemContainer.appendChild(noStoresP);
            }
        }
    });
})


function setStoresIdsAndPrices() {
    let storesTablePriceCellsInputs = document.getElementsByClassName(STORES_TABLE_PRICE_CELL_INPUT_CLASS);
    for (let input of storesTablePriceCellsInputs) {
        let price = input.value;
        if (price) {
            let storeId = input.name.split("-")[1];
            storesIdsAndPrices[storeId] = price;
        }
    }
}


function purchaseCategoryWasChosen() {
    let purchaseCategoryRadioButtons = document.getElementsByClassName(ITEM_PURCHASE_CATEGORY_RADIO_BUTTON);
    for (let radio of purchaseCategoryRadioButtons) {
        if (radio.checked) {
            let chosenPurchaseCategoryInput = document.getElementById(CHOSEN_PURCHASE_CATEGORY_INPUT);
            chosenPurchaseCategoryInput.value = radio.value;
        }
    }
}


function getQueryParameters() {
    let paramArr = [];
    let addItemForm = document.getElementById(ADD_ITEM_FORM_ID);
    for (let i = 0; i < addItemForm.length; i++) {
        let inputName = addItemForm.elements[i].name;
        let inputValue = addItemForm.elements[i].value;
        if (inputName) {
            if (!inputName.startsWith("storeId")) {
                paramArr.push(inputName + "=" + inputValue);
            }
        }
    }
    paramArr.push("storesIdsAndPrices=" + encodeURIComponent(JSON.stringify(storesIdsAndPrices)));
    return paramArr.join("&");
}


function goBack() {
    window.history.back();
}


function finishedAddItem() {
    $(`#${ADD_ITEM_MSG_LABEL_ID}`).text(ADD_ITEM_MSG_SUCCESS);

    let addItemFormButton = document.getElementById(ADD_ITEM_FORM_BUTTON_ID);
    addItemFormButton.disabled = true;

    let backToSellZoneButton = document.createElement("button");
    backToSellZoneButton.id = GO_BACK_BUTTON_ID;
    backToSellZoneButton.textContent = "Back To Sell Zone";
    backToSellZoneButton.classList.add(ADD_ITEM_BUTTONS_CLASS);
    backToSellZoneButton.addEventListener("click", () => {
        goBack();
    })

    let addItemContainer = document.getElementById(ADD_ITEM_CONTAINER_ID);
    addItemContainer.appendChild(backToSellZoneButton);
}


$(function() {
    $(`#${ADD_ITEM_FORM_ID}`).submit(function() {

        setStoresIdsAndPrices();
        if (Object.keys(storesIdsAndPrices).length > 0) {
            let parameters = getQueryParameters();
            $.ajax({
                data: parameters,
                url: this.action,
                timeout: 2000,
                headers: {
                    'cache-control': 'no-store,no-cache',
                },
                error: function() {
                    console.error("Failed to submit");
                    $(`#${ADD_ITEM_MSG_LABEL_ID}`).text("Failed to get result from server");
                },
                success: function(r) {
                    if (r.length > 0) {
                        $(`#${ADD_ITEM_MSG_LABEL_ID}`).text(r);
                    }
                    else {
                        finishedAddItem();
                    }
                }
            });
        }
        else {
            $(`#${ADD_ITEM_MSG_LABEL_ID}`).text(ADD_ITEM_MSG_EMPTY_ITEMS);
        }

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})
