package com.musang.musang_forum.util;

import com.musang.musang_forum.client.Client;

public class ClientManager {
    public static Client client;

    public static void setClient(Client x) {
        client = x;
    }

    public static Client getClient() {
        return client;
    }
}
