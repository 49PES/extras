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

    public void enumerate() {
        numSides = 0;
        numAngles = 0;
        if (sideA != 0.0) numSides++;
        if (sideB != 0.0) numSides++;
        if (sideC != 0.0) numSides++;

        if (angleA != 0.0) numAngles++;
        if (angleB != 0.0) numAngles++;
        if (angleC != 0.0) numAngles++;
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
          side1, side2, side3 = a, b, c */
        

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
        }
        enumerate();
    }

    private void lawOfSines() {
        if (numAngles >= 2 && numSides >= 1) {
            if (sideA != 0.0 && angleA != 0.0 && angleB != 0.0 && sideB == 0.0) {
                sideB = lawOfSinesHelper(sideA, angleA, angleB);
                lawOfCosines();
            }

            if (sideB != 0.0 && angleB != 0.0 && angleA != 0.0 && sideA == 0.0) {
                sideA = lawOfSinesHelper(sideB, angleB, angleA);
                lawOfCosines();
            }

            if (sideA != 0.0 && angleA != 0.0 && angleC != 0.0 && sideC == 0.0) {
                sideC = lawOfSinesHelper(sideA, angleA, angleC);
                lawOfCosines();
            }

            if (sideC != 0.0 && angleC != 0.0 && angleA != 0.0 && sideA == 0.0) {
                sideA = lawOfSinesHelper(sideC, angleC, angleA);
                lawOfCosines();
            }

            if (sideB != 0.0 && angleB != 0.0 && angleC != 0.0 && sideC == 0.0) {
                sideC = lawOfSinesHelper(sideB, angleB, angleC);
                lawOfCosines();
            }

            if (sideC != 0.0 && angleC != 0.0 && angleB != 0.0 && sideB == 0.0) {
                sideB = lawOfSinesHelper(sideC, angleC, angleB);
                lawOfCosines();
            }
        }


    }

    public double lawOfSinesHelper(double side1, double angle1, double angle2) {
        // Law of sines: b/sinB = a/sinA
        // b = (a * sinB) / sinA
        return side1 * Math.sin(angle2 * Math.PI / 180) / Math.sin(angle1 * Math.PI / 180);
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
        // Triangle threeSixNine = new Triangle(2, 2 * Math.sqrt(3), 0, 30, 60, 0);
        // System.out.println(threeSixNine.toString());
    }
}
