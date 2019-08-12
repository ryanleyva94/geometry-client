package com.leyva.geometry.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shape {

    @JsonProperty("shapeName")
    private String shapeName;
    @JsonProperty("volume")
    private double volume;
    @JsonProperty("surfaceArea")
    private double surfaceArea;

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }
}
