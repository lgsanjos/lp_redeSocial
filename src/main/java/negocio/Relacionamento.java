package negocio;

import java.util.LinkedList;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;
import persistencia.Tabela;

public class Relacionamento extends Entidade {

	public static final String nomeTabela = "relacionamento";
	// seguidor -> seguido
	private Usuario usuarioSeguidor;
	private Usuario usuarioSeguido;
	
	public Relacionamento() {
		super();
	}
	
	@Override
	public String getNomeTabela() {
		return nomeTabela;
	}		
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		
		if (this.usuarioSeguidor == null) {
		  throw new CheckParamException("seguidor-nao-encontrado");
		}

		if (this.usuarioSeguido == null) {
		  throw new CheckParamException("seguido-nao-encontrado");
		}
		
		if (this.usuarioSeguido.getId() == this.usuarioSeguidor.getId()) {
		  throw new CheckParamException("seguidor-e-seguido-sao-iguais");
		}
		
		Relacionamento rel = Relacionamento.procuraDado(this.usuarioSeguidor.getNome(), this.usuarioSeguido.getNome());
		if (rel != null) {
			throw new CheckParamException("ja-seguindo");
		}
		
	}
	
	public Usuario getUsuarioSeguidor() {
		return this.usuarioSeguidor;
	}

	public Usuario getUsuarioSeguido() {
		return usuarioSeguido;
	}
	
	
	public static LinkedList<Relacionamento> todosDadoSeguidor(String nomeSeguidor) {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela(nomeTabela);
		LinkedList<Relacionamento> retorno = new LinkedList<Relacionamento>();
		Relacionamento rel;
		
		if (tabela == null) {
			return retorno;
		}		
		
		for (int i = 0; i < tabela.size(); i++) {
			rel = (Relacionamento) tabela.get(i);
			if (rel.getUsuarioSeguidor().getNome().equalsIgnoreCase(nomeSeguidor)) {
				retorno.add(rel);				
			}
		}
		return retorno;
	}
	
	public static LinkedList<Relacionamento> todosDadoSeguido(String nomeSeguido) {
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Relacionamento.nomeTabela);
		LinkedList<Relacionamento> retorno = new LinkedList<Relacionamento>();
		Relacionamento rel;
		
		if (tabela == null) {
			return retorno;
		}
		
		for (int i = 0; i < tabela.size(); i++) {
			rel = (Relacionamento) tabela.get(i);
			if (rel.getUsuarioSeguido().getNome().equalsIgnoreCase(nomeSeguido)) {
				retorno.add(rel);				
			}
		}
		return retorno;
	}
	
	public static Relacionamento procuraDado(String nomeSeguidor, String nomeSeguido) {
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Relacionamento.nomeTabela);
		
		if (tabela == null) { 
			return null;
		}
		
		Relacionamento rel;
		
		for (int i = 0; i < tabela.size(); i++) {
			rel = (Relacionamento) tabela.get(i);
			if (rel.getUsuarioSeguidor().getNome().equalsIgnoreCase(nomeSeguidor) &&
					(rel.getUsuarioSeguido().getNome().equalsIgnoreCase(nomeSeguido))) {
				return rel;
			}
		}		
		
		return null;
	}
	
	public void seguir(String usuarioSeguidor, String usuarioASeguir) throws CheckParamException {
		
		this.usuarioSeguidor = Usuario.procuraUsuario(usuarioSeguidor);
		this.usuarioSeguido = Usuario.procuraUsuario(usuarioASeguir);
		
		this.salvar();
	}
	
	public void deixarDeSeguir(String usuarioSeguidor, String usuarioASeguir) throws CheckParamException {
		
		Usuario seguidor = Usuario.procuraUsuario(usuarioSeguidor);
		Usuario seguido = Usuario.procuraUsuario(usuarioASeguir);
		
		if (seguidor == null) {
			throw new CheckParamException("seguidor-nao-encontrado");
		}
		
		if (seguido == null) {
			throw new CheckParamException("seguido-nao-encontrado");
		}		
		
		Relacionamento rel;
		rel = Relacionamento.procuraDado(usuarioSeguidor, usuarioASeguir);
		if (rel == null) {
			throw new CheckParamException("nao-seguindo");
		}

		rel.remover();
	}
	

}
