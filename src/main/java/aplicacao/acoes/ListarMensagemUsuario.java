package aplicacao.acoes;

import java.util.LinkedList;

import negocio.Mensagem;

public class ListarMensagemUsuario {
	
	public static String executa(String comando) {
		
		try {
			LinkedList<Mensagem> msgs = Mensagem.todasDadoUsuario(comando);
			String retorno = "";
			
			for (int i = 0; i < msgs.size(); i++) {
				retorno += msgs.get(i).getMensagem().trim() + "\n";
			}
			
			return retorno.trim();
		} catch(Exception e) {
			return e.getMessage().trim();
		}
		
	}

}
