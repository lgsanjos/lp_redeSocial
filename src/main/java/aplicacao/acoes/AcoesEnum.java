package aplicacao.acoes;

import java.util.HashMap;
import java.util.Map;

public enum AcoesEnum {
	
	CRIARUSUARIO("criar-usuario"),	
	DEIXAR_DE_SEGUIR("deixar-de-seguir"),
	LISTAR_ESTATISTICAS_USUARIO("listar-estatisticas-usuario"),
	LISTAR_MENSAGEM_SEGUIDOS("listar-mensagens-seguidos"),
	LISTAR_MENSAGEM_USUARIO("listar-mensagens-usuario"),
	LISTAR_PALAVRA_MARCADA("listar-mensagens-com-palavra-marcada"),
	LISTAR_SEGUIDORES("listar-seguidores"),
	LISTAR_SEGUIDOS("listar-seguidos"),
	LISTAR_TENDENCIAS("listar-tendencia"),
	POSTAR_MENSAGEM("postar-mensagem"),
	RESETAR("resetar"),
	SEGUIR("seguir");
	
	private static final Map<String, AcoesEnum> acoes = new HashMap<String, AcoesEnum>();
	private final String comando;
	
	private AcoesEnum(String comando) {
		this.comando = comando;
	}
	
	static {
		for (AcoesEnum acao : AcoesEnum.values()) {
			AcoesEnum.acoes.put(acao.getComando(), acao);
		}
	}	
	
	public String getComando() {
		return this.comando;
	}
	
	public static AcoesEnum getEnumOf(String comando) {
		return AcoesEnum.acoes.get(comando);
	}
	
}
