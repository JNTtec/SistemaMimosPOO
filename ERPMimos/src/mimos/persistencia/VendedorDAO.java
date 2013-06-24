package mimos.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mimos.constantes.Constante;
import mimos.excecao.MimosException;
import mimos.modelo.Cliente;
import mimos.modelo.Vendedor;;


public class VendedorDAO {
	 Connection con = null;
	    PreparedStatement stmt = null;
	    Vendedor vendedor;
	    ResultSet rs = null;
	 private static final String SQL_INCLUIRVENDEDOR = "insert into vendedor (cod_vendedor,senha,nome,usuario,salario,comissao_perc,valor_comissao,id_empresa"+
	             " values (?,?,?,?,?,?,?,?)";

	private static final String SQL_ALTERARVENDEDOR = "UPDATE vendedor set "
	+"senha=?,nome=?,usuario=?,salario=?,comissao_perc=?,valor_comissao=?,id_empresa"
	+"where cod_vendedor=?";
	private static final String SQL_EXCLUIRVENDEDOR =  
	"Delete cliente where cod_vendedor = ?";
	public void alterarVendedor(Vendedor vendedor) throws MimosException {
		 if (vendedor == null){
		 String mensagem = "Não foi informado o vendedor a ser alterado";
		 throw new MimosException(mensagem);
		 }
		 con = null;
		 stmt =null;
		 try{
		     con = GerenciadorDeConexao.getConexao();
		     stmt = con.prepareStatement(SQL_ALTERARVENDEDOR);        
		     stmt.setString(1, vendedor.getSenha());
		     stmt.setString(2, vendedor.getNome());
		     stmt.setString(3, vendedor.getUsuario());
		     stmt.setDouble(4, vendedor.getSalario());
		     stmt.setDouble(5, vendedor.getComissao());
		     stmt.setDouble(6, vendedor.getValorcomissao());
		     stmt.setLong(7, vendedor.getIdEmpresa());
		     stmt.executeUpdate();
		     
		     }catch(SQLException ex){
		         StringBuffer mensagem = new StringBuffer("Não foi possível atualizar "
		         + "os dados do Cliente");
		         mensagem.append("\nMotivo: "+ex.getMessage());
		         throw new MimosException(mensagem.toString());
		     } finally {
		     GerenciadorDeConexao.closeConexao(con, stmt);
		 }
		}
	public void excluirVendedor(Vendedor vendedor) throws MimosException {
	    if (vendedor == null){
	    String mensagem = "Não foi informado o vendedor a ser desativado!";
	    throw new MimosException(mensagem);
	    }
	    con = null;
	    stmt = null;
	    try {
	    con = GerenciadorDeConexao.getConexao();
	    stmt = con.prepareStatement(SQL_EXCLUIRVENDEDOR);
	    stmt.setLong(1, vendedor.getCodVendedor());
	    stmt.executeUpdate();
	    }catch(SQLException ex){
	    StringBuffer mensagem = new StringBuffer("Não foi possível excluir o hospede");
	    mensagem.append("\nMotivo: "+ex.getMessage());
	    throw new MimosException(mensagem.toString());
	    } finally {
	    GerenciadorDeConexao.closeConexao(con, stmt);
	    }
	    }
	public void gravarVendedor(Vendedor vendedor)throws MimosException {
	    if (vendedor.getCodVendedor() == Constante.NOVO){
	        incluirVendedor(vendedor);
	    }
	    
	}   
	public void incluirVendedor (Vendedor vendedor) throws MimosException{
	    if (vendedor== null){
	        String mensagem = "não foi informado o vendedor a cadastrar";
	        throw new MimosException(mensagem);
	    }
	    try{
	        
	        con = GerenciadorDeConexao.getConexao();
	        stmt = con.prepareStatement (SQL_INCLUIRVENDEDOR);
	        GeradorDeChave geradorDeChave = new GeradorDeChave("VENDEDOR");
	        long codigovendedor = geradorDeChave.getProximoCodigo();
	        String status = "ativo";
	        stmt.setLong (1 ,codigovendedor);
	        stmt.setString (2 ,vendedor.getSenha());
	        stmt.setString (3 ,vendedor.getNome());
	        stmt.setString (4 ,vendedor.getUsuario());
	        stmt.setDouble(5 ,vendedor.getSalario());
	        stmt.setDouble(6 ,vendedor.getComissao());
	        stmt.setDouble(7 ,vendedor.getValorcomissao());
	        stmt.setDouble(8 ,vendedor.getIdEmpresa());
	        
	    //    stmt.setLong (5, cliente.getId_empresa());
	        stmt.executeUpdate();
	        
	    }
	    catch (SQLException ex){
	        StringBuffer mensagem = new StringBuffer ("não foi possível incluir o vendedor");
	        mensagem.append("\nMotivo:"+ex.getMessage());
	        throw new MimosException (mensagem.toString());            
	    }
	    finally{
	        GerenciadorDeConexao.closeConexao (con,stmt);
	    }
	}
	public List pesquisarVendedor (String clausulaWhere) throws MimosException {
	    clausulaWhere = "%"+clausulaWhere+"%";
	    String sql = "select * from vendedor where nome like ?";
	    List resultado = new ArrayList();
	    try{
	        con = GerenciadorDeConexao.getConexao();
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, clausulaWhere);
	        rs = stmt.executeQuery();
	        while (rs.next()){
	             vendedor = criarVendedor(rs);
	            resultado.add(vendedor);
	        }
	    }catch (SQLException ex){
	        StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
	        mensagem.append("\nMOtivo:" +ex);
	        
	    }finally{
	        GerenciadorDeConexao.closeConexao(con,stmt,rs);
	    }
	    return resultado;
	      }
	private Vendedor criarVendedor(ResultSet rs)throws MimosException {
	    Vendedor vendedor= new Vendedor();
	    try{
	        vendedor.setCodVendedor(rs.getLong("COD_VENDEDOR"));
	        vendedor.setSenha(rs.getString("SENHA"));
	        vendedor.setNome(rs.getString("NOME"));
	        vendedor.setUsuario(rs.getString("USUARIO"));
	        vendedor.setSalario(rs.getDouble("SALARIO"));
	        vendedor.setComissao(rs.getDouble("COMISSAO_PERC"));
	        vendedor.setValorcomissao(rs.getDouble("VALOR_COMISSAO"));
	        vendedor.setIdEmpresa(rs.getLong("ID_EMPRESA"));
	        
	        
	    //    cliente.setId_empresa(rs.getLong("ID_EMPRESA"));        
	       }
	       catch (SQLException exe){
	           StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do cliente");
	           mensagem.append("\nMOtivo:" +exe);
	           throw new MimosException(mensagem.toString());
	       }
	 return vendedor;   
	}


}
