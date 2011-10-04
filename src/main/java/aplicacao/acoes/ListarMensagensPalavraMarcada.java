package aplicacao.acoes;

import java.util.LinkedList;

import negocio.Mensagem;

public class ListarMensagensPalavraMarcada {

	public static String executa(String comando) {
		
		try {
			LinkedList<Mensagem> todasMensagens = Mensagem.todas();
			String retorno = "";
			
			for (int i = todasMensagens.size() -1; i >= 0; i--) {
				Mensagem mensagem = todasMensagens.get(i);
				if (mensagem.getMensagem().contains(comando))
					retorno += mensagem.getUsuarioCriador().getNome() + " " + mensagem.getMensagem().trim() + "\n"; 
			}

			return retorno.trim();
		} catch(Exception e ) {
			return e.getMessage();
		}
	}
	
}
