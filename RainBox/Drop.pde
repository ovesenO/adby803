class Drop
{
  float[] x;
  float[] y;
  float[] z;
  float[] radi;
  float radiusRange = 3;
  int setDrops(int num)
  {
    x = new float[num];
    y = new float[num];
    z = new float[num];
    radi = new float[num];

    for (int i = 0; i<x.length; i++) {
      x[i] = random(0, offset);
      y[i] = random(0, offset);
      z[i] = random(0, offset);
      radi[i] = random(1,1);
    }
    return num;
  }

  void ballDraw()
  {
    //Lights on the rain drops make them look sd from certain agnles...
    noStroke();
    fill(108, 215, 255, 40);
    //set's the drop position draws it then undoes that translation so as not to affect the next drops position
    for (int i = 0; i<x.length; i++) {
      translate(x[i], y[i], z[i]);
      //The box shape is much more optimized than the sphere
      //hence we can have a much larger amount of drops
      //sphere(radi[i]
      box(radi[i]);
      translate(-x[i], -y[i], -z[i]);
    }
 
  }

  void ballMove(float sideways, float gravity, float ahead) {
    for (int i = 0; i<x.length; i++) {
      //left movement 
      x[i]=x[i]+random(sideways-2, sideways);
      //gravity
      y[i]=y[i]+random(gravity-3, gravity);
      //forwards and backwards
      z[i]=z[i]+random(ahead-2,ahead);

      //Spawn rain from bottom of cube to top
      if (y[i]>offset*1.5-radi[i]) {
        y[i] = offset*0.5;
      }
      //addition logic reset __ To keep the rainwithin bounds of the box
      else if (x[i]>offset) {
        x[i] = 0;
      }
      else if (z[i]>offset) {
        z[i] = 0;
      }

      //negative logic reset __ To keep the rain within bounds of the box
      else if (x[i]<0) {
        x[i] = offset;
      }
      else if (z[i]<0) {
        z[i] = offset;
      }
    }
  }
}
