package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.MateriaPrima;
import mimos.modelo.Produto;
import mimos.persistencia.MateriaPrimaDAO;
import mimos.persistencia.ProdutoDAO;

import java.util.*;
public class MateriaPrimaControle {
	public void adiciona (MateriaPrima materia) throws MimosException{
		
        MateriaPrimaDAO dao = new MateriaPrimaDAO ();
        dao.gravarMateria(materia);
    }
	 public List <MateriaPrima> realizaPesquisa(String nome) throws MimosException{
	      List <MateriaPrima> lista = new ArrayList<MateriaPrima>();
	      MateriaPrimaDAO dao = new MateriaPrimaDAO();
	      lista = dao.pesquisarMateria(nome);
	  
	      return lista;
	 }
	 public void excluirMateria(MateriaPrima materia) throws MimosException {
	        MateriaPrimaDAO dao = new MateriaPrimaDAO();
	        dao.excluirMateria(materia);
	}
	 public void alterarMateria(MateriaPrima materia) throws MimosException {
	        MateriaPrimaDAO dao = new MateriaPrimaDAO();
	        dao.alterarMateria(materia);
	}
}
