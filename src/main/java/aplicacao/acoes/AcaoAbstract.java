package aplicacao.acoes;

public abstract class AcaoAbstract {
	
	public static String[] parseComando(String comando) {
		return comando.split(" ");
	}
	
	public abstract String executa(String comando );
	

}
