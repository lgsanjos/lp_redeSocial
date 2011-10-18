package aplicacao.acoes;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import negocio.Mensagem;

public class ListarTendencia extends AcaoAbstract{
	
	private static final int totalDeRetorno = 5;
		
	public String executa(String comando) {
		
		try {
			
			TreeMap<String, Integer> todasTendencias = Mensagem.listarTendencias();
			String retorno = "";

			Set<Entry<String, Integer>> msgSet = todasTendencias.entrySet();
			Iterator<Entry<String, Integer>> i = msgSet.iterator();
			
			int contador = 0;
			while ( i.hasNext() && (contador < totalDeRetorno) ) {
	           Entry<String, Integer> m = i.next();
	           
		       String tendencia = (String)m.getKey();
		       retorno += tendencia + '\n';      
		       contador ++;     
			}
			
			return retorno.trim();				
			
			
		} catch(Exception e ) {
			return e.getMessage();
		}
	}

}
