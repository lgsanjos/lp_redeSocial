package aplicacao.acoes;

import java.util.Arrays;
import java.util.HashMap;

import aplicacao.MapUtils;

import negocio.Mensagem;

public class ListarTendencia {
	
	private static final int totalDeRetorno = 5;
	
	public static String executa(String comando) {
		
		try {
			
			HashMap<String, Integer> msgs = Mensagem.listarTendencias();
			String retorno = "";

			int i = 0;
			while ( i < ListarTendencia.totalDeRetorno) {
				retorno +=  MapUtils.getKeysByValue(msgs, lista[i]) + "\n";
				i ++;
			}
			
			return retorno.trim();				
			
			
		} catch(Exception e ) {
			return e.getMessage();
		}
	}

}
