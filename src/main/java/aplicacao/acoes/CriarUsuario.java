package aplicacao.acoes;

import negocio.Usuario;

public class CriarUsuario {
	
	public static String executa(String comando) {

		try {
			String nome = AcoesHelper.parseComando(comando)[0];
			
			Usuario user = new Usuario();
			user.setNome(nome);
			user.salvar();
		} catch (Exception e) {
			return e.getMessage().trim();
		}
		
		return "ok";
	}

}
