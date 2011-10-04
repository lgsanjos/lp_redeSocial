package testesFuncionais;

import negocio.Usuario;
import persistencia.Persistencia;
import persistencia.Tabela;
import junit.framework.TestCase;
import aplicacao.Apresentacao;

public class AcoesTest extends TestCase {
	
	@Override
	protected void setUp() {
		Persistencia.getInstancia().reset();
	}

	public void testCriarUsuarioHappyday() {
		
		String comando = "criar-usuario joao";
		String retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		Tabela tabelaUsuario;
		tabelaUsuario = Persistencia.getInstancia().procuraTabela("usuario");

		assertNotNull(tabelaUsuario);
		assertEquals(1, tabelaUsuario.size());
		
		Usuario joao = (Usuario) tabelaUsuario.get(0);
		
		assertEquals("joao", joao.getNome().trim());
		assertEquals(1, joao.getId());
		
	}
	
	public void testCriarUsuarioRainnyday() {
		
		// nome muito curto
		String comando = "criar-usuario jo";
		String retorno;
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("nome-invalido", retorno);
		
		Tabela tabelaUsuario;
		tabelaUsuario = Persistencia.getInstancia().procuraTabela("usuario");
		assertNotNull(tabelaUsuario);
		assertEquals(0, tabelaUsuario.size());
		
		// Nome muito longo 
		comando = "criar-usuario mariaDoCarmoDaSilvaSantos mariaDoCarmoDaSilvaSantos mariaDoCarmoDaSilvaSantos mariaDoCarmoDaSilvaSantos";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("nome-invalido", retorno);
		
		tabelaUsuario = Persistencia.getInstancia().procuraTabela("usuario");
		assertNotNull(tabelaUsuario);
		assertEquals(0, tabelaUsuario.size());
		
		// Com número
		comando = "criar-usuario paulo23";
		retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("nome-invalido", retorno);
		
		tabelaUsuario = Persistencia.getInstancia().procuraTabela("usuario");
		assertNotNull(tabelaUsuario);
		assertEquals(0, tabelaUsuario.size());		
		
	}
	
	public void testTentaCriarUsuarioComNomeRepetido() {
		
		String comando = "criar-usuario joao";
		String retorno;
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		Tabela tabelaUsuario;
		tabelaUsuario = Persistencia.getInstancia().procuraTabela("usuario");
		assertNotNull(tabelaUsuario);
		assertEquals(1, tabelaUsuario.size());
		
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("usuario-ja-existe", retorno);
		assertEquals(1, tabelaUsuario.size());
		
	}	
	
	public void testPostarMensagemHappyDay() {
		String comando = "criar-usuario joao";
		String retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		
		
		comando = "postar-mensagem joao Eu moro em Maringá";
		retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);				
	}
	
	public void testPostarMensagemRainnyDay() {
		
		String comando = "criar-usuario joao";
		String retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		
		
		// sem msg
		comando = "postar-mensagem joao ";
		retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("mensagem-invalida", retorno);
		
		// msg mto longa
		comando = "postar-mensagem joao Vejam este nome: Pedro de Alcântara Francisco António João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim";
		retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("mensagem-invalida", retorno);		
	}
	
	public void testPostarMensagemUsuarioNaoExistente() {
		
		String comando = "postar-mensagem maria Meus amigos me chamam de Mari";
		String retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("usuario-nao-encontrado", retorno);		
	}
	
	public void testListarMensagemUsuarioHappyDay() {
		String comando = "criar-usuario joao";
		String retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		
		
		comando = "postar-mensagem joao Eu moro em Maringá";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem joao Gosto de jogar futebol com os amigos";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem joao Eu torço para o Galo";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "listar-mensagens-usuario joao";
		String retornoEsperado;
		retornoEsperado = "Eu torço para o Galo" + "\n" + 
						  "Gosto de jogar futebol com os amigos" + "\n" + 
						  "Eu moro em Maringá";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals(retornoEsperado.trim(), retorno);		
	}
	
	public void testSeguirHappyDay() {
		String comando = "criar-usuario joao";
		String retorno = "";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir joao maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		assertEquals(1, Persistencia.getInstancia().procuraTabela("relacionamento").size());
		
		comando = "listar-seguidores maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("joao", retorno);
		
		comando = "listar-seguidos joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("maria", retorno);
		
		comando = "criar-usuario pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		
		
		comando = "seguir joao pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		
		
		comando = "listar-seguidos joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("pedro\nmaria", retorno);		
		
	}
	
	public void testSeguirRainnyDay() {
		String comando = "criar-usuario joao";
		String retorno;
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);

		comando = "seguir ana maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("seguidor-nao-encontrado", retorno);
		assertEquals(0, Persistencia.getInstancia().procuraTabela("relacionamento").size());
		
		comando = "seguir maria ana";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("seguido-nao-encontrado", retorno);
		assertEquals(0, Persistencia.getInstancia().procuraTabela("relacionamento").size());
		
		comando = "seguir joao joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("seguidor-e-seguido-sao-iguais", retorno);
		assertEquals(0, Persistencia.getInstancia().procuraTabela("relacionamento").size());
		
		comando = "seguir maria joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		assertEquals(1, Persistencia.getInstancia().procuraTabela("relacionamento").size());
		
		comando = "seguir maria joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ja-seguindo", retorno);
		assertEquals(1, Persistencia.getInstancia().procuraTabela("relacionamento").size());		
		
	}	
	
	public void testDeixarDeSeguirHappyDay() {
		
		String comando = "criar-usuario joao";
		String retorno;
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir joao maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "listar-seguidores maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("joao", retorno);
		
		comando = "listar-seguidos joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("maria", retorno);
		
		comando = "deixar-de-seguir joao maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		assertEquals(0, Persistencia.getInstancia().procuraTabela("relacionamento").size());
		
		comando = "listar-seguidores maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("", retorno);
		
		comando = "listar-seguidos joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("", retorno);
		
	}
	
	public void testDeixarDeSeguirRainnyDay() {
		
		String comando;
		String retorno;
		
		comando = "deixar-de-seguir ana maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("seguidor-nao-encontrado", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		
		
		comando = "deixar-de-seguir maria ana";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("seguido-nao-encontrado", retorno);
		
		comando = "criar-usuario joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "deixar-de-seguir maria joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("nao-seguindo", retorno);		
	}
	
	
	public void testListarMensagensSeguidosHappyDay() {
		String comando, retorno;
		
		comando = "criar-usuario joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		
		// seguir
		comando = "seguir joao maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
			
		comando = "seguir joao pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "listar-seguidos joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("pedro\nmaria", retorno);
		
		// postar mensagens
		comando = "postar-mensagem maria Vou ao cinema hoje";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem pedro Comprei um computador novo";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem maria Alguém que ir comigo?";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);	
		
		comando = "listar-mensagens-seguidos joao";
		String retornoEsperado;
		retornoEsperado = "maria Alguém que ir comigo?" + "\n" + 
						  "pedro Comprei um computador novo" + "\n" + 
						  "maria Vou ao cinema hoje";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals(retornoEsperado.trim(), retorno);
		
		comando = "listar-seguidos joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("pedro\nmaria", retorno);		
		
	}
	
	public void testListarEstatisticasHappyDay() {
		String comando, retorno;

		comando = "criar-usuario joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario juliana";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir joao maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir joao pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir maria joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir pedro joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "seguir juliana joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		

		comando = "postar-mensagem joao Acordei de mau humor!";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);

		comando = "postar-mensagem joao O trânsito está uma loucura!";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);

		comando = "postar-mensagem joao Só falta chover!";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem joao Alguém vai implicar com o tanto de exclamações?!";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		

		comando = "listar-estatisticas-usuario joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("4\n2\n3", retorno);
	}
	
	public void testListarTendenciasHappyDay() {
		String comando, retorno;

		comando = "criar-usuario joao";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario maria";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "criar-usuario pedro";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem joao Estou assistindo o #brasileirao na #tv.";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);

		comando = "postar-mensagem maria Acabei de ver a #cozinha dos meus sonhos na #tv!";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);

		comando = "postar-mensagem maria Pena que custa uma #fortuna...";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem pedro Os #pol�ticos no #brasil ganham uma #fortuna.";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem joao Acho que vou na #cozinha procurar alguma coisa para comer.";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);
		
		comando = "postar-mensagem pedro S� passa desgra�a na #tv.";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("ok", retorno);		

		comando = "listar-tendencia";
		retorno = Apresentacao.getInstancia().processa(comando);
		assertEquals("#tv\n#cozinha\n#fortuna\n#brasil\n#brasileirao", retorno);
		
		comando = "listar-mensagens-com-palavra-marcada #tv";
		retorno = Apresentacao.getInstancia().processa(comando);
		String retornoEsperado = "";
		retornoEsperado += "pedro S� passa desgra�a na #tv.\n" +
				   "maria Acabei de ver a #cozinha dos meus sonhos na #tv!\n" + 
				   "joao Estou assistindo o #brasileirao na #tv.";		
		assertEquals(retornoEsperado, retorno);		
	}	

}
