package aplicacao.acoes;

import persistencia.Persistencia;

public class Resetar extends AcaoAbstract {

	public String executa(String comando) {
		try {
			Persistencia.getInstancia().reset();
		} catch (Exception e) {
			return e.getMessage().trim();
		}
		
		return "ok";
	}
}
