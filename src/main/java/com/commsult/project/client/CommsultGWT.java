package com.commsult.project.client;

import java.util.ArrayList;

import com.commsult.project.server.MainController;
import com.commsult.project.server.Sensors;
import com.commsult.project.server.Thermometer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	  private VerticalPanel mainPanel = new VerticalPanel();	

	public void onModuleLoad() {
		MainController controllerObserver = new MainController();
		ArrayList<Sensors> sensors = new ArrayList<>();
		sensors.add(new Thermometer(controllerObserver));
		sensors.get(0).setMeasurement(10.00);
		tempLabel.setText("It's temperature!");
		timeLabel.setText("It's time!");
		windLabel.setText("It's wind!");
		
		tempPanel.add(tempLabel);
		timePanel.add(timeLabel);
		windPanel.add(windLabel);
		
		tempValue.setText("100");
		timeValue.setText("10");
		windValue.setText("250!");
		
		tempPanel.add(tempValue);
		timePanel.add(timeValue);
		windPanel.add(windValue);
	
		valuePanel.add(tempPanel);
		valuePanel.add(timePanel);
		valuePanel.add(windPanel);
		valuePanel.addStyleName("valuePanel");
		
		RootPanel.get("mainContainer").add(valuePanel);
	}
	
	public void printSomething(String toprint) {
		labelPrint.setText(toprint);
	}

	
}
