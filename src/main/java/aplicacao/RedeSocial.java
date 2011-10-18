package aplicacao;

public class RedeSocial {

	public static void main(String[] params) {
		
		System.out.println("Rede Social - Luiz Guilherme S. Anjos RA: 45220");
        System.out.println("Iniciando servidor...");
        
        try {
        	SocketServer servidor = new SocketServer();
        	servidor.iniciar();
        } catch (Exception e) {
        	System.out.println("Fatal error: " + e.getMessage());	
        }
        
	}
	
}
