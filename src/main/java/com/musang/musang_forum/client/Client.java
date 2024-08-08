package com.musang.musang_forum.client;

import com.musang.musang_forum.controller.main.ForumController;
import com.musang.musang_forum.model.*;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final ForumController controller;
    private final User user;

    private volatile boolean running = false;

    public Client(String serverAddress, int port, ForumController controller, User user) {
        this.controller = controller;
        this.user = user;

        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            running = true;
            this.listenForMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForMessages() {
        new Thread(() -> {
            try {
                String serializedMessage;
                while (running && (serializedMessage = in.readLine()) != null) {
                    Message message = Message.deserialize(serializedMessage);
                    Platform.runLater(() -> {
                        if (message.getUser().getId() != user.getId()) {
                            controller.receiveMessage(message);
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String text) {
        Forum currentForum = CurrentForum.getInstance().get();
        Message message = new Message(text, user, currentForum.getId());
        out.println(message.serialize());
        controller.addMessageToUI(message, true);
        message.save();
    }

    public void close() {
        running = false;

        try {
            if (out != null) {
                out.close();
            }
            System.out.println("pass 1");
            if (in != null) {
                in.close();
            }
            System.out.println("pass 2");
            if (socket != null) {
                socket.close();
            }
            System.out.println("pass 3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
