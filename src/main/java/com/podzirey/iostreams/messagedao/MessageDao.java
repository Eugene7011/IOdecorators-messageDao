package com.podzirey.iostreams.messagedao;

import java.io.IOException;

public interface MessageDao {
    void save(Message message) throws IOException;

    Message load() throws IOException, ClassNotFoundException;
}
