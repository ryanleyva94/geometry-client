package com.leyva.geometry.client.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyva.geometry.client.model.Shape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ShapeRepository {

    private final ObjectMapper objectMapper;

    public ShapeRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void displayShape(Shape shape) throws IOException {
        Date date = Date.valueOf(LocalDate.now());
        String fileName = date + "_shapes.txt";

        File file = new File(fileName);
        boolean exists = file.exists();

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, exists));
        writer.write(objectMapper.writeValueAsString(shape));
        writer.newLine();
        writer.close();
    }

}
