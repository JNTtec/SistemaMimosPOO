package mimos.gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;




import mimos.modelo.Produto;
public class ProdutoModel extends AbstractTableModel{
	private final static String[] NOMES_COLUNAS = {"Código","Descrição", "Preco","Margem Lucro", "Preco Venda", "Quantidade"};
	private final static int COLUNAS = NOMES_COLUNAS.length;
	private List<Produto> produto;
	public ProdutoModel (List<Produto> produto){
		this.produto=produto;
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
	public Produto get(int linha) { 
		return produto.get(linha);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.produto.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		Produto produtos = this.produto.get(linha);
		Object o = "";
		if (coluna == 0) { 
			o = produtos.getCodigoProd();
		} else if (coluna == 1) { 
			o = produtos.getDescricao();
		} else if (coluna == 2) { 
			o = produtos.getPreco();
		} else if (coluna == 3) { 
			o = produtos.getMargemLucro();
		} else if (coluna == 4) { 
			o = produtos.getPrecoVenda();
		} else if (coluna == 5) { 
			o = produtos.getQuantidade();
		}
		return o;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto=produto;
	}
	
}
