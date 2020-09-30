# include "DHT.h"                 //DHT library
# define DHTPIN 11                 //DHT pin
# define DHTTYPE DHT11            
DHT dht(DHTPIN, DHTTYPE);
# include "LiquidCrystal.h"            // lcd library 
int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
int FAN = 9;
float t;                                    // temperature in celcius
float h;                                   // humidity in percentage 

void setup(){                 //setup
  Serial.begin(9600);
  pinMode(FAN,OUTPUT);
  dht.begin();
  lcd.begin(16, 2);
  lcd.setCursor(0, 0);
  lcd.print("  Done by   ");
  lcd.setCursor(0, 1);
  lcd.print("  Team 12...    ");
  delay(2000);
  lcd.clear();
}

void loop()                         //loop
{
  h = dht.readHumidity() ;                                                                                         
  t = dht.readTemperature();
  lcd.setCursor(0, 0);
  lcd.print("Temp: ");
  lcd.print(t);
  lcd.print("C.");
  lcd.setCursor(0, 1);
  lcd.print("Humi: ");
  lcd.print(h);
  lcd.print("% ");
  if(h >=82 || t>=30)
  {
    digitalWrite(FAN,HIGH);
  }
  else
  {
    digitalWrite(FAN,LOW);
  }
  
  delay(500);
  
  delay(500);
}
