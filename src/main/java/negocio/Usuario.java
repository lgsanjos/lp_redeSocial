package negocio;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;
import persistencia.Tabela;

public class Usuario extends Entidade {
	
	private String nome;
	public static final String nomeTabela = "usuario";
	
	public Usuario() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}
	
	@Override
	public String getNomeTabela() {
		return nomeTabela;
	}	
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		
		boolean nomeInvalido = false;
		boolean nomeDuplicado = false;
		
		nomeInvalido |= (this.nome.length() < 3);
		nomeInvalido |= (this.nome.length() > 20);
		nomeInvalido |= this.nome.matches(".*[0-9]+.*");
		
		if (nomeInvalido) {
			throw new CheckParamException("nome-invalido");
		}
		
		nomeDuplicado = (Usuario.procuraUsuario(this.nome) != null);
		
		if (nomeDuplicado) {
			throw new CheckParamException("usuario-ja-existe");
		}
	}
	
	public static Usuario procuraUsuario(String nome) {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela("usuario");
		if (tabela == null) {
			return null;
		}	
		
		for (int i = 0; i < tabela.size(); i++) {
   		   Usuario user = (Usuario) tabela.get(i); 
   		   if (user.getNome().equalsIgnoreCase(nome)) {
   			   return user;
   		   }
		}
		
		return null;
	}
	
	public void postarMensagem(String mensagem) throws CheckParamException {
		Mensagem msg = new Mensagem();
		msg.setMensagem(mensagem);
		msg.setUsuarioCriador(this);
		msg.salvar();
	}
	
}
