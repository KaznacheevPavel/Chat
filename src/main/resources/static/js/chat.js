document.addEventListener("DOMContentLoaded", function(event) {

    let stompClient;
    getAllMessage();
    connect();
    bindSendFileButton();
    bindSendMessageButton();
    bindDateInput();



    function getAllMessage() {
        fetch("/api/v1/messages", {
            method: "GET",
            headers: {
                'Content-type': "application/json"
            }
        })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
            })
            .then(function (json) {
                for (const jsonElement of json) {
                    printMessage(jsonElement);
                }
                scrollDown();
            })
    }

    function connect() {
        let socket = new SockJS('/register');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/messages', function (data) {
                let json = JSON.parse(data.body);
                printMessage(json)
            })
        });
    }

    function printMessage(json) {
        if (json.text.includes("FILE: ")) {
            let link = json.text.replace("FILE: ", "");
            link = link.substr(0, link.indexOf(","));
            let fileName = json.text.replace(link + ",", "");
            json.text = "<a class=\"link\" href=" + link + ">" + fileName + "</a>";
        }
        if (json.senderName === getCookie("USERNAME")) {
            printYourMessage(json.timestamp, json.text)
            scrollDown();
        } else {
            if (checkBottomScrollPosition()) {
                printOtherMessage(json.timestamp, json.senderName, json.text);
                scrollDown();
            }
            printOtherMessage(json.timestamp, json.senderName, json.text)
        }
    }

    function printYourMessage(timestamp, text) {
        let message = `
            <div class="outer-message-container">
                <div class="message sent">
                    <p class="message-info this-name">{timestamp} <br> Вы</p>
                    <p class="message-text your-message">{text}</p>
                </div>
            </div>
        `;
        message = message.replace("{timestamp}", timestamp);
        message = message.replace("{text}", text);
        let messageContainer = document.getElementsByClassName("inner-messages-container")[0];
        messageContainer.insertAdjacentHTML("beforeend", message);
    }

    function printOtherMessage(timestamp, username, text) {
        let message = `
            <div class="outer-message-container">
                <div class="message">
                    <p class="message-info">{timestamp} <br> {username}</p>
                    <p class="message-text">{text}</p>
                </div>
            </div>
        `;
        message = message.replace("{timestamp}", timestamp);
        message = message.replace("{username}", username);
        message = message.replace("{text}", text);
        let messageContainer = document.getElementsByClassName("inner-messages-container")[0];
        messageContainer.insertAdjacentHTML("beforeend", message);
    }

    function checkBottomScrollPosition() {
        let scroll = document.getElementsByClassName("inner-messages-container")[0];
        let scrollPosition = scroll.scrollHeight - scroll.scrollTop - scroll.offsetHeight;
        if (scrollPosition === 0) {
            return true;
        } else {
            return false;
        }
    }

    function scrollDown() {
        const element = document.getElementsByClassName("outer-message-container");
        if (element.length !== 0) {
            element[element.length - 1].scrollIntoView();
        }
    }

    function getCookie(name) {
        let matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    }

    function bindSendMessageButton() {
        const sendButton = document.getElementsByClassName("submit-button")[0];
        sendButton.addEventListener("click", function (e) {
            e.preventDefault();
            const textarea = document.getElementsByClassName("text-input")[0];
            const fileInput = document.getElementsByClassName("file_input")[0];
            if (textarea.value.length === 0) {
                return;
            }
            let date = new Date();
            stompClient.send("/app/chat", {}, JSON.stringify({
                "senderId": getCookie("USER_ID"),
                "timestamp": date.toLocaleString(),
                "text": textarea.value
            }));
            fileInput.value = "";
            textarea.value = "";
        });
    }

    function bindSendFileButton() {
        let fileInput = document.getElementsByClassName("file_input")[0];
        fileInput.addEventListener("change", async function (e) {
            e.preventDefault();
            let file = getFile();
            if (file != null) {
                return await fetch('/api/v1/files', {
                    method: 'POST',
                    body: file
                })
                    .then(response => {
                        if (response.status === 200) {
                            return response.json();
                        }
                    })
                    .then(json => {
                        let date = new Date();
                        stompClient.send("/app/chat", {}, JSON.stringify({
                            "senderId": getCookie("USER_ID"),
                            "timestamp": date.toLocaleString(),
                            "text": "FILE_ID:" + json["uuid"] + "," + json["name"]
                        }));
                        fileInput.value = "";
                    })
            }
            return "";
        })
    }

    function getFile() {
        let file = document.getElementsByClassName("file_input")[0].files[0];
        if (file != null) {
            if (file.size > 10485760) {
                alert("Мы не принимаем файлы больше 10Мб(");
                document.getElementsByClassName("file_input")[0].value = "";
                return null;
            }
            let formData = new FormData();
            formData.append("file", file);
            return formData;
        }
        return null;
    }

    function bindDateInput() {
        let dateInput = document.getElementsByClassName("date_input")[0];
        dateInput.addEventListener("change", function (e) {
            e.preventDefault();
            let year = dateInput.value.substr(0, 4);
            let month = dateInput.value.substr(5, 2);
            let day = dateInput.value.substr(8, 2);
            let formatDate = day + "." + month + "." + year;
            findMessageByDate(formatDate);
        })
    }

    function findMessageByDate(date) {
        let messagesInfo = document.getElementsByClassName("message-info");
        for (const messageInfo of messagesInfo) {
            if (messageInfo.innerHTML.includes(date)) {
                messageInfo.scrollIntoView();
                break;
            }
        }
    }

});