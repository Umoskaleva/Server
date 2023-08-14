import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8080);) {// порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                String reply = "Привет я клиент";
                out.println(reply);
                out.flush();

                String replyServer = in.readLine();
                System.out.println(replyServer);
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
            throw new RuntimeException(e);
        }
    }
}
