const DATE_PICKER_INPUT_ID = "date";
const X_LOCATION_INPUT_ID = "location-x";
const Y_LOCATION_INPUT_ID = "location-y";
const ORDER_CATEGORY_RADIO_BUTTON_CLASS = "order-category-radio-button";
const CHOSEN_ORDER_CATEGORY_INPUT_ID = "chosen-order-category-input";
const CHOSEN_STORE_INPUT_ID = "chosen-store-input";
const DYNAMIC_ORDER_STORE_INPUT_ID = -1;
const STORES_SELECT_CONTAINER_ID = "stores-select-container";
const STORES_SELECT_ID = "stores-select";
const STORE_SELECT_DEFAULT_OPTION_ID = "stores-select-default-option";
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

const ORDER_SUMMERY_DATE_VALUE_LABEL_ID = "order-summery-date-value-label";
const ORDER_SUMMERY_LOCATION_VALUE_LABEL_ID = "order-summery-location-value-label";
const ORDER_SUMMERY_ORDER_CATEGORY_VALUE_LABEL_ID = "order-summery-order-category-value-label";
const ORDER_SUMMERY_TOTAL_ITEMS_COST_VALUE_LABEL_ID = "order-summery-total-items-cost-value-label";
const ORDER_SUMMERY_TOTAL_DELIVERY_COST_VALUE_LABEL_ID = "order-summery-total-delivery-cost-value-label";
const ORDER_SUMMERY_TOTAL_ORDER_COST_VALUE_LABEL_ID = "order-summery-total-order-cost-value-label";

const FINISH_ORDER_BUTTON_ID = "finish-order-button";
const FINISH_ORDER_MSG_LABEL_ID = "finish-order-msg-label";
const FINISH_ORDER_TAKEN_LOCATION_MSG = "The order location is a store location, please choose a different location.";
const FINISH_ORDER_EMPTY_QUANTITIES_MSG = "Your cart is empty, please choose at least one item and fill its quantity.";
const DYNAMIC_ORDER_STORES_DETAILS_CONTAINER_ID = "dynamic-order-stores-details-container";
const DYNAMIC_ORDER_STORES_DETAILS_LIST_ID = "dynamic-order-stores-details-list";

const ORDER_DISCOUNTS_CONTAINER_ID = "order-discounts-container";
const DISCOUNT_CONTAINER_CLASS = "discount-container";
const DISCOUNT_HEADER_CLASS = "discount-header";
const DISCOUNT_FIELD_LABEL_CLASS = "discount-field-label";
const DISCOUNT_VALUE_LABEL_CLASS = "discount-value-label";
const APPLY_DISCOUNT_BUTTON_CLASS = "apply-discount-button";
const DISCOUNT_SINGLE_OFFER_CLASS = "discount-single-offer";
const DISCOUNT_SINGLE_OFFER_RADIO_BUTTON_CLASS = "discount-single-offer-radio-button";
const DISCOUNT_APPLIES_AMOUNT_CLASS = "discount-applies-amount-label"

const ORDER_SUMMERY_CONTAINER_ID = "order-summery-container";
const ORDER_SUMMERY_STORES_INFO_CONTAINER_ID = "order-summery-stores-info-container";
const ORDER_SUMMERY_STORES_INFO_UL_ID = "order-summery-stores-info-ul";
const ORDER_SUMMERY_STORE_HEADER_CLASS = "order-summery-store-header";
const ORDER_SUMMERY_STORE_FIELD_LABEL_CLASS = "order-summery-store-field-label";
const ORDER_SUMMERY_STORE_VALUE_LABEL_CLASS= "order-summery-store-value-label";

const PURCHASED_STORE_ITEMS_TABLE_CONTAINER_ID =  "purchased-store-items-table-container";
const PURCHASED_STORE_ITEMS_TABLE_ID =  "purchased-store-items-table";
const PURCHASED_STORE_ITEMS_TABLE_CAPTION_ID =  "purchased-store-items-table-caption";
const PURCHASED_STORE_ITEMS_TABLE_BODY_ID =  "purchased-store-items-table-body";
const PURCHASED_STORE_ITEMS_TABLE_HEADERS = ["ID", "Name", "Purchase Category", "Quantity", "Price Per Unit", "Total Cost", "Discount"];
const PURCHASED_STORE_ITEMS_TABLE_COL = "purchased-store-items-table-col";
const PURCHASED_STORE_ITEMS_TABLE_CELL_CLASS = "purchased-store-items-table-cell";
const PURCHASED_STORE_ITEMS_TABLE_NUMBER_OF_COLUMNS = 7;
const ITEM_PURCHASE_NOT_FROM_DISCOUNT_STR = "NO";
const ITEM_PURCHASE_FROM_DISCOUNT_STR = "YES";

const ADD_ORDER_FORM_ID = "add-order-form";
const FINAL_ORDER_BUTTONS_CONTAINER_ID = "final-order-buttons-container";
const CONFIRM_ORDER_BUTTON_ID = "confirm-order-button";
const CANCEL_ORDER_BUTTON_ID = "cancel-order-button";

const ORDER_FEEDBACK_CONTAINER_ID = "order-feedback-container";
const STORE_RATE_CONTAINER_CLASS = "store-rate-container";
const STORE_RATE_HEADER_CLASS = "store-rate-header";
const STORE_RATE_FIELD_CLASS = "store-rate-field";
const STORE_RATE_INPUT_CLASS = "store-rate-input";
const STORE_RATE_INPUT_NOTE_CLASS = "store-rate-input-note";
const SEND_STORE_RATE_BUTTON_CLASS = "store-rate-button";


const SET_STORE_DELIVERY_COST_URL_RESOURCE = "setStoreDeliveryCost";
let SET_STORE_DELIVERY_COST_URL = buildUrlWithContextPath(SET_STORE_DELIVERY_COST_URL_RESOURCE);
const SET_DISTANCE_FROM_STORE_URL_RESOURCE = "setDistanceFromStore";
let SET_DISTANCE_FROM_STORE_URL = buildUrlWithContextPath(SET_DISTANCE_FROM_STORE_URL_RESOURCE);
const SET_STORE_DYNAMIC_ORDER_STORES_DETAILS_RESOURCE = "setDynamicOrderStoresDetails";
let SET_STORE_DYNAMIC_ORDER_STORES_DETAILS_URL = buildUrlWithContextPath(SET_STORE_DYNAMIC_ORDER_STORES_DETAILS_RESOURCE);
const SET_DISCOUNTS_RESOURCE = "setDiscounts";
let SET_DISCOUNT_URL = buildUrlWithContextPath(SET_DISCOUNTS_RESOURCE);

let stores = [];
let items = [];
let storesIds = [];
let date;
let xLocation;
let yLocation;
let orderCategory;
let storeId;
let distanceFromStore;
let itemsCost = 0;
let deliveryCost = 0;
let totalDeliveryCost = 0;
let itemsIdsAndQuantities = {};
let dynamicOrderStoresDetails = {};
let discounts = {};
let tempDiscounts;
let numberOfDiscountsRemained;
let appliedOffers = {};
let itemsIdsAndQuantitiesAfterAppliedDiscounts = {};
let appliedDiscountsNamesAndAppliesAmount = {};
let storesIdsAndAppliedOffers = {};
let appliedOfferRowIndex = 0;
let storesAndRates = {};


function ajaxSetStores() {
    return $.ajax({
        url: STORES_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
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
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function() {
            console.error("Failed to submit");
            $("#error-msg").text("Failed to get result from server");
        },
        success: function(allItems) {
            setItemsArray(allItems);
        }
    });
}


function getAddOrderFormInputsAsQueryParameters() {
    let paramArr = [];
    let addOrderForm = document.getElementById(ADD_ORDER_FORM_ID);
    for (let i = 0; i < addOrderForm.length; i++) {
        let inputName = addOrderForm.elements[i].name;
        let inputValue = addOrderForm.elements[i].value;
        if (inputName) {
            if (!inputName.startsWith("itemId")) {
                paramArr.push(inputName + "=" + inputValue);
            }
        }
    }
    paramArr.push("itemsIdsAndQuantities=" + encodeURIComponent(JSON.stringify(itemsIdsAndQuantities)));
    let appliedOffersJson={};
    Object.keys(appliedOffers).forEach(function(discountName) {
        let appliedOffersStrArr=[];
        let offers = appliedOffers[discountName];
        for (let offer of offers) {
            appliedOffersStrArr.push(offer["storeItemId"]);
        }
        appliedOffersJson[discountName] = appliedOffersStrArr.join(" ");
    });
    paramArr.push("appliedOffers=" + encodeURIComponent(JSON.stringify(appliedOffersJson)));
    return paramArr.join("&");
}


function setItemsIdsAndQuantities() {
    let itemsTableQuantityCellsInputs = document.getElementsByClassName(ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS);
    for (let input of itemsTableQuantityCellsInputs) {
        let quantity = input.value;
        if (quantity) {
            let itemId = input.name.split("-")[1];
            itemsIdsAndQuantities[itemId] = quantity;
        }
    }
}


function isAllQuantitiesInputAreEmpty() {
    let itemsTableQuantityCellsInputs = document.getElementsByClassName(ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS);
    for (let input of itemsTableQuantityCellsInputs) {
        let quantity = input.value;
        if (quantity) {
            return false;
        }
    }
    return true;
}


function isLocationAlreadyExistsForStore() {
    let store;
    let storeXLocation;
    let storeYLocation;
    for (let j = 0; j < stores.length; j++) {
        store = stores[j];
        storeXLocation = store["xLocation"];
        storeYLocation = store["yLocation"];
        if (storeXLocation === parseInt(xLocation) && storeYLocation === parseInt(yLocation)) {
            return true
        }
    }
    return false;
}


function setDiscountsAndStoreItemQuantities() {
    for (let discount of discounts) {
        let discountStoreItemId = discount["storeItemId"];
        itemsIdsAndQuantitiesAfterAppliedDiscounts[discountStoreItemId] =
            parseInt(itemsIdsAndQuantities[discountStoreItemId]);
    }
}


function initAppliedDiscountsNamesAndAppliesAmount() {
    for (let discount of discounts) {
        let discountName = discount["name"];
        appliedDiscountsNamesAndAppliesAmount[discountName] = 0;
    }
}


function ajaxGetDiscounts() {
    let parameters = getAddOrderFormInputsAsQueryParameters();
    // parameters["chosen-order-category"] = orderCategory;
    // parameters["itemsIdsAndQuantities"] = itemsIdsAndQuantities;
    // if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
    //     parameters[ "chosen-store"] = storeId;
    // }

    $.ajax({
        data: parameters,
        url: SET_DISCOUNT_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function(e) {
            console.error(e);
        },
        success: function(relevantDiscounts) {
            if (relevantDiscounts.length > 0) {
                discounts = relevantDiscounts;
                tempDiscounts = [...relevantDiscounts];     // clone discounts
                numberOfDiscountsRemained = relevantDiscounts.length;
                setDiscountsAndStoreItemQuantities();
                initAppliedDiscountsNamesAndAppliesAmount();
                showDiscounts();
            }
            else {
                showOrderSummery();
            }
        }
    });
}


function ajaxGetDynamicOrderStoresDetails() {
    let parameters = getAddOrderFormInputsAsQueryParameters();

    $.ajax({
        data: parameters,
        url: SET_STORE_DYNAMIC_ORDER_STORES_DETAILS_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function(e) {
            console.error(e);
        },
        success: function(storesDetails) {
            console.log(storesDetails);
            dynamicOrderStoresDetails = storesDetails;
            showStoresDetailsForDynamicOrder();
            ajaxGetDiscounts();
        }
    });
}


function showStoresDetailsForDynamicOrder() {
    $(`#${DYNAMIC_ORDER_STORES_DETAILS_CONTAINER_ID}`).show();
    $.each(dynamicOrderStoresDetails || [], function(index, storeDetails) {
        let storeDetailsStr = `ID: ${storeDetails.id}, Name: ${storeDetails.name}, 
                                Location: (${storeDetails["xLocation"]},${storeDetails["yLocation"]}), 
                                Distance (KM): ${storeDetails["distance"]}, 
                                PPK: ${storeDetails["ppk"]},
                                Delivery Cost: ${storeDetails["deliveryCost"]},
                                Number of Different Items type: ${storeDetails["differentItemsType"]},
                                Items Cost: ${storeDetails["itemsCost"]}
                                `;
        $('<li>' + storeDetailsStr + '</li>').appendTo($(`#${DYNAMIC_ORDER_STORES_DETAILS_LIST_ID}`));
    });
}


function getOfferDataAsString(offer) {
    let offerStoreItemId = offer["storeItemId"];
    let offerStoreItemName = offer["storeItemName"];
    let offerStoreItemPurchaseCategory = offer["storeItemPurchaseCategory"];
    let offerQuantity = offer["quantity"];
    let offerAdditionalPrice = offer["additionalPrice"];
    return `ID: ${offerStoreItemId}${TAB}Name: ${offerStoreItemName}${TAB}
                                      Purchase Category: ${offerStoreItemPurchaseCategory}${TAB}
                                      Quantity: ${offerQuantity}${TAB}Additional Price: ${offerAdditionalPrice}`;
}


function addDiscountOfferForCategoryIrrelevantAndAllOrNothing(discountContainer, offer) {
    let offerP = document.createElement("p");
    offerP.classList.add(DISCOUNT_SINGLE_OFFER_CLASS);

    offerP.innerHTML = getOfferDataAsString(offer);
    discountContainer.appendChild(offerP);
}


function getAppliedOfferOneOfDiscount(discountOffers) {
    let appliedOfferOneOfDiscount = [];
    let offersOneOfDiscountRadioButtons = document.getElementsByClassName(DISCOUNT_SINGLE_OFFER_RADIO_BUTTON_CLASS);
    for (const radio of offersOneOfDiscountRadioButtons) {
        if (radio.checked) {
            let storeItemId = parseInt(radio.value);
            for (const offer of discountOffers) {
                if (offer["storeItemId"] === storeItemId) {
                    appliedOfferOneOfDiscount.push(offer);
                }
            }
        }
    }
    return appliedOfferOneOfDiscount;
}


function addOneOfDiscountOffers(discountContainer, discountOffers, applyDiscountButton) {
    for (let offer of discountOffers) {
        let offerStoreItemId = offer["storeItemId"];
        let offerStoreItemName = offer["storeItemName"];
        let offerStoreItemPurchaseCategory = offer["storeItemPurchaseCategory"];
        let offerQuantity = offer["quantity"];
        let offerAdditionalPrice = offer["additionalPrice"];

        let offerRadioButton = document.createElement("input");
        offerRadioButton.style.marginRight = "10px";
        offerRadioButton.id = offerStoreItemId
        offerRadioButton.classList.add(DISCOUNT_SINGLE_OFFER_CLASS , DISCOUNT_SINGLE_OFFER_RADIO_BUTTON_CLASS);
        offerRadioButton.type = "radio";
        offerRadioButton.name = "one-of-offer";
        offerRadioButton.value = offerStoreItemId;
        let offerRadioButtonLabel = document.createElement("label");
        offerRadioButtonLabel.htmlFor = offerStoreItemId;

        offerRadioButtonLabel.innerHTML = `ID: ${offerStoreItemId}${TAB}Name: ${offerStoreItemName}${TAB}
                                      Purchase Category: ${offerStoreItemPurchaseCategory}${TAB}
                                      Quantity: ${offerQuantity}${TAB}Additional Price: ${offerAdditionalPrice}`;
        offerRadioButton.onchange = function() {
            applyDiscountButton.disabled = false;
        }
        discountContainer.appendChild(offerRadioButton);
        discountContainer.appendChild(offerRadioButtonLabel);
        let newLine = document.createElement("br");
        discountContainer.appendChild(newLine);
    }
}


function checkIfDiscountsStillRelevant(appliedDiscount) {
    let discountStoreItemId = appliedDiscount["storeItemId"];
    let discountStoreItemQuantity = appliedDiscount["storeItemQuantity"];
    let remainderQuantity = itemsIdsAndQuantitiesAfterAppliedDiscounts[discountStoreItemId];
    itemsIdsAndQuantitiesAfterAppliedDiscounts[discountStoreItemId] = remainderQuantity - discountStoreItemQuantity;
    let discount;
    for (let i = 0; i < tempDiscounts.length; i++) {
        discount = tempDiscounts[i];
        discountStoreItemId = discount["storeItemId"];
        let currDiscountName = discount["name"];
        let currDiscountStoreItemQuantity = discount["storeItemQuantity"];
        let remainderQuantity = itemsIdsAndQuantitiesAfterAppliedDiscounts[discountStoreItemId];
        if (remainderQuantity < currDiscountStoreItemQuantity) {
            numberOfDiscountsRemained--;
            let discountContainer = document.getElementById(`${currDiscountName}-discount-container`);
            discountContainer.remove();
            tempDiscounts.splice(i, 1);     // remove current discount from tempDiscounts
        }
    }
    if (numberOfDiscountsRemained === 0) {
        let discountsContainer = document.getElementById(ORDER_DISCOUNTS_CONTAINER_ID);
        discountsContainer.style.display = "none";
        showOrderSummery();
    }
}


function setDiscountAppliesAmountLabel(discount) {
    let discountName = discount["name"];
    let appliesAmount = appliedDiscountsNamesAndAppliesAmount[discountName];
    appliesAmount++;
    appliedDiscountsNamesAndAppliesAmount[discountName] = appliesAmount;
    let discountAppliesAmountLabel = document.getElementById(`${discountName}-discount-applies-amount-label`);
    discountAppliesAmountLabel.textContent = `You applied this discount ${appliesAmount} times`;
}


function discountWasApplied(discount) {
    let discountName = discount["name"];
    let discountCategory = discount["category"];
    let discountOffers = discount["offersDto"];
    let appliedDiscountOffers;
    if (discountCategory === DISCOUNT_CATEGORY_ONE_OF_STR) {
        appliedDiscountOffers = getAppliedOfferOneOfDiscount(discountOffers);
    } else {
        appliedDiscountOffers = discountOffers;
    }

    if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
        let currDiscountAppliedOffers = appliedOffers[discountName];
        if (currDiscountAppliedOffers) {
            currDiscountAppliedOffers.push(...appliedDiscountOffers);
            appliedOffers[discountName] = currDiscountAppliedOffers;
        }
        else {
            appliedOffers[discountName] = appliedDiscountOffers;
        }
    }
    else {
        let storeId = discount["storeId"];
        let currStoreAppliedDiscountOffers = storesIdsAndAppliedOffers[storeId];
        if (currStoreAppliedDiscountOffers) {
            currStoreAppliedDiscountOffers.push(...appliedDiscountOffers);
        }
        else {
            currStoreAppliedDiscountOffers = appliedDiscountOffers;
        }
        storesIdsAndAppliedOffers[storeId] = currStoreAppliedDiscountOffers;
    }

    setDiscountAppliesAmountLabel(discount);
    checkIfDiscountsStillRelevant(discount);
}


function createApplyDiscountButton(discountContainer, discount) {
    let applyDiscountButton = document.createElement("button");
    applyDiscountButton.id = `${discount["name"]}-apply-discount-button`;
    applyDiscountButton.classList.add(APPLY_DISCOUNT_BUTTON_CLASS);
    applyDiscountButton.textContent = "Apply Discount";
    applyDiscountButton.addEventListener("click", () => {
        discountWasApplied(discount);
    })
    return applyDiscountButton;
}


function showDiscountByCategory(discountContainer, discount, applyDiscountButton, thenYouGetValueLabel) {
    let discountCategory = discount["category"];
    let discountOffers = discount["offersDto"];
    if (discountCategory === DISCOUNT_CATEGORY_ONE_OF_STR) {
        applyDiscountButton.disabled = true;
        thenYouGetValueLabel.textContent = "one of the following items:";
        addOneOfDiscountOffers(discountContainer, discountOffers, applyDiscountButton);
    }
    else if (discountCategory === DISCOUNT_CATEGORY_ALL_OR_NOTHING_STR) {
        thenYouGetValueLabel.textContent = "all the following items:";
        for (let offer of discountOffers) {
            addDiscountOfferForCategoryIrrelevantAndAllOrNothing(discountContainer, offer);
        }
    }
    else {
        thenYouGetValueLabel.textContent = "the following item:";
        let offer = discountOffers[0];
        addDiscountOfferForCategoryIrrelevantAndAllOrNothing(discountContainer, offer);
    }
}


function createDiscountChildrenElements(discountContainer, discount, applyDiscountButton) {
    let discountName = discount["name"];
    let discountStoreItemId = discount["storeItemId"];
    let discountStoreItemName = discount["storeItemName"];
    let discountStoreItemQuantity = discount["storeItemQuantity"];
    let discountHeader = document.createElement("h4");
    discountHeader.textContent = discountName;
    discountHeader.classList.add(DISCOUNT_HEADER_CLASS);
    let ifYouBuyFieldLabel = document.createElement("label");
    ifYouBuyFieldLabel.textContent = "If you buy ";
    ifYouBuyFieldLabel.classList.add(DISCOUNT_FIELD_LABEL_CLASS);
    let ifYouBuyValueLabel = document.createElement("label");
    ifYouBuyValueLabel.classList.add(DISCOUNT_VALUE_LABEL_CLASS);
    ifYouBuyValueLabel.textContent = `${discountStoreItemName} (ID ${discountStoreItemId})
                                          of total quantity ${discountStoreItemQuantity}`;
    let newLine = document.createElement("br");
    let thenYouGetFieldLabel = document.createElement("label");
    thenYouGetFieldLabel.id = `${discountName}-then-you-get-field-label`;
    thenYouGetFieldLabel.classList.add(DISCOUNT_FIELD_LABEL_CLASS);
    thenYouGetFieldLabel.textContent = "Then you get ";
    let thenYouGetValueLabel = document.createElement("label");
    thenYouGetValueLabel.classList.add(DISCOUNT_VALUE_LABEL_CLASS);

    discountContainer.appendChild(discountHeader);
    discountContainer.appendChild(ifYouBuyFieldLabel);
    discountContainer.appendChild(ifYouBuyValueLabel);
    discountContainer.appendChild(newLine);
    discountContainer.appendChild(thenYouGetFieldLabel);
    discountContainer.appendChild(thenYouGetValueLabel);
    newLine = document.createElement("br");
    discountContainer.appendChild(newLine);

    showDiscountByCategory(discountContainer, discount, applyDiscountButton, thenYouGetValueLabel);
}


function showDiscount(discount) {
    let orderDiscountsContainer = document.getElementById(ORDER_DISCOUNTS_CONTAINER_ID);
    let discountName = discount["name"];

    let discountContainer = document.createElement("div");
    discountContainer.id = `${discountName}-discount-container`;
    discountContainer.classList.add(DISCOUNT_CONTAINER_CLASS);
    orderDiscountsContainer.appendChild(discountContainer);
    let applyDiscountButton = createApplyDiscountButton(discountContainer, discount);
    createDiscountChildrenElements(discountContainer, discount, applyDiscountButton);
    discountContainer.appendChild(applyDiscountButton);
    let discountAppliesAmountLabel = document.createElement("label");
    discountAppliesAmountLabel.id = `${discountName}-discount-applies-amount-label`;
    discountAppliesAmountLabel.classList.add(DISCOUNT_APPLIES_AMOUNT_CLASS);
    discountContainer.appendChild(discountAppliesAmountLabel);
}


function showDiscounts() {
    $(`#${ORDER_DISCOUNTS_CONTAINER_ID}`).show();
    for (let discount of discounts) {
        showDiscount(discount);
    }
    let orderDiscountsNextButton = document.createElement("button");
    orderDiscountsNextButton.id = "order-discounts-next-button";
    orderDiscountsNextButton.textContent = "Next";
    orderDiscountsNextButton.addEventListener("click", () => {
        let discountsContainer = document.getElementById(ORDER_DISCOUNTS_CONTAINER_ID);
        discountsContainer.style.display = "none";
        showOrderSummery();
    });
    let orderDiscountsContainer = document.getElementById(ORDER_DISCOUNTS_CONTAINER_ID);
    orderDiscountsContainer.appendChild(orderDiscountsNextButton);
}


function getSelectedStore() {
    for (let store of stores) {
        if (store["id"] === storeId) {
            return store;
        }
    }
}


function addStoreDetailToOrderSummeryStore(storeContainer, field, val) {
    let fieldLabel = document.createElement("label");
    fieldLabel.classList.add(ORDER_SUMMERY_STORE_FIELD_LABEL_CLASS);
    fieldLabel.textContent = `${field}`;

    let valueLabel = document.createElement("label");
    valueLabel.classList.add(ORDER_SUMMERY_STORE_VALUE_LABEL_CLASS);
    valueLabel.innerText = `${val}`;
    valueLabel.style.marginRight = "30px";

    storeContainer.appendChild(fieldLabel);
    storeContainer.appendChild(valueLabel);
}


function addStoreDetailsToOrderSummeryStore(storeContainer, store) {
    let ppk = parseFloat(store["ppk"]);
    addStoreDetailToOrderSummeryStore(storeContainer, "ID: ", store["id"]);
    addStoreDetailToOrderSummeryStore(storeContainer, "PPK: ", ppk);
    addStoreDetailToOrderSummeryStore(storeContainer, "Distance (KM): ", distanceFromStore);
    addStoreDetailToOrderSummeryStore(storeContainer, "Delivery Cost: ", deliveryCost);
}


function addHeadersToPurchasedItemsTable(thead) {
    for (let i = 0; i < PURCHASED_STORE_ITEMS_TABLE_HEADERS .length; i++) {
        let header = document.createElement("th");
        header.class = PURCHASED_STORE_ITEMS_TABLE_COL ;
        header.innerHTML = PURCHASED_STORE_ITEMS_TABLE_HEADERS[i];
        thead.appendChild(header);
    }
}


function getItem(itemId) {
    for (let item of items) {
        if (itemId === item["id"]) {
            return item;
        }
    }
}


function getStore(storeId) {
    for (let store of stores) {
        if (storeId === store["id"]) {
            return store;
        }
    }
}


function getItemPriceInStore(storeId, itemId) {
    let store = getStore(storeId);
    let items = store["storeItemsDto"];
    for (let item of items) {
        if (itemId === item["id"]) {
            return item["price"];
        }
    }
}


function getPurchasedStoresItemsData(store) {
    let purchasedStoresItemsData = [];
    let i = 0;
    Object.keys(itemsIdsAndQuantities).forEach(function(itemId) {
        purchasedStoresItemsData[i] = new Array(PURCHASED_STORE_ITEMS_TABLE_NUMBER_OF_COLUMNS);
        let itemData = purchasedStoresItemsData[i];

        let parsedItemId = parseInt(itemId);
        let item = getItem(parsedItemId);
        let itemQuantity = itemsIdsAndQuantities[itemId];
        let itemPrice = getItemPriceInStore(store["id"], parsedItemId);

        itemData[0] = parsedItemId;
        itemData[1] = item["name"];
        itemData[2] = item["purchaseCategory"];
        itemData[3] = itemQuantity;
        itemData[4] = itemPrice;
        let itemCost = Math.round((itemQuantity * itemPrice) * 100) / 100;
        itemsCost += itemCost;
        itemData[5] = itemCost;
        itemData[6] = ITEM_PURCHASE_NOT_FROM_DISCOUNT_STR;
        i++;
    });
    return purchasedStoresItemsData;
}


function addPurchasedItemsTableCells(purchasedItemsTableBody, storesItemsData) {
    for (let itemData of storesItemsData) {
        let row = purchasedItemsTableBody.insertRow();
        for (let data of itemData) {
            let cell = row.insertCell();
            cell.classList.add(PURCHASED_STORE_ITEMS_TABLE_CELL_CLASS);
            cell.textContent = data;
        }
    }
}


function addItemsToPurchasedItemsTable(purchasedItemsTableBody, store) {
    let purchasedStoresItemsData = getPurchasedStoresItemsData(store);
    addPurchasedItemsTableCells(purchasedItemsTableBody, purchasedStoresItemsData);
}


function addOffersToPurchasedStoresItemsDataFromAppliedOffers(
    purchasedStoresItemsDataFromAppliedOffers, offers) {
    for (let offer of offers) {
        purchasedStoresItemsDataFromAppliedOffers[appliedOfferRowIndex] = new Array(PURCHASED_STORE_ITEMS_TABLE_NUMBER_OF_COLUMNS);
        let itemData = purchasedStoresItemsDataFromAppliedOffers[appliedOfferRowIndex];

        let itemQuantity = offer["quantity"];
        let itemPrice = offer["additionalPrice"];

        itemData[0] = offer["storeItemId"];
        itemData[1] = offer["storeItemName"];
        itemData[2] = offer["storeItemPurchaseCategory"];
        itemData[3] = itemQuantity;
        itemData[4] = itemPrice;
        let itemCost = Math.round((itemQuantity * itemPrice) * 100) / 100;
        itemsCost += itemCost;
        itemData[5] = itemCost;
        itemData[6] = ITEM_PURCHASE_FROM_DISCOUNT_STR;
        appliedOfferRowIndex++;
    }
}


function getPurchasedStoresItemsDataFromAppliedOffers() {
    let purchasedStoresItemsDataFromAppliedOffers = [];
    if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
        Object.keys(appliedOffers).forEach(function(discountName) {
            let offers = appliedOffers[discountName];
            addOffersToPurchasedStoresItemsDataFromAppliedOffers(
                purchasedStoresItemsDataFromAppliedOffers, offers
            );
        });
    }
    else {
        addOffersToPurchasedStoresItemsDataFromAppliedOffers(
            purchasedStoresItemsDataFromAppliedOffers, appliedOffers
        );
    }
    return purchasedStoresItemsDataFromAppliedOffers;
}


function addAppliedOffersToPurchasedItemsTable(purchasedItemsTableBody) {
    let purchasedStoresItemsDataFromAppliedOffers = getPurchasedStoresItemsDataFromAppliedOffers();
    addPurchasedItemsTableCells(purchasedItemsTableBody, purchasedStoresItemsDataFromAppliedOffers);
}


function addPurchasedItemsToOrderSummeryStore(storeContainer, store) {
    let purchasedItemTableContainer = document.createElement("div");
    purchasedItemTableContainer.id = PURCHASED_STORE_ITEMS_TABLE_CONTAINER_ID;
    let purchasedItemTable = document.createElement("table");
    purchasedItemTable.id = PURCHASED_STORE_ITEMS_TABLE_ID;
    purchasedItemTableContainer.appendChild(purchasedItemTable);
    let caption = purchasedItemTable.createCaption();
    caption.id = PURCHASED_STORE_ITEMS_TABLE_CAPTION_ID;
    caption.textContent = "Purchased Items";
    let thead = document.createElement("thead");
    purchasedItemTable.appendChild(thead);
    let purchasedItemsTableBody = document.createElement("tbody");
    purchasedItemsTableBody.id = PURCHASED_STORE_ITEMS_TABLE_BODY_ID;
    purchasedItemTable.appendChild(purchasedItemsTableBody);
    addHeadersToPurchasedItemsTable(thead);

    addItemsToPurchasedItemsTable(purchasedItemsTableBody, store);
    addAppliedOffersToPurchasedItemsTable(purchasedItemsTableBody);

    storeContainer.appendChild(purchasedItemTable);
}


function addStoreToOrderSummery(store) {
    let orderSummeryStoresInfoUl = document.getElementById(ORDER_SUMMERY_STORES_INFO_UL_ID);
    let storeLi = document.createElement("li");
    let storeContainer = document.createElement("div");
    storeLi.appendChild(storeContainer);
    let storeHeader = document.createElement("h4");
    storeHeader.textContent = `${store["name"]}`;
    storeHeader.classList.add(ORDER_SUMMERY_STORE_HEADER_CLASS);
    storeContainer.appendChild(storeHeader);

    addStoreDetailsToOrderSummeryStore(storeContainer, store);
    addPurchasedItemsToOrderSummeryStore(storeContainer, store);

    orderSummeryStoresInfoUl.appendChild(storeLi);
}


function addStoreToToOrderSummeryStoresForStaticOrder() {
    let store = getSelectedStore();
    addStoreToOrderSummery(store);
}


function addStoreToToOrderSummeryStoresForDynamicOrder() {
    $.each(dynamicOrderStoresDetails || [], function(index, storeDetails) {
        let storeId = storeDetails["id"];
        itemsIdsAndQuantities = storeDetails["itemIdsAndQuantities"];
        distanceFromStore = storeDetails["distance"];
        deliveryCost = storeDetails["deliveryCost"];
        totalDeliveryCost += deliveryCost;
        appliedOffers = storesIdsAndAppliedOffers[storeId] || [];
        addStoreToOrderSummery(storeDetails);
    });
}


function showOrderSummeryForStaticOrder(storeId, orderCategoryValue) {
    ajaxGetDistanceFromStore(storeId, orderCategoryValue);
}


function showOrderSummeryForDynamicOrder(orderCategoryValue) {
    addStoreToToOrderSummeryStoresForDynamicOrder();
    setStoreTotalDetails(orderCategoryValue);
    showOrderConfirmAndCancelButtons();
}


function showOrderSummery() {
    $(`#${ORDER_SUMMERY_CONTAINER_ID}`).show();
    document.getElementById(ORDER_SUMMERY_DATE_VALUE_LABEL_ID).textContent = date;
    document.getElementById(ORDER_SUMMERY_LOCATION_VALUE_LABEL_ID).textContent = `(${xLocation},${yLocation})`;

    let orderCategoryValue;
    if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
        orderCategoryValue = "One Store";
        showOrderSummeryForStaticOrder(storeId, orderCategoryValue);
    }
    else {
        orderCategoryValue = "Best Cart";
        showOrderSummeryForDynamicOrder(orderCategoryValue);
    }
}


function disableOrderInterface() {
    let finishOrderButton = document.getElementById(FINISH_ORDER_BUTTON_ID);
    let datePicker = document.getElementById(DATE_PICKER_INPUT_ID);
    let xLocationInput = document.getElementById(X_LOCATION_INPUT_ID);
    let yLocationInput = document.getElementById(Y_LOCATION_INPUT_ID);
    let OrderCategoryRadioButtonsInputs = document.getElementsByClassName(ORDER_CATEGORY_RADIO_BUTTON_CLASS);
    let storeSelectInput = document.getElementById(STORES_SELECT_ID);
    let deliveryCostLabel = document.getElementById(STORE_DELIVERY_COST_LABEL_CONTAINER_ID);
    let itemsTableQuantityCellsInputs = document.getElementsByClassName(ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS);
    finishOrderButton.disabled = true;
    datePicker.disabled = true;
    xLocationInput.disabled = true;
    yLocationInput.disabled = true;
    for (let radio of OrderCategoryRadioButtonsInputs) {
        radio.disabled = true;
    }
    storeSelectInput.disabled = true;
    deliveryCostLabel.disabled = true;
    for (let cellInput of itemsTableQuantityCellsInputs) {
        cellInput.disabled = true;
    }
}


function finishOrder() {
    let finishOrderMsgLabel = document.getElementById(FINISH_ORDER_MSG_LABEL_ID);
    finishOrderMsgLabel.textContent = "";
    let datePicker = document.getElementById(DATE_PICKER_INPUT_ID);
    date = datePicker.value;

    let isLocationAlreadyExists = isLocationAlreadyExistsForStore();
    let isAllQuantitiesAreEmpty = isAllQuantitiesInputAreEmpty();

    if (isLocationAlreadyExists) {
        finishOrderMsgLabel.textContent = FINISH_ORDER_TAKEN_LOCATION_MSG;
    }
    else if (isAllQuantitiesAreEmpty) {
        finishOrderMsgLabel.textContent = FINISH_ORDER_EMPTY_QUANTITIES_MSG;
    }
    else {
        disableOrderInterface();
        setItemsIdsAndQuantities();
        if (orderCategory === ORDER_CATEGORY_DYNAMIC_STR) {
            ajaxGetDynamicOrderStoresDetails();
        }
        else {
            ajaxGetDiscounts();
        }
    }
}


function enableOrderConfirmAndCancelButtons() {
    let confirmOrderButton = document.getElementById(CONFIRM_ORDER_BUTTON_ID);
    confirmOrderButton.disabled = true;
    let cancelOrderButton = document.getElementById(CANCEL_ORDER_BUTTON_ID);
    cancelOrderButton.disabled = true;
}


function storeWasRated(storeId, storeRateInput, storeRateFeedback) {
    storesAndRates[storeId] = {
        "storeRate": parseInt(storeRateInput.value),
        "storeFeedback": storeRateFeedback.value,
    }
}


function showRateStore(store) {
    let orderFeedbackContainer = document.getElementById(ORDER_FEEDBACK_CONTAINER_ID);
    let storeId = store["id"];
    let storeName = store["name"];

    const STORE_RATE_INPUT_ID = `${storeId}-store-rate-input`;
    const STORE_RATE_FEEDBACK_ID = `${storeId}-store-rate-feedback`;
    const STORE_RATE_FEEDBACK_ROWS = 6;
    const STORE_RATE_FEEDBACK_COLS = 70;
    const STORE_RATE_FEEDBACK_LENGTH = 300;

    let storeRateContainer = document.createElement("div");
    storeRateContainer.classList.add(STORE_RATE_CONTAINER_CLASS);
    let storeRateHeader = document.createElement("h4");
    storeRateHeader.classList.add(STORE_RATE_HEADER_CLASS);
    storeRateHeader.textContent = storeName;
    let storeRateInput = document.createElement("input");
    storeRateInput.id = STORE_RATE_INPUT_ID;
    storeRateInput.classList.add(STORE_RATE_INPUT_CLASS);
    storeRateInput.type = "number";
    storeRateInput.min = "1";
    storeRateInput.max = "5";
    let storeRateInputLabel = document.createElement("label");
    storeRateInputLabel.classList.add(STORE_RATE_FIELD_CLASS);
    storeRateInputLabel.textContent = "Rate: ";
    storeRateInputLabel.htmlFor = STORE_RATE_INPUT_ID;
    let storeRateInputNoteLabel = document.createElement("label");
    storeRateInputNoteLabel.classList.add(STORE_RATE_INPUT_NOTE_CLASS);
    storeRateInputNoteLabel.textContent = "1 - very poor, 5 - excellent";
    let storeRateFeedbackLabel = document.createElement("label");
    storeRateFeedbackLabel.classList.add(STORE_RATE_FIELD_CLASS);
    storeRateFeedbackLabel.textContent = "Feedback: ";
    let storeRateFeedback = document.createElement("textarea");
    storeRateFeedback.id = STORE_RATE_FEEDBACK_ID;
    storeRateFeedback.classList.add(STORE_RATE_FIELD_CLASS);
    storeRateFeedback.rows = STORE_RATE_FEEDBACK_ROWS;
    storeRateFeedback.cols = STORE_RATE_FEEDBACK_COLS;
    storeRateFeedback.maxlength = STORE_RATE_FEEDBACK_LENGTH;
    storeRateFeedback.disabled = true;

    let sendStoreRateButton = document.createElement("button");
    sendStoreRateButton.classList.add(SEND_STORE_RATE_BUTTON_CLASS );
    sendStoreRateButton.textContent = "Send Rate";
    sendStoreRateButton.disabled = true;
    sendStoreRateButton.addEventListener("click", () => {
            storeWasRated(storeId, storeRateInput, storeRateFeedback);
        }
    );

    storeRateInput.addEventListener("change", () => {
        sendStoreRateButton.disabled = !storeRateInput.value;
        storeRateFeedback.disabled = !storeRateInput.value;
    });

    let newLine = document.createElement("br");
    storeRateContainer.appendChild(storeRateHeader);
    storeRateContainer.appendChild(storeRateInputLabel);
    storeRateContainer.appendChild(storeRateInput);
    storeRateContainer.appendChild(storeRateInputNoteLabel);
    storeRateContainer.appendChild(newLine);
    storeRateContainer.appendChild(storeRateFeedbackLabel);
    newLine = document.createElement("br");
    storeRateContainer.appendChild(newLine);
    storeRateContainer.appendChild(storeRateFeedback);
    newLine = document.createElement("br");
    storeRateContainer.appendChild(newLine);
    storeRateContainer.appendChild(sendStoreRateButton);

    orderFeedbackContainer.appendChild(storeRateContainer);
}


function finishOrderRate() {
    goBack();
}


function showOrderRateStores() {
    let orderFeedbackContainer = document.getElementById(ORDER_FEEDBACK_CONTAINER_ID);
    if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
        let store = getSelectedStore();
        showRateStore(store);
    }
    else {
        $.each(dynamicOrderStoresDetails || [], function(index, store) {
            showRateStore(store);
        });
    }

    let finishButton = document.createElement("button");
    finishButton.id = "order-rate-finish-button";
    finishButton.textContent = "Finish";
    finishButton.onclick = finishOrderRate;

    orderFeedbackContainer.appendChild(finishButton);
    orderFeedbackContainer.style.display = "block";
}


function ajaxAddOrder() {
    $("#add-order-form").submit(function() {
        let parameters = getAddOrderFormInputsAsQueryParameters();

        $.ajax({
            data: parameters,
            url: this.action,
            timeout: 2000,
            headers: {
                'cache-control': 'no-store,no-cache',
            },
            error: function(e) {
                console.error(e);
                console.error("Failed to submit");
                $("#error-msg").text("Failed to get result from server");
            },
            success: function() {
                enableOrderConfirmAndCancelButtons();
                showOrderRateStores();
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    });
}


function configFinishOrderButton() {
    let finishOrderButton = document.getElementById(FINISH_ORDER_BUTTON_ID);
    finishOrderButton.addEventListener("click", finishOrder);
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
    let finishOrderButton = document.getElementById(FINISH_ORDER_BUTTON_ID);
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            orderCategory = radio.value;
            document.getElementById(CHOSEN_ORDER_CATEGORY_INPUT_ID).value = orderCategory;
            if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
                storesSelectContainer.style.display = "inline-block";
                storeDeliveryCostLabelContainer.style.display = "inline-block";
                itemTablePriceHeader.style.display = "block";
                $(".items-table-price-cell").show();
            }
            else {
                itemsTableContainer.style.display = "inline-block";
                finishOrderButton.disabled = false;
                document.getElementById(CHOSEN_STORE_INPUT_ID).value = DYNAMIC_ORDER_STORE_INPUT_ID;
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
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function() {
            console.error("Failed to submit");
            $("#error-msg").text("Failed to get result from server");
        },
        success: function(storeDeliveryCost) {
            deliveryCost = parseFloat(storeDeliveryCost);
            totalDeliveryCost = deliveryCost;
            $("#store-delivery-cost-label").text("Delivery Cost: " + storeDeliveryCost);
            $("#store-delivery-cost-label-container").visibility = "visible";
        }
    });
}


function setStoreTotalDetails(orderCategoryValue) {
    let orderCategoryValueLabel = document.getElementById(ORDER_SUMMERY_ORDER_CATEGORY_VALUE_LABEL_ID);
    let totalItemsCostValueLabel = document.getElementById(ORDER_SUMMERY_TOTAL_ITEMS_COST_VALUE_LABEL_ID);
    let totalDeliveryCostValueLabel = document.getElementById(ORDER_SUMMERY_TOTAL_DELIVERY_COST_VALUE_LABEL_ID);
    let totalOrderCostValueLabel = document.getElementById(ORDER_SUMMERY_TOTAL_ORDER_COST_VALUE_LABEL_ID);
    let roundedItemsCost = Math.round(itemsCost * 100) / 100;
    let roundedTotalDeliveryCost = Math.round(totalDeliveryCost * 100) / 100;
    let orderTotalCost = Math.round((itemsCost + totalDeliveryCost) * 100) / 100;
    orderCategoryValueLabel.textContent = orderCategoryValue;
    totalItemsCostValueLabel.textContent = `${roundedItemsCost}`;
    totalDeliveryCostValueLabel.textContent = `${roundedTotalDeliveryCost}`;
    totalOrderCostValueLabel.textContent = `${orderTotalCost}`;
}


function showOrderConfirmAndCancelButtons() {
    let confirmOrderButton = document.getElementById(FINAL_ORDER_BUTTONS_CONTAINER_ID);
    confirmOrderButton.style.display = "block";
}


function ajaxGetDistanceFromStore(storeId, orderCategoryValue) {
    let parameters = {
        "storeId": storeId,
        "location-x": xLocation,
        "location-y": yLocation,
    };

    $.ajax({
        data: parameters,
        url: SET_DISTANCE_FROM_STORE_URL,
        timeout: 2000,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        error: function() {
            console.error("Failed to submit");
            $("#error-msg").text("Failed to get result from server");
        },
        success: function(distanceFromStoreRes) {
            distanceFromStore = parseFloat(distanceFromStoreRes);
            addStoreToToOrderSummeryStoresForStaticOrder();
            setStoreTotalDetails(orderCategoryValue);
            showOrderConfirmAndCancelButtons();
        }
    });
}


function setQuantityCellContentToInput(row, quantityCell, itemId) {
    quantityCell.innerHTML = `<input id=${itemId} name="itemId-${itemId}" 
                                         class=${ITEMS_TABLE_QUANTITY_CELL_INPUT_CLASS} 
                                         type="number" min="0.1" step=".01"
                                         form=${ADD_ORDER_FORM_ID}>`;
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
    let itemsTableContainer = document.getElementById(ITEMS_TABLE_CONTAINER_ID);
    itemsTableContainer.style.display = "inline-block";
    let finishOrderButton = document.getElementById(FINISH_ORDER_BUTTON_ID);
    finishOrderButton.disabled = false;
    let index;

    document.getElementById(STORE_SELECT_DEFAULT_OPTION_ID).disabled = true;

    index = this.selectedIndex - 1;
    for (let i = 0; i < storesIds.length; i++) {
        if (i === index) {
            storeId = storesIds[i];
            break;
        }
    }
    document.getElementById(CHOSEN_STORE_INPUT_ID).value = storeId;
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


function goBack() {
    window.history.back();
}


function cancelOrder() {
    goBack();
}


$(function() {
    $.when(ajaxSetStores(), ajaxItemsTable()).then(function() {
        configFinishOrderButton();
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