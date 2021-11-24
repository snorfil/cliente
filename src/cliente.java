import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class cliente extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private comunicacion inter;
    private int jugador ;


    public cliente(Socket cli ,comunicacion in,int player) throws IOException
    {
        inter = in;
        clientSocket = cli;
        jugador = player;
        startConnection();

    }

    public void startConnection() throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }


    @Override
    public void run() {
        while (true)
        try {
            in.readLine();
            inter.broadcast(jugador);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        clientSocket.close();
        inter = null;
        out.close();
        clientSocket.close();
    }
}
