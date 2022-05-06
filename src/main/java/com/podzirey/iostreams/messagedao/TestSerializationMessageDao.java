package com.podzirey.iostreams.messagedao;

import java.io.*;
import java.util.Date;

public class TestSerializationMessageDao {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializationMessageDao serializationMessageDao =
                new SerializationMessageDao(new ObjectOutputStream(new FileOutputStream("test")),
                        new ObjectInputStream(new FileInputStream("test")));

        serializationMessageDao.save(new Message(new Date(), "Hello SerializationMessage!", 1));

        Message message = serializationMessageDao.load();
        System.out.println(message.toString());
    }
}
