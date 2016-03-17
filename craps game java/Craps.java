
/**
 * Write a description of class Craps here.
 * 
 * @author Parker O'Brien
 
 */
public class Craps
{
   private int points;
   private int credits;
   private String message;
   
   private GVDie d1;
   private GVDie d2;
   
   public int toComeOut;
   public int toRoll;
   
  public Craps()
   { 
    points = -1;
    credits = 10;
    d1 = new GVDie();
    d2 = new GVDie();
    toComeOut = 0;
    toRoll = 1;
    message = "New Game of Craps";
  }

  private boolean legalComeOut( ) {
      if (toComeOut == 0) {
      return true;
    }
    else {
        return false;
    }
  }
   
  public int getCredits() {
    return credits;
  }
   
  public int getPoint() {
      return points;
  }
    
  public String getMessage() {
      return message;
  }

  public GVDie getDie (int num)
  {     
     if (num == 1) {
            return d1;
     }
     if (num == 2) {
            return d2;
     } 
     else {
            return d1;
     }
  }
    
  public void setCredits (int amount) {
    if (amount < 0 ) {
     credits = credits; 
    }
    else {
     credits = amount; 
    }
  }
  //Mutator methods
  
  public void comeOut ( ) {
     if (toComeOut == 0 && credits > 0) { 
     d1.roll();
     d2.roll(); 
     int v1 = d1.getValue();
     int v2 = d2.getValue();
     int sum = v1 + v2;
    if (sum == 7 || sum == 11) {
      credits = credits +1;
      message = "Your Come out is  " + sum +"." +" You win.";
      toComeOut = 0;
      toRoll = 1;
    }
    else if (sum == 2 || sum == 3 || sum == 12) {
      credits = credits - 1;
      message = "Your Come out is " + sum +"."  +" You Loose.";
      toComeOut = 0;
      toRoll = 1;
    }
    else {
      points = v1 + v2;
      message = "Your Come out is " + sum +"."  +" Now Roll.";
      toComeOut = 1;
      toRoll = 0;
    }
   }
   else if (credits == 0) {
    message = "You are out of Credits";
   }
   else if (toComeOut > 0) {
     message = "Already Rolled Come out. Now Roll";
   }
  }
    
  public boolean okToRoll ( ) {
    if (toRoll == 0) {
        return true;
    }
    else {
        return false;
    }
  }

  public void roll () {
    if (toRoll == 0 && credits > 0) {
      d1.roll();
      d2.roll(); 
      int v1 = d1.getValue();
      int v2 = d2.getValue();
      int sum = v1 + v2;
    if (sum == 7) {
      credits = credits -1;
      points = -1;
      message = "You rolled " + sum +"." +" You Loose";
      toComeOut = 0;
      toRoll = 1;
    }
    else if (sum == points) {
      credits = credits +1;
      points = -1;
      message = "You rolled " + sum +"." +" You win";
      toComeOut = 0;
      toRoll = 1;
    }
    else {
      points = points;
      credits = credits;
      message = "You rolled " + sum +"." +" Roll again.";
      toComeOut = 1;
      toRoll = 0;
    }
   }
   else 
   { 
      credits = credits;
      points = points;
      message = "Must Roll Come Out";
   }
}




}