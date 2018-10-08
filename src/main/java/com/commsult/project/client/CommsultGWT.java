package com.commsult.project.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gwt.i18n.client.DateTimeFormat;

import com.commsult.project.server.Anemometer;
import com.commsult.project.server.Clock;
import com.commsult.project.server.MainController;
import com.commsult.project.server.Sensors;
import com.commsult.project.server.Thermometer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CommsultGWT implements EntryPoint {
	
	  private int upperbound = 30000;
	  private int lowerbound = 5000;
	  private int CLOCK_INTERVAL = 1000;
	  private Random rand = new Random(10);
	  private int TEMP_INTERVAL = rand.nextInt(upperbound-lowerbound) + lowerbound;
	  private int WIND_INTERVAL = rand.nextInt(upperbound-lowerbound) + lowerbound;
	  private Label labelPrint = new Label();
	  private HorizontalPanel valuePanel = new HorizontalPanel();
	  private Label tempValue = new Label();
	  private Label timeValue = new Label();
	  private Label windValue = new Label();
	  
	  private VerticalPanel bottomPanel = new VerticalPanel();
	  
	  private HorizontalPanel imagePanel = new HorizontalPanel();
	  private Image airconditioning = new Image("images/AirConditioning.png");
	  private Image blinds = new Image("images/Blinds.png");
	  private Image lightbulb = new Image("images/LightBulb.png");

	  private HorizontalPanel titlePanel = new HorizontalPanel();
	  private Label titleAc = new Label("Air Conditioning");
	  private Label titleB = new Label("Blinds");
	  private Label titleLb = new Label("Lights");
	  
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  private Label buttonAc = new Label("\u25A0");
	  private Label buttonB = new Label("\u25A0");
	  private Label buttonLb = new Label("\u25A0");
	  
	  private Date t;
	  private ArrayList<Sensors> sensors = new ArrayList<>();

	public void onModuleLoad() {
		MainController controllerObserver = new MainController();
		sensors.add(new Thermometer(controllerObserver));
		sensors.add(new Anemometer(controllerObserver));
		sensors.get(0).setMeasurement(24.00);
		sensors.get(1).setMeasurement(10.00);
		refreshClock();
		
		tempValue.addStyleDependentName("Temperature");
		valuePanel.add(tempValue);
		timeValue.addStyleDependentName("Time");
		valuePanel.add(timeValue);
		windValue.addStyleDependentName("Wind");
		valuePanel.add(windValue);
		valuePanel.addStyleName("valuePanel");
		
		RootPanel.get("mainContainer").add(valuePanel);
		
		titlePanel.add(titleAc);
		titlePanel.add(titleLb);
		titlePanel.add(titleB);
		titlePanel.addStyleName("titlePanel");
		
		imagePanel.add(airconditioning);
		lightbulb.addStyleDependentName("lightbulb");
		imagePanel.add(lightbulb);
		imagePanel.add(blinds);
		imagePanel.addStyleName("imagePanel");
		
		buttonPanel.add(buttonAc);
		buttonPanel.add(buttonLb);
		buttonPanel.add(buttonB);
		buttonPanel.addStyleName("buttonPanel");

		bottomPanel.add(titlePanel);
		bottomPanel.add(imagePanel);
		bottomPanel.add(buttonPanel);
		RootPanel.get("mainContainer").add(bottomPanel);
		
	    Timer clockTimer = new Timer() {
	    	@Override
	    	public void run() {
	          refreshClock();
	    	}
	    };
	    clockTimer.scheduleRepeating(CLOCK_INTERVAL);
	    
	    Timer tempTimer = new Timer() {
	    	@Override
	    	public void run() {
	    		sensors.get(0).setMeasurement((-10.0 + rand.nextDouble() * 30.0));
	    	}
	    };
	    tempTimer.scheduleRepeating(TEMP_INTERVAL);
	    
	    Timer windTimer = new Timer() {
	    	@Override
	    	public void run() {
	    		sensors.get(1).setMeasurement((0.0 + rand.nextDouble() *70.0));
	    	}
	    };
	    windTimer.scheduleRepeating(WIND_INTERVAL);
	}
	

	public void refreshClock() {
		t = new Date();
		@SuppressWarnings("deprecation")
		String formattedtime = DateTimeFormat.getMediumTimeFormat().format(t);
		timeValue.setText(formattedtime);
	}
	
	public void updateTemp() {
		tempValue.setText("FUCK");
	}
	
	public void updateWind() {
		windValue.setText("FUCK");
	}
	
	
}
