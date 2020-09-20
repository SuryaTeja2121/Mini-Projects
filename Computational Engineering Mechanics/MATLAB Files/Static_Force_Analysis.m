%Static Force Analysis
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
thetaDegrees = 0; 
thetaRadians = thetaDegrees*(pi/180.0);
pointA = pointO + lengthOA*[cos(thetaRadians) sin(thetaRadians)];
[pointC1, pointC2] = CircleCircleIntersection(pointA, lengthAC, pointD, lengthDC);
pointC = pointC1;
[pointB1, pointB2] = CircleCircleIntersection(pointA, lengthAB, pointC, lengthBC);
pointB = pointB2;
[pointG1, pointG2] = CircleCircleIntersection(pointB2, lengthGB, pointF, lengthFG);
pointG = pointG1;

%FBD of Farthest Link CD
F =10;
theta = pi/2;
pointF = pointO + [F*cos(theta) F*sin(theta)];
dirOfF=(pointF-pointO)/norm(pointF-pointO);
dirOfCD=(pointC-pointD)/norm(pointC-pointD);
midpointOfCD=pointD+(lengthDC/2)*dirOfCD;
F_ext=midpointOfCD-F*dirOfF;
dirOfD1=(F_ext-pointD)/norm(F_ext-pointD);
dirOfC1=(F_ext-pointC)/norm(F_ext-pointC);

Poly_c=pointO-20*dirOfC1;
Poly_d=pointF+20*dirOfD1;

Polygon1_int=LinesIntersection(pointO,dirOfC1,pointF,dirOfD1);
dirOfPolygon1_int=(Polygon1_int-pointO)/norm(Polygon1_int-pointO);

%Force Polygon and FBD of Farthest Link
figure(1)
plot([pointO(1) pointF(1)] , [pointO(2) pointF(2)] , 'r-o');
grid on
hold on
plot([pointC(1) pointD(1)] , [pointC(2) pointD(2)] , 'r-o');
plot([midpointOfCD(1) F_ext(1) ] ,[midpointOfCD(2) F_ext(2) ] , 'b');
plot([pointC(1) F_ext(1)] , [pointC(2) F_ext(2)] , 'r--');
plot([F_ext(1) pointD(1)] , [F_ext(2) pointD(2)] , 'r--');
plot([pointO(1) Poly_c(1)] , [pointO(2) Poly_c(2)] , 'r--');
plot([pointF(1) Poly_d(1)] , [pointF(2) Poly_d(2)] , 'r--');
plot([pointO(1) Polygon1_int(1)] , [pointO(2) Polygon1_int(2)] , 'b');
plot([pointF(1) Polygon1_int(1)] , [pointF(2) Polygon1_int(2)] , 'b');
hold off
axis([-30 30 -30 30])

%FBD of Ternary Link
Poly2_c=pointC+30*dirOfPolygon1_int;
dirOfBG=(pointB-pointG)/norm(pointB-pointG);
Poly2_b=pointB+20*dirOfBG;
int_A=LinesIntersection(pointC,dirOfPolygon1_int,pointB,dirOfBG);
dirOfA_int_A=(int_A-pointA)/norm(int_A-pointA);
Polygon2_int1=pointO-10*dirOfA_int_A;
Polygon2_int2=Polygon1_int-10*dirOfBG;
Polygon2_int=LinesIntersection(pointO,dirOfA_int_A,Polygon1_int,dirOfBG);

Reaction_Force_on_crank=norm(pointO-Polygon2_int)
Perp_dist=1.338;
Moment_on_crank=Reaction_Force_on_crank*Perp_dist
Required_Moment_for_static=-Moment_on_crank

%Figure of Force Polygon Of Ternary Link
figure(2)
plot([pointA(1) pointC(1)] , [pointA(2) pointC(2)] , 'r-o');
grid on
hold on
plot([pointC(1) pointB(1)] , [pointC(2) pointB(2)] , 'r-o');
plot([pointB(1) pointA(1)] , [pointB(2) pointA(2)] , 'r-o');
plot([pointO(1) Polygon1_int(1)] , [pointO(2) Polygon1_int(2)] , 'b');
plot([pointC(1) Poly2_c(1)] , [pointC(2) Poly2_c(2)] , 'g--');
plot([pointB(1) Poly2_b(1)] , [pointB(2) Poly2_b(2)] , 'g--');
plot([pointA(1) int_A(1)] , [pointA(2) int_A(2)] , 'g--');
plot([pointO(1) Polygon2_int1(1)] , [pointO(2) Polygon2_int1(2)] , 'r--');
plot([Polygon2_int2(1) Polygon1_int(1)] , [Polygon2_int2(2) Polygon1_int(2)] , 'r--');
plot([pointO(1) Polygon2_int(1)] , [pointO(2) Polygon2_int(2)] , 'b'); plot([Polygon2_int(1) Polygon1_int(1)] , [Polygon2_int(2) Polygon1_int(2)] , 'b');
hold off
axis([-30 30 -30 30])

