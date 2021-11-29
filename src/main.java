import java.io.IOException;

public class main {

    public static servidor server;

    public static void main(String[] args) {

        try {
            server = new  servidor();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
