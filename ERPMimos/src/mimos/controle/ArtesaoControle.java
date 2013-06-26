package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Artesao;
import mimos.persistencia.ArtesaoDAO;
import java.util.*;

public class ArtesaoControle {
	public void adiciona (Artesao artesao, String nome) throws MimosException{
        ArtesaoDAO dao = new ArtesaoDAO ();
        dao.gravarArtesao(artesao, nome);
    }
    public List <Artesao> realizaPesquisa(String nome) throws MimosException{
      List <Artesao> lista = new ArrayList<Artesao>();
      ArtesaoDAO dao = new ArtesaoDAO();
      lista = dao.pesquisarArtesao(nome);
      return lista;
      
    }
    public ArrayList<Artesao> buscaTodos()throws MimosException{
		ArrayList<Artesao> busca = new ArrayList<Artesao>();
		ArtesaoDAO dao = new ArtesaoDAO();
		busca=dao.CarregarCombo();
		
    	return busca;
    	
    }
    public void excluirArtesao(Artesao artesao) throws MimosException {
        ArtesaoDAO dao = new ArtesaoDAO();
        dao.excluirArtesao(artesao);
}
    
    public void alterarArtesao(Artesao artesao, String nome) throws MimosException {
        ArtesaoDAO dao = new ArtesaoDAO();
        dao.alterarArtesao(artesao,nome);
}

}
