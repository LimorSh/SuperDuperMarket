

const ADD_STORE_FORM_ID = "add-store-form";
const ADD_STORE_MSG_LABEL_ID = "add-store-msg-label";
const ADD_STORE_MSG_SUCCESS = "The store was added successfully!";







$(function() {
    $(`#${ADD_STORE_FORM_ID}`).submit(function() {
        let parameters = $(this).serialize();

        $.ajax({
            data: parameters,
            url: this.action,
            timeout: 2000,
            headers: {
                'cache-control': 'no-store,no-cache',
            },
            error: function() {
                console.error("Failed to submit");
                $("#error-msg").text("Failed to get result from server");
            },
            success: function(r) {
                if (r.length > 0) {
                    $(`#${ADD_STORE_MSG_LABEL_ID}`).text(r);
                }
                else {
                    $(`#${ADD_STORE_MSG_LABEL_ID}`).text(ADD_STORE_MSG_SUCCESS);

                    // go back button
                }
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})
