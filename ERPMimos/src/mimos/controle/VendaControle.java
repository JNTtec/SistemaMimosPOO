package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.Produto;
import mimos.modelo.Venda;
import mimos.persistencia.ProdutoDAO;
import mimos.persistencia.VendaDAO;
import java.util.*;
public class VendaControle {
	public void adiciona (Venda venda) throws MimosException{
		
        VendaDAO dao = new VendaDAO ();
        dao.gravarVenda(venda);
    }
	public List <Venda> realizaPesquisa(int id) throws MimosException{
	      List <Venda> lista = new ArrayList<Venda>();
	      VendaDAO dao = new VendaDAO();
	      lista = dao.pesquisarVenda(id);
	      System.out.println("passou");
	      return lista;
	}
	 
	  public void alterarVenda(Venda venda) throws MimosException {
	        VendaDAO dao = new VendaDAO();
	        dao.alterarVenda(venda);
	}
}
