package com.podzirey.iostreams.messagedao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

class DataMessageDao implements MessageDao {
    private DataOutputStream objectOutputStream;
    private DataInputStream objectInputStream;
    private short messageSize;

    public DataMessageDao(DataOutputStream objectOutputStream, DataInputStream objectInputStream) {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void save(Message message) throws IOException {
        Date date = message.getDate();
        double amount = message.getAmount();
        short messageSize = (short) (message.getMessage().getBytes().length);
        String messageText = message.getMessage();

        objectOutputStream.writeLong(date.getTime());
        objectOutputStream.writeDouble(amount);
        objectOutputStream.writeShort(messageSize);
        objectOutputStream.writeBytes(messageText);
    }

    @Override
    public Message load() throws IOException {
        Message loadedMessage = new Message();

        Date date = new Date(objectInputStream.readLong());
        loadedMessage.setDate(date);

        loadedMessage.setAmount(objectInputStream.readDouble());

        messageSize = objectInputStream.readShort();
        String text = new String(objectInputStream.readNBytes(messageSize));
        loadedMessage.setMessage(text);

        return loadedMessage;
    }
}
