package aplicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private static final int PORTA = 1234;
    
    public void iniciar() throws IOException {
        ServerSocket socket = new ServerSocket(PORTA);

        try {
            while (true) {
                atenderCliente(socket.accept());
            }
        } finally {
            socket.close();
        }
    }
    
    private void atenderCliente(final Socket cliente) throws IOException {        
        String comando = readLine(cliente.getInputStream());
        writeLine(cliente.getOutputStream(), Apresentacao.getInstancia().processa(comando));
        cliente.close();
    }
    
    private static String readLine(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }
    
    private static void writeLine(OutputStream out, String linhas) throws IOException {
        out.write(linhas.getBytes());
        out.write('\n');
    }
}