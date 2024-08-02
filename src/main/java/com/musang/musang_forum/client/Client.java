package com.musang.musang_forum.client;

import com.musang.musang_forum.controller.ForumController;
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

    public Client(String serverAddress, int port, ForumController controller, User user) {
        this.controller = controller;
        this.user = user;

        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            this.listenForMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForMessages() {
        new Thread(() -> {
            try {
                String serializedMessage;
                while ((serializedMessage = in.readLine()) != null) {
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
        Message message = new Message(text, user);
        out.println(message.serialize());
        controller.addMessageToUI(message, true);
//        this.saveMessage(message);
    }

    private void saveMessage(String message) {
//        User currentUser = CurrentUser.getInstance().get();
//        Forum currentForum = CurrentForum.getInstance().get();
//        new Message(message, currentUser.getId(), currentForum.getId()).save();
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
