package negocio;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;
import persistencia.Tabela;


class ValueComparator implements Comparator<Object> {

	  Map<String, Integer> base;
	  public ValueComparator(Map<String, Integer> base) {
	      this.base = base;
	  }

	  public int compare(Object a, Object b) {

	    if((Integer)base.get(a) <= (Integer)base.get(b)) {
	      return 1;
	    } else {
	      return -1;
	    }
	  }
}

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
		boolean mensagemInvalida = false;
		
		mensagemInvalida |= (this.getMensagem().length() == 0);
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
	
	public static LinkedList<Mensagem> todas() {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Mensagem.nomeTabela);		
		LinkedList<Mensagem> retorno = new LinkedList<Mensagem>();		
		
		if (tabela == null)
			return null;
		
		for (Entidade msg : tabela)
			retorno.add( (Mensagem) msg);
		
		return  retorno;		
	}
	
	public static TreeMap<String, Integer> listarTendencias() {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Mensagem.nomeTabela);

		TreeMap<String, Integer> contador = new TreeMap<String, Integer>();
        ValueComparator comparator =  new ValueComparator(contador);
        TreeMap<String,Integer> sorted_map = new TreeMap<String, Integer>(comparator);
        
		if (tabela == null) {
			return sorted_map;
		}
		
		Mensagem msg;
		for (int i = 0; i < tabela.size(); i++) {
			msg = (Mensagem) tabela.get(i);
			
			Pattern MY_PATTERN = Pattern.compile("#([a-z]|[A-Z]|[0-9]|[á-í])*");
			Matcher m = MY_PATTERN.matcher(msg.getMensagem());
			while (m.find()) {
			    String s = m.group(0).trim();
				int acumulado = 0;
				
				if (contador.containsKey(s)) {
					acumulado = contador.get(s);
					contador.remove(s);
				}
				acumulado ++;
				contador.put(s, acumulado);	
			    
			}
		}
		sorted_map.putAll(contador);		
		return sorted_map;
	}

}
