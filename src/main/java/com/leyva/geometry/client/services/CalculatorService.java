package com.leyva.geometry.client.services;

import com.leyva.geometry.client.contollers.CalculatorClient;
import com.leyva.geometry.client.model.QueueShape;
import com.leyva.geometry.client.model.Shape;
import com.leyva.geometry.client.repositories.ShapeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorService {

    private static Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    private final CalculatorClient calculatorClient;
    private final ShapeRepository shapeRepository;

    public CalculatorService(CalculatorClient calculatorClient, ShapeRepository shapeRepository) {
        this.calculatorClient = calculatorClient;
        this.shapeRepository = shapeRepository;
    }

    public void processShape(QueueShape queueShape){
        Shape shape = determineEndpoint(queueShape);

        if(shape != null){
            shapeRepository.displayShape(shape);
        }

    }

    Shape determineEndpoint(QueueShape queueShape){
        Shape shape = null;

        switch (queueShape.getShapeType().toUpperCase()){
            case "CUBE":
                shape = calculatorClient.calculateCube(queueShape.getWidth());
                break;
            case "RECTANGULARPRISM":
                shape = calculatorClient.calculateRectangularPrism(queueShape.getWidth(), queueShape.getHeight(), queueShape.getDepth());
                break;
            default:
                logger.warn("The shape: " + queueShape.getShapeType() + " is not yet able to be processed by the server.");
        }

        return shape;
    }

}
