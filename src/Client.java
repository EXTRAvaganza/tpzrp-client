import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "test.jks");
        Socket socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket("localhost", 4444);
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter a username: ");
        printWriter.println(commandPromptBufferedReader.readLine());
        String message = null;
        while (true) {
            System.out.println("please enter a message to send to server: ");
            message = commandPromptBufferedReader.readLine();
            if (message.equals("quit")) {
                printWriter.println(message);
                socket.close();
                break;
            }
            printWriter.println(message);
            System.out.println("message reply from server: ");
            System.out.println(socketBufferedReader.readLine());
        }
    }
}
