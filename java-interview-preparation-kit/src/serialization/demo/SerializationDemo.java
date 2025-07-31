package serialization.demo;

import java.io.*;

/*Notes on Serialization
* In Java, the Serializable interface is used to enable the serialization of an object,
* which is the process of converting an object's state into a byte stream.
* This byte stream can then be reverted back into a copy of the object, a process known
* as deserialization.
*
* Purpose of Serializable in Java
Persistence: Objects can be saved to a file or a database and later reconstructed. This is useful for
* saving the state of an object to be retrieved and used at a later time.

Communication: Objects can be sent over a network, for instance, between different components of a
* distributed application. Serialization allows objects to be transferred as a stream of bytes.

Deep Copy: Serialization can be used to create a deep copy of an object. By serializing and then
* deserializing an object, a new instance with the same state can be created.

Caching: Objects can be serialized and stored in a cache for quick retrieval without the need to
* recreate the object from scratch.
*
*
* In below scenario we're trying to store an object, which can be vulnerable, so we serialize it
* and the format of that can only be understood by java, not by humans*/
public class SerializationDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p = new Person("Varun", "varun@hotmail.com", "Am not sensitive");

        FileOutputStream fos = new FileOutputStream("person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        System.out.println("Employee has been serialized, please check person.txt file for more info.");

        FileInputStream fis = new FileInputStream("person.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person savedPersonObject = (Person)ois.readObject();

        System.out.println("deserialized object: " + savedPersonObject);
        /*sample output
        * deserialized object: Person{name='Varun', email='varun@hotmail.com', notSensitiveData='null'}
        * Above you can see notSensitiveData(transient) is not stored at all*/

    }


}

class Person implements Serializable {
    private String name;
    private String email;
    /*Fields marked with transient will not be serialized, means are not stored in the output file
    * we use this to store some sensitive data, which is not required to serialize*/
    private transient String notSensitiveData;

    public String getName() {
        return name;
    }
    public String getNotSensitiveData() {
        return notSensitiveData;
    }

    public void setNotSensitiveData(String notSensitiveData) {
        this.notSensitiveData = notSensitiveData;
    }


    public Person(String name, String email, String notSensitiveData) {
        this.name = name;
        this.email = email;
        this.notSensitiveData = notSensitiveData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", notSensitiveData='" + notSensitiveData + '\'' +
                '}';
    }
}

