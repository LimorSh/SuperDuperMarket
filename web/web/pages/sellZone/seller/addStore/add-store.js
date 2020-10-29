const ADD_STORE_CONTAINER_ID = "add-store-container";
const ADD_STORE_FORM_ID = "add-store-form";
const ADD_STORE_FORM_BUTTON_ID = "add-store";
const ADD_STORE_MSG_LABEL_ID = "add-store-msg-label";
const ADD_STORE_MSG_SUCCESS = "The store was added successfully!";
const ADD_STORE_MSG_EMPTY_ITEMS = "Please choose at least one item and enter its price.";

const ITEMS_TABLE_CONTAINER_ID = "items-table-container";
const ITEMS_TABLE_ID = "items-table";
const ITEMS_TABLE_THEAD_ID = "items-table-thead";
const ITEMS_TABLE_PRICE_COL_ID = "items-table-price-col";
const ITEMS_TABLE_COL = "items-table-col";
const ITEMS_TABLE_BODY_ID = "items-table-body";
const ITEMS_TABLE_CELL_CLASS = "items-table-cell";
const ITEMS_TABLE_PRICE_CELL_CLASS = "items-table-price-cell";
const ITEMS_TABLE_PRICE_CELL_INPUT_CLASS = "items-table-price-cell-input";

const GO_BACK_BUTTON_ID = "go-back-button";
const ADD_STORE_BUTTONS_CLASS = "add-store-buttons";

let itemsIdsAndPrices = {};


function setPriceCellContentToInput(row, priceCell, itemId) {
    priceCell.innerHTML = `<input id=${itemId} name="itemId-${itemId}" 
                                         class=${ITEMS_TABLE_PRICE_CELL_INPUT_CLASS} 
                                         type="number" min="0.1" step=".01"
                                         form=${ADD_STORE_FORM_ID}>`;
}


function addPriceCellsInputs() {
    let itemsTableBody = document.getElementById(ITEMS_TABLE_BODY_ID);
    for (let row of itemsTableBody.rows) {
        let priceCell = row.insertCell();
        priceCell.classList.add(ITEMS_TABLE_CELL_CLASS);
        priceCell.classList.add(ITEMS_TABLE_PRICE_CELL_INPUT_CLASS);
        let itemId = row.cells[0].textContent;
        setPriceCellContentToInput(row, priceCell, itemId);
    }
}


function setItemsTableData(items) {
    $.each(items || [], function(index, item) {
        addElemToTable(item, ITEMS_TABLE_BODY_ID, ITEMS_TABLE_CELL_CLASS);
    });

    addPriceCellsInputs();
}

$(function() {
     $.ajax({
            url: BASIC_ITEMS_URL,
            timeout: 2000,
            headers: {
                'cache-control': 'no-store,no-cache',
            },
            error: function () {
                console.error("Failed to submit");
                $(`#${ADD_STORE_MSG_LABEL_ID}`).text("Failed to get result from server");
            },
            success: function (items) {
                setItemsTableData(items);
            }
     });
})


function setItemsIdsAndPrices() {
    let itemsTablePriceCellsInputs = document.getElementsByClassName(ITEMS_TABLE_PRICE_CELL_INPUT_CLASS);
    for (let input of itemsTablePriceCellsInputs) {
        let price = input.value;
        if (price) {
            let itemId = input.name.split("-")[1];
            itemsIdsAndPrices[itemId] = price;
        }
    }
}


function getQueryParameters() {
    let paramArr = [];
    let addStoreForm = document.getElementById(ADD_STORE_FORM_ID);
    for (let i = 0; i < addStoreForm.length; i++) {
        let inputName = addStoreForm.elements[i].name;
        let inputValue = addStoreForm.elements[i].value;
        if (inputName) {
            if (!inputName.startsWith("itemId")) {
                paramArr.push(inputName + "=" + inputValue);
            }
        }
    }
    paramArr.push("itemsIdsAndPrices=" + encodeURIComponent(JSON.stringify(itemsIdsAndPrices)));
    return paramArr.join("&");
}


function goBack() {
    window.history.back();
}


function finishedAddStore() {
    $(`#${ADD_STORE_MSG_LABEL_ID}`).text(ADD_STORE_MSG_SUCCESS).removeClass("error");;

    let addStoreFormButton = document.getElementById(ADD_STORE_FORM_BUTTON_ID);
    addStoreFormButton.disabled = true;

    let backToSellZoneButton = document.createElement("button");
    backToSellZoneButton.id = GO_BACK_BUTTON_ID;
    backToSellZoneButton.textContent = "Back To Sell Zone";
    backToSellZoneButton.classList.add(ADD_STORE_BUTTONS_CLASS);
    backToSellZoneButton.addEventListener("click", () => {
        goBack();
    })

    let addStoreContainer = document.getElementById(ADD_STORE_CONTAINER_ID);
    addStoreContainer.appendChild(backToSellZoneButton);
}


$(function() {
    $(`#${ADD_STORE_FORM_ID}`).submit(function() {

        setItemsIdsAndPrices();
        if (Object.keys(itemsIdsAndPrices).length > 0) {
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
                    $(`#${ADD_STORE_MSG_LABEL_ID}`).text("Failed to get result from server");
                },
                success: function(r) {
                    if (r.length > 0) {
                        $(`#${ADD_STORE_MSG_LABEL_ID}`).text(r).addClass("error");
                    }
                    else {
                        finishedAddStore();
                    }
                }
            });
        }
        else {
            $(`#${ADD_STORE_MSG_LABEL_ID}`).text(ADD_STORE_MSG_EMPTY_ITEMS).addClass("error");;
        }

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})
