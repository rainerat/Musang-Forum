package com.musang.forum.client;

import com.musang.forum.controller.main.AllDiscussionsController;
import com.musang.forum.controller.main.ForumController;
import com.musang.forum.model.*;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private AllDiscussionsController discussionsController;
    private ForumController forumController;
    private final User user;

    private volatile boolean running = false;

    public Client(String serverAddress, int port, User user) {
        this.user = user;

        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            running = true;
            this.listenForMessages();
        } catch (IOException e) {
            System.err.println("[ CLIENT couldn't connect to the server ]");
        }
    }

    private void listenForMessages() {
        new Thread(() -> {
            try {
                String serializedMessage;
                while (running && (serializedMessage = in.readLine()) != null) {
                    if (serializedMessage.startsWith("NEW_FORUM:")) {
                        // Deserialize the new forum
                        String forumData = serializedMessage.substring("NEW_FORUM:".length());
                        Forum newForum = Forum.deserialize(forumData);  // Assuming you have a deserialize method

                        // Refresh the forum list
                        Platform.runLater(() -> {
                            discussionsController.addNewForum(newForum);
                        });
                    } else {
                        Message message = Message.deserialize(serializedMessage);
                        Platform.runLater(() -> {
                            if (message.getUser().getId() != user.getId() && message.getForumID() == CurrentForum.getInstance().get().getId()) {
                                forumController.receiveMessage(message);
                            }
                        });
                    }
                }
            } catch (IOException e) {
                System.err.println("[ Can't read from the server : Client closed ]");
            }
        }).start();
    }

    public void sendMessage(String text) {
        Forum currentForum = CurrentForum.getInstance().get();
        Message message = new Message(text, user, currentForum.getId());
        out.println(message.serialize());
        forumController.addMessageToUI(message, true);
        message.save();
    }

    public void close() {
        running = false;
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }

    public void setDiscussionsController(AllDiscussionsController discussionsController) {
        this.discussionsController = discussionsController;
    }

}
