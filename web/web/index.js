function pageRedirect() {
    window.location.replace("pages/main/main.html");
}

function configUserTypeRadioButtons() {
    let userType = "";

    let radios = document.getElementsByClassName("user-type-radio-button");
    for (let i = 0; i < radios.length; i++) {
        let radio = radios[i];
        radio.onchange = function() {
            document.getElementById("login").disabled = false;
            userType = radio.value;
        }
    }
}


$(function () {
    configUserTypeRadioButtons();
})


$(function() {
    $("#login-form").submit(function() {
        let parameters = $(this).serialize();

        let form = this;

        $.ajax({
            data: parameters,
            url: form.action,
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

