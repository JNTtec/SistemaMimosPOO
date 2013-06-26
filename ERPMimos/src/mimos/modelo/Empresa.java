package mimos.modelo;

public class Empresa {
	private long id_empresa=-1;
	private String endereco;
	private String filial;
	public long getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFilial() {
		return filial;
	}
	public void setFilial(String filial) {
		this.filial = filial;
	}
}
