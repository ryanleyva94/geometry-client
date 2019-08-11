package com.leyva.geometry.client.services;

import com.leyva.geometry.client.contollers.CalculatorClient;
import com.leyva.geometry.client.model.Shape;
import com.leyva.geometry.client.repositories.ShapeRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final CalculatorClient calculatorClient;
    private final ShapeRepository shapeRepository;

    public CalculatorService(CalculatorClient calculatorClient, ShapeRepository shapeRepository) {
        this.calculatorClient = calculatorClient;
        this.shapeRepository = shapeRepository;
    }

    public void processShape(){
        Shape shape = calculatorClient.calculateCube(5);
        shapeRepository.displayShape(shape);
    }

}
