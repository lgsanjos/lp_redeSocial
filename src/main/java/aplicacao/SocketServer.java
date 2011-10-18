package aplicacao;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {
    private static final int PORTA = 1234;
    
    public void iniciar() throws IOException {
        ServerSocket socket = new ServerSocket(PORTA);

        try {
            while (true) {
            	SocketClient client = new SocketClient(socket.accept());
            	Thread clientExecution = new Thread(client);
            	clientExecution.start();
            }
        } finally {
            socket.close();
        }
    }
    
}