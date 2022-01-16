import java.util.Scanner;
public class Triangle {
    private double sideA, sideB, sideC, angleA, angleB, angleC, area, perimeter;
    private int numSides, numAngles;

    public Triangle() {
        this(0, 0, 0, 0, 0, 0, 0);
    }

    public Triangle(double a, double b, double c, double d, double e, double f, double g) {
        sideA = a;
        sideB = b;
        sideC = c;

        angleA = d;
        angleB = e;
        angleC = f;

        this.enumerate();

        area = g; // For weird situations when area is given & two sides (heron updates the area to an invalid value)
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
        enumerate();

        // SSS Case
        if (numSides == 3) {
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

        // SAS Cases
        else if (numSides == 2 && numAngles >= 1) {
            if (sideA == 0.0 && angleA != 0.0) {
                sideA = lawOfCosinesHelperSAS(sideB, sideC, angleA);
                lawOfCosines();
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
      // Two sides & area case (Have to assume acute angle D:  )
        if(numSides == 2 && area > 0) {
          if(sideA > 0 && sideB > 0) angleC = lawOfSinesHelperSSArea(sideA, sideB, area); lawOfCosines();
          if(sideA > 0 && sideC > 0) angleB = lawOfSinesHelperSSArea(sideA, sideC, area); lawOfCosines();
          if(sideB > 0 && sideC > 0) angleA = lawOfSinesHelperSSArea(sideB, sideC, area); lawOfCosines();
         }

        // ASA Cases
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

        return MathC.asind( (side2 * MathC.sind(angle1)) / side1 );
    }

    private double lawOfSinesHelperSSArea(double side1, double side2, double area){
      // Given two sides of a triangle and the area of the triangle, you can determine the angle between the two sides*
      // * assume the angle is <= 90
      /* (a * b * sinC) / 2 = area   [Projection of a or b with * sinC to become the height]
      a * b * sinC = 2 * area
      sinC = (2 * area) / (a * b)
      C = arcsin( (2 * area) / (a * b)  )
      */
      return MathC.asind( (2 * area) / (side1 * side2) );

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
      Scanner sc = new Scanner(System.in);
      Triangle temp = new Triangle();
        /* Triangle babyRight = new Triangle(3, 4, 0, 0, 0, 90);
        Triangle equilateral = new Triangle(4, 4, 4, 0, 0, 0);
        Triangle threeSixNine = new Triangle(2, 2 * Math.sqrt(3), 0, 30, 60, 0);
         System.out.println(threeSixNine.toString());
        Triangle fourFive = new Triangle(0, 4, 5, 0, 0, 90);
        System.out.println("\n" +fourFive.toString());
        Triangle threeFive = new Triangle(3, 0, 5, 0, 0, 90);
        System.out.println("\n" + threeFive.toString()); */
        /*
        Triangle dmOne = new Triangle(15, 21, 29, 0, 0, 0);
        System.out.println(dmOne.toString()); */
          while(true){
            System.out.println("Please input the values for your triangle, inputting 0 where unknown");
            double sA, sB, sC, aA, aB, aC, area;
            System.out.print("Side A = "); sA = sc.nextDouble();
            System.out.print("Side B = "); sB = sc.nextDouble();
            System.out.print("Side C = "); sC = sc.nextDouble();
            System.out.print("Angle A = "); aA = sc.nextDouble();
            System.out.print("Angle B = "); aB = sc.nextDouble();
            System.out.print("Angle C = "); aC = sc.nextDouble();
            System.out.print("Area = "); area = sc.nextDouble();
            temp = new Triangle(sA, sB, sC, aA, aB, aC, area);
            System.out.println( temp.toString() );

            System.out.print("Would you like to exit? Y / N ");
            String input = sc.next();
            if(input.equals("Y")){break;}
          }

    }
}
