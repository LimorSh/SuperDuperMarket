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

const ORDER_SUMMERY_STORES_INFO_CONTAINER_ID = "order-summery-stores-info-container";
const ORDER_SUMMERY_STORES_INFO_UL_ID = "order-summery-stores-info-ul";
const ORDER_SUMMERY_STORE_HEADER_CLASS = "order-summery-store-header";
const ORDER_SUMMERY_STORE_FIELD_LABEL_CLASS = "order-summery-store-field-label";
const ORDER_SUMMERY_STORE_VALUE_LABEL_CLASS= "order-summery-store-value-label";

const ADD_ORDER_FORM_ID = "add-order-form";

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
let itemsIdsAndQuantities = {};
let dynamicOrderStoresDetails = {};
let discounts = {};
let appliedOffers = {};
let itemsIdsAndQuantitiesAfterAppliedDiscounts = {};
let appliedDiscountsNamesAndAppliesAmount = {};


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
            if (relevantDiscounts) {
                discounts = relevantDiscounts;
                setDiscountsAndStoreItemQuantities();
                initAppliedDiscountsNamesAndAppliesAmount();
                showDiscounts();
            }
            showOrderSummery();
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
        let storeDetailsStr = `id: ${storeDetails.id}, name: ${storeDetails.name}, 
                                location: (${storeDetails["xLocation"]},${storeDetails["yLocation"]}), 
                                distance (KM): ${storeDetails["distance"]}, 
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
    let updatedRemainderQuantity = remainderQuantity - discountStoreItemQuantity;
    itemsIdsAndQuantitiesAfterAppliedDiscounts[discountStoreItemId] = updatedRemainderQuantity;
    for (let discount of discounts) {
        let currDiscountName = discount["name"];
        let currDiscountStoreItemQuantity = discount["storeItemQuantity"];
        if (updatedRemainderQuantity < currDiscountStoreItemQuantity) {
            let discountContainer = document.getElementById(`${currDiscountName}-discount-container`);
            discountContainer.remove();
        }
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
    appliedOffers[discountName] = appliedDiscountOffers;

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
    fieldLabel.textContent = `${field}`;
    fieldLabel.classList.add(ORDER_SUMMERY_STORE_FIELD_LABEL_CLASS);

    let valueLabel = document.createElement("label");
    valueLabel.classList.add(ORDER_SUMMERY_STORE_VALUE_LABEL_CLASS);
    valueLabel.textContent = `${val}`;
    valueLabel.style.marginRight = "30px";

    storeContainer.appendChild(fieldLabel);
    storeContainer.appendChild(valueLabel);
}


function addStoreDetailsToOrderSummeryStore(storeContainer, store) {
    let ppk = parseFloat(store["ppk"]);
    let deliveryCost = ppk * distanceFromStore;
    addStoreDetailToOrderSummeryStore(storeContainer, "ID: ", store["id"]);
    addStoreDetailToOrderSummeryStore(storeContainer, "PPK: ", ppk);
    addStoreDetailToOrderSummeryStore(storeContainer, "Distance (KM): ", distanceFromStore);
    addStoreDetailToOrderSummeryStore(storeContainer, "Delivery Cost: ", deliveryCost);
}


function addStoreToToOrderSummeryStoresForStaticOrder() {
    let store = getSelectedStore();
    let orderSummeryStoresInfoUl = document.getElementById(ORDER_SUMMERY_STORES_INFO_UL_ID);
    let storeLi = document.createElement("li");
    let storeContainer = document.createElement("div");
    storeLi.appendChild(storeContainer);
    let storeHeader = document.createElement("h4");
    storeHeader.textContent = `${store["name"]}`;
    storeHeader.classList.add(ORDER_SUMMERY_STORE_HEADER_CLASS);
    storeContainer.appendChild(storeHeader);

    addStoreDetailsToOrderSummeryStore(storeContainer, store);


    orderSummeryStoresInfoUl.appendChild(storeLi);
}


function showOrderSummery() {
    document.getElementById(ORDER_SUMMERY_STORES_INFO_CONTAINER_ID).style.display = "inline-block";
    document.getElementById(ORDER_SUMMERY_DATE_VALUE_LABEL_ID).textContent = date;
    document.getElementById(ORDER_SUMMERY_LOCATION_VALUE_LABEL_ID).textContent = `(${xLocation},${yLocation})`;
    let orderCategoryValueLabel = document.getElementById(ORDER_SUMMERY_ORDER_CATEGORY_VALUE_LABEL_ID);
    let totalItemsCostValueLabel = document.getElementById(ORDER_SUMMERY_TOTAL_ITEMS_COST_VALUE_LABEL_ID);
    let totalDeliveryCostValueLabel = document.getElementById(ORDER_SUMMERY_TOTAL_DELIVERY_COST_VALUE_LABEL_ID);
    let totalOrderCostValueLabel = document.getElementById(ORDER_SUMMERY_TOTAL_ORDER_COST_VALUE_LABEL_ID);
    let orderCategoryValue;
    let totalItemsCost;
    let totalDeliveryCost;
    if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
        ajaxGetDistanceFromStore(storeId);
        orderCategoryValue = "One Store";
        addStoreToToOrderSummeryStoresForStaticOrder();
    }
    else {
        orderCategoryValue = "Best Cart";
    }
    orderCategoryValueLabel.textContent = orderCategoryValue;
    // totalItemsCostValueLabel.textContent = totalItemsCost;
    // totalDeliveryCostValueLabel.textContent = totalDeliveryCost;
    // totalOrderCostValueLabel.textContent = totalItemsCost + totalDeliveryCost;
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
        setItemsIdsAndQuantities();
        if (orderCategory === ORDER_CATEGORY_DYNAMIC_STR) {
            ajaxGetDynamicOrderStoresDetails();
        }
        else {
            ajaxGetDiscounts();
        }
    }
}


function ajaxAddOrder() {
    $("#add-order-form").submit(function() {
        let parameters = getAddOrderFormInputsAsQueryParameters();
        console.log(parameters);

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
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            itemsTableContainer.style.display = "inline-block";

            // document.getElementById("login").disabled = false;
            orderCategory = radio.value;
            document.getElementById(CHOSEN_ORDER_CATEGORY_INPUT_ID).value = orderCategory;
            if (orderCategory === ORDER_CATEGORY_STATIC_STR) {
                storesSelectContainer.style.display = "inline-block";
                storeDeliveryCostLabelContainer.style.display = "inline-block";
                itemTablePriceHeader.style.display = "block";
                $(".items-table-price-cell").show();
            }
            else {
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
            $("#store-delivery-cost-label").text("Delivery Cost: " + storeDeliveryCost);
            $("#store-delivery-cost-label-container").visibility = "visible";
        }
    });
}


function ajaxGetDistanceFromStore(storeId) {
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