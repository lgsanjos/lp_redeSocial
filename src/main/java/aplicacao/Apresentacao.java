package aplicacao;

import aplicacao.acoes.AcoesEnum;
import aplicacao.acoes.AcoesFactory;

public class Apresentacao {
	
	private static Apresentacao instancia;
	
	public static Apresentacao getInstancia() {
		if (instancia == null) {
			instancia = new Apresentacao();
		}
		
		return instancia;
	}
	
	public String processa(String comando) {
		
		String nomeDoComando = comando.split(" ")[0];
		
		comando = comando.replace(nomeDoComando, "").trim();
		AcoesEnum acao = AcoesEnum.getEnumOf(nomeDoComando);
		
		if (acao != null)		
			return AcoesFactory.executaAcao(acao, comando);
		
		return "Comando inválido.";
	}

}
