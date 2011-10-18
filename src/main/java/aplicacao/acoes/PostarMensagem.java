package aplicacao.acoes;

import negocio.Usuario;

public class PostarMensagem extends AcaoAbstract {

	private static String extrairNome(String comando) {
	  if (comando.indexOf(" ") > 0) {
		  return comando.substring(0, comando.indexOf(" "));
	  }
	  return comando;
	}
	
	private static String extrairMensagem(String comando) {
 	  if (comando.indexOf(" ") > 0) {
	    return comando.substring(comando.indexOf(" "), comando.length());
 	  }
 	  
 	  return "";
	}

	public String executa(String comando) {
		try {
			
			String nomeUsuario = extrairNome(comando);
			String mensagem = extrairMensagem(comando);
		
			Usuario user = Usuario.procuraUsuario(nomeUsuario);
			if (user == null) {
				return "usuario-nao-encontrado";
			}
		
			user.postarMensagem(mensagem);
		} catch (Exception e) {
			return e.getMessage().trim();
		}
		
		return "ok";
	}
}
