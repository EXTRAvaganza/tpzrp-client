import Client.Client;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            new Client("localhost",4444).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
