package mimos.gui;

import java.util.List;
import  mimos.*;
import mimos.modelo.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
public class VendaModel extends AbstractTableModel {


	private final static String[] NOMES_COLUNAS = {"C�digo da venda","Endere�o", "Filial"};
	private final static int COLUNAS = NOMES_COLUNAS.length;
	private List<Venda> venda;
	public VendaModel (List<Venda> venda){
		this.venda=venda;
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
	public Venda get(int linha) { 
		return venda.get(linha);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.venda.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		Venda venda = this.venda.get(linha);
		Object o = "";
//		if (coluna == 0) { 
//			o = venda.getCod_produto()();
//		} else if (coluna == 1) { 
//			o = venda.get();
//		} else if (coluna == 2) { 
//			o = venda.getFilial();		
//		}
		return o;
	}
	public List<Venda> getvenda() {
		return venda;
	}
	public void setvenda(List<Venda> venda) {
		this.venda=venda;
	}
	

}
