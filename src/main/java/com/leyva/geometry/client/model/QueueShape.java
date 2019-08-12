package com.leyva.geometry.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueueShape {

    @JsonProperty("shape")
    private String shapeType;
    @JsonProperty("width")
    private double width;
    @JsonProperty("length")
    private double depth;
    @JsonProperty("height")
    private double height;
    @JsonProperty("radius")
    private double radius;

    public String getShapeType() {
        return shapeType;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
