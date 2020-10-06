// onload - capture the submit event on the form.
$(function() { // onload...do
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
                $("#error-msg").text(r);
            }
        });

        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})
