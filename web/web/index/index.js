const USER_TYPE_RADIO_BUTTON_CLASS_NAME = "user-type-radio-button";
const LOGIN_FORM_SUBMIT_BUTTON_ID = "login";
const LOGIN_FORM_USER_TYPE_INPUT_ID = "user-type-input";

let userType = "";


function pageRedirect() {
    window.location.replace(DASHBOARD_URL);
}


function configUserTypeRadioButtons() {
    let radios = document.getElementsByClassName(USER_TYPE_RADIO_BUTTON_CLASS_NAME);
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            document.getElementById(LOGIN_FORM_SUBMIT_BUTTON_ID).disabled = false;
        }
    }
}


$(function () {
    configUserTypeRadioButtons();
})


$(function() {
    $("#login-form").submit(function() {
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
                    $("#error-msg").text(r);
                }
                else {
                    pageRedirect();
                }
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})

