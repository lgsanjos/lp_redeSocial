package negocio;

import java.text.Bidi;
import java.util.HashMap;
import java.util.LinkedList;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;
import persistencia.Tabela;

public class Mensagem extends Entidade {

	public static final String nomeTabela = "mensagem";
	private String mensagem;
	private Usuario usuarioCriador;
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
	
	public Mensagem() {
		super();
	}

	@Override
	public String getNomeTabela() {
		return nomeTabela;
	}
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		// não vazias com até 140
		boolean mensagemInvalida = false;
		
		mensagemInvalida |= (this.getMensagem().isEmpty());
		mensagemInvalida |= (this.getMensagem().length() > 140);
		
		if (mensagemInvalida) {
			throw new CheckParamException("mensagem-invalida");
		}
		
	}
	
	public static LinkedList<Mensagem> todasDadoUsuario(String nome) {
		LinkedList<Mensagem> todas = new LinkedList<Mensagem>();
		Tabela tabela = Persistencia.getInstancia().procuraTabela("mensagem");
		
		Usuario dono;
		Mensagem msg;
		
		for (int i = tabela.size() -1; i >= 0; i--) {
			msg = (Mensagem) tabela.get(i);
			dono =  msg.getUsuarioCriador();
			
			if ( dono.getNome().equalsIgnoreCase(nome) ) {
				todas.add(msg);
			}
		}
		return todas;
	}
	
	public static LinkedList<Mensagem> todasDadoUsuarioSeguido(String nome) {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Mensagem.nomeTabela);		
		LinkedList<Mensagem> retorno = new LinkedList<Mensagem>();		
		LinkedList<Relacionamento> relacionamentos = Relacionamento.todosDadoSeguidor(nome);
		
		Usuario dono, usuarioSeguido;
		Mensagem msg;
		
		for (int i = tabela.size() -1; i >= 0; i--) {
			msg = (Mensagem) tabela.get(i);
			dono =  msg.getUsuarioCriador();
			
			for (int j = 0; j < relacionamentos.size(); j++) {
				usuarioSeguido = (Usuario) relacionamentos.get(j).getUsuarioSeguido();
				if ( dono.getNome().equalsIgnoreCase(usuarioSeguido.getNome()) ) {
					retorno.add(msg);
				}	
			}
		}
		return retorno;
	}
	
	public static Bidi algo() {
		
		return null;
	}
	public static HashMap<String, Integer> listarTendencias() {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Mensagem.nomeTabela);
		
		if (tabela == null) {
			return null;
		}
		
		HashMap<String, Integer> contador = new HashMap<String, Integer>();
		Mensagem msg;
		
		for (int i = 0; i < tabela.size(); i++) {
			msg = (Mensagem) tabela.get(i);
			String[] topicos = msg.getMensagem().split("#([a-z][A-Z][0-9])* ");
			if (topicos.length > 0) {
				for ( int j = 0; j < topicos.length; j++) {
					
					int acumulado = 0;
					if (contador.containsValue(topicos[j])) {
						acumulado = contador.get(topicos[j]);
					}
					
					contador.put(topicos[j], acumulado + 1);
				}
			}
		}
		
		return contador;
	}

}
