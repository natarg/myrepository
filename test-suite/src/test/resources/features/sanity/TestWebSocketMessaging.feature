Feature: This is to test the websocket api which sends messages for subscribing and unsubscribing to events, markets or its outcomes

@run_websocket_subscribealloutcomes
Scenario Outline: testsocket
When the socket client is opened with "<message>" ready to be pushed to subscribe for all outcomes
Then the response message is verified not to be null
Examples:
|message|
|o.*|

@run_websocket_subscribespecificevent
Scenario Outline:
When there is a websocket "<message>" to subscribe to a specific event
Then the response message is verified not to be null
Examples:
|message|
|21249939|

