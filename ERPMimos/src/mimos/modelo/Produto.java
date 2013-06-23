package mimos.modelo;

public class Produto {
	private String descricao;
	private double preco;
	private double margemLucro;
	private double precoVenda;
	private double quantidade;
	
	private long codigoProd;
	public long getCodigoProd() {
		return codigoProd;
	}
	public void setCodigoProd(long codigoProd) {
		this.codigoProd = codigoProd;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getMargemLucro() {
		return margemLucro;
	}
	public void setMargemLucro(double margemLucro) {
		this.margemLucro = margemLucro;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
}
