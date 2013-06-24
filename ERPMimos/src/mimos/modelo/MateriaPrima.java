package mimos.modelo;

public class MateriaPrima {
	private long cod_materia_prima;
	private String nome;
	private long cod_fornecedor;
	private String tipo;
	private String descricao;
	public long getCod_materia_prima() {
		return cod_materia_prima;
	}
	public void setCod_materia_prima(long cod_materia_prima) {
		this.cod_materia_prima = cod_materia_prima;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getCod_fornecedor() {
		return cod_fornecedor;
	}
	public void setCod_fornecedor(long cod_fornecedor) {
		this.cod_fornecedor = cod_fornecedor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
}
