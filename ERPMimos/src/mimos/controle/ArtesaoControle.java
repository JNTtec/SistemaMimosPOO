package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Artesao;
import mimos.persistencia.ArtesaoDAO;
import java.util.*;

public class ArtesaoControle {
	public void adiciona (Artesao artesao) throws MimosException{
        ArtesaoDAO dao = new ArtesaoDAO ();
        dao.gravarArtesao(artesao);
    }
    public List <Artesao> realizaPesquisa(String nome) throws MimosException{
      List <Artesao> lista = new ArrayList<Artesao>();
      ArtesaoDAO dao = new ArtesaoDAO();
      lista = dao.pesquisarArtesao(nome);
      return lista;
      
    }
    public void excluirArtesao(Artesao artesao) throws MimosException {
        ArtesaoDAO dao = new ArtesaoDAO();
        dao.excluirArtesao(artesao);
}
    
    public void alterarArtesao(Artesao artesao) throws MimosException {
        ArtesaoDAO dao = new ArtesaoDAO();
        dao.alterarArtesao(artesao);
}

}
