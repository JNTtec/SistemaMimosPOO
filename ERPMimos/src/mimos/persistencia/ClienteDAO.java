package mimos.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mimos.constantes.Constante;
import mimos.excecao.MimosException;
import mimos.modelo.Artesao;
import mimos.modelo.Cliente;

public class ClienteDAO {
	
	 Connection con = null;
	    PreparedStatement stmt = null;
	    Cliente cliente;
	    ResultSet rs = null;
	    
	    private static final String SQL_INCLUIRCLIENTE = "insert into cliente (cod_cliente,nome,telefone,endereco,id_empresa"+
             " values (?,?,?,?,?)";

private static final String SQL_ALTERARCLIENTE = "UPDATE cliente set "
+"nome=?,telefone=?,=?,endereco=?,"
+"id_empresa=? "
+"where cod_cliente=?";
private static final String SQL_EXCLUIRCLIENTE =  
"Delete artesao where cod_cliente = ?";

public void alterarCliente(Cliente cliente) throws MimosException {
 if (cliente == null){
 String mensagem = "Não foi informado o cliente a ser alterado";
 throw new MimosException(mensagem);
 }
 con = null;
 stmt =null;
 try{
     con = GerenciadorDeConexao.getConexao();
     stmt = con.prepareStatement(SQL_ALTERARCLIENTE);        
     stmt.setString(1, cliente.getNome());
     stmt.setString(2, cliente.getTelefone());
     stmt.setString(3, cliente.getEndereco());
     stmt.setDouble(4, cliente.getId_empresa());
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
public void excluirCliente(Cliente cliente) throws MimosException {
    if (cliente == null){
    String mensagem = "Não foi informado o hospede a ser desativado!";
    throw new MimosException(mensagem);
    }
    con = null;
    stmt = null;
    try {
    con = GerenciadorDeConexao.getConexao();
    stmt = con.prepareStatement(SQL_EXCLUIRCLIENTE);
    stmt.setLong(1, cliente.getCod_cliente());
    stmt.executeUpdate();
    }catch(SQLException ex){
    StringBuffer mensagem = new StringBuffer("Não foi possível excluir o hospede");
    mensagem.append("\nMotivo: "+ex.getMessage());
    throw new MimosException(mensagem.toString());
    } finally {
    GerenciadorDeConexao.closeConexao(con, stmt);
    }
    }
public void gravarCliente(Cliente cliente)throws MimosException {
    if (cliente.getCod_cliente() == Constante.NOVO){
        incluirCliente(cliente);
    }
    
}        

public void incluirCliente (Cliente cliente) throws MimosException{
    if (cliente== null){
        String mensagem = "não foi informado o cliente a cadastrar";
        throw new MimosException(mensagem);
    }
    try{
        
        con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement (SQL_INCLUIRCLIENTE);
        GeradorDeChave geradorDeChave = new GeradorDeChave("CLIENTE");
        long codigocliente = geradorDeChave.getProximoCodigo();
        String status = "ativo";
        stmt.setLong (1 ,codigocliente);
        stmt.setString (2 ,cliente.getNome());
        stmt.setString (3 ,cliente.getTelefone());
        stmt.setString (4 ,cliente.getEndereco());
        stmt.setLong (5, cliente.getId_empresa());
        stmt.executeUpdate();
        
    }
    catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("não foi possível incluir o cliente");
        mensagem.append("\nMotivo:"+ex.getMessage());
        throw new MimosException (mensagem.toString());            
    }
    finally{
        GerenciadorDeConexao.closeConexao (con,stmt);
    }
}
public List pesquisarCliente (String clausulaWhere) throws MimosException {
    clausulaWhere = "%"+clausulaWhere+"%";
    String sql = "select * from cliente where nome like ?";
    List resultado = new ArrayList();
    try{
        con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement(sql);
        stmt.setString(1, clausulaWhere);
        rs = stmt.executeQuery();
        while (rs.next()){
             cliente = criarCliente(rs);
            resultado.add(cliente);
        }
    }catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
        mensagem.append("\nMOtivo:" +ex);
        
    }finally{
        GerenciadorDeConexao.closeConexao(con,stmt,rs);
    }
    return resultado;
      }
private Cliente criarCliente(ResultSet rs)throws MimosException {
    Cliente cliente= new Cliente();
    try{
        cliente.setCod_cliente(rs.getLong("COD_CLIENTE"));
        cliente.setNome(rs.getString("NOME"));
        cliente.setTelefone(rs.getString("TELEFONE"));
        cliente.setEndereco(rs.getString("ENDERECO"));
        cliente.setId_empresa(rs.getLong("ID_EMPRESA"));        
       }
       catch (SQLException exe){
           StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do cliente");
           mensagem.append("\nMOtivo:" +exe);
           throw new MimosException(mensagem.toString());
       }
 return cliente;   
}

 
}
