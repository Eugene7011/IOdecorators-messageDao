package com.podzirey.iostreams.messagedao;

import java.io.*;
import java.util.Date;

public class TestDataMessageDao {

    public static void main(String[] args) throws IOException {
        DataMessageDao dataMessageDao = new DataMessageDao(new DataOutputStream(new FileOutputStream("test")),
                new DataInputStream(new FileInputStream("test")));

        dataMessageDao.save(new Message(new Date(), "Hello DataMessage!", 2));
        Message message = dataMessageDao.load();
        System.out.println(message);
    }

}
