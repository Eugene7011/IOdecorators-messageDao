package com.podzirey.iostreams.messagedao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class SerializationMessageDao implements MessageDao {
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    public SerializationMessageDao(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void save(Message message) throws IOException {
        objectOutputStream.writeObject(message);
    }

    @Override
    public Message load() throws IOException, ClassNotFoundException {
        return (Message) objectInputStream.readObject();
    }
}
