package mimos.modelo;

public class Artesao {
	private long codartesao=-1;
	private String senha;
	private String nome;
	private String usuario;
	private double salario;
	private String habilidade;
	private long id_empresa;
	
	public long getcodArtesao() {
		return codartesao;
	}
	public void setcodArtesao(long artesao) {
		this.codartesao = artesao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}
	public long getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}
}
