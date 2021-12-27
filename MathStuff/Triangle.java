public class Triangle{
    private double sideA, sideB, sideC, angleA, angleB, angleC, area, perimeter;
    private int numSides, numAngles;
    
    public Triangle() {
        this(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }
    
    public Triangle(double a, double b, double c, double d, double e, double f){
        sideA = a; sideB = b; sideC = c;
        angleA = d; angleB = e; angleC = f;
        perimeter = sideA + sideB + sideC;
        this.enumerate();
        this.lawOfSines();
        this.lawOfCosines();
        this.heron();
        
    }
    
    public void heron(){
        area = perimeter / 2;
        area *= (area - sideA) * (area - sideB) * (area - sideC);
        area = Math.sqrt(area);
    }
    
    public void enumerate(){
        numSides = 0; numAngles = 0;
        if(sideA != 0.0F) numSides++;
        if(sideB != 0.0F) numSides++;
        if(sideC != 0.0F) numSides++;
        
        if(angleA != 0.0F) numAngles++;
        if(angleB != 0.0F) numAngles++;
        if(angleC != 0.0F) numAngles++;
    }    
    
    public double lawOfCosinesHelperAngles(double side1, double side2, double side3){
        // Three sides - determine the angle opposite of "side3" (in degrees)
      
        /* a^2 + b^2 - 2ab cos C = c^2 
          -2ab cos C = c^2 - a^2 - b^2 
          cos C = (a^2 + b^2 - c^2)/(2ab)
          C = arccos( (a^2 + b^2 - c^2) / (2ab) )
          side1, side2, side3 = a, b, c 
        */
      
        return Math.acos( (Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(side3, 2))/(2 * side1 * side2 ) ) * 180 / Math.PI; 
    }
    public double lawOfCosinesHelperSides(double side1, double side2, double angle3){
      // Two sides and an angle in-between to determine the opposite angle
      
      /* a^2 + b^2 - 2abcosC = c^2 
           sqrt(a^2 + b^2 - 2abcosC) = c 
           side1, side2, angle3 = a, b, C  */
        return Math.sqrt( Math.pow(side1, 2) + Math.pow(side2, 2) - 2 * side1 *  side2 * Math.cos(angle3 * Math.PI / 180)  );
    }
    
    public void lawOfCosines(){
    
        if(numSides == 3 && numAngles != 3){
           angleA = lawOfCosinesHelperAngles(sideB, sideC, sideA);
           angleB = lawOfCosinesHelperAngles(sideA, sideC, sideB);
           angleC = lawOfCosinesHelperAngles(sideA, sideB, sideC);
           numAngles = 3;
        }
        else if(numSides == 2 && numAngles >= 1){
            if(sideA != 0.0 && sideB != 0.0 && angleC != 0.0){
                sideC = lawOfCosinesHelperSides(sideA, sideB, angleC) ;
            }
        
            if(sideB != 0.0 && sideC != 0.0 && angleA != 0.0){
                sideA = lawOfCosinesHelperSides(sideB, sideC, angleA) ;
            }
        
            if(sideA != 0.0 && sideC != 0.0 && angleB != 0.0){
                sideB = lawOfCosinesHelperSides(sideA, sideC, angleB) ;
            }
            
            numSides++;
            perimeter = sideA + sideB + sideC;
        }
    }
    
    public void lawOfSines(){
        if(numAngles < 3){
            if(numAngles == 2 && numSides >= 1){
            if(sideA != 0.0 && angleA != 0.0 && angleB != 0.0){
                sideB = lawOfSinesHelper(sideA, angleA, angleB);
            }
            
            if(sideB != 0.0 && angleB != 0.0 && angleA != 0.0){
                sideA = lawOfSinesHelper(sideB, angleB, angleA); 
            }
            
            if(sideA != 0.0 && angleA != 0.0 && angleC != 0.0){
                sideC = lawOfSinesHelper(sideA, angleA, angleC); 
            }
            
            if(sideC != 0.0 && angleC != 0.0 && angleA != 0.0){
                sideA = lawOfSinesHelper(sideC, angleC, angleA);
            }
            
            if(sideB != 0.0 && angleB != 0.0 && angleC != 0.0){
                sideC = lawOfSinesHelper(sideB, angleB, angleC); 
            }
            
            if(sideC != 0.0 && angleC != 0.0 && angleB != 0.0){
                sideB = lawOfSinesHelper(sideC, angleC, angleB); 
            }
        } 
            
        }
    }
    public double lawOfSinesHelper(double side1, double angle1, double angle2){
        double side2 = side1 * Math.sin(angle2 * Math.PI / 180) / Math.sin(angle2 * Math.PI / 180); // Law of sines: b = a * sinB / sinA
        return side2;
    }
    
    public String toString(){
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
