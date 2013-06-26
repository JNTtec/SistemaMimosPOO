package mimos.gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import mimos.modelo.Artesao;
import mimos.modelo.Produto;
public class ArtesaoModel extends AbstractTableModel{
	
		private final static String[] NOMES_COLUNAS = {"Código","Senha", "Nome","Usuário", "Salário", "Habilidade", "ID_empresa"};
		private final static int COLUNAS = NOMES_COLUNAS.length;
		private List<Artesao> artesao;
		public ArtesaoModel (List<Artesao> artesao){
			this.artesao=artesao;
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
		public Artesao get(int linha) { 
			return artesao.get(linha);
		}
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.artesao.size();
		}
		@Override
		public Object getValueAt(int linha, int coluna) {
			// TODO Auto-generated method stub
			Artesao artesao = this.artesao.get(linha);
			Object o = "";
			if (coluna == 0) { 
				o = artesao.getcodArtesao();
			} else if (coluna == 1) { 
				o = artesao.getSenha();
			} else if (coluna == 2) { 
				o = artesao.getNome();
			} else if (coluna == 3) { 
				o = artesao.getUsuario();
			} else if (coluna == 4) { 
				o = artesao.getSalario();
			} else if (coluna == 5) { 
				o = artesao.getHabilidade();
			} else if (coluna ==6){
				o = artesao.getId_empresa();
			return o;
		}
			return null;
		}
		public List<Artesao> getArtesao() {
			return artesao;
		}
		public void setArtesao(List<Artesao> artesao) {
			this.artesao=artesao;
		}
}
