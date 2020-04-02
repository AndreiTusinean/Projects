#include <Servo.h>
#include <NewPing.h>
#include <SoftwareSerial.h>



SoftwareSerial mySerial(12, 13); // RX, TX
// Pinii motor 1
#define mpin00 5
#define mpin01 6
// Pinii motor 2
#define mpin10 3
#define mpin11 11
#define TRIGGER_PIN  2
#define ECHO_PIN     10
#define MAX_DISTANCE 200
Servo srv;
char c;
int aux;
int led;

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);
void setup() {
   // configurarea pinilor motor ca iesire, initial valoare 0
   digitalWrite(mpin00, 0);
   digitalWrite(mpin01, 0);
   digitalWrite(mpin10, 0);
   digitalWrite(mpin11, 0);
   pinMode (mpin00, OUTPUT);
   pinMode (mpin01, OUTPUT);
   pinMode (mpin10, OUTPUT);
   pinMode (mpin11, OUTPUT);
   // pin LED
   Serial.begin(9600);
   mySerial.begin(9600);
   
   pinMode(A0,OUTPUT);
   pinMode(A1,OUTPUT);
}
// Funcție pentru controlul unui motor
// Intrare: pinii m1 și m2, direcția și viteza
void StartMotor (int m1, int m2, int forward, int speed)
{

 if (speed==0) // oprire
 {
   digitalWrite(m1, 0);
   digitalWrite(m2, 0);
 }
 else
 {
 if (forward)
 {
 digitalWrite(m2, 0);
analogWrite(m1, speed); // folosire PWM
 }
 else
 {
 digitalWrite(m1, 0);
 analogWrite(m2, speed);
 }
 }
}
// Funcție de siguranță
// Execută oprire motoare, urmată de delay
void delayStopped(int ms)
{
 StartMotor (mpin00, mpin01, 0, 0);
 StartMotor (mpin10, mpin11, 0, 0);
 delay(ms);
}
// Utilizare servo
// Poziționare în trei unghiuri
// La final, rămâne în mijloc (90 grade)
void playWithServo(int pin)
{
 srv.attach(pin);
 srv.write(45);//0
 delay(500);
 srv.write(135);//
 delay(500);
 srv.write(90);
 delay(500);
 srv.detach();
}

int speedf(char c)
{
  int aux;
  if(c=='0')
  {
    aux=0;  
  }
    if(c=='1'||c=='2')
    {
      aux = 32;
    }
    else
      if(c=='3'||c=='4'||c=='5')
      {
        aux = 64;
      }
      else
        if(c=='6'||c=='7'||c=='8')
        {
          aux = 96;
        }
        else
          if(c=='9'||c=='q')
          {
            aux = 128;
          }
  return aux;
}

void light()
{
  
}

bool sensor()
{
  int leng=sonar.ping_cm();
  Serial.print("Ping: ");
  Serial.print(leng);
  Serial.println("cm");
  if(leng<20&&leng!=0)
  {
    return false;
  }
  else
    return true;
}

void loop() { 

  
  //playWithServo(8);
  //bool verif = sensor();
  //aux=64;
  if(mySerial.available())
  {   
      c=mySerial.read();
  if(c=='0')
  {
    aux=0;  
  }
    if(c=='1'||c=='2')
    {
      aux = 32;
    }
    else
      if(c=='3'||c=='4'||c=='5')
      {
        aux = 64;
      }
      else
        if(c=='6'||c=='7'||c=='8')
        {
          aux = 96;
        }
        else
          if(c=='9'||c=='q')
          {
            aux = 128;
          }
      if(c=='W')
      {
        analogWrite(A0,255);
      }
      if(c=='w')
      {
        analogWrite(A0,0);
      }
      if(c=='U')
      {
        analogWrite(A1,255);
      }
      if(c=='u')
      {
        analogWrite(A1,0);
      }
      if(c=='F')
      {
        c=mySerial.read();
        //if(verif==false)
       // {
          //aux=0;
        //}
        StartMotor (mpin00, mpin01, 1, aux);
        StartMotor (mpin10, mpin11, 1, aux);
        delay(30);
        StartMotor (mpin00, mpin01, 1, 0);
        StartMotor (mpin10, mpin11, 1, 0);
        c=' ';
      }
      if(c=='B')
      {
        //playWithServo(8);
        StartMotor (mpin00, mpin01, 0, aux);
        StartMotor (mpin10, mpin11, 0, aux);
        delay(30);
        StartMotor (mpin00, mpin01, 1, 0);
        StartMotor (mpin10, mpin11, 1, 0);
        c=' ';
      }
      if(c=='L')
      {
        //playWithServo(8);
        StartMotor (mpin00, mpin01, 1, aux);
        StartMotor (mpin10, mpin11, 0, aux);
        delay(30);
        StartMotor (mpin00, mpin01, 1, 0);
        StartMotor (mpin10, mpin11, 1, 0);
        c=' ';
      }
      if(c=='R')
      {
        //playWithServo(8);
        StartMotor (mpin00, mpin01, 0, aux);
        StartMotor (mpin10, mpin11, 1, aux);
        delay(30);
        StartMotor (mpin00, mpin01, 1, 0);
        StartMotor (mpin10, mpin11, 1, 0);
        c=' ';
      }
      if(c=='G')
      {
        //playWithServo(8);
        StartMotor (mpin00, mpin01, 0, aux);
        StartMotor (mpin10, mpin11, 1, aux);
        delay(30);
        StartMotor (mpin00, mpin01, 1, 0);
        StartMotor (mpin10, mpin11, 1, 0);
        c=' ';
      }
      if(c=='L')
      {
        StartMotor (mpin00, mpin01, 0, aux);
        StartMotor (mpin10, mpin11, 1, aux);
        delay(30);
        StartMotor (mpin00, mpin01, 1, 0);
        StartMotor (mpin10, mpin11, 1, 0);
        c=' ';
      }
      c=' ';
   }
 
} 
 
