import javax.swing.*;
import java.text.*;
import java.util.Scanner;
/**
 * Write a description of class CellPhone here.
 * 
 * Parker O'Brien 
 * 10-10-15
 */
public class MyPhone
{
    // variables
    private int totalTexts = 0;
    private double dataConsumed; 
    private double remainingBattery;
    private String customerName;
    private String phoneNumber;
    private String sendText;
    
    private final double VIDEO_DATA_PER_MIN = 0.250 / 60.0;
    private final double AUDIO_DATA_PER_MIN = 0.065 / 60.0;
    
    private final double AUDIO_MINUTES = 720.0;
    private final double VIDEO_MINUTES = 360.0;
    
    //private helpers
    private double additionalDataFee;
    private double usageFee;
    private double totalFee;
    private String formatPhoneNum;
    
    private boolean wifi;
    
    

  /**
   * Constructor for objects of class MyPhone
    */
  public MyPhone(String name,String num)
  {
        customerName = name;
        phoneNumber = num;
        wifi = false;
  }
  
  public int getNumTexts() {
        return totalTexts;
  }
    
  public double getBatteryLife() {
        return remainingBattery;
  }
    
  public double getDataUsage() {
        return dataConsumed;
  }
    
  public void setName (String customerName)
  {
        this.customerName = customerName;
  }
    
  public void setNum (String phoneNumber){
      if (phoneNumber.length() != 10)
      {
       this.phoneNumber = "9999999999";
      }
      else
      {
       this.phoneNumber = phoneNumber;
      }
  }
     
  public void sendText (String sendText){
      if (remainingBattery > 0) {
        this.sendText = sendText;
        totalTexts = totalTexts +1;
        JOptionPane.showMessageDialog(null,"Message Sent");
      }
      else
      {JOptionPane.showMessageDialog(null,"Phone Needs to Be Charged");
      }   
  }
   
  public void readText(){
     if (remainingBattery > 0) {
        int choice = (int) (Math.random( ) * 5);
       if (choice == 0){
       JOptionPane.showMessageDialog(null,"Hello!");
      }
      if (choice == 1) {
        JOptionPane.showMessageDialog(null,"How are you?");
      }
      if (choice == 2) {
        JOptionPane.showMessageDialog(null,"Want to grab coffee later?");
      }
      if (choice == 3) {
        JOptionPane.showMessageDialog(null,"Are you free?");
      }
      if (choice == 4) {
        JOptionPane.showMessageDialog(null,"Want to get together?");
      }
      totalTexts = totalTexts +1;
    }
    else 
    {
     JOptionPane.showMessageDialog(null,"Phone Needs to Be Charged");
    }
  }
    
  public void chargeBattery (int mins){
    if (mins < 120) {
        remainingBattery = remainingBattery + (mins / 120.0);
        if ( remainingBattery > 1.0) {
            remainingBattery = 1.0;
        }
        JOptionPane.showMessageDialog(null,"Battery Life:" + remainingBattery*100 + "%");
    }  
    else  
    {  
        remainingBattery = 1.0;
       JOptionPane.showMessageDialog(null,"Battery Life:" + remainingBattery*100 + "%");
    }
  }
        
  public void streamVideo (int mins){    
    int availableMinutes = (int) (remainingBattery * VIDEO_MINUTES);   
    if (mins < availableMinutes) { 
        if (wifi == true) {
           remainingBattery = remainingBattery - (mins / VIDEO_MINUTES);
           dataConsumed = dataConsumed;
        }
        if (wifi == false) {
          remainingBattery = remainingBattery - (mins / VIDEO_MINUTES);
          dataConsumed = dataConsumed = dataConsumed + (mins * VIDEO_DATA_PER_MIN); 
        }
    }
    else if (wifi == false) {
        remainingBattery = 0.0;
        dataConsumed = dataConsumed + (availableMinutes * VIDEO_DATA_PER_MIN);
        JOptionPane.showMessageDialog(null,"Phone Needs to Be Charged");
    }  
    else if (wifi == true) {
        remainingBattery = 0.0;
        dataConsumed = dataConsumed;
        JOptionPane.showMessageDialog(null,"Phone Needs to Be Charged");
    }
  } 
      
  public void streamAudio (int mins){
      int availableMinutes = (int) (remainingBattery * AUDIO_MINUTES);
      if (mins < availableMinutes) {
         if (wifi == true) {
          remainingBattery = remainingBattery - (mins / AUDIO_MINUTES);
          dataConsumed = dataConsumed;
         }
         if (wifi == false) {
          remainingBattery = remainingBattery - (mins / AUDIO_MINUTES);
          dataConsumed = dataConsumed = dataConsumed + (mins * AUDIO_DATA_PER_MIN);
         }
      }
      else if (wifi == false) {
          remainingBattery = 0.0;
          dataConsumed = dataConsumed + (availableMinutes * AUDIO_DATA_PER_MIN);
          JOptionPane.showMessageDialog(null,"Phone Needs to Be Charged");
      }
      else if ( wifi == true) {
          remainingBattery = 0.0;
          dataConsumed = dataConsumed;
          JOptionPane.showMessageDialog(null,"Phone Needs to Be Charged");
      }   
  }
// wifi enable. Code was helped with through success center.
  public void setWifi (boolean status) {
      wifi = status;
   }
//Private Helpers
  private void startNewMonth() {
    dataConsumed = 0;
    totalTexts = 0;
  }
// code was helped with through success center
  private double additionalDataFee(){
   double answer;
    if (dataConsumed > 2.0){
     answer = Math.ceil(dataConsumed-2) * 15;
     return answer;
    }
    else 
     return 0;
  }

  private double usageFee() { 
    return (50.0 + additionalDataFee()) * .03;

  }

  private double totalFee() {
    return (50.0 + 0.61 + additionalDataFee() + usageFee());
  }

  private String formatPhoneNum() {
    formatPhoneNum = phoneNumber;
    return  "(" + formatPhoneNum.substring(0,3) + ")" + formatPhoneNum.substring(3,6) + "-" + formatPhoneNum.substring(6);
    
  }
 
// Monthly Print statment
  public void printStatement() { 
     NumberFormat fmt = NumberFormat.getCurrencyInstance();
     
     
     Scanner sc = new Scanner(System.in);
     System.out.println ("Your Monthly Phone Statement");
     System.out.println ("-------------------------------------------");
     System.out.println ("Customer:                  " +customerName);
     System.out.println ("Number:                    " +formatPhoneNum());
     System.out.println ("Texts:                     " +totalTexts);
     System.out.println ("Data Usage:                " +dataConsumed +"(GB)");
     System.out.println ("-------------------------------------------");
     System.out.println ("2GB Data Plan:             $50.00");
     System.out.println ("Additional Data Fee:       " +fmt.format(additionalDataFee()));
     System.out.println ("Universal Usage (3%):      " +fmt.format(usageFee()));
     System.out.println ("Administrative Fee:        $0.61  ");
     System.out.println ("Total Charges:             " +fmt.format(totalFee()));
     
     startNewMonth();
     
     
 
  }


}