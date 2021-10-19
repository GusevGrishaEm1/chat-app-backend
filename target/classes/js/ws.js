'use strict'

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#text');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var roomId = document.location.pathname.substr(document.location.pathname.lastIndexOf("message"))
var userId = document.location.pathname.substr(document.location.pathname.lastIndexOf("message")+8)


var stompClient = null;
var username = null;

function onConnected() {
    stompClient.subscribe('/room/public', onMessageReceived);
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = "Could not connect to WebSocket server. Please refresh this page to try again!";
    connectingElement.style.color = 'red';
}

function send(event) {
    if (!stompClient) {
        username = document.querySelector('#name').textContent.trim();
        if(username)
            var socket = new SockJS('/chat-websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        }
    event.preventDefault();
    else {
        var messageContent = messageInput.value.trim();
        if(messageContent && stompClient) {
            var chatMessage = {
                value: messageInput.value,
                roomId: roomId
                userId: userId
            };
            stompClient.send("/message/chat.send", {}, JSON.stringify(chatMessage));
            messageInput.value = '';
        }
        event.preventDefault();
    }

function onMessageReceived() {
    var message = JSON.parse(payload.body)
    if ((message.roomId === roomId ) && (message.userId === userId)) {
        var messageElement = document.createElement("li");
        messageElement.classList.add('chatMessage');
        var usernameElement = document.createElement('span');
        var usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
        var textElement = document.createElement('p');
        textElement.innerHTML = message.text;
        messageElement.appendChild(textElement);
        messageArea.appendChild(messageElement);
    }

}

messageForm.addEventListener('submit', send, true);
document.querySelector('#send').click();