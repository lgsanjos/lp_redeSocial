package aplicacao.acoes;

import negocio.Relacionamento;

public class DeixarDeSeguir extends AcaoAbstract {
	
	public String executa(String comando) {
		
		try {
			String[] parsed = parseComando(comando);
			
			String nomeSeguidor = parsed[0];
			String nomeSeguido = parsed[1];
			
			Relacionamento rel = new Relacionamento();
			rel.deixarDeSeguir(nomeSeguidor, nomeSeguido);
		} catch(Exception e) {
			return e.getMessage().trim();
		}
		
		return "ok";
	}

}
