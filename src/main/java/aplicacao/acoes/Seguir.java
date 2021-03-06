package aplicacao.acoes;

import negocio.Relacionamento;

public class Seguir extends AcaoAbstract {
	
	public String executa(String comando) {
		
		try {
			String[] parsed = AcaoAbstract.parseComando(comando);
			
			String nomeSeguidor = parsed[0];
			String nomeSeguido = parsed[1];
			
			Relacionamento rel = new Relacionamento();
			rel.seguir(nomeSeguidor, nomeSeguido);
		} catch(Exception e) {
			return e.getMessage().trim();
		}
		
		return "ok";
	}

}
