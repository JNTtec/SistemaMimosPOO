package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Vendedor;
import mimos.persistencia.VendedorDAO;
import java.util.*;
public class VendedorControle {
	public void adiciona (Vendedor vendedor) throws MimosException{
        VendedorDAO dao = new VendedorDAO ();
        dao.gravarVendedor(vendedor);
    }
	public List <Vendedor> realizaPesquisa(String nome) throws MimosException{
	      List <Vendedor> lista = new ArrayList<Vendedor>();
	      VendedorDAO dao = new VendedorDAO();
	      lista = dao.pesquisarVendedor(nome);
	      return lista;
	}
	public void excluirVendedor(Vendedor vendedor) throws MimosException {
        VendedorDAO dao = new VendedorDAO();
        dao.excluirVendedor(vendedor);
}
	 public void alterarVendedor(Vendedor vendedor) throws MimosException {
	        VendedorDAO dao = new VendedorDAO();
	        dao.alterarVendedor(vendedor);
	
}
}