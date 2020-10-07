package Client;

import javax.imageio.ImageIO;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
    private Socket socket;
    private final String HOST;
    private final int PORT;
    public Client(String host,int port) {
        this.HOST = host;
        this.PORT = port;
        System.setProperty("javax.net.ssl.trustStore", "test.jks");
        connectToServer(true);
        sendImage("D:\\image.jpg");
    }

    public Client(String host,int port,String imagePath) {
        this.HOST = host;
        this.PORT = port;
        connectToServer(false);
        sendImage(imagePath);
    }
    public Client(String host,int port,String imagePath,String certPath) {
        this.HOST = host;
        this.PORT = port;
        System.setProperty("javax.net.ssl.trustStore", certPath);
        connectToServer(true);
        sendImage(imagePath);
    }

    public void close() throws IOException {
        socket.close();
    }
    public void connectToServer(boolean ssl)
    {
        try {
            if(ssl)
                socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(HOST, PORT);
            else
                socket = SocketFactory.getDefault().createSocket(HOST,PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendImage(String imagePATH) {
        try
        {
            ImageIcon imageIcon = new ImageIcon(imagePATH);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            Image image = imageIcon.getImage();
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.createGraphics();
            graphics.drawImage(image,0,0,null);
            graphics.dispose();
            ImageIO.write(bufferedImage,"jpg",bufferedOutputStream);
            bufferedOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
