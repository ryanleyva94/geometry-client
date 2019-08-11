package com.leyva.geometry.client.repositories;

import com.leyva.geometry.client.model.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ShapeRepository {

    private static Logger log = LoggerFactory.getLogger(ShapeRepository.class);

    public void displayShape(Shape shape){
        log.info("Shape: " + shape.getShapeName());
        log.info("Volume: " + shape.getVolume());
        log.info("Surface Area: " + shape.getSurfaceArea());
    }

}
