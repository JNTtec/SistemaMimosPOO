package mimos.modelo;

import mimos.constantes.Sexo;

public class Funcionario{
	

	private Double salario; 
	private String nCarteiraTrabalho; // Numero caterira de trabalho
	private String nomeUsuario;
	private String senha;
	private Boolean status;
	private Sexo sexo;
	private String cpf;
	private java.util.Date dataNascimento;
	private String email;
	
	
	

	//Getter and Setters
	
	
	
	
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getnCarteiraTrabalho() {
		return nCarteiraTrabalho;
	}
	public void setnCarteiraTrabalho(String nCarteiraTrabalho) {
		this.nCarteiraTrabalho = nCarteiraTrabalho;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public java.util.Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(java.util.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
