package NPRTutorial.MidtermProject;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatRoomServer {
    private int port;
    public static ArrayList<Socket> ListSK;

    public ChatRoomServer(int port) {
        this.port = port;
    }

    private void execute() throws IOException {
        ServerSocket server = new ServerSocket(port);
        WriteServer write = new WriteServer();
        write.start();
        
        while (true) {
            Socket socket = server.accept();
            System.out.println("Accept a client with " + socket);
            ChatRoomServer.ListSK.add(socket);
            ReadServer read = new ReadServer(socket);
            read.start();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatRoomServer.ListSK = new ArrayList<>();
        ChatRoomServer server = new ChatRoomServer(9876);
        System.out.println("Server is waiting to accept user...");
        server.execute();
    }
}

class ReadServer extends Thread {
    private Socket socket;

    public ReadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                String message = dis.readUTF();
                if (message.contains("exit")) {
                    ChatRoomServer.ListSK.remove(socket);
                    dis.close();
                    System.out.println("Disconnected to " + socket);
                    socket.close();
                    continue;
                }
                for (Socket item : ChatRoomServer.ListSK) {
                    if (item.getPort() != socket.getPort()) {
                        DataOutputStream dos = new DataOutputStream(item.getOutputStream());
                        dos.writeUTF(message);
                    }
                }
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteServer extends Thread {
    @Override
    public void run() {
        DataOutputStream dos = null;
        Scanner input = new Scanner(System.in);

        while (true) {
            String message = input.nextLine();
            try {
                for (Socket item : ChatRoomServer.ListSK) {
                    dos = new DataOutputStream(item.getOutputStream());
                    dos.writeUTF("Server: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}