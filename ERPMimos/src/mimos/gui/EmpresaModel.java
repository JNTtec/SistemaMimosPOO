package mimos.gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import mimos.modelo.Empresa;
import mimos.modelo.Produto;
public class EmpresaModel extends AbstractTableModel {
	private final static String[] NOMES_COLUNAS = {"C�digo da Empresa","Endere�o", "Filial"};
	private final static int COLUNAS = NOMES_COLUNAS.length;
	private List<Empresa> empresa;
	public EmpresaModel (List<Empresa> empresa){
		this.empresa=empresa;
	}
	public String getColumnName(int coluna){
		if (coluna < COLUNAS) { 
			return NOMES_COLUNAS[ coluna ];
		} else { 
			return "Outras";
		}
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUNAS;
	}
	public Empresa get(int linha) { 
		return empresa.get(linha);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.empresa.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		Empresa empresa = this.empresa.get(linha);
		Object o = "";
		if (coluna == 0) { 
			o = empresa.getId_empresa();
		} else if (coluna == 1) { 
			o = empresa.getEndereco();
		} else if (coluna == 2) { 
			o = empresa.getFilial();		
		}
		return o;
	}
	public List<Empresa> getEmpresa() {
		return empresa;
	}
	public void setEmpresa(List<Empresa> empresa) {
		this.empresa=empresa;
	}
	
}
