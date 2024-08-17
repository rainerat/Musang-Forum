package com.musang.forum.server;

import com.musang.forum.model.Forum;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private static final int PORT = 59001;
    private static final Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Server is running...");

        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(listener.accept()).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientHandlers) {
                    clientHandlers.add(this);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    this.broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientHandlers) {
                    clientHandlers.remove(this);
                }
            }
        }

        private  void  broadcastNewForum(Forum forum){
            String serializedForum = forum.serialize();
            broadcastMessage("NEW_FORUM:" + serializedForum);
        }
        private void broadcastMessage(String message) {
            synchronized (clientHandlers) {
                for (ClientHandler handler : clientHandlers) {
                    handler.out.println(message);
                }
            }
        }
    }
}
