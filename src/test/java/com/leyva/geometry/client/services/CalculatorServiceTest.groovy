package com.leyva.geometry.client.services

import com.leyva.geometry.client.contollers.CalculatorClient
import com.leyva.geometry.client.model.QueueShape
import com.leyva.geometry.client.model.Shape
import com.leyva.geometry.client.repositories.ShapeRepository
import spock.lang.Specification

class CalculatorServiceTest extends Specification {

    private CalculatorClient calculatorClient
    private ShapeRepository shapeRepository

    private CalculatorService calculatorService

    def setup(){
        calculatorClient = Mock(CalculatorClient)
        shapeRepository = Mock(ShapeRepository)

        calculatorService = new CalculatorService(calculatorClient, shapeRepository)
    }

    def "test calculateCubeIsCalledOnWhenTypeIsCube"(){
        given:
        QueueShape queueShape = new QueueShape()
        queueShape.setShapeType("Cube")
        queueShape.setWidth(10)

        when:
        calculatorService.determineEndpoint(queueShape)

        then:
        1 * calculatorClient.calculateCube(queueShape.getWidth())
    }

    def "test calculateRectangularPrismIsCalledOnWhenTypeIsRectangularPrism"(){
        given:
        QueueShape queueShape = new QueueShape()
        queueShape.setShapeType("RectangularPrism")
        queueShape.setWidth(10)
        queueShape.setHeight(5)
        queueShape.setDepth(2)

        when:
        calculatorService.determineEndpoint(queueShape)

        then:
        1 * calculatorClient.calculateRectangularPrism(queueShape.getWidth(), queueShape.getHeight(), queueShape.getDepth())
    }

    def "test noMethodCalledOnWhenShapeIsUnknown"(){
        given:
        QueueShape queueShape = new QueueShape()
        queueShape.setShapeType("Circle")

        when:
        calculatorService.determineEndpoint(queueShape)

        then:
        0 * calculatorClient._
    }

    def "test shapeRepositoryNotHitWhenShapeIsNull"(){
        given:
        QueueShape queueShape = new QueueShape()
        queueShape.setShapeType("Circle")

        when:
        calculatorService.processShape(queueShape)

        then:
        0 * shapeRepository.displayShape(_)
    }

    def "test shapeRepositoryHitWhenShapeIsNotNull"(){
        given:
        QueueShape queueShape = new QueueShape()
        queueShape.setShapeType("Cube")
        queueShape.setWidth(10)

        when:
        calculatorClient.calculateCube(queueShape.getWidth()) >> {new Shape()}

        calculatorService.processShape(queueShape)

        then:
        1 * shapeRepository.displayShape(_)
    }


}
