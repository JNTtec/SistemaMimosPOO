package mimos.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mimos.constantes.Constante;
import mimos.excecao.MimosException;
import mimos.modelo.Empresa;
import mimos.modelo.Empresa;
public class EmpresaDAO {
	 Connection con = null;
	    PreparedStatement stmt = null;
	    Empresa empresa;
	    ResultSet rs = null;
	    private static final String SQL_INCLUIREMPRESA = "insert into empresa (id_empresa,endereco,filial"+
	             " values (?,?,?)";
	    private static final String SQL_ALTERAREMPRESA = "UPDATE empresa set "
	    		+"endereco=?,filial=?"
	    		+"where id_empresa=?";
	    private static final String SQL_EXCLUIREMPRESA =  
	    		"Delete empresa where id_empresa = ?";
	    public void alterarEmpresa(Empresa empresa) throws MimosException {
	    	 if (empresa == null){
	    	 String mensagem = "Não foi informado a empresa a ser alterado";
	    	 throw new MimosException(mensagem);
	    	 }
	    	 con = null;
	    	 stmt =null;
	    	 try{
	    	     con = GerenciadorDeConexao.getConexao();
	    	     stmt = con.prepareStatement(SQL_ALTERAREMPRESA);        
	    	     stmt.setString(1, empresa.getEndereco());
	    	     stmt.setString(2, empresa.getFilial());
	    	     stmt.setLong(3, empresa.getId_empresa());;
	    	     stmt.executeUpdate();
	    	     
	    	     }catch(SQLException ex){
	    	         StringBuffer mensagem = new StringBuffer("Não foi possível atualizar "
	    	         + "os dados da Empresa");
	    	         mensagem.append("\nMotivo: "+ex.getMessage());
	    	         throw new MimosException(mensagem.toString());
	    	     } finally {
	    	     GerenciadorDeConexao.closeConexao(con, stmt);
	    	 }
	    	}
	    public void excluirEmpresa(Empresa empresa) throws MimosException {
	        if (empresa == null){
	        String mensagem = "Não foi informado a empresa a ser excluida";
	        throw new MimosException(mensagem);
	        }
	        con = null;
	        stmt = null;
	        try {
	        con = GerenciadorDeConexao.getConexao();
	        stmt = con.prepareStatement(SQL_EXCLUIREMPRESA);
	        stmt.setLong(1, empresa.getId_empresa());
	        stmt.executeUpdate();
	        }catch(SQLException ex){
	        StringBuffer mensagem = new StringBuffer("Não foi possível excluir a empresa");
	        mensagem.append("\nMotivo: "+ex.getMessage());
	        throw new MimosException(mensagem.toString());
	        } finally {
	        GerenciadorDeConexao.closeConexao(con, stmt);
	        }
	        }
	    public void gravarEmpresa(Empresa empresa)throws MimosException {
	        if (empresa.getId_empresa() == Constante.NOVO){
	            incluirEmpresa(empresa);
	        }
	        
	    } 
	    public void incluirEmpresa (Empresa empresa) throws MimosException{
	        if (empresa== null){
	            String mensagem = "não foi informado a empresa a cadastrar";
	            throw new MimosException(mensagem);
	        }
	        try{
	            
	            con = GerenciadorDeConexao.getConexao();
	            stmt = con.prepareStatement (SQL_INCLUIREMPRESA);
	            GeradorDeChave geradorDeChave = new GeradorDeChave("EMPRESA");
	            long codigoempresa = geradorDeChave.getProximoCodigo();
	            String status = "ativo";
	            stmt.setLong (1 ,codigoempresa);
	            stmt.setString (2 ,empresa.getEndereco());
	            stmt.setString (3 ,empresa.getFilial());
	            stmt.executeUpdate();
	            
	        }
	        catch (SQLException ex){
	            StringBuffer mensagem = new StringBuffer ("não foi possível incluir a empresa");
	            mensagem.append("\nMotivo:"+ex.getMessage());
	            throw new MimosException (mensagem.toString());            
	        }
	        finally{
	            GerenciadorDeConexao.closeConexao (con,stmt);
	        }
	    }
	    public List pesquisarEmpresa (String clausulaWhere) throws MimosException {
	        clausulaWhere = "%"+clausulaWhere+"%";
	        String sql = "select * from empresa where filial like ?";
	        List resultado = new ArrayList();
	        try{
	            con = GerenciadorDeConexao.getConexao();
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, clausulaWhere);
	            rs = stmt.executeQuery();
	            while (rs.next()){
	                 empresa = criarEmpresa(rs);
	                resultado.add(empresa);
	            }
	        }catch (SQLException ex){
	            StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
	            mensagem.append("\nMOtivo:" +ex);
	            
	        }finally{
	            GerenciadorDeConexao.closeConexao(con,stmt,rs);
	        }
	        return resultado;
	          } 
	    private Empresa criarEmpresa(ResultSet rs)throws MimosException {
	        Empresa empresa= new Empresa();
	        try{
	            empresa.setId_empresa(rs.getLong("ID_EMPRESA"));
	            empresa.setEndereco(rs.getString("ENDERECO"));
	            empresa.setFilial(rs.getString("FILIAL"));
	           }
	           catch (SQLException exe){
	               StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da empresa");
	               mensagem.append("\nMOtivo:" +exe);
	               throw new MimosException(mensagem.toString());
	           }
	     return empresa;   
	    }

	    
}
