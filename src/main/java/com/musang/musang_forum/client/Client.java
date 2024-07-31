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

    public Client(String serverAddress, int port, ForumController controller) {
        this.controller = controller;

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
                String message;
                while ((message = in.readLine()) != null) {
                    String finalMessage = message;
                    Platform.runLater(() -> controller.receiveMessage(finalMessage));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String message) {
        out.println(message);
        this.saveMessage(message);
    }

    private void saveMessage(String message) {
        User currentUser = CurrentUser.getInstance().get();
        Forum currentForum = CurrentForum.getInstance().get();
        new Message(message, currentUser.getId(), currentForum.getId()).save();
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
