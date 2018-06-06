package org.sbtechtest.api;

import static com.jayway.restassured.RestAssured.get;

import org.sbtechtest.common.GetUrl;

import com.jayway.restassured.response.Response;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class FootballLiveApiStepDef {

	GetUrl setObj = GetUrl.getInstance();
	Response rspObj;
	public String resource ;
	private Scenario scenario;

	@Before("@run_football_liveapi_query,@run_football_sportsbookapi_query")
	public void before(Scenario scenario){
		// This writes on to the cucumber html reports produced, so the report can print what needs to be checked.
		System.out.println("Im inside before hook of football live");
		this.scenario = scenario;
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
