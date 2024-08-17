package com.musang.forum.util;

import com.musang.forum.client.Client;

public class ClientManager {
    public static Client client;

    public static void setClient(Client x) {
        client = x;
    }

    public static Client getClient() {
        return client;
    }
}
