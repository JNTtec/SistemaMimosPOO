package mimos.controle;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.Empresa;
import mimos.persistencia.EmpresaDAO;
import java.util.*;
public class EmpresaControle {
	public void adiciona (Empresa empresa) throws MimosException{
        EmpresaDAO dao = new EmpresaDAO ();
        dao.gravarEmpresa(empresa);
    }
    public List <Empresa> realizaPesquisa(String nome) throws MimosException{
      List <Empresa> lista = new ArrayList<Empresa>();
      EmpresaDAO dao = new EmpresaDAO();
      lista = dao.pesquisarEmpresa(nome);
      return lista;
      
    }
    public void excluirEmpresa(Empresa empresa) throws MimosException {
        EmpresaDAO dao = new EmpresaDAO();
        dao.excluirEmpresa(empresa);
}
    
    public void alterarEmpresa(Empresa empresa) throws MimosException {
        EmpresaDAO dao = new EmpresaDAO();
        dao.alterarEmpresa(empresa);
}


}
