package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.CloudPackage;
import com.geekbrains.geek.cloud.common.FileTransfer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer implements Runnable{
    public static final String serverIP = "80.94.162.69";
    public static final int serverPort = 2525;

    String filePath = "/Users/bigbadwolf/Desktop/geek-cloud/server_repository/1.txt";

    private Socket socket;

    public MainServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Клиент подключился");
        FileTransfer.send(socket, new File(filePath));
        System.out.println("Клиент отключился");
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);

            while (true) {

                Socket incoming = serverSocket.accept();
                Thread thread = new Thread(new MainServer(incoming));
                thread.start();
            }
        } catch (IOException ex) {
            System.out.println("Ошибка.");
        }
    }
}
