package persistencia;

public abstract class Entidade {
	
	protected Tabela tabela;
	protected int id;
	protected abstract void testaConsistencia() throws CheckParamException;
	
	public abstract String getNomeTabela();
	
	public int getId() {
		return this.id;
	}
		
	public Entidade() {
		this.id = 0; 
	}

	public void salvar() throws CheckParamException {

		Tabela tabela;
		tabela = Persistencia.getInstancia().criaNovaTabelaSeNaoExiste(this.getNomeTabela());
		
		if (this.id == 0) {
			this.id = tabela.proximaId();
		}		
		
		this.testaConsistencia();
			
		Entidade elemento;
		elemento = Persistencia.getInstancia().procuraElemenetoNaTabela(this.getNomeTabela(), this.id);
		if (this.equals(elemento)) {
			elemento.remover();
		}
		
		tabela.add(this);
	}
	
	public void remover() {
		
		if (Persistencia.getInstancia().procuraElemenetoNaTabela(this.getNomeTabela(), this.id) != null) {
			Tabela tabela = Persistencia.getInstancia().procuraTabela(this.getNomeTabela());
			
			int i = 0;
			while (i < tabela.size()) {
				if (tabela.get(i).getId() == this.id) {
					tabela.remove(i);
				}
				i++;
			}
			
		}
		
	}
	
}
