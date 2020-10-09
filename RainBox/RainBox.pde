Drop drop;
BoxBounds bb;
BoxBounds cc;
int dropNum;

float rotX, rotY, offset;
void setup()
{
  
  size(1400, 1400, P3D);
  //global variable used to sync everything to scale = Cube Size & rain parameters
  
  offset = 500;
  drop = new Drop();
  bb = new BoxBounds();
  //cc = new BoxBounds();
  

  //cc.BoundsSet(0, 0, 0);
  bb.BoundsSet(0, 0, 0);
  //I dont recoment going above 9000
  dropNum = drop.setDrops(9000);
}

void draw()
{

  background(#000000);
  //this translate is very important because this is what set's everything in the middle of the screen
  //INCLUDING THE ROTATION - rotation around center of box in 
  // therefor I can have the box bounds cnter variable be 0,0,0 for the sake of simplicity when debugging
  translate(width/2, height/2);
  bb.BoundsDraw();
  //cc.BoundsDraw();
  //sphere(500);

  pushMatrix();
  translate(-offset/2, -offset, -offset/2);
  drop.ballDraw();
  //sideway movement, gravity, forwards movemnt
  // slow down gravity enough and it's just like snow
  drop.ballMove(1, 18, -1);
  popMatrix();
}

void mouseDragged() {
  rotY -= (mouseX - pmouseX) * 0.01;
  rotX -= (mouseY - pmouseY) * 0.01;
}
