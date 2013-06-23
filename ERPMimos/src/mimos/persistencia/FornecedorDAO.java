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
import mimos.modelo.Fornecedor;
import mimos.modelo.Fornecedor;
public class FornecedorDAO {
	 Connection con = null;
	    PreparedStatement stmt = null;
	    Fornecedor fornecedor;
	    ResultSet rs = null;
	    private static final String SQL_INCLUIRFORNECEDOR = "insert into fornecedor (cod_fornecedor,razao_social,cnpj,nome_fantasia,telefone,"+
	            "endereco, ramo, inscricao_municipal,inscricao_estadual, tel_secundario, id_empresa)"+
	    		" values (?,?,?,?,?,?,?,?,?,?,?)";
	    private static final String SQL_ALTERARFORNECEDOR = "UPDATE fornecedor set "
	    		+"razao_social=?,cnpj=?, nome_fantasia=?, telefone=?,endereco=?, ramo=?, incricao_municipal=?, inscricao_estadual=?, tel_secundario=?, "+
	    		"id_empresa=? where cod_fornecedor=?";
	    		
	    private static final String SQL_EXCLUIRFORNECEDOR =  
	    		"Delete fornecedor where cod_fornecedor = ?";
	    public void alterarFonecedor(Fornecedor fornecedor) throws MimosException {
	    	 if (fornecedor == null){
	    	 String mensagem = "Não foi informado o fornecedor a ser alterado";
	    	 throw new MimosException(mensagem);
	    	 }
	    	 con = null;
	    	 stmt =null;
	    	 try{
	    	     con = GerenciadorDeConexao.getConexao();
	    	     stmt = con.prepareStatement(SQL_ALTERARFORNECEDOR);        
	    	     stmt.setString(1, fornecedor.getRazao_social());
	    	     stmt.setString(2, fornecedor.getCnpj());
	    	     stmt.setString(3, fornecedor.getNome_fantasia());
	    	     stmt.setString(4, fornecedor.getTelefone());
	    	     stmt.setString(5, fornecedor.getEndereco());
	    	     stmt.setString(6, fornecedor.getRamo());
	    	     stmt.setString(7, fornecedor.getInsc_municipal());
	    	     stmt.setString(8, fornecedor.getInsc_estadual());
	    	     stmt.setString(9, fornecedor.getTel_secundario());
	    	     stmt.setString(10, fornecedor.getCnpj());
	    	     stmt.setLong(11, fornecedor.getCod_fornecedor());
	    	     stmt.executeUpdate();
	    	     
	    	     }catch(SQLException ex){
	    	         StringBuffer mensagem = new StringBuffer("Não foi possível atualizar "
	    	         + "os dados do Fornecedor");
	    	         mensagem.append("\nMotivo: "+ex.getMessage());
	    	         throw new MimosException(mensagem.toString());
	    	     } finally {
	    	     GerenciadorDeConexao.closeConexao(con, stmt);
	    	 }
	    	}
	    public void excluirFornecedor(Fornecedor fornecedor) throws MimosException {
	        if (fornecedor == null){
	        String mensagem = "Não foi informado o fornecedor a ser excluido";
	        throw new MimosException(mensagem);
	        }
	        con = null;
	        stmt = null;
	        try {
	        con = GerenciadorDeConexao.getConexao();
	        stmt = con.prepareStatement(SQL_EXCLUIRFORNECEDOR);
	        stmt.setLong(1, fornecedor.getCod_fornecedor());
	        stmt.executeUpdate();
	        }catch(SQLException ex){
	        StringBuffer mensagem = new StringBuffer("Não foi possível excluir a empresa");
	        mensagem.append("\nMotivo: "+ex.getMessage());
	        throw new MimosException(mensagem.toString());
	        } finally {
	        GerenciadorDeConexao.closeConexao(con, stmt);
	        }
	        }
	    public void gravarFornecedor(Fornecedor fornecedor)throws MimosException {
	        if (fornecedor.getCod_fornecedor() == Constante.NOVO){
	            incluirFornecedor(fornecedor);
	        }
	        
	    } 
	    public void incluirFornecedor (Fornecedor fornecedor) throws MimosException{
	        if (fornecedor== null){
	            String mensagem = "não foi informado o fornecedor a ser cadastrado";
	            throw new MimosException(mensagem);
	        }
	        try{
	            
	            con = GerenciadorDeConexao.getConexao();
	            stmt = con.prepareStatement (SQL_INCLUIRFORNECEDOR);
	            GeradorDeChave geradorDeChave = new GeradorDeChave("FORNECEDOR");
	            long codigofornecedor = geradorDeChave.getProximoCodigo();
	            stmt.setLong (1 ,codigofornecedor);
	            stmt.setString(2, fornecedor.getRazao_social());
	    	     stmt.setString(3, fornecedor.getCnpj());
	    	     stmt.setString(4, fornecedor.getNome_fantasia());
	    	     stmt.setString(5, fornecedor.getTelefone());
	    	     stmt.setString(6, fornecedor.getEndereco());
	    	     stmt.setString(7, fornecedor.getRamo());
	    	     stmt.setString(8, fornecedor.getInsc_municipal());
	    	     stmt.setString(9, fornecedor.getInsc_estadual());
	    	     stmt.setString(10, fornecedor.getTel_secundario());
	    	     stmt.setString(11, fornecedor.getCnpj());
	            stmt.executeUpdate();
	            
	        }
	        catch (SQLException ex){
	            StringBuffer mensagem = new StringBuffer ("não foi possível incluir o Fornecedor");
	            mensagem.append("\nMotivo:"+ex.getMessage());
	            throw new MimosException (mensagem.toString());            
	        }
	        finally{
	            GerenciadorDeConexao.closeConexao (con,stmt);
	        }
	    }
	    public List pesquisarFornecedor (String clausulaWhere) throws MimosException {
	        clausulaWhere = "%"+clausulaWhere+"%";
	        String sql = "select * from fornecedor where razao_social like ?";
	        List resultado = new ArrayList();
	        try{
	            con = GerenciadorDeConexao.getConexao();
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, clausulaWhere);
	            rs = stmt.executeQuery();
	            while (rs.next()){
	                 fornecedor = criarFornecedor(rs);
	                resultado.add(fornecedor);
	            }
	        }catch (SQLException ex){
	            StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
	            mensagem.append("\nMOtivo:" +ex);
	            
	        }finally{
	            GerenciadorDeConexao.closeConexao(con,stmt,rs);
	        }
	        return resultado;
	          } 
	    private Fornecedor criarFornecedor(ResultSet rs)throws MimosException {
	        Fornecedor fornecedor= new Fornecedor();
	        try{
	            fornecedor.setCod_fornecedor(rs.getLong("COD_FORNECEDOR"));
	            fornecedor.setRazao_social(rs.getString("RAZAO_SOCIAL"));
	            fornecedor.setCnpj(rs.getString("CNPJ"));
	            fornecedor.setNome_fantasia(rs.getString("NOME_FANTASIA"));
	            fornecedor.setTelefone(rs.getString("TELEFONE"));
	            fornecedor.setEndereco(rs.getString("ENDERECO"));
	            fornecedor.setRamo(rs.getString("RAMO"));
	            fornecedor.setInsc_municipal(rs.getString("INSCRICAO_MUNICIPAL"));
	            fornecedor.setInsc_estadual(rs.getString("INSCRICAO_ESTADUAL"));
	            fornecedor.setTel_secundario(rs.getString("TELEFONE SEGUNDARIO"));
	            fornecedor.setId_empresa(rs.getLong("ID_EMPRESA"));
	           }
	           catch (SQLException exe){
	               StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do fornecedor");
	               mensagem.append("\nMOtivo:" +exe);
	               throw new MimosException(mensagem.toString());
	           }
	     return fornecedor;   
	    }
	    
}
