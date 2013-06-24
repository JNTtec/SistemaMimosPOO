package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.Produto;
import mimos.persistencia.ClienteDAO;
import mimos.persistencia.ProdutoDAO;
import java.util.*;
public class ProdutoControle {
	public void adiciona (Produto produto) throws MimosException{
	
        ProdutoDAO dao = new ProdutoDAO ();
        dao.gravarProduto(produto);
    }
	 public List <Produto> realizaPesquisa(String nome) throws MimosException{
	      List <Produto> lista = new ArrayList<Produto>();
	      ProdutoDAO dao = new ProdutoDAO();
	      lista = dao.pesquisarProduto(nome);
	      System.out.println("passou");
	      return lista;
	 }
	 public void excluirProduto(Produto produto) throws MimosException {
	        ProdutoDAO dao = new ProdutoDAO();
	        dao.excluirProduto(produto);
	}
	    
	    public void alterarProduto(Produto produto) throws MimosException {
	        ProdutoDAO dao = new ProdutoDAO();
	        dao.alterarProduto(produto);
	}
}
