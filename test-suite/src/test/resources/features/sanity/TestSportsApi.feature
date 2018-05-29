Feature: To test the football live api and sports api feature for its events, markets, outcomes

@run_football_liveapi_query
Scenario:
When the football live is queried for its primary markets 
Then the response contains the markets and its outcomes for all the events


@run_football_sportsbookapi_query
Scenario:
Given there are set of foot ball live events
Then the response to the query on sports events matches the events schema
Then the response to the query on markets matches the markets schema
Then the response to the query on outcomes events matches the outcomes schema