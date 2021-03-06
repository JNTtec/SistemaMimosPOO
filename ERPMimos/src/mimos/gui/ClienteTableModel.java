package mimos.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import mimos.modelo.Cliente;
import mimos.modelo.Produto;


public class ClienteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -118848275315309927L;
	private List<Cliente> clientes;
	private final static String[] NomeColunas = {"COD", "NOME", "ENDERE�O", "TELEFONE", "CPF","SEXO" , "DATA NASCIMENTO","EMAIL"};
	private final static int COLUNAS = NomeColunas.length;
	public ClienteTableModel(List<Cliente> clientes) { 
		this.clientes = clientes;
	}
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUNAS;
	}
	public Cliente get(int linha) { 
		return clientes.get(linha);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return clientes.size();
	}
	
	

	@Override
	public String getColumnName(int coluna) {
		if (coluna < COLUNAS) { 
			return NomeColunas[ coluna ];
		} else { 
			return "Outras";
		}
	}
	@Override
	public Object getValueAt(int linha, int coluna) {
		Cliente c = clientes.get( linha );
		Object o = "";
		if (coluna == 0) { 
			o = c.getCod_cliente();
		} else if (coluna == 1) {
			o = c.getNome();
		} else if (coluna == 2) {
			o = c.getEndereco();
		} else if (coluna == 3) {
			o = c.getTelefone();
		} else if (coluna == 4) {
			o = c.getCpf();
		} else if (coluna == 5) {
			o = c.getSexo();
		} else if (coluna == 6) {
			o = c.getDataNascimento();
		}	else if (coluna == 7) {
			o = c.getEmail();
		}
		
		return o;
	}
	
	
	public List<Cliente> getCliente() {
		return clientes;
	}
	public void setCliente(List<Cliente> cliente) {
		this.clientes=cliente;
	}
	
	

}
