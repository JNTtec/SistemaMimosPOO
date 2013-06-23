package mimos.modelo;

public class Fornecedor {
	private long cod_fornecedor;
	private String razao_social;
	private String cnpj;
	private String nome_fantasia;
	private String telefone;
	private String endereco;
	private String ramo;
	private String insc_municipal;
	private String insc_estadual;
	private String tel_secundario;
	private long id_empresa;
	public long getCod_fornecedor() {
		return cod_fornecedor;
	}
	public void setCod_fornecedor(long cod_fornecedor) {
		this.cod_fornecedor = cod_fornecedor;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	public String getInsc_municipal() {
		return insc_municipal;
	}
	public void setInsc_municipal(String insc_municipal) {
		this.insc_municipal = insc_municipal;
	}
	public String getInsc_estadual() {
		return insc_estadual;
	}
	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}
	public String getTel_secundario() {
		return tel_secundario;
	}
	public void setTel_secundario(String tel_secundario) {
		this.tel_secundario = tel_secundario;
	}
	public long getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}
	
}
