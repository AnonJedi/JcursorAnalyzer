package com.omstu.cursorAnalyzer.repository;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.RepositoryException;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ParamsRepository {

    public static void savePoints(List<Point> points) throws RepositoryException {
        String filename = "point.txt";
        File file = new File(Common.OUT_PATH + '/' + filename);
        int i = 1;
        while (file.exists()) {
            file = new File(Common.OUT_PATH + '/' + filename.split("\\.")[0] + i + ".txt");
            i++;
        }
        try {
            if (file.createNewFile()) {
                FileOutputStream outputStream = new FileOutputStream(file);
                for (Point p : points) {
                    outputStream.write((p.getX() + " " + p.getY() + " 1\n").getBytes());
                }
                outputStream.close();
            } else {
                System.out.println("Cannot create point file");
            }
        } catch (IOException e) {
            String error = "Error occurred while file create";
            throw new RepositoryException(error);
        }
    }
}
