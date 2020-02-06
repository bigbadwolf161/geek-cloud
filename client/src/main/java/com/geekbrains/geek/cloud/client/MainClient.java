package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.FileTransfer;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClient {
    public static final int serverPort = 2525;
    String filePath = "/Users/bigbadwolf/Desktop/geek-cloud/client_repository/1.txt";

    public static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", serverPort);
            FileTransfer.read(s);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}