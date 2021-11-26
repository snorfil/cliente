import javax.xml.parsers.SAXParser;
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
    Partida partidas;

    public servidor() throws IOException {
        Init(1568);
    }
    public void Init(int port) throws IOException {

        System.out.println("____debug____Servidor : creando servidor central");
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
                System.out.println("____debug____Servidor : Accept()");

                clientSocket = serverSocket.accept();

                System.out.println("____debug____Servidor : añadiendo jugador.....");
                PrintWriter out;
                BufferedReader in;

                out = new PrintWriter(clientSocket.getOutputStream(), true);

                salidas.add(out);


                if (salidas.size() == 2)
                {
                    System.out.println("____debug____Servidor : creando partida con " + salidas.size() + " jugadores");
                    new Partida(salidas,500);

                }

//                if (entradas.size() == 2)
//                {
//                    partidas = new Partida(salidas);
//
//
//                    entradas.clear();
//                    salidas.clear();
//                }
            }catch (IOException e)
            {
                System.out.println("El servidor central no se creó");
            }
        }
    }
}
