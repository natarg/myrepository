package org.sbtechtest.runcukes;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/sanity/TestWebSocketMessaging.feature", plugin = {
"org.sbtechtest.common.CustomCucumberJSONFormatter:target/$BUILD_ID/cucumber/cucumber_json/websocketapi.json" }, glue = {
"org.sbtechtest.api"}, tags={"@run_websocket_subscribespecificevent"})

public class Websocketapi_AT {

}
