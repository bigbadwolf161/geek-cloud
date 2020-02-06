//package Serializator;
//
//import java.io.*;
//
//public class SerializatorImpl implements Serializator {
//    @Override
//    public void serialize(com.geekbrains.geek.cloud.common.File o, String fileName) {
//        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
//            objectOutputStream.writeObject(o);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Object deserialize(String fileName) throws IOException, ClassNotFoundException {
//        try(FileInputStream fileInputStream = new FileInputStream(fileName);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
//        {
//            return (File) objectInputStream.readObject();
//        }
//    }
//}
