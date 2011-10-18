package aplicacao.acoes;

import negocio.Mensagem;
import negocio.Relacionamento;

public class ListarEstatisticasUsuario extends AcaoAbstract {
	
	// número de mensagens, seguidos e seguidores
	public String executa(String comando) {
		
		try {
			String nome = AcaoAbstract.parseComando(comando)[0];
			String retorno = "";
			
			retorno += Mensagem.todasDadoUsuario(nome).size();
			retorno += "\n" + Relacionamento.todosDadoSeguidor(nome).size();
			retorno += "\n" + Relacionamento.todosDadoSeguido(nome).size();			
			
			return retorno;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
