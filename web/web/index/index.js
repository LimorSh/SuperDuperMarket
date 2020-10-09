let userType = "";


function pageRedirect() {
    window.location.replace(DASHBOARD_URL);
}


function configUserTypeRadioButtons() {
    let radios = document.getElementsByClassName("user-type-radio-button");
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            document.getElementById("login").disabled = false;
            userType = radio.value;
            document.getElementById("user-type-input").value = userType;
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

