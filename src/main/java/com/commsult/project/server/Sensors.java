package com.commsult.project.server;

import java.beans.PropertyChangeListener;

public interface Sensors {
	public void updateMeasurement(Double measurement);
	public void setMeasurement(Double measurement);
	public Double getMeasurement();
	public void addPropertyChangeListener(PropertyChangeListener pcl);
	public void removePropertyChangeListener(PropertyChangeListener pcl);
}
