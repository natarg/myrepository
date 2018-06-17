package org.sbtechtest.api;

import static com.jayway.restassured.RestAssured.get;

import org.junit.Assert;
import org.sbtechtest.common.GetUrl;
import org.sbtechtest.common.JSONResponseMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.response.Response;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class FootballLiveApiStepDef {

	GetUrl setObj = GetUrl.getInstance();
	Response rspObj;
	JsonNode rspNode;
	public String resource ;
	private Scenario scenario;
	JSONResponseMapper getObj = JSONResponseMapper.getInstance();
	@Before("@run_football_liveapi_query,@run_football_sportsbookapi_query")
	public void before(Scenario scenario){

		System.out.println("Im inside before hook of football live");
		this.scenario = scenario;
	}

	@When("^the football live is queried$")
	public void the_football_live_is_queried() throws Throwable {
		rspObj =get(setObj.getFootBallLive());


	}

	@Then("^the response contains competitor information for each of the events listed$")
	public void the_response_contains_competitor_information_for_each_of_the_events_listed() throws Throwable {
		rspNode = getObj.getJsonNode(rspObj.asString());
		System.out.println("Printing full response"+ rspObj.asString());

		int sizeOfevents = rspNode.get("events").size();
		// got the size of events printed

		for (int i=0;i<sizeOfevents; i++){
			//getting the size of the competition in an event
			int sizeOfComp= rspNode.get("events").get(i).get("competitors").size();
			for (int j=0; j<sizeOfComp; j++){


				System.out.println("Printing the elements of competitor array"+rspNode.get("events").get(i).get("competitors").get(j).get("name").asText());
				System.out.println("Printing the elements of position for the competition"+rspNode.get("events").get(i).get("competitors").get(j).get("position").asText());
				Assert.assertNotNull(rspNode.get("events").get(i).get("competitors").get(j).get("name").asText());
				Assert.assertNotNull(rspNode.get("events").get(i).get("competitors").get(j).get("position").asText());

				Assert.assertTrue(rspNode.get("events").get(i).get("competitors").get(j).get("position").asText().equals("home") ||rspNode.get("events").get(i).get("competitors").get(j).get("position").asText().equals("away")   );


				//A(rspNode.get("events").get(i).get("competitors").get(j).get("position").asText().equals("away")){

			}

		}



	}





	@When("^the football live is queried for its primary markets$")
	public void the_football_live_is_queried_for_its_markets() throws Throwable {
		rspObj =get(setObj.getFootBallLive()+"?primaryMarkets=true");
		scenario.write("<br>Printing the outcome of an event</br>"+ rspObj.asString());
	}


	@Then("^the response contains the markets and its outcomes for all the events$")
	public void the_response_contains_the_markets_and_its_outcomes_for_all_the_events_confirming_to_the_response_schema() throws Throwable {
		rspObj.then().assertThat().statusCode(200);
		scenario.write("<br>Printing the outcome of an event</br>"+ rspObj.asString());
	}



}
