%Project Main File
clear all; clc;
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

thetaDegreesArray = 0:-5:-360; %in degrees, accordingly to be used
thetaRadiansArray = thetaDegreesArray*(pi/180.0);
thetaInitial = thetaRadiansArray(1);

pointA = pointO + lengthOA*[cos(thetaInitial) sin(thetaInitial)];

[pointC1, pointC2] = CircleCircleIntersection(pointA, lengthAC, pointD, lengthDC);
pointC = pointC1
[pointB1, pointB2] = CircleCircleIntersection(pointA, lengthAB, pointC, lengthBC);
pointB = pointB2
[pointG1, pointG2] = CircleCircleIntersection(pointB2, lengthGB, pointF, lengthFG);
pointG = pointG1

%trace
pointATrace = zeros(1,2);
pointBTrace = zeros(1,2);
pointCTrace = zeros(1,2);

figure(2)
grid on

axis equal;
for index = 1:length(thetaRadiansArray)
    theta = thetaRadiansArray(index);
   
    pointA = pointO + lengthOA*[cos(theta) sin(theta)];

    [pointC1, pointC2] = CircleCircleIntersection(pointA, lengthAC, pointD, lengthDC);
    distBetweenPrevCandC1 = norm(pointC-pointC1);
    distBetweenPrevCandC2 = norm(pointC-pointC2);
    %Choose the solution that is nearest to the previous point B
    if(distBetweenPrevCandC1 < distBetweenPrevCandC2)
        pointC = pointC1;
    else
        pointC = pointC2;
    end
   
    [pointB1, pointB2] = CircleCircleIntersection(pointA, lengthAB, pointC, lengthBC);
    distBetweenPrevBandB1 = norm(pointB-pointB1);
    distBetweenPrevCandB2 = norm(pointB-pointB2);
    %Choose the solution that is nearest to the previous point B
    if(distBetweenPrevBandB1 < distBetweenPrevCandB2)
        pointB = pointB1;
    else
        pointB = pointB2;
    end
   
   
    [pointG1, pointG2] = CircleCircleIntersection(pointB2, lengthGB, pointF, lengthFG);
    distBetweenPrevGandG1 = norm(pointG-pointG1);
    distBetweenPrevCandG2 = norm(pointG-pointG2);
    %Choose the solution that is nearest to the previous point B
    if(distBetweenPrevGandG1 < distBetweenPrevCandG2)
        pointG = pointG1;
    else
        pointG = pointG2;
    end
   
    pointATraceXArray(index) = pointA(1);
    pointATraceYArray(index) = pointA(2);
   
    pointBTraceXArray(index) = pointB(1);
    pointBTraceYArray(index) = pointB(2);
   
    pointCTraceXArray(index) = pointC(1);
    pointCTraceYArray(index) = pointC(2);

   
    plot([pointO(1) pointA(1)],[pointO(2) pointA(2)],'r-o')
    hold on;

    plot([pointA(1) pointC(1)] ,[pointA(2) pointC(2)] , 'k-o');
    plot([pointD(1) pointC(1)] , [pointD(2) pointC(2)],'r-o');
     plot([pointC(1) pointB(1)] , [pointC(2) pointB(2)],'K-o');
      plot([pointA(1) pointB(1)] , [pointA(2) pointB2(2)],'K-o');
      plot([pointB(1) pointG(1)] , [pointB(2) pointG(2)],'r-o');
      plot([pointF(1) pointG(1)] , [pointF(2) pointG(2)],'r-o');
     
      plot(pointATraceXArray, pointATraceYArray, 'c-.');
      plot(pointBTraceXArray, pointBTraceYArray, 'm-.');
      plot(pointCTraceXArray, pointCTraceYArray, 'g-.');
 
     
      hold off;
  axis([-20 10 -23 7])
   drawnow();
    pause(0.001);
end
