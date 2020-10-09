float[] pointsX;
float[] pointsY;
float[] pointsZ;

class BoxBounds
{
  //When calling this function one gives the position of point 0 and total cube size
  public void BoundsSet(float middlePositionX, float middlePositionY, float middlePositionZ) {
    float middleDistance = offset/2;
    //creating 9 points in space to then create a cube from
    pointsX = new float[9];
    pointsY = new float[9];
    pointsZ = new float[9];
    
    //Setting all points to equal point 0
    for (int i = 0; i<pointsX.length; i++) {
      pointsX[i] = middlePositionX;
      pointsY[i] = middlePositionY;
      pointsZ[i] = middlePositionZ;

    }
    

    /**
    now we take point 1 to 8 and add/subtract each point with cubesize/2
    to get the distance each point will be away from the center point
    - Kind of like radius for circles but it's a square &
    we need to go + a certain distance in x, y & z to get out front top right corner
    **/
    //topLeft
    pointsX[1] = pointsX[1] - middleDistance;
    pointsY[1] = pointsY[1] - middleDistance;
    pointsZ[1] = pointsZ[1] + middleDistance;
    //topRight
    pointsX[2] = pointsX[2] + middleDistance;
    pointsY[2] = pointsY[2] - middleDistance;
    pointsZ[2] = pointsZ[2] + middleDistance;
    //bottomLeft
    pointsX[3] = pointsX[3] - middleDistance;
    pointsY[3] = pointsY[3] + middleDistance;
    pointsZ[3] = pointsZ[3] + middleDistance;
    //bottomRight
    pointsX[4] = pointsX[4] + middleDistance;
    pointsY[4] = pointsY[4] + middleDistance;
    pointsZ[4] = pointsZ[4] + middleDistance;
    //inverseZtopLeft
    pointsX[5] = pointsX[5] - middleDistance;
    pointsY[5] = pointsY[5] - middleDistance;
    pointsZ[5] = pointsZ[5] - middleDistance;
    //inverseZtopRight
    pointsX[6] = pointsX[6] + middleDistance;
    pointsY[6] = pointsY[6] - middleDistance;
    pointsZ[6] = pointsZ[6] - middleDistance;
    //inverseZbottomLeft
    pointsX[7] = pointsX[7] - middleDistance;
    pointsY[7] = pointsY[7] + middleDistance;
    pointsZ[7] = pointsZ[7] - middleDistance;
    //inverseZbottomRight
    pointsX[8] = pointsX[8] + middleDistance;
    pointsY[8] = pointsY[8] + middleDistance;
    pointsZ[8] = pointsZ[8] - middleDistance;
    
    //personal debuggin to check that all cordinates of the 8 points add up.
    // at this point... none of the 8 points should equal the values of points[0]
    for (int i = 0; i<pointsX.length; i++) {
      println(i, "x:", pointsX[i], " y:", pointsY[i], " z:", pointsZ[i]);
    }

     
  }

  void BoundsDraw() {
    //setup
    smooth();
    lights();
    fill(50, 50, 50, 50);
    stroke(1);
  
    //rotation
    rotateX(rotX);
    rotateY(-rotY); 
    //personal rotation troubleshoot
    println(rotX, rotY);
  
    /**
    here we draw a line() inbetween the 8 points to form a fully formed cube
    **/
    //top face
    //top left to top right
    stroke(#FFFFFF);
    line(pointsX[1], pointsY[1], pointsZ[1], pointsX[2], pointsY[2], pointsZ[2]);
    //inverse top left to inverse top right
    line(pointsX[5], pointsY[5], pointsZ[5], pointsX[6], pointsY[6], pointsZ[6]);
    //top left to inverse top left
    line(pointsX[1], pointsY[1], pointsZ[1], pointsX[5], pointsY[5], pointsZ[5]);
    //top right to inverse top right
    line(pointsX[2], pointsY[2], pointsZ[2], pointsX[6], pointsY[6], pointsZ[6]);

    //bottom face
    //bottom left to bottom right
    line(pointsX[3], pointsY[3], pointsZ[3], pointsX[4], pointsY[4], pointsZ[4]);
    //inverse bottom left to inverse bottom right
    line(pointsX[7], pointsY[7], pointsZ[7], pointsX[8], pointsY[8], pointsZ[8]);
    //bottom left to inverse left
    line(pointsX[3], pointsY[3], pointsZ[3], pointsX[7], pointsY[7], pointsZ[7]);
    //bottom right to inverse bottom right
    line(pointsX[4], pointsY[4], pointsZ[4], pointsX[8], pointsY[8], pointsZ[8]);

    //four holding pillars to connect the previous two faces
    //top left to bottom left
    line(pointsX[1], pointsY[1], pointsZ[1], pointsX[3], pointsY[3], pointsZ[3]);
    //inverse top left to inverse bottom left
    line(pointsX[5], pointsY[5], pointsZ[5], pointsX[7], pointsY[7], pointsZ[7]);
    //top right to bottom right
    line(pointsX[2], pointsY[2], pointsZ[2], pointsX[4], pointsY[4], pointsZ[4]);
    //inverse top right to inverse bottom right
    line(pointsX[6], pointsY[6], pointsZ[6], pointsX[8], pointsY[8], pointsZ[8]);

  }


}
