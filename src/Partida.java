import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Partida extends Thread implements comunicacion {

    final int puerto ;
    private boolean enable = true;

    ArrayList<PrintWriter> out;
    ArrayList<Jugador> jugadores;
    private Partida partida = this;
    int resultado = 0;
    private ServerSocket serverSocket;

    public Partida(ArrayList<PrintWriter> salidas,int port) {
        puerto = port;
        out = salidas;
        jugadores = new ArrayList<>();
        partida.start();
    }

    @Override
    public void run() {
        super.run();
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("enviando puerto a los clientes");
            for (PrintWriter i:out) {
                i.println(puerto);
                i.close();
            }

            int contador = 1;
            while (true) {
                Socket sk = serverSocket.accept();
                System.out.println("___Partida___ Aceptado jugador a partida....");

                BufferedReader reader = new BufferedReader(new InputStreamReader(sk.getInputStream()));
                PrintWriter writer = new PrintWriter(sk.getOutputStream(),true);
                jugadores.add(new Jugador(writer,reader, partida, contador));

                contador++;

                if (jugadores.size() == 2) {
                    System.out.println("___Partida___ iniciando juego!!!!!");
                    for (Jugador i:jugadores) {
                        i.start();

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    public synchronized void broadcast(int jugador) {
        System.out.println("jugador : " + jugador);
        if (jugador == 1) {
            resultado++;
        } else {
            resultado--;
        }

    }

    @Override
    public void destruir() {

        if (enable)
        {
            System.out.println("Destruyendo partida");
            enable = false;
            for (Jugador i:jugadores) {
                i.destroy();
                i.interrupt();
            }
            this.interrupt();
            System.out.println("DESTRUYENDO LA PARTIDA");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

