package aplicacao.acoes;

import java.util.LinkedList;

import negocio.Relacionamento;
import negocio.Usuario;

public class ListarSeguidos {
	
	public static String executa(String comando) {
		
		try {
			LinkedList<Relacionamento> rel = Relacionamento.todosDadoSeguidor(comando) ;
			
			String retorno = "";
			Usuario seguido;
			for (int i = rel.size() -1; i >= 0; i--) {
				seguido = rel.get(i).getUsuarioSeguido();
				retorno += seguido.getNome().trim() + "\n";
			}
			
			return retorno.trim();
		} catch(Exception e) {
			return e.getMessage().trim();
		}
	}	

}
