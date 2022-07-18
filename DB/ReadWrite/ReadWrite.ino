#include <SPI.h>
#include <SD.h>
#include <Ethernet.h>

byte mac[] = { 0x00, 0xAA, 0xBB, 0xCC, 0xDE, 0x02 };

EthernetServer server(8000);

File myFile;
String FILE_NAME_DB = "DB_T.DB";

void setup() {
  // Open serial communications and wait for port to open:
  
  Serial.begin(9600);
    
  pinMode(4,OUTPUT);
  pinMode(10,OUTPUT);
  pinMode(53,OUTPUT);
  digitalWrite(4,HIGH);
  digitalWrite(10,HIGH);
  digitalWrite(53,HIGH);

  if (!SD.begin(4)) {
    serialPrint("||FAIL_INIT_SD||");
    while (1);
  }
    
  serialPrint("Initialize Ethernet with DHCP:");
  if (Ethernet.begin(mac) == 0) {
    serialPrint("Failed to configure Ethernet using DHCP");
    if (Ethernet.hardwareStatus() == EthernetNoHardware) {
      serialPrint("Ethernet shield was not found.  Sorry, can't run without hardware. :(");
    } else if (Ethernet.linkStatus() == LinkOFF) {
      serialPrint("Ethernet cable is not connected.");
    }
  }else{
    serialPrint("My IP address: ");
    serialPrint(DisplayAddress(Ethernet.localIP()));
  }
  server.begin();
  delay(1000);
      
}

String DisplayAddress(IPAddress address)
{
 return String(address[0]) + "." + 
        String(address[1]) + "." + 
        String(address[2]) + "." + 
        String(address[3]);
}

void serialPrint(String msg){
  if(Serial){
    Serial.println(msg);
  }    
}

void loop() {
  EthernetClient client = server.available(); 
  if (client)
  {
    bool currentLineIsBlank = true;
    String cadena = "";
    while (client.connected()) 
    { 
      String command = "";
      if (client.available()) 
      {        
        command = String(client.readString());
        serialPrint(command);
      }
      else
      {
        command = "";
        continue;
      }
      
      if(command.length() >= 3)
      { 
        if(command.indexOf("#S#") > -1)                     //SAVE      
        { 
          String resp = insertOne(command.substring(3,command.length()));
          client.print(resp);
        }
        else if(command.indexOf("#R#") > -1)                //READ_ALL
        { 
          readAll(client);          
        }              
        else
        {
          client.print("||INVALID_COMMAND||");
        }        
        
        client.print("||END||");
        delay(1000);
        client.stop();
      }
      else
      {
        command = "";        
        client.print("||END||");
        client.stop();
        continue;
      }
      
    }
  }
}

String insertOne(String value){
  myFile = SD.open(FILE_NAME_DB, FILE_WRITE);
  if (myFile) {
    myFile.println(value);   
    myFile.close();
  }  
  return "||SAVED||";
}

String readAll(EthernetClient client){
  myFile = SD.open(FILE_NAME_DB);
  String data = "";
  if (myFile) {
    while (myFile.available()) {
      char letter = myFile.read(); 
      client.print(letter);
    }
    return data;
  } else {    
    return "||ERROR_OPENING_DB||";
  }
}
