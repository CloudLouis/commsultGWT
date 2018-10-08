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
	  private Label tempLabel = new Label();
	  private Label timeLabel = new Label();
	  private Label windLabel = new Label();
	  private VerticalPanel mainPanel = new VerticalPanel();	

	public void onModuleLoad() {
		MainController controllerObserver = new MainController();
		ArrayList<Sensors> sensors = new ArrayList<>();
		sensors.add(new Thermometer(controllerObserver));
		sensors.get(0).setMeasurement(10.00);
		valuePanel.add(tempLabel);
		valuePanel.add(timeLabel);
		valuePanel.add(windLabel);
	}
	
	public void printSomething(String toprint) {
		labelPrint.setText(toprint);
	}

	
}
