package game;

import java.io.*;


public class SaveSystem //https://www.geeksforgeeks.org/serialization-in-java/
{
    public int currentSaveIndex = 1;
    public SavedInfo currentSave;

    // Serializes saved data to a file
    public void Save(SavedInfo newSave)
    {
        SavedInfo savedInfo = new SavedInfo(newSave.score,
         newSave.health, newSave.xPos, newSave.levelIndex, newSave.getCurrentShip()) ;

        try
        {
            FileOutputStream file = new FileOutputStream("SavedData"+currentSaveIndex+".VeryMuchLearningRandomStuff");

            ObjectOutputStream oos = new ObjectOutputStream(file);

            oos.writeObject(savedInfo);

            currentSave = savedInfo;

            oos.close();
            file.close();
        }catch(IOException exception)
        {
            System.out.println("BIG ERROR WITH SAVING FILE : Fatal Io Exception");
        }
    }

    // Deserializes saved data from file
    public void Load()
    {
        SavedInfo savedInfo = new SavedInfo();
        File savedFile = new File("SavedData"+currentSaveIndex+".VeryMuchLearningRandomStuff");
        if(savedFile.exists())
        {
            try
            {
                FileInputStream file = new FileInputStream("SavedData"+currentSaveIndex+".VeryMuchLearningRandomStuff");
                ObjectInputStream ois = new ObjectInputStream(file);
                
                savedInfo = (SavedInfo)ois.readObject();
                currentSave = savedInfo;
    
                file.close();
                ois.close();
                System.out.println("File has been loaded");
            }catch(IOException exception)
            {
                System.out.println("BIG ERROR WITH SAVING FILE : Fatal Io Exception");
            }catch(ClassNotFoundException exception)
            {
                System.out.println("Massive Loading Error : File name we choose to load does not exist or can not be accessed");
            }
            // Creates a new save if no file exists
        }else
        {
            //Default level one parameters
            Save(new SavedInfo(0, 3, 0, 0, 0));
        }

    }
    // resets save
    public void resetSave()
    {
        Save(new SavedInfo(0, 3, 0, 0, 0));
    }
}
