import javax.imageio.ImageIO;
import javax.net.ssl.SSLSocketFactory;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "test.jks");
        Socket socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket("localhost", 4444);
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        PrintWriter printWriter = new PrintWriter(out, true);
        BufferedReader commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter a username: ");
        printWriter.println(commandPromptBufferedReader.readLine());
        while (true) {
            BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] data = bos.toByteArray();
            int length = data.length;
            printWriter.write(length);
            printWriter.flush();
            for (int i = 0; i <= length / 10000; i++) {
                if (length - i * 10000 > 10000) {
                    out.write(data, i * 10000, 10000);
                } else {
                    out.write(data, i * 10000, length - i * 10000);
                }
            }
            System.out.println("sent data: " + length + " bytes");
            System.out.print("message reply from server: ");
            System.out.println(socketBufferedReader.readLine());
            socket.close();
            out.close();
            break;
        }
    }
}
