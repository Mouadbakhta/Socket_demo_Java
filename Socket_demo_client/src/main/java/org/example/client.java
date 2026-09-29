package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", 8080);
        System.out.println("connect");
        PrintWriter writer =
                new PrintWriter(socket.getOutputStream(), true);

        writer.println("5 + 10");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );



        String result = reader.readLine();

        System.out.println("Serveur : " + result);

        socket.close();
    }
}
