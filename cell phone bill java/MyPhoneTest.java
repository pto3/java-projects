
/**
 * Write a description of class MyPhoneTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyPhoneTest
{
    public static void main(String [] args){
 MyPhone mine = new MyPhone("Parker", "9999999999");
 MyPhone yours = new MyPhone("Mikey", "9999999999");
 
 mine.chargeBattery(120);
 mine.streamVideo(360);
 mine.chargeBattery(100);
 mine.streamVideo(360);
 mine.chargeBattery(60);
 mine.streamAudio(300);
 mine.setWifi(true);
 mine.chargeBattery(120);
 mine.streamAudio(200);
 mine.streamVideo(60);
 mine.setWifi(false);
 mine.streamAudio(60);
 mine.streamVideo(60);
 mine.sendText("hey");
 mine.readText();
 mine.setNum("2489469282");
 mine.setName("Parker O'Brien");
 mine.printStatement();
 
 
 yours.chargeBattery(110);
 yours.streamVideo(150);
 yours.chargeBattery(40);
 yours.streamVideo(150);
 yours.chargeBattery(120);
 yours.streamAudio(200);
 yours.setWifi(true);
 yours.chargeBattery(80);
 yours.streamAudio(200);
 yours.streamVideo(60);
 yours.setWifi(false);
 yours.streamAudio(30);
 yours.chargeBattery(100);
 yours.streamVideo(200);
 yours.sendText("Hello");
 yours.readText();
 yours.setNum("5173243333");
 yours.setName("Mikey Mike");
 yours.printStatement();
}
}
