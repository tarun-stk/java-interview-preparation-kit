package serialization.demo;

import java.io.*;

/*Notes:
 *   When working with serialization, there might be a chance that we're modified our class
 * after serializing it, so when we try to deserialize it now, then we get exception because
 * class has been modified
 * In this scenario we can make use of serialization uuid, by default jre will generate one by default if
 * not created, explicit mention is much more useful, now when above scenario happens, we dont get exception
 * because the uuid will be different for our current class ana serialized object, deserializtion wil
 * happen only when uuid matches in both versions*/
public class SerializationWithUUID {
    public static void main(String[] args) {
//        serialize();
        deserialize();
    }

    private static void serialize() {
        Animal animal = new Animal(4);
        try {
            FileOutputStream fos = new FileOutputStream("animal.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(animal);
            System.out.println("animal object written");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void deserialize() {
        /*trying to deserialize without id*/
        try {
            FileInputStream fis = new FileInputStream("animal.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Animal savedAnimal = (Animal) ois.readObject();
            System.out.println("File read: " + savedAnimal);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

class Animal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int numberOfLimbs;
//    private String name;

//    public Animal(int numberOfLimbs, String  name) {
//        this.numberOfLimbs = numberOfLimbs;
//        this.name = name;
//    }

    public Animal(int numberOfLimbs) {
        this.numberOfLimbs = numberOfLimbs;
    }

    public int getNumberOfLimbs() {
        return numberOfLimbs;
    }

    public void setNumberOfLimbs(int numberOfLimbs) {
        this.numberOfLimbs = numberOfLimbs;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public String toString() {
        return "Animal{" +
                "numberOfLimbs=" + numberOfLimbs +
//                ", name='" + name + '\'' +
                '}';
    }
}

