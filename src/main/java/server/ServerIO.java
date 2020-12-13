package server;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIO {
    private ServerSocket server;
    private int port;
    private static final int MAX_BUFFER_SIZE = 1024;
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerIO(int port) {
        this.port = port;
    }


    public void runServerSingle() throws IOException {
        int i=0;
        while(i<2) {
            this.server = new ServerSocket(this.port);
            System.out.println("base socket server started.");
            // the code will block here till the request come.
            Socket socket = server.accept();
            System.out.println("gggggg");
            InputStream inputStream = socket.getInputStream();
            byte[] readBytes = new byte[MAX_BUFFER_SIZE];
            int msgLen;
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("11111");
            while ((msgLen = inputStream.read(readBytes)) != -1) {
                System.out.println(new String(readBytes, 0, msgLen, "UTF-8"));
                stringBuilder.append(new String(readBytes, 0, msgLen, "UTF-8"));
            }
            System.out.println("2222");
            System.out.println("get message from client: " + stringBuilder);
            inputStream.close();
            socket.close();

        }

        server.close();
    }

    public static void main(String[] args) {
        ServerIO serverIO = new ServerIO(8888);
        try {
            serverIO.runServerSingle();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}