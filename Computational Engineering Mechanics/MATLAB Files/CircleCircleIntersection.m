function [pointIntersectionA, pointIntersectionB] = CircleCircleIntersection(circleCenterA, rA, circleCenterB, rB)

pointIntersectionA = []; pointIntersectionB = []; %empty arrays would be returned if any error

deltaCVector = circleCenterB - circleCenterA; lengthCVector = norm(deltaCVector);
dirCVector = deltaCVector/lengthCVector; %Normalize as unit vector

%defining simpler variables to match with derivation
xCA = circleCenterA(1); yCA = circleCenterA(2);

m=(lengthCVector*lengthCVector + rA*rA - rB*rB)/(2*lengthCVector);

chordCenterPoint = circleCenterA + m*(dirCVector);
dirLine = [-dirCVector(2) dirCVector(1)]; %Rotation about Z axis by 90degree => dirChord = dirCVector*[0 1; -1 0] (row vector!)

xL = chordCenterPoint(1); yL = chordCenterPoint(2); fL = dirLine(1); gL = dirLine(2);

denomTerm = fL*fL + gL*gL;
tempTerm = fL*(yL-yCA)-gL*(xL-xCA);
discriminant = rA*rA*denomTerm - tempTerm*tempTerm;
if discriminant < 0
    return; %Non intersecting case, empty arrays are not populated
end

if discriminant == 0
    t = (fL*(xCA-xL) + gL*(yCA-yL))/denomTerm;
    tA = t; tB = t; %equal and real
else
    tExpression1 = (fL*(xCA-xL)+gL*(yCA-yL))/denomTerm;
    tExpression2 = sqrt(rA*rA*denomTerm - (fL*(yL-yCA)-gL*(xL-xCA))*(fL*(yL-yCA)-gL*(xL-xCA)));
    tA = tExpression1 + tExpression2;
    tB = tExpression1 - tExpression2;
end

pointIntersectionA = chordCenterPoint + tA*dirLine; 
pointIntersectionB = chordCenterPoint + tB*dirLine; 

end