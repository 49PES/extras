public class Triangle {
    private double sideA, sideB, sideC, angleA, angleB, angleC, area, perimeter;
    private int numSides, numAngles;

    public Triangle() {
        this(0, 0, 0, 0, 0, 0);
    }

    public Triangle(double a, double b, double c, double d, double e, double f) {
        sideA = a;
        sideB = b;
        sideC = c;
        angleA = d;
        angleB = e;
        angleC = f;

        this.enumerate();
        this.lawOfSines();
        this.lawOfCosines();
        this.enumerate();
    }

    private void heron() {
        area = perimeter / 2; // s = (a + b + c) / 2
        area *= (area - sideA) * (area - sideB) * (area - sideC); // A = Sqrt( s(s - a)(s - b)(s - b) ) w/ Heron's Formula
        area = Math.sqrt(area);
    }

    private void enumerate() {
        numSides = 0;
        numAngles = 0;
        if (sideA != 0.0F) numSides++;
        if (sideB != 0.0F) numSides++;
        if (sideC != 0.0F) numSides++;

        if (angleA != 0.0F) numAngles++;
        if (angleB != 0.0F) numAngles++;
        if (angleC != 0.0F) numAngles++;
        if (numAngles == 2) {
            if (angleA == 0.0) {
                angleA = 180 - angleB - angleC;
            }
            if (angleB == 0.0) {
                angleB = 180 - angleA - angleC;
            }
            if (angleC == 0.0) {
                angleC = 180 - angleA - angleB;
            }
            numAngles = 3;
        }

        perimeter = sideA + sideB + sideC;
        heron();
    }

    public double lawOfCosinesHelperAngles(double side1, double side2, double side3) {
        // Three sides - determine the angle opposite of "side3" (in degrees)

        /* a^2 + b^2 - 2ab cos C = c^2
          -2ab cos C = c^2 - a^2 - b^2
          cos C = (a^2 + b^2 - c^2)/(2ab)
          C = arccos( (a^2 + b^2 - c^2) / (2ab) )
          side1, side2, side3 = a, b, c
        */

        return Math.acos((Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(side3, 2)) / (2 * side1 * side2)) * 180 / Math.PI;
    }

    private double lawOfCosinesHelperSides(double side1, double side2, double angle3) {
        // Two sides and an angle in-between to determine the opposite angle

      /* a^2 + b^2 - 2abcosC = c^2
           sqrt(a^2 + b^2 - 2abcosC) = c
           side1, side2, angle3 = a, b, C  */
        
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2) - 2 * side1 * side2 * Math.cos(angle3 * Math.PI / 180));
    }

    private void lawOfCosines() {
        enumerate();
        if (numSides == 3) {
            if (angleA == 0.0) {
                angleA = lawOfCosinesHelperAngles(sideB, sideC, sideA);
            }
            if (angleB == 0.0) {
                angleB = lawOfCosinesHelperAngles(sideA, sideC, sideB);
            }
            if (angleC == 0.0) {
                angleC = lawOfCosinesHelperAngles(sideA, sideB, sideC);
            }
        }
        else if (numSides == 2 && numAngles >= 1) {

            // SAS Cases
            if (sideA == 0.0 && angleA != 0.0) {
                sideA = lawOfCosinesHelperSides(sideB, sideC, angleA);
                lawOfCosines();
            }

            if (sideB == 0.0 && angleB != 0.0) {
                sideB = lawOfCosinesHelperSides(sideA, sideC, angleB);
                lawOfCosines();
            }

            if (sideC == 0.0 && angleC != 0.0) {
                sideC = lawOfCosinesHelperSides(sideA, sideB, angleC);
                lawOfCosines();
            }
            /*
            // HLR Cases

            if(sideA == 0.0 && (angleB == 90.0 || angleC == 90.0) ){
                sideA = Math.sqrt( Math.abs( Math.pow(sideB, 2) - Math.pow(sideC, 2)   )  );
                lawOfCosines();
            }
            if(sideB == 0.0 && (angleA == 90.0 || angleC == 90.0) ){
                sideB = Math.sqrt( Math.abs( Math.pow(sideA, 2) - Math.pow(sideC, 2)   )  );
                lawOfCosines();
            }
            if(sideC == 0.0 && (angleA == 90.0 || angleB == 90.0) ){
                sideC = Math.sqrt( Math.abs( Math.pow(sideB, 2) - Math.pow(sideC, 2)   )  );
                lawOfCosines();
            } */
        }
        enumerate();
    }

    private void lawOfSines() {
        if (numAngles >= 2 && numSides >= 1) {
            // ASA Cases
            if (sideA != 0.0 && angleA != 0.0 && angleB != 0.0 && sideB == 0.0) {
                sideB = lawOfSinesHelper(sideA, angleA, angleB);
            }

            if (sideB != 0.0 && angleB != 0.0 && angleA != 0.0 && sideA == 0.0) {
                sideA = lawOfSinesHelper(sideB, angleB, angleA);
            }

            if (sideA != 0.0 && angleA != 0.0 && angleC != 0.0 && sideC == 0.0) {
                sideC = lawOfSinesHelper(sideA, angleA, angleC);
            }

            if (sideC != 0.0 && angleC != 0.0 && angleA != 0.0 && sideA == 0.0) {
                sideA = lawOfSinesHelper(sideC, angleC, angleA);
            }

            if (sideB != 0.0 && angleB != 0.0 && angleC != 0.0 && sideC == 0.0) {
                sideC = lawOfSinesHelper(sideB, angleB, angleC);
            }

            if (sideC != 0.0 && angleC != 0.0 && angleB != 0.0 && sideB == 0.0) {
                sideB = lawOfSinesHelper(sideC, angleC, angleB);
            }
        }

        if(numSides == 2 && numAngles == 1) {
            // SSA Cases (Only when triangle is known to be right [HLR] or obtuse - when an angle is greater or equal to 90.0 degrees )
            if(sideA != 0.0 && sideB != 0.0 && (angleA >= 90.0 || angleB >= 90.0 ) ){
                if(angleA == 0.0){angleA = Math.asin( (sideA * Math.sin(angleB * Math.PI / 180)) / sideB  ) * 180 / Math.PI; }
                if(angleB == 0.0){angleB = Math.asin( (sideB * Math.sin(angleA * Math.PI / 180)) / sideA  ) * 180 / Math.PI; }
            }

            if(sideB != 0.0 && sideC != 0.0 && (angleB >= 90.0 || angleC >= 90.0 ) ){
                if(angleB == 0.0){angleB = Math.asin( (sideB * Math.sin(angleC * Math.PI / 180)) / sideC  ) * 180 / Math.PI; }
                if(angleC == 0.0){angleC = Math.asin( (sideC * Math.sin(angleB * Math.PI / 180)) / sideB  ) * 180 / Math.PI; }
            }

            if(sideA != 0.0 && sideC != 0.0 && (angleA >= 90.0 || angleC >= 90.0 ) ){
                if(angleA == 0.0){angleA = Math.asin( (sideA * Math.sin(angleC * Math.PI / 180)) / sideC  ) * 180 / Math.PI; }
                if(angleC == 0.0){angleC = Math.asin( (sideC * Math.sin(angleA * Math.PI / 180)) / sideA  ) * 180 / Math.PI; }
            }

        }

    }

    private double lawOfSinesHelper(double side1, double angle1, double angle2) {
        // Law of sines: b/sinB = a/sinA
        // b = (a * sinB) / sinA
        double side2 = side1 * Math.sin(angle2 * Math.PI / 180) / Math.sin(angle1 * Math.PI / 180);
        return side2;
    }

    public String toString() {
        enumerate();
        String output = "Side A: " + sideA + ",  Angle A: " + angleA;
        output += "\nSide B: " + sideB + ",  Angle B: " + angleB;
        output += "\nSide C: " + sideC + ",  Angle C: " + angleC;
        output += "\nPerimeter: " + perimeter;
        output += "\nArea: " + area;
        output += "\n# of Sides: " + numSides;
        output += "\n# of Angles: " + numAngles;
        return output;
    }

    public static void main(String[] args) {
        // Triangle babyRight = new Triangle(3, 4, 0, 0, 0, 90);
        // Triangle equilateral = new Triangle(4, 4, 4, 0, 0, 0);
         Triangle threeSixNine = new Triangle(2, 2 * Math.sqrt(3), 0, 30, 60, 0);
         System.out.println(threeSixNine.toString());
        Triangle fourFive = new Triangle(0, 4, 5, 0, 0, 90);
        System.out.println("\n" +fourFive.toString());
        Triangle threeFive = new Triangle(3, 0, 5, 0, 0, 90);
        System.out.println("\n" + threeFive.toString());
    }
}
