package org.sbtechtest.runcukes;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/sanity/TestSportsApi.feature", plugin = {
"org.sbtechtest.common.CustomCucumberJSONFormatter:target/$BUILD_ID/cucumber/cucumber_json/testsportsapi.json" }, glue = {
"org.sbtechtest.api"})
public class Sportsbookapi_AT {

}
