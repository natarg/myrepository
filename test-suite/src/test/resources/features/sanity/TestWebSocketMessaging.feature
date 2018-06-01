Feature: This is to test the websocket api which sends messages for subscribing and unsubscribing to events, markets or its outcomes

@run_websocket_subscribealloutcomes
Scenario:
Given there is a message to subscribetoall outcomes 
When the socket client is opened
Then the response is verified for relevant outcomes when the message is pushed


@run_websocket_subscribespecificevent
Scenario Outline:
When there is a websocket "<message>" to subscribe to a specific event
Then the message is pushed and the response is asserted to have outcome related responses
Examples:
|message|
|e.21249939|

