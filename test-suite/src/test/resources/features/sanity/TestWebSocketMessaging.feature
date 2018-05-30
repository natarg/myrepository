Feature: This is to test the websocket api which sends messages for subscribing and unsubscribing to events, markets or its outcomes

Scenario Outline:
Given a socket "<message>" is sent to subscribe on an event
Then the socket message is published with status on the event

Examples:
|message|
|m.*|
