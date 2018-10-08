package com.commsult.project.client;

import java.util.ArrayList;

import com.commsult.project.server.MainController;
import com.commsult.project.server.Sensors;
import com.commsult.project.server.Thermometer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CommsultGWT implements EntryPoint {
	
	  private Label labelPrint = new Label();

	public void onModuleLoad() {
		MainController controllerObserver = new MainController();
		ArrayList<Sensors> sensors = new ArrayList<>();
		sensors.add(new Thermometer(controllerObserver));
		sensors.get(0).setMeasurement(10.00);
		
	}
	
	public void printSomething(String toprint) {
		labelPrint.setText(toprint);
		RootPanel.get("testprint").add(labelPrint);
	}

	
}
