import java.io.IOException;

public class movil {
    public static void main(String[] args) {
        try {
            new  servidor().start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
