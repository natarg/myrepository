package com.sbtechtest.api;

import static com.jayway.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.response.Response;
import com.sbtechtest.common.GetUrl;
import com.sbtechtest.common.JSONDeserializer;
import com.sbtechtest.common.SchemaInit;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TestSportsBookApi extends SchemaInit  {
	GetUrl setObj = GetUrl.getInstance();
	private Scenario scenario;
	public Response rsp;
	JsonNode myNode;
	String eventId;
	String marketId;
	String outcomeId;
	JSONDeserializer getObj = JSONDeserializer.getInstance();
	@Before("@run_football_sportsbookapi_query")
	public void setScenarioObj(Scenario scenario){
		// This writes on to the cucumber html reports produced, so the report can print what needs to be checked.
		this.scenario = scenario;

	}



	@Given("^there are set of foot ball live events$")
	public void there_are_set_of_foot_ball_live_events() throws Throwable {
		// Below is a sample to retrieve an event id from football live api for testing events schema later
		// This uses Jackson JSONNode class to extract a value

		rsp = get(setObj.getFootBallLive());
		myNode = getObj.getJsonNode(rsp.asString());

	}




	@Then("^the response to the query on sports events matches the events schema$")
	public void the_response_to_the_query_on_sports_events_matches_the_events_schema()
			throws Throwable {
		// This has extracted one event id and passed the same to events resource path under sportsbook and verified the schema of the resultant response
		eventId = myNode.get("events").get(0).get("eventId").asText();
		get(setObj.getSportsEventUrl()+"/"+eventId).then().assertThat().body(matchesJsonSchemaInClasspath("EventSchema.json").using(jsonSchemaFactoryObj));
		scenario.write("The schema verified for the eventId"+ eventId);
	}
	@Then("^the response to the query on markets matches the markets schema$")
	public void the_response_to_the_query_on_markets_matches_the_markets_schema() throws Throwable {
		// This has extracted one market id and passed the same to markets resource path under sportsbook and verified the schema of the resultant response
		// market is for an event
		/*JsonNode baseNode;
		baseNode = myNode.get("markets").get("\""+eventId+"\"");
		marketId = baseNode.get(0).get("marketId").asText();
		get(setObj.getSportsMarketUrl()+"/"+marketId).then().assertThat().body(matchesJsonSchemaInClasspath("MarketSchema.json").using(jsonSchemaFactoryObj));
		scenario.write("The schema verified for the marketId"+ marketId);*/
	}

	@Then("^the response to the query on outcomes events matches the outcomes schema$")
	public void the_response_to_the_query_on_outcomes_events_matches_the_outcomes_schema() throws Throwable {
		// This has extracted one outcome id and passed the same to outcome resource path under sportsbook and verified the schema of the resultant response
		// an outcome is for a market
		/*outcomeId = myNode.get("outcomes").get("\""+marketId+"\"").get(0).get("outcomeId").asText();
		get(setObj.getSportsOutComeUrl()+"/"+outcomeId).then().assertThat().body(matchesJsonSchemaInClasspath("MarketSchema.json").using(jsonSchemaFactoryObj))*/;
	}
}
