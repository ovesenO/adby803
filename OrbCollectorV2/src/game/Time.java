package game;

public class Time {

    public long lastTime;
    public double deltaTime = 0;

    // Delta time - calculates time between previous & current frame
    public void updateTime()
    {
        lastTime = System.nanoTime();
        deltaTime = (lastTime - (lastTime = System.nanoTime())) / -1000000.0;//Draw Frame
    }

}