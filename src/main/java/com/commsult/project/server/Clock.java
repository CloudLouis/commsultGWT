package com.commsult.project.server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Clock implements Sensors {
	
	private Double measurement;
	private PropertyChangeSupport support;
	
	public Clock(PropertyChangeListener pcl) {
		support = new PropertyChangeSupport(this);
		this.addPropertyChangeListener(pcl);
	}

	@Override
	public void updateMeasurement(Double measurement) {
		// TODO Auto-generated method stub
		support.firePropertyChange("Time", this.measurement, measurement);
	}

	@Override
	public void setMeasurement(Double measurement) {
		// TODO Auto-generated method stub
		this.measurement = measurement;
		updateMeasurement(measurement);
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
		support.addPropertyChangeListener(pcl);
	}

}
