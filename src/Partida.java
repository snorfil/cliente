import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Partida extends Thread implements comunicacion{

    final int puerto = 500;
    ArrayList<PrintWriter> out;
    ArrayList<cliente> jugadores;
    int resultado = 0;
    private ServerSocket serverSocket;

    public Partida(ArrayList<PrintWriter> salidas)
    {
        out     = salidas;
        jugadores = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    inicio();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
    private void inicio() throws IOException {
        int contador = 1;
       while (true)
       {
           Socket sk = serverSocket.accept();
           jugadores.add(new cliente(sk, this,contador));
           contador++;
           if (contador == 3)
               break;
       }
       jugadores.get(0).start();
       jugadores.get(1).start();
    }

    @Override
    public void run() {
        super.run();

        try {
            inicio();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void broadcast(int jugador) {
        System.out.println("jugador : " + jugador);
        if (jugador == 1)
        {
            resultado++;
        }else
        {
            resultado--;
        }

    }
}

