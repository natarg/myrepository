Feature: This is to test the websocket api which sends messages for subscribing and unsubscribing to events, markets or its outcomes

Scenario:
Given there is a websocket message to subscribe to all events
When the message is pushed via websocket api
Then the response to the message published has status for all events

Scenario Outline:
Given there is a websocket "<message>" to subscribe to a specific event
When the message is pushed via websocket api
Then the response to the message published has status for the specific event requested
Examples:
|message|
|e.21249939|