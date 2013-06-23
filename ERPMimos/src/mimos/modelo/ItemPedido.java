package mimos.modelo;
import java.util.Date;
public class ItemPedido {
	private long cod_pedido;
	private long cod_produto;
	private Date datapedido;
	private double valor;
	public long getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(long cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public long getCod_produto() {
		return cod_produto;
	}
	public void setCod_produto(long cod_produto) {
		this.cod_produto = cod_produto;
	}
	public Date getDatapedido() {
		return datapedido;
	}
	public void setDatapedido(Date datapedido) {
		this.datapedido = datapedido;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
