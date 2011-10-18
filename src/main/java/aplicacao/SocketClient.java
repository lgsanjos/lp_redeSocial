package aplicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient implements Runnable {

	private final Socket cliente;
	
	public SocketClient (final Socket cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
        try {
        	//System.out.println("  Thread: " + Thread.currentThread().getId());
        	
            String comando = readLine(cliente.getInputStream());
            //System.out.println("    In: " + comando);
            
        	String retorno = Apresentacao.getInstancia().processa(comando);
        	//System.out.println("    Out: " + retorno);
        	
        	writeLine(cliente.getOutputStream(), retorno);
        	
        	cliente.close();
        	
        } catch (Exception e) {
        	System.out.println("    Exception: " + e.getMessage());
        	
        } finally {
        	//System.out.println("  DisposeThread: " + Thread.currentThread().getId());
        }
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
