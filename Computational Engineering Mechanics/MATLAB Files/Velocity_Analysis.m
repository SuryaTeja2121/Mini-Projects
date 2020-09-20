%Velocity analysis
clear all;
clc;clf;
lengthOA=3.49;
lengthAC=15.33;
lengthAB=6.14;
lengthBC=11.01;
lengthGB=4.5;
lengthFG=7.00;
lengthDC=10.17;
pointO = [0 0];
pointD = pointO + [-8.93 -12.43]
pointF = pointO + [-5.50 3.00]
pointo=[0 ;0];
thetaDegrees = 0; 
thetaRadians = thetaDegrees*(pi/180.0);
pointA = pointO + lengthOA*[cos(thetaRadians) sin(thetaRadians)];
[pointC1, pointC2] = CircleCircleIntersection(pointA, lengthAC, pointD, lengthDC);
pointC = pointC1
[pointB1, pointB2] = CircleCircleIntersection(pointA, lengthAB, pointC, lengthBC);
pointB = pointB2
[pointG1, pointG2] = CircleCircleIntersection(pointB2, lengthGB, pointF, lengthFG);
pointG = pointG1

pointd=pointo;
pointf=pointo;
w=1;
velOfoa=w*lengthOA;
vectorOA=pointA'-pointo;
dirOfOA=vectorOA/lengthOA;
dirOfOA_Per=[-dirOfOA(2) ;(dirOfOA(1))];    
pointa=pointo+dirOfOA_Per*velOfoa;

vectorAC=pointC'-pointA';
dirOfAC=vectorAC/norm(vectorAC);
dirOfAC_Per=[-dirOfAC(2) ;(dirOfAC(1))];
pointc_inter1=pointa+dirOfAC_Per*5;

vectorDC=pointC'-pointD;
dirOfDC=vectorDC/norm(vectorDC);
dirOfDC_Per=[-dirOfDC(2) ;(dirOfDC(1))];
pointc_inter2=pointo+dirOfDC_Per*5;


pointc=LinesIntersection(pointa,dirOfAC_Per,pointo,dirOfDC_Per)

vectorCB=pointB'-pointC';
dirOfCB=vectorCB/norm(vectorCB);
dirOfCB_Per=[-dirOfCB(2) ;(dirOfCB(1))];
pointb_inter1=pointc+dirOfCB_Per*5;

vectorAB=pointB'-pointA;
dirOfAB=vectorAB/norm(vectorAB);
dirOfAB_Per=[-dirOfAB(2) ;(dirOfAB(1))];
pointb_inter2=pointa+dirOfAB_Per*5;

pointb=LinesIntersection(pointc,dirOfCB_Per,pointa,dirOfAB_Per)

vectorGF=pointF'-pointG'
dirOfGF=vectorGF/norm(vectorGF);
dirOfGF_Per=[-dirOfGF(2) ;(dirOfGF(1))];
pointe_inter1=pointo-dirOfGF_Per*5;

vectorBG=pointG-pointB;
dirOfBG=vectorBG/norm(vectorBG);
dirOfBG_Per=[-dirOfBG(2) ;(dirOfBG(1))];
pointe_inter2=pointb+dirOfBG_Per*5;

pointg=LinesIntersection(pointo,dirOfGF_Per,pointb,dirOfBG_Per)

figure(1)
hold on
plot([pointo(1) pointa(1)],[pointo(2) pointa(2)],'b')
plot([pointa(1) pointc_inter1(1)],[pointa(2) pointc_inter1(2)],'k--')
plot([pointo(1) pointc_inter2(1)],[pointo(2) pointc_inter2(2)],'k--')
plot([pointo(1) pointc(1)],[pointo(2) pointc(2)],'r')
plot([pointa(1) pointc(1)],[pointa(2) pointc(2)],'g')
plot([pointc(1) pointb_inter1(1)],[pointc(2) pointb_inter1(2)],'m--')
plot([pointa(1) pointb_inter2(1)],[pointa(2) pointb_inter2(2)],'m--')

plot([pointc(1) pointb(1)],[pointc(2) pointb(2)],'r')
plot([pointa(1) pointb(1)],[pointa(2) pointb(2)],'g')
plot([pointo(1) pointe_inter1(1)],[pointo(2) pointe_inter1(2)],'m--')
plot([pointb(1) pointe_inter2(1)],[pointb(2) pointe_inter2(2)],'m--')
plot([pointb(1) pointg(1)],[pointb(2) pointg(2)],'r')
plot([pointo(1) pointg(1)],[pointo(2) pointg(2)],'g')
axis([-5 10 -5 10])
hold off