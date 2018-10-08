package com.commsult.project.server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.gwt.core.shared.GWT;


public class Thermometer implements Sensors {
	
	private Double measurement;
	private PropertyChangeSupport support;
	
	public Thermometer(PropertyChangeListener pcl) {
		support = new PropertyChangeSupport(this);
		addPropertyChangeListener(pcl);
	}

	@Override
	public void updateMeasurement(Double measurement) {
		support.firePropertyChange("Temperature", this.measurement, measurement);
	}

	@Override
	public void setMeasurement(Double measurement) {
		// TODO Auto-generated method stub
		GWT.log("set");
		support.firePropertyChange("Temperature", this.measurement, measurement);
		this.measurement = measurement;
	}

	@Override
	public Double getMeasurement() {
		// TODO Auto-generated method stub
		return measurement;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		// TODO Auto-generated method stub
		support.addPropertyChangeListener(pcl);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		// TODO Auto-generated method stub
		support.removePropertyChangeListener(pcl);
	}

}
