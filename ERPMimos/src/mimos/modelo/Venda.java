package mimos.modelo;
import java.util.Date;
public class Venda {
	private long venda;
	private long cod_vendedor;
	private long cod_pedido;
	private long cod_cliente;
	private double valor_total;
	private double desconto_valor;
	private double valor_troco;
	private String status;
	private	Date data_venda;
	private long cod_produto;
	private double valor_pago;
	public double getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}
	public long getVenda() {
		return venda;
	}
	public void setVenda(long venda) {
		this.venda = venda;
	}
	public long getCod_vendedor() {
		return cod_vendedor;
	}
	public void setCod_vendedor(long cod_vendedor) {
		this.cod_vendedor = cod_vendedor;
	}
	public long getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(long cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public long getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	public double getDesconto_valor() {
		return desconto_valor;
	}
	public void setDesconto_valor(double desconto_valor) {
		this.desconto_valor = desconto_valor;
	}
	public double getValor_troco() {
		return valor_troco;
	}
	public void setValor_troco(double valor_troco) {
		this.valor_troco = valor_troco;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getData_venda() {
		return data_venda;
	}
	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}
	public long getCod_produto() {
		return cod_produto;
	}
	public void setCod_produto(long cod_produto) {
		this.cod_produto = cod_produto;
	}
}
