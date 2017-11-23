#include "DHT.h"

#define DHTPIN 2
#define DHTTYPE DHT22

#define INPIN1 A0
#define INPIN2 A1

#define VCC 5
#define R1 10000

#define A 0.00088386
#define B 0.0002583
#define C 1.1661 * pow(10, -7)

#define OUT 8

DHT dht(DHTPIN, DHTTYPE);

void setup(){
  Serial.begin(9600);
  dht.begin();
}

void loop(){
  
  if(Serial.available() > 0)
  {
    String received = Serial.readString();
    if(received.equals("update"))
    {
      String toPrint = "";

      float h = dht.readHumidity();
      int UInt = analogRead(INPIN1);
      int UExt = analogRead(INPIN2);
      
      toPrint += getTemperature(UInt) + ";";
      toPrint += getTemperature(UExt) + ";";
      toPrint += h;
      
      Serial.println(toPrint);
    }
    else
    {
      int value = received.toInt();
      if(value)
      {
        digitalWrite(OUT, HIGH);
      }
      else
      {
        digitalWrite(OUT, LOW);
      }
    }
  }
}


String getTemperature(int val)
{

  double tension = 5.0 / 1024.0 * val;
  
  double R2 = (tension * R1) / (VCC - tension);
  
  double tempT = A + B * log(R2) + C * pow(log(R2), 3);
  
  double t = (1.0 / tempT) - 273.15;
  return String(t);
}

