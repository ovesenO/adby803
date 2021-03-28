package game;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

import org.jbox2d.common.Vec2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityGenerator
{
    private List<Asteroid> spawnedAsteroids = new ArrayList<Asteroid>();
    private List<Orb> spawnedOrbs = new ArrayList<Orb>();
    private Level currentLevel;
    private World world;
    private Vec2 spawnPosXRange;
    private int spawnHeight;
    private float currentCoolDown;
    Time time = new Time();

    /***
     * Entity Generator is responsible for spawning entities such as orbs and
     * asteroids into game; deleting these entities as well as updating them.
     *
     *
     * @param world             City engine world
     * @param spawnPosXRange    Range of which entities will spawn [0..n]
     * @param spawnHeight       Optimized spawn height above player view
     * @param initialLevel      Initial level entity generator gets constructed with
     */
    public EntityGenerator(World world,  Vec2 spawnPosXRange, int spawnHeight, Level initialLevel)
    {
        this.world = world;
        this.spawnPosXRange = spawnPosXRange;
        this.spawnHeight = spawnHeight;
        this.currentLevel = initialLevel;
    }

    /***
     *Weight based rarity system: Each entity that is stored in
     * an arraylist in the Level class has it's own Weight, using
     * the sum of all weights and individual weights I calculate
     * the rarity. I spawn entities every x seconds based on a timer.
     */
    public void entityBehaviour()
    {
        time.updateTime();

        if(currentCoolDown > 0)
        {
            currentCoolDown -= time.deltaTime;
        }
        if(currentCoolDown <= 0)
        {
            currentCoolDown = currentLevel.getSpawnRate();
            int i = getRandomNumber(0, currentLevel.getWeightSum()+1);
            
            //This is where we spawn the entities depending on their individual Rarity Weight.
            loop: for(WeightRarity<String, Integer, Integer> e: currentLevel.allEntities)
            {
                if(e.GetWeightSum() >= i && i >= e.GetWeightSum() - e.GetWeight())
                {

                    switch(e.GetEntityName())
                    {
                        case "GreenOrb":
                            Shape greenOrbShape = new CircleShape(2);
                            Orb go = new Orb(world, greenOrbShape, new BodyImage("data/OrbGreen.png", 2), 10, "data/3.wav");
                            go.setPosition(new Vec2(getRandomNumber((int)spawnPosXRange.x, (int)spawnPosXRange.y), spawnHeight));
                            spawnedOrbs.add(go);
                            go.setLinearVelocity(new Vec2(0, -10));
                            break loop;

                        case "CyanOrb":
                            Shape cyanOrbShape = new CircleShape(2);
                            Orb oc = new Orb(world, cyanOrbShape, new BodyImage("data/OrbCyan.png", 2), 15, "data/4.wav");
                            oc.setPosition(new Vec2(getRandomNumber((int)spawnPosXRange.x, (int)spawnPosXRange.y), spawnHeight));
                            spawnedOrbs.add(oc);
                            oc.setLinearVelocity(new Vec2(0, -10));
                        break loop;

                        case "PinkOrb":
                            Shape pinkOrbShape = new CircleShape(2);
                            Orb po = new Orb(world, pinkOrbShape, new BodyImage("data/OrbPink.png", 2), 7, "data/2.wav");
                            po.setPosition(new Vec2(getRandomNumber((int)spawnPosXRange.x, (int)spawnPosXRange.y), spawnHeight));
                            spawnedOrbs.add(po);
                            po.setLinearVelocity(new Vec2(0, -10));
                        break loop;

                        case "OrangeOrb":
                            Shape orangeOrbShape = new CircleShape(2);
                             Orb oo = new Orb(world, orangeOrbShape, new BodyImage("data/OrbOrange.png", 2), 5, "data/1.wav");
                             oo.setPosition(new Vec2(getRandomNumber((int)spawnPosXRange.x, (int)spawnPosXRange.y), spawnHeight));
                             spawnedOrbs.add(oo);
                             oo.setLinearVelocity(new Vec2(0, -10));
                         break loop;

                         case "SmallAsteroid":
                           Shape asteroidShape = new CircleShape(4);
                           Asteroid sa = new Asteroid(world, asteroidShape, new BodyImage("data/Asteroid.png", 2), "data/1.wav");
                           sa.setPosition(new Vec2(getRandomNumber((int)spawnPosXRange.x, (int)spawnPosXRange.y), spawnHeight));
                           spawnedAsteroids.add(sa);
                           sa.setLinearVelocity(new Vec2(0, -15));
                        break loop;

                        default:
                        System.out.println(e.GetEntityName() + " <-- That entity does not exist (yet)!");
                        break loop;
                    }
                }
            }
        }
    }

    public boolean clearEntities(boolean CLEARNOW)
    {
        if(CLEARNOW)
        {
            for(Iterator<Orb> IT = spawnedOrbs.iterator(); IT.hasNext();)
            {
                Orb o = IT.next();                
                o.destroy();
                IT.remove();
            }
            for(Iterator<Asteroid> AT = spawnedAsteroids.iterator(); AT.hasNext();)
            {
                Asteroid a = AT.next();                
                a.destroy();
                AT.remove();
            }
        }
        return CLEARNOW=false;
    }
    // Deletes orbs when they are out of bounds
    public void updateOrbs()
    {
        for(Iterator<Orb> IT = spawnedOrbs.iterator(); IT.hasNext();)
        {
            Orb o = IT.next();
            if(o.getPosition().y <= -29)
                {
                    
                    o.destroy();
                    IT.remove();
                }
        }
    }
    public void pushLevel(Level level)
    {
        currentLevel = level;
    }

    public int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void setSpawnPosXRange(Vec2 spawnPosXRange) { this.spawnPosXRange = spawnPosXRange; }

    public Vec2 getSpawnPosXRange() { return spawnPosXRange; }

    public void setSpawnHeight(int spawnHeight) { this.spawnHeight = spawnHeight; }

    public int getSpawnHeight() { return spawnHeight; }
}
