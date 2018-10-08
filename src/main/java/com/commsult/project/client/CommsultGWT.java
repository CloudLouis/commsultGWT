package com.commsult.project.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CommsultGWT implements EntryPoint, PropertyChangeListener {
	
	  
	  private int upperbound = 5000;
	  private int lowerbound = 5000;
	  private int CLOCK_INTERVAL = 1000;
	  private Random rand = new Random(10);
	  private int TEMP_INTERVAL = lowerbound + rand.nextInt(upperbound);
	  private int WIND_INTERVAL = lowerbound + rand.nextInt(upperbound);;
	  private Label labelPrint = new Label();
	  private HorizontalPanel valuePanel = new HorizontalPanel();
	  private static Label tempValue = new Label();
	  private static Label timeValue = new Label();
	  private static Label windValue = new Label();
	  
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
	  private static ArrayList<Sensors> sensors = new ArrayList<>();

	public void onModuleLoad() {
		sensors.add(new Thermometer(this));
		sensors.add(new Anemometer(this));
		sensors.get(0).setMeasurement((-10.0 + rand.nextDouble() * 40.0));
		sensors.get(1).setMeasurement((0.0 + rand.nextDouble() *70.0));
		try {
			refreshClock();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	          try {
				refreshClock();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
	    };
	    clockTimer.scheduleRepeating(CLOCK_INTERVAL);
	    
	    Timer tempTimer = new Timer() {
	    	@Override
	    	public void run() {
	    		sensors.get(0).setMeasurement((-10.0 + rand.nextDouble() * 40.0));
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
	

	public void refreshClock() throws ParseException {
		t = new Date();
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
		@SuppressWarnings("deprecation")
		String formattedtime = DateTimeFormat.getMediumTimeFormat().format(t);
		timeValue.setText(formattedtime);
		Date comp = parser.parse(formattedtime);
		Date limit = parser.parse("18:00:00");
		if(comp.after(limit)) {
			buttonLb.getElement().getStyle().setColor("yellow");
		}else {
			buttonLb.getElement().getStyle().setColor("black");
		}
	}
	
	public void updateTemp(Double val) {
		tempValue.setText((val.toString().substring(0, 4))+"\u00b0C");
		if(val>25.00) {
			buttonAc.getElement().getStyle().setColor("yellow");
		}else {
			buttonAc.getElement().getStyle().setColor("black");
		}
	}
	
	public void updateWind(Double val) {
		windValue.setText((val.toString().substring(0, 4))+" m/h");
		if(val>15.00) {
			buttonB.getElement().getStyle().setColor("yellow");
		}else {
			buttonB.getElement().getStyle().setColor("black");
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		GWT.log("change");
		if (isTemperature(evt)) {
			updateTemp((Double)evt.getNewValue());
		}
		else if (isWind(evt)) {
			updateWind((Double)evt.getNewValue());
		}
		else if (isTime(evt)) {
			String event = evt.getPropertyName() + " "+evt.getNewValue();

		}
	}

	private boolean isTime(PropertyChangeEvent evt) {
		return evt.getPropertyName().equalsIgnoreCase("Time");
	}

	private boolean isWind(PropertyChangeEvent evt) {
		return evt.getPropertyName().equalsIgnoreCase("Wind");
	}

	private boolean isTemperature(PropertyChangeEvent evt) {
		return evt.getPropertyName().equalsIgnoreCase("Temperature");
	}
}
