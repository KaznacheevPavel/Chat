document.addEventListener("DOMContentLoaded", function(event) {

    const form = document.getElementsByClassName("form")[0];

    const errorDiv = `
        <div class="error-container">
            <p class="error">Неверный логин или пароль</p>
        </div>
    `;

    form.addEventListener("submit", function(e) {
        e.preventDefault()
        fetch("/api/v1/login", {
            method: "POST",
            body: new FormData(form)
        })
            .then(response => {
                if (response.redirected) {
                    document.location = response.url;
                }
                if (response.status === 400 && document.querySelector(".error-container") === null) {
                    document.body.insertAdjacentHTML("beforeend", errorDiv);
                }
            })
    });

});