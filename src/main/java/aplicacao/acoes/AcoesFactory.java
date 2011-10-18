package aplicacao.acoes;

import java.util.HashMap;
import java.util.Map;

public class AcoesFactory {
	
	private Map<AcoesEnum, AcaoAbstract> acoes;
	private static AcoesFactory instancia = new AcoesFactory();
	
	private AcoesFactory() {
		acoes = new HashMap<AcoesEnum, AcaoAbstract>();
		acoes.put(AcoesEnum.CRIARUSUARIO, new CriarUsuario());
		acoes.put(AcoesEnum.SEGUIR, new Seguir());
		acoes.put(AcoesEnum.POSTAR_MENSAGEM, new PostarMensagem());
		acoes.put(AcoesEnum.DEIXAR_DE_SEGUIR, new DeixarDeSeguir());
		acoes.put(AcoesEnum.LISTAR_ESTATISTICAS_USUARIO, new ListarEstatisticasUsuario());
		acoes.put(AcoesEnum.LISTAR_MENSAGEM_SEGUIDOS, new ListarMensagemSeguidos());
		acoes.put(AcoesEnum.LISTAR_MENSAGEM_USUARIO, new ListarMensagemUsuario());
		acoes.put(AcoesEnum.LISTAR_PALAVRA_MARCADA, new ListarMensagensPalavraMarcada());
		acoes.put(AcoesEnum.LISTAR_SEGUIDORES, new ListarSeguidores());
		acoes.put(AcoesEnum.LISTAR_SEGUIDOS, new ListarSeguidos());
		acoes.put(AcoesEnum.LISTAR_TENDENCIAS, new ListarSeguidos());
		acoes.put(AcoesEnum.LISTAR_TENDENCIAS, new ListarTendencia());
		acoes.put(AcoesEnum.RESETAR, new Resetar());
	}
	
	public static AcaoAbstract getAcao(AcoesEnum acao) {
		return instancia.acoes.get(acao);
	}
	
	public static String executaAcao(AcoesEnum nomeAcao, String comando) {
		AcaoAbstract acao = instancia.acoes.get(nomeAcao);
		if (nomeAcao != null)
			return acao.executa(comando);
		
		return "Acao invalida.";
	}	

}
