package aplicacao.acoes;

import java.util.LinkedList;
import negocio.Relacionamento;
import negocio.Usuario;

public class ListarSeguidores extends AcaoAbstract {

	public String executa(String comando) {
		
		try {
			LinkedList<Relacionamento> rel = Relacionamento.todosDadoSeguido(comando) ;
			
			String retorno = "";
			Usuario seguidor;
//			for (int i = 0; i < rel.size(); i++) {
			for (int i = rel.size() -1; i >= 0; i--) {				
				seguidor = rel.get(i).getUsuarioSeguidor();
				retorno += seguidor.getNome().trim() + "\n";
			}
			
			return retorno.trim();
		} catch(Exception e) {
			return e.getMessage().trim();
		}
	}
}
