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

    private void enumerate() {
        numSides = 0;
        numAngles = 0;
        if (sideA != 0.0) numSides++;
        if (sideB != 0.0) numSides++;
        if (sideC != 0.0) numSides++;

        if (angleA != 0.0) numAngles++;
        if (angleB != 0.0) numAngles++;
        if (angleC != 0.0) numAngles++;

        // Deduce third angle given two angles using 180 - <1 - <2
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

    private void heron() {
        // Area of a triangle =sqrt( s(s - a)(s - b)(s - b) ) w/ Heron's Formula
        area = perimeter / 2; // s = (a + b + c) / 2  <- Semi-Perimeter of the Triangle
        area *= (area - sideA) * (area - sideB) * (area - sideC);
        area = Math.sqrt(area);
    }


    private void lawOfCosines() {
      // Law of Cosines: a^2 + b^2 - 2ab cosC = c^2
        enumerate();

        // SSS Case - determining angle C using a, b, c
        if (numSides == 3) {
            // Update angle measure only if angle is not already known
            if (angleA == 0.0) {
                angleA = lawOfCosinesHelperSSS(sideB, sideC, sideA);
            }
            if (angleB == 0.0) {
                angleB = lawOfCosinesHelperSSS(sideA, sideC, sideB);
            }
            if (angleC == 0.0) {
                angleC = lawOfCosinesHelperSSS(sideA, sideB, sideC);
            }
        }

        // SAS Cases - determining side c using a, b, and angle C
        else if (numSides == 2 && numAngles >= 1) {
            if (sideA == 0.0 && angleA != 0.0) {
                sideA = lawOfCosinesHelperSAS(sideB, sideC, angleA);
                lawOfCosines(); // Once all three sides are known, you can calculate all the angles
            }

            if (sideB == 0.0 && angleB != 0.0) {
                sideB = lawOfCosinesHelperSAS(sideA, sideC, angleB);
                lawOfCosines();
            }

            if (sideC == 0.0 && angleC != 0.0) {
                sideC = lawOfCosinesHelperSAS(sideA, sideB, angleC);
                lawOfCosines();
            }
        }
    }

    public double lawOfCosinesHelperSSS(double side1, double side2, double side3) {
        // Three sides - determine the angle opposite of "side3" (in degrees)

        /* a^2 + b^2 - 2ab cos C = c^2
          -2ab cos C = c^2 - a^2 - b^2
          cos C = (a^2 + b^2 - c^2)/(2ab)
          C = arccos( (a^2 + b^2 - c^2) / (2ab) )
          side1, side2, side3 = a, b, c
        */

        return MathC.acosd((Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(side3, 2)) / (2 * side1 * side2)) ;
    }

    private double lawOfCosinesHelperSAS(double side1, double side2, double angle3) {
        // Two sides and an angle in-between to determine the opposite angle

      /* a^2 + b^2 - 2abcosC = c^2
           sqrt(a^2 + b^2 - 2abcosC) = c
           side1, side2, angle3 = a, b, C  */
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2) - 2 * side1 * side2 * MathC.cosd(angle3));
    }


    private void lawOfSines() {
      // sinA/a = sinB/b

        // ASA / AAS Cases
        if (numAngles >= 2 && numSides >= 1) {

            if (sideA != 0.0 && angleA != 0.0 && angleB != 0.0 && sideB == 0.0) {
                sideB = lawOfSinesHelperASA(sideA, angleA, angleB);
                lawOfCosines();
            }

            if (sideB != 0.0 && angleB != 0.0 && angleA != 0.0 && sideA == 0.0) {
                sideA = lawOfSinesHelperASA(sideB, angleB, angleA);
                lawOfCosines();
            }

            if (sideA != 0.0 && angleA != 0.0 && angleC != 0.0 && sideC == 0.0) {
                sideC = lawOfSinesHelperASA(sideA, angleA, angleC);
                lawOfCosines();
            }

            if (sideC != 0.0 && angleC != 0.0 && angleA != 0.0 && sideA == 0.0) {
                sideA = lawOfSinesHelperASA(sideC, angleC, angleA);
                lawOfCosines();
            }

            if (sideB != 0.0 && angleB != 0.0 && angleC != 0.0 && sideC == 0.0) {
                sideC = lawOfSinesHelperASA(sideB, angleB, angleC);
                lawOfCosines();
            }

            if (sideC != 0.0 && angleC != 0.0 && angleB != 0.0 && sideB == 0.0) {
                sideB = lawOfSinesHelperASA(sideC, angleC, angleB);
                lawOfCosines();
            }
        }

        // SSA Cases (Only when triangle is known to be right [HLR] or obtuse - when an angle >= 90.0 degrees )
        if(numSides == 2 && numAngles == 1) {

            if(sideC == 0.0 && (angleA >= 90.0 || angleB >= 90.0 ) ){
                if(angleA == 0.0){
                    angleA = lawOfSinesHelperSSA(sideB, sideA, angleB);
                    enumerate(); lawOfSines();
                }

                if(angleB == 0.0){
                    angleB = lawOfSinesHelperSSA(sideA, sideB, angleA);
                    enumerate(); lawOfSines();
                }
            }

            if(sideA == 0.0 && (angleB >= 90.0 || angleC >= 90.0 ) ){
                if(angleB == 0.0){
                    angleB = lawOfSinesHelperSSA(sideC, sideB, angleC);
                    enumerate(); lawOfSines();
                }
                if(angleC == 0.0){
                    angleC = lawOfSinesHelperSSA(sideB, sideC, angleB);
                    enumerate(); lawOfSines();
                }
            }

            if(sideB == 0.0 &&  (angleA >= 90.0 || angleC >= 90.0 ) ){
                if(angleA == 0.0){
                    angleA = lawOfSinesHelperSSA(sideC, sideA, angleC);
                    enumerate(); lawOfSines();
                }

                if(angleC == 0.0){
                    angleC = lawOfSinesHelperSSA(sideA, sideC, angleA);
                    enumerate(); lawOfSines();
                }
            }

        }

    }

    private double lawOfSinesHelperASA(double side1, double angle1, double angle2) {
        // Law of sines: b/sinB = a/sinA
        // b = (a * sinB) / sinA
        double side2 = side1 * MathC.sind(angle2) / MathC.sind(angle1);
        return side2;
    }

    private double lawOfSinesHelperSSA(double side1, double side2, double angle1){
        // sinB/b = sinC/c
        // sinB = bsinC/c
        // B =  arcsin ( bsinC / c )
        // e.g. angleC = Math.asin( (sideC * Math.sin(angleA * Math.PI / 180)) / sideA  ) * 180 / Math.PI;

        // asind returns a principal value within 0 and 90, so if the given angle is less than 90,
        // then it is possible that the angle returned is < 90 or > 90 - so angle 1 should be > 90 to prevent ambiguity

        return MathC.asind( (side2 * MathC.sind(angle1)) / side1 );
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

}
