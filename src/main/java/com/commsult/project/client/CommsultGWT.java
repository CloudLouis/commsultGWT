package com.commsult.project.client;

import java.util.ArrayList;

import com.commsult.project.server.Anemometer;
import com.commsult.project.server.Clock;
import com.commsult.project.server.MainController;
import com.commsult.project.server.Sensors;
import com.commsult.project.server.Thermometer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CommsultGWT implements EntryPoint {
	
	  private Label labelPrint = new Label();
	  private HorizontalPanel valuePanel = new HorizontalPanel();
	  private Label tempValue = new Label();
	  private Label timeValue = new Label();
	  private Label windValue = new Label();
	  private HorizontalPanel imagePanel = new HorizontalPanel();
	  private Image airconditioning = new Image("images/AirConditioning.png");
	  private Image blinds = new Image("images/Blinds.png");
	  private Image lightbulb = new Image("images/LightBulb.png");
	  
	  //OwO

	public void onModuleLoad() {
		MainController controllerObserver = new MainController();
		ArrayList<Sensors> sensors = new ArrayList<>();
		sensors.add(new Thermometer(controllerObserver));
		sensors.add(new Clock(controllerObserver));
		sensors.add(new Anemometer(controllerObserver));
		sensors.get(0).setMeasurement(24.00);
		sensors.get(1).setMeasurement(0000.00);
		sensors.get(2).setMeasurement(10.00);
		tempValue.setText(sensors.get(0).getMeasurement().toString());
		timeValue.setText(sensors.get(1).getMeasurement().toString());
		windValue.setText(sensors.get(2).getMeasurement().toString());
		
		
		valuePanel.add(tempValue);
		valuePanel.add(timeValue);
		valuePanel.add(windValue);
		valuePanel.addStyleName("valuePanel");
		
		RootPanel.get("mainContainer").add(valuePanel);
		
		imagePanel.add(airconditioning);
		imagePanel.add(lightbulb);
		imagePanel.add(blinds);
		imagePanel.addStyleName("imagePanel");
		
		RootPanel.get("mainContainer").add(imagePanel);
	}
	
	public void printSomething(String toprint) {
		labelPrint.setText(toprint);
	}

	
}
