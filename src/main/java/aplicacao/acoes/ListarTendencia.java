package aplicacao.acoes;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import negocio.Mensagem;

public class ListarTendencia {
	
	private static final int totalDeRetorno = 5;
		
	public static String executa(String comando) {
		
		try {
			
			TreeMap<String, Integer> todasTendencias = Mensagem.listarTendencias();
			String retorno = "";

			Set msgSet = todasTendencias.entrySet();
			Iterator i = msgSet.iterator();
			
			int contador = 0;
			while ( i.hasNext() && (contador < totalDeRetorno) ) {
	           Map.Entry m =(Map.Entry)i.next();
	           
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
