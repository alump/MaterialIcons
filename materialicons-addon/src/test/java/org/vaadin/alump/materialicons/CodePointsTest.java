package org.vaadin.alump.materialicons;

import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Created by alump on 15/06/2017.
 */
public class CodePointsTest {

    public static class CodePoint {
        private final String name;
        private final int codePoint;

        public CodePoint(String name, int codePoint) {
            this.name = name;
            this.codePoint = codePoint;
        }

        public String getName() {
            return name;
        }

        public String getEnumName() {
            String enumName = name.toUpperCase();
            if(!Character.isAlphabetic(enumName.charAt(0))) {
                enumName = "_" + enumName;
            }
            return enumName;
        }

        public int getCodePoint() {
            return codePoint;
        }
    }

    private List<CodePoint> readCodePoints() {
        try {
            List<CodePoint> codePoints = new ArrayList<>();
            Stream<String> stream = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/codepoints"))).lines();

            stream.forEach(line -> {
                String[] parts = line.split("\\s", 2);
                int code = Integer.parseInt(parts[1], 16);
                codePoints.add(new CodePoint(parts[0], code));
            });

            return codePoints;
        } catch(Exception e) {
            throw new RuntimeException("Failed to read code points", e);
        }
    }

    @Test
    public void checkCodePoints() {
        List<CodePoint> codePoints = readCodePoints();

        try {
            Assert.assertEquals(codePoints.size(), MaterialIcons.values().length);
            codePoints.forEach(codePoint -> {
                MaterialIcons.valueOf(codePoint.getEnumName());
            });
        } catch(AssertionError e) {
            System.err.println("Code points file do not match with enumeration class!");
            generateUpdatedEnums(codePoints);
            throw e;
        }
    }

    private void generateUpdatedEnums(List<CodePoint> codePoints) {
        StringJoiner sj = new StringJoiner(",\n");
        codePoints.forEach(codePoint -> {
            sj.add(codePoint.getEnumName() + "(0x" + Integer.toString(codePoint.getCodePoint(), 16) + ")");
        });
        System.out.print(sj.toString());
    }
}
