package com.sbtechtest.common;
import static cucumber.runtime.Utils.toURL;
import gherkin.formatter.JSONFormatter;

import java.io.IOException;
import java.net.URL;

import cucumber.runtime.io.URLOutputStream;
import cucumber.runtime.io.UTF8OutputStreamWriter;

public class CustomCucumberJSONFormatter extends JSONFormatter {
	// This class helps with producing cucumber html reports against each maven test build
	public CustomCucumberJSONFormatter(URL url) throws IOException {
		super(new UTF8OutputStreamWriter(new URLOutputStream(toURL(url.toString().replaceAll("\\$BUILD_ID",
				System.getenv("jenkins.buildId") != null ? System.getenv("jenkins.buildId") : "localhost")))));

		System.out.println("toString:" + url.toString().replaceAll("\\$BUILD_ID",
				System.getenv("jenkins.buildId") != null ? System.getenv("jenkins.buildId") : "localhost"));

	}
}
