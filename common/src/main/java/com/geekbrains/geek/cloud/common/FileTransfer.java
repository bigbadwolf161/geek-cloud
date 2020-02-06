package com.geekbrains.geek.cloud.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileTransfer {
    private static int bufferSize = 1024;

    public static void send(Socket socket, File imageFile) {
        FileInputStream inputFile = null;
        ObjectOutputStream out = null;
        FilePackage filePackage = null;
        byte [] bytes = new byte[bufferSize];
        int length = 0;

        try {
            try {
                inputFile = new FileInputStream(imageFile);
                out = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    length = inputFile.read(bytes, 0, bufferSize);
                    if (length == -1) {
                        filePackage = new FilePackage(bytes, 0, true);
                        out.writeObject(filePackage);
                        break;
                    }
                    filePackage = new FilePackage(bytes, length, false);
                    out.writeObject(filePackage);
                    out.flush();
                }
            } finally {
                inputFile.close();
                System.out.println("Файл передан.");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static File read(Socket socket) {
        File file = null;
        FileOutputStream outFile = null;
        ObjectInputStream input = null;
        FilePackage filePackage = null;
        try {
            try {
                outFile = new FileOutputStream(new File("/Users/bigbadwolf/Desktop/geek-cloud/client_repository/1.txt"));
                input = new ObjectInputStream(socket.getInputStream());
                filePackage = (FilePackage) input.readObject();

                while (!filePackage.isFinish()) {
                    outFile.write(filePackage.getBytes(), 0, filePackage.getLength());
                    filePackage = (FilePackage) input.readObject();
                }
            } finally {
                outFile.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    private static class FilePackage implements Serializable {
        private boolean finish;
        private int length;
        private byte[] bytes;

        public FilePackage(byte[] b, int length, boolean f) {
            this.length = length;
            bytes = b.clone();
            finish = f;
        }

        public boolean isFinish() {
            return finish;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public int getLength() {
            return this.length;
        }
    }
}
