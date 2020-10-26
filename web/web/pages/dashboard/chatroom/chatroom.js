let chatVersion = 0;
let triggerAjaxChatContentRefreshRate = 2000; //milli seconds

const CHAT_AREA_ID = "chat-area";

const CHAT_LIST_URL_RESOURCE = "chat";
let CHAT_LIST_URL = buildUrlWithContextPath(CHAT_LIST_URL_RESOURCE);


function appendToChatArea(entries) {
    $.each(entries || [], appendChatEntry);
    
    let scroller = $(`#${CHAT_AREA_ID}`);
    let height = scroller[0].scrollHeight - $(scroller).height();
    $(scroller).stop().animate({ scrollTop: height }, "slow");
}

function appendChatEntry(index, entry){
    let entryElement = createChatEntry(entry);
    $(`#${CHAT_AREA_ID}`).append(entryElement).append("<br>");
}

function createChatEntry (entry){
    // entry.chatString = entry.chatString.replace (":)", "<img class='smiley-image' src='../../common/images/smiley.png'/>");
    return $("<span class=\"success\">").append(entry.username + "> " + entry.chatString);
}


//call the server and get the chat version
//we also send it the current chat version so in case there was a change
//in the chat content, we will get the new string as well
function ajaxChatContent() {
    $.ajax({
        url: CHAT_LIST_URL,
        data: "chatVersion=" + chatVersion,
        dataType: 'json',
        success: function(newChatLines) {
            if (newChatLines.version !== chatVersion) {
                chatVersion = newChatLines.version;
                appendToChatArea(newChatLines.entries);
            }
            triggerAjaxChatContent();
        },
        error: function(error) {
            triggerAjaxChatContent();
        }
    });
}

//add a method to the button in order to make that form use AJAX
//and not actually submit the form
$(function() { // onload...do
    //add a function to the submit event
    $("#chat-form").submit(function() {
        $.ajax({
            data: $(this).serialize(),
            url: this.action,
            timeout: 2000,
            error: function() {
                console.error("Failed to submit");
            },
            success: function(r) {
                //do not add the user string to the chat area
                //since it's going to be retrieved from the server
                //$("#result h1").text(r);
            }
        });

        $("#user-chat-msg").val("");
        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    });
});

function triggerAjaxChatContent() {
    setTimeout(ajaxChatContent, triggerAjaxChatContentRefreshRate);
}

//activate the timer calls after the page is loaded
$(function() {
    //The chat content is refreshed only once (using a timeout) but
    //on each call it triggers another execution of itself later (1 second later)
    triggerAjaxChatContent();
});