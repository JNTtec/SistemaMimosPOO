package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Artesao;
import mimos.modelo.Cliente;
import mimos.persistencia.ArtesaoDAO;
import mimos.persistencia.ClienteDAO;
import java.util.*;

public class ClienteControle {
	public void adiciona (Cliente cliente, String nome) throws MimosException{
        ClienteDAO dao = new ClienteDAO ();
        dao.gravarCliente(cliente, nome);
    }
    public List <Cliente> realizaPesquisa(String nome) throws MimosException{
      List <Cliente> lista = new ArrayList<Cliente>();
      ClienteDAO dao = new ClienteDAO();
      lista = dao.pesquisarCliente(nome);
      return lista;
      
    }
    public void excluirCliente(Cliente cliente) throws MimosException {
        ClienteDAO dao = new ClienteDAO();
        dao.excluirCliente(cliente);
}
    
    public void alterarCliente(Cliente cliente, String nome) throws MimosException {
        ClienteDAO dao = new ClienteDAO();
        dao.alterarCliente(cliente, nome);
}
    public ArrayList<Cliente> buscaTodos()throws MimosException{
		ArrayList<Cliente> busca = new ArrayList<Cliente>();
		ArtesaoDAO dao = new ArtesaoDAO();
		busca=dao.CarregarCombo();
		
    	return busca;
    	
    }

}
