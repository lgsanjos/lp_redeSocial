package aplicacao;

import aplicacao.acoes.CriarUsuario;
import aplicacao.acoes.DeixarDeSeguir;
import aplicacao.acoes.ListarEstatisticasUsuario;
import aplicacao.acoes.ListarMensagemSeguidos;
import aplicacao.acoes.ListarMensagemUsuario;
import aplicacao.acoes.ListarSeguidores;
import aplicacao.acoes.ListarSeguidos;
import aplicacao.acoes.ListarTendencia;
import aplicacao.acoes.PostarMensagem;
import aplicacao.acoes.Seguir;

public class Apresentacao {
	
	private static Apresentacao instancia;
	
	public static Apresentacao getInstancia() {
		if (instancia == null) {
			instancia = new Apresentacao();
		}
		
		return instancia;
	}
	
	public String processa(String comando) {
		
		if (comando.startsWith("criar-usuario")) {
		  comando = comando.replace("criar-usuario", "").trim();
		  return CriarUsuario.executa(comando);	
		}
		
		if (comando.startsWith("postar-mensagem")) {
			  comando = comando.replace("postar-mensagem", "").trim();
			  return PostarMensagem.executa(comando);	
		}
		
		if (comando.startsWith("listar-mensagens-usuario")) {
			  comando = comando.replace("listar-mensagens-usuario", "").trim();
			  return ListarMensagemUsuario.executa(comando);	
		}
		
		if (comando.startsWith("seguir")) {
			  comando = comando.replace("seguir", "").trim();
			  return Seguir.executa(comando);	
		}

		if (comando.startsWith("listar-seguidores")) {
			  comando = comando.replace("listar-seguidores", "").trim();
			  return ListarSeguidores.executa(comando);	
		}

		if (comando.startsWith("listar-seguidos")) {
			  comando = comando.replace("listar-seguidos", "").trim();
			  return ListarSeguidos.executa(comando);
		}

		if (comando.startsWith("deixar-de-seguir")) {
			  comando = comando.replace("deixar-de-seguir", "").trim();
			  return DeixarDeSeguir.executa(comando);
		}
		
		if (comando.startsWith("listar-mensagens-seguidos")) {
			  comando = comando.replace("listar-mensagens-seguidos", "").trim();
			  return ListarMensagemSeguidos.executa(comando);
		}
		
		if (comando.startsWith("listar-estatisticas-usuario")) {
			  comando = comando.replace("listar-estatisticas-usuario", "").trim();
			  return ListarEstatisticasUsuario.executa(comando);
		}
		
		if (comando.startsWith("listar-tendencia")) {
			  comando = comando.replace("listar-tendencia", "").trim();
			  return ListarTendencia.executa(comando);
		}		

		return "Comando inválido.";
	}

}
