package com.sbtechtest.runcukes;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/sanity/TestWebSocketMessaging.feature", plugin = {
"com.sbtechtest.common.CustomCucumberJSONFormatter:target/$BUILD_ID/cucumber/cucumber_json/testsportsapi.json" }, glue = {
"com.sbtechtest.apssi"})

public class RunCukes_AT {

}
