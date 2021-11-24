import javax.sound.midi.Track;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class servidor extends Thread{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    ArrayList<BufferedReader> entradas;
    ArrayList<PrintWriter> salidas;

    public servidor() throws IOException {
        start(1568);
    }
    public void start(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        salidas = new ArrayList<>();
        entradas = new ArrayList<>();

    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            try {

                clientSocket = serverSocket.accept();

                PrintWriter out;
                BufferedReader in;

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                entradas.add(in);
                salidas.add(out);

                if (entradas.size() == 2)
                {
                    new Partida(salidas).start();
                    for (int i = 0; i < 2; i++) {

                        salidas.get(i).println(500);
                        salidas.get(i).close();
                    }
                    entradas.clear();
                    salidas.clear();
                }
            }catch (IOException e)
            {
            }
        }
    }
}
