package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.Fornecedor;
import mimos.persistencia.ClienteDAO;
import mimos.persistencia.FornecedorDAO;
import java.util.*;
public class FornecedorControle {
	public void adiciona (Fornecedor fornecedor) throws MimosException{
        FornecedorDAO dao = new FornecedorDAO ();
        dao.gravarFornecedor(fornecedor);
    }
	 public List <Fornecedor> realizaPesquisa(String nome) throws MimosException{
	      List <Fornecedor> lista = new ArrayList<Fornecedor>();
	      FornecedorDAO dao = new FornecedorDAO();
	      lista = dao.pesquisarFornecedor(nome);
	      return lista;
	 }
	 public void excluirFornecedor(Fornecedor fornecedor) throws MimosException {
	        FornecedorDAO dao = new FornecedorDAO();
	        dao.excluirFornecedor(fornecedor);
	 }
	 public void alterarFornecedor(Fornecedor fornecedor) throws MimosException {
	        FornecedorDAO dao = new FornecedorDAO();
	        dao.alterarFonecedor(fornecedor);
	}
	 
}
