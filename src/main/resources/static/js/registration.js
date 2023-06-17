document.addEventListener("DOMContentLoaded", function(event) {

    const form = document.getElementsByClassName("form")[0];

    form.addEventListener("submit", function(e) {
        e.preventDefault()
        if (document.querySelector(".error-container") != null) {
            document.querySelector(".error-container").remove();
        }
        const formData = new FormData(e.target);
        const formDataObj = Object.fromEntries(formData.entries());
        fetch("/api/v1/accounts", {
            method: "POST",
            headers: {
                'Content-type': "application/json"
            },
            body: JSON.stringify(formDataObj)
        })
            .then(response => {
                if (response.status === 200) {
                    document.location = "/login";
                } else {
                    response.json().then(data => {
                        const errorDiv = createErrorDiv(data);
                        document.body.insertAdjacentHTML("beforeend", errorDiv);
                    });
                }
            })
    });

    function createErrorDiv(data) {
        const errors = data['messages'];
        let errorDiv = "<div class=\"error-container\">";
        for (let i = 0; i < errors.length; i++) {
            errorDiv = errorDiv + "<p class=\"error\">" +  errors[i] + "</p>";
        }
        errorDiv = errorDiv + "</div>";
        return errorDiv;
    }

});