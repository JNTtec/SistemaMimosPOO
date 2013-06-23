package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.ItemPedido;
import mimos.persistencia.ClienteDAO;
import mimos.persistencia.ItemPedidoDAO;
import java.util.*;
public class ItemPedidoControle {
	public void adiciona (ItemPedido item) throws MimosException{
        ItemPedidoDAO dao = new ItemPedidoDAO ();
        dao.gravarItem(item);
    }
	 public List <ItemPedido> realizaPesquisa(Long id) throws MimosException{
	      List <ItemPedido> lista = new ArrayList<ItemPedido>();
	      ItemPedidoDAO dao = new ItemPedidoDAO();
	      lista = dao.pesquisarItem(id);
	      return lista;
	      
	    }
	 public void excluirItem(ItemPedido item) throws MimosException {
	        ItemPedidoDAO dao = new ItemPedidoDAO();
	        dao.excluirItem(item);
	}
}
