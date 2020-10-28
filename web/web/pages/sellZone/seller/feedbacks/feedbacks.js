const FEEDBACKS_CONTAINER_ID = "feedbacks-container";
const FEEDBACK_CONTAINER_ID = "feedback-container";
const FEEDBACK_FIELD_LABEL_CLASS = "feedback-field-label";
const FEEDBACK_VALUE_LABEL_CLASS = "feedback-value-label";
const NO_FEEDBACKS_P_ID = "no-feedbacks-p";

let refreshRate = 2000; //milli seconds
const FEEDBACKS_URL_RESOURCE = "getFeedbacks";
let FEEDBACKS_URL = buildUrlWithContextPath(FEEDBACKS_URL_RESOURCE);


function getNewLineElement() {
    return document.createElement("br");
}

function addFeedbackData(feedbackContainer, fieldLabelText, valueLabelText) {
    let fieldLabel = document.createElement("label");
    fieldLabel.classList.add(FEEDBACK_FIELD_LABEL_CLASS);
    fieldLabel.innerHTML = fieldLabelText;
    let valueLabel = document.createElement("label");
    valueLabel.classList.add(FEEDBACK_VALUE_LABEL_CLASS);
    valueLabel.textContent = valueLabelText;

    feedbackContainer.appendChild(fieldLabel);
    feedbackContainer.appendChild(valueLabel);
    feedbackContainer.appendChild(getNewLineElement());
}

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

    addFeedbackData(feedbackContainer, "Store:", `${storeName} (ID ${storeId})`);
    addFeedbackData(feedbackContainer, "Order Date:", `${orderDate}`);
    addFeedbackData(feedbackContainer, "Customer Name:", `${customerName}`);
    addFeedbackData(feedbackContainer, "Rate:", `${rate}`);
    addFeedbackData(feedbackContainer,  "Feedback:", `${feedbackDescription}`);

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
                let noFeedbacksP = document.getElementById(NO_FEEDBACKS_P_ID);
                noFeedbacksP.style.display = "block";
            }
        }
    });
}


$(function() {
    setInterval(ajaxFeedbacks, refreshRate);
});