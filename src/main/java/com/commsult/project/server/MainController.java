package com.commsult.project.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.commsult.project.client.CommsultGWT;

public class MainController implements PropertyChangeListener {
	
	public Double measurement;
	public CommsultGWT commsultgwt = new CommsultGWT();

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		System.out.println(evt.getPropertyName() + " " + evt.getOldValue());
		System.out.println(evt.getPropertyName() + " " + evt.getNewValue());
		if (isTemperature(evt)) {
			String event = evt.getPropertyName() + " "+evt.getNewValue();
			commsultgwt.updateTemp();
		}
		else if (isWind(evt)) {
			String event = evt.getPropertyName() + " "+evt.getNewValue();
			commsultgwt.updateWind();
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
