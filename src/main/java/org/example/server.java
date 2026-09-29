package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("Server en attente...");

        while (true) {

            Socket socket = serverSocket.accept();

            Thread thread = new Thread(() -> {
                System.out.println(
                        "Client traité par : " +
                                Thread.currentThread().getName()
                );
                handleClient(socket);
            });

            thread.start();
        }
    }

    public static void handleClient(Socket socket) {

        try {

            System.out.println("Client connecté");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    );

            String operation = reader.readLine();

            String[] parts = operation.split(" ");

            int number1 = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int number2 = Integer.parseInt(parts[2]);

            int result ;
             switch (operator){
                 case "+":
                     result = number1 + number2;
                     break;
                 case "-":
                     result = number1 - number2;
                     break;
                 case "*":
                     result = number1 * number2;
                     break;
                 case "/":
                     result = number1 / number2 ;
                     break;
                 default:
                     System.out.println("Opérateur inconnu");
                     socket.close();
                     return;
             }









            PrintWriter writer =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );
             writer.println(result);


            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}