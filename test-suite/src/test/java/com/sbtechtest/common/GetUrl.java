package com.sbtechtest.common;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetUrl {
	// This class helps the suite be run on various environments based on envName selection: qa, stag, dev

	String nameofpage ;
	String url;
	public static final String ENVNAME = System.getenv("envName");

	private static GetUrl instance = new GetUrl();
	private GetUrl(){}

	//Get the only object available
	public static GetUrl getInstance(){
		return instance;
	}
	public String readUrlFile(String readstr) throws IOException {

		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+ENVNAME+"suite.properties";
		System.out.println("Print"+ filePath);
		BufferedReader inFile = new BufferedReader(new FileReader(filePath));
		String line;
		String[] retarr = new String[2];
		while((line=inFile.readLine())!= null	){
			String[] temp = line.split("=");

			retarr[0] = temp[0];
			if (retarr[0].equals(readstr)){
				retarr[1] = temp[1];
			} else {
				//System.out.println("do nothing");
			}

		}
		System.out.println("The url is"+ retarr[1]);
		inFile.close();
		return retarr[1];
	}
	public String getFootBallLive() throws IOException{
		System.out.println("returning"+readUrlFile("baseUri")+"football/live");
		return (readUrlFile("baseUri")+"football/live");
	}

	public String getSportsEventUrl() throws IOException{
		System.out.println("returning"+readUrlFile("baseUri")+"sportsbook/event");
		return (readUrlFile("baseUri")+"sportsbook/event");
	}
	public String getSportsMarketUrl() throws IOException{
		System.out.println("returning"+readUrlFile("baseUri")+"sportsbook/market");
		return (readUrlFile("baseUri")+"sportsbook/market");
	}
	public String getSportsOutComeUrl() throws IOException{
		System.out.println("returning"+readUrlFile("baseUri")+"sportsbook/outcome");
		return (readUrlFile("baseUri")+"sportsbook/outcome");
	}

}


