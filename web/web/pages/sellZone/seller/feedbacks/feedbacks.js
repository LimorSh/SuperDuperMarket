const FEEDBACKS_CONTAINER_ID = "feedbacks-container";
const FEEDBACK_CONTAINER_ID = "feedback-container";
const FEEDBACK_FIELD_LABEL_CLASS = "feedback-field-label";
const FEEDBACK_VALUE_LABEL_CLASS = "feedback-value-label";
const NO_FEEDBACKS_P_ID = "no-feedbacks-p";

let refreshRate = 2000; //milli seconds
const FEEDBACKS_URL_RESOURCE = "getFeedbacks";
let FEEDBACKS_URL = buildUrlWithContextPath(FEEDBACKS_URL_RESOURCE);


function addFeedback(feedback) {
    let storeId = feedback["storeId"];
    let storeName = feedback["storeName"];
    let orderDate = feedback["orderDateStr"];
    let customerName = feedback["customerName"];
    let rate = feedback["rate"];
    let feedbackDescription = feedback["feedback"];

    let feedbacksContainer = document.getElementById(FEEDBACKS_CONTAINER_ID);
    let feedbackContainer = document.createElement("div");
    feedbackContainer.classList.add(FEEDBACK_CONTAINER_ID);

    let storeFieldLabel = document.createElement("label");
    storeFieldLabel.classList.add(FEEDBACK_FIELD_LABEL_CLASS);
    storeFieldLabel.textContent = "Store: ";
    let storeValueLabel = document.createElement("label");
    storeValueLabel.classList.add(FEEDBACK_VALUE_LABEL_CLASS);
    storeValueLabel.textContent = `${storeName} (ID ${storeId})`;

    let orderDateFieldLabel = document.createElement("label");
    orderDateFieldLabel.classList.add(FEEDBACK_FIELD_LABEL_CLASS);
    orderDateFieldLabel.textContent = "Order Date: ";
    let orderDateValueLabel = document.createElement("label");
    orderDateValueLabel.classList.add(FEEDBACK_VALUE_LABEL_CLASS);
    orderDateValueLabel.textContent = `${orderDate}`;

    let customerNameFieldLabel = document.createElement("label");
    customerNameFieldLabel.classList.add(FEEDBACK_FIELD_LABEL_CLASS);
    customerNameFieldLabel.textContent = "Customer Name: ";
    let customerNameValueLabel = document.createElement("label");
    customerNameValueLabel.classList.add(FEEDBACK_VALUE_LABEL_CLASS);
    customerNameValueLabel.textContent = `${customerName}`;

    let rateFieldLabel = document.createElement("label");
    rateFieldLabel.classList.add(FEEDBACK_FIELD_LABEL_CLASS);
    rateFieldLabel.textContent = "Rate: ";
    let rateValueLabel = document.createElement("label");
    rateValueLabel.classList.add(FEEDBACK_VALUE_LABEL_CLASS);
    rateValueLabel.textContent = `${rate}`;

    let feedbackDescriptionFieldLabel = document.createElement("label");
    feedbackDescriptionFieldLabel.classList.add(FEEDBACK_FIELD_LABEL_CLASS);
    feedbackDescriptionFieldLabel.textContent = "Feedback: ";
    let feedbackDescriptionValueLabel = document.createElement("label");
    feedbackDescriptionValueLabel.classList.add(FEEDBACK_VALUE_LABEL_CLASS);
    feedbackDescriptionValueLabel.textContent = `${feedbackDescription}`;

    let newLine = document.createElement("br");

    feedbackContainer.appendChild(storeFieldLabel);
    feedbackContainer.appendChild(storeValueLabel);
    feedbackContainer.appendChild(newLine);
    feedbackContainer.appendChild(orderDateFieldLabel);
    feedbackContainer.appendChild(orderDateValueLabel);
    newLine = document.createElement("br");
    feedbackContainer.appendChild(newLine);
    feedbackContainer.appendChild(customerNameFieldLabel);
    feedbackContainer.appendChild(customerNameValueLabel);
    newLine = document.createElement("br");
    feedbackContainer.appendChild(newLine);
    feedbackContainer.appendChild(rateFieldLabel);
    feedbackContainer.appendChild(rateValueLabel);
    newLine = document.createElement("br");
    feedbackContainer.appendChild(newLine);
    feedbackContainer.appendChild(feedbackDescriptionFieldLabel);
    feedbackContainer.appendChild(feedbackDescriptionValueLabel);
    newLine = document.createElement("br");
    feedbackContainer.appendChild(newLine);

    feedbacksContainer.appendChild(feedbackContainer);
}


function refreshFeedbacks(feedbacks) {
    $(`#${FEEDBACKS_CONTAINER_ID}`).empty();

    // rebuild the feedbacks: scan all feedbacks and add them to the feedbacks container
    $.each(feedbacks || [], function(index, feedback) {
        addFeedback(feedback);
    });
}


function ajaxFeedbacks() {
    $.ajax({
        url: FEEDBACKS_URL,
        headers: {
            'cache-control': 'no-store,no-cache',
        },
        success: function(feedbacks) {
            if (feedbacks.length > 0) {
                refreshFeedbacks(feedbacks);
            }
            else {
                let noFeedbacksP = document.getElementById("no-feedbacks-p");
                noFeedbacksP.style.display = "block";
            }
        }
    });
}


$(function() {
    setInterval(ajaxFeedbacks, refreshRate);
});