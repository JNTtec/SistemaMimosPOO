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
import mimos.gui.ArtesaoView;

public class ArtesaoDAO {
	static Long resultid;
	 Connection con = null;
	    PreparedStatement stmt = null;
	    Artesao artesao;
	    ResultSet rs = null;
	    
	    private static final String SQL_INCLUIRARTESAO = "insert into artesao (cod_artesao,senha,nome,usuario,salario,habilidade,id_empresa)"+
                " values (?,?,?,?,?,?,?)";

private static final String SQL_ALTERARARTESAO = "UPDATE artesao set "
+"senha=?,nome=?,usuario=?,salario=?,"
+"habilidade=?,id_empresa=? "
+"where cod_artesao=?";
private static final String SQL_EXCLUIRARTESAO =  
"Delete from artesao where cod_artesao = ?";


public void alterarArtesao(Artesao artesao, String nome) throws MimosException {
    if (artesao == null){
    String mensagem = "Não foi informado o hospede a ser alterado";
    throw new MimosException(mensagem);
    }
    
    con = null;
    stmt =null;
 try{
        
        con = GerenciadorDeConexao.getConexao();
        String sql = "Select * from empresa where filial = '"+nome+"'";
        stmt = con.prepareStatement (sql);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()==true){
        	artesao.setId_empresa(rs.getLong("ID_EMPRESA"));
        	resultid = artesao.getId_empresa();
        	System.out.println(artesao.getId_empresa());
        }else{
        	System.out.println("Empresa não encontrado");
        }
        rs.close();
        ps.close();
    }catch(SQLException e){  
        System.out.println("Erro de SQL");  
        e.printStackTrace();  
    } 

    try{
        con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement(SQL_ALTERARARTESAO);        
        stmt.setString(1, artesao.getSenha());
        stmt.setString(2, artesao.getNome());
        stmt.setString(3, artesao.getUsuario());
        stmt.setDouble(4, artesao.getSalario());
        stmt.setString(5, artesao.getHabilidade());
        stmt.setLong(6, artesao.getId_empresa());
        stmt.setLong(7, artesao.getcodArtesao());
        stmt.executeUpdate();
        
        }catch(SQLException ex){
            StringBuffer mensagem = new StringBuffer("Não foi possível atualizar "
            + "os dados do Artesão");
            mensagem.append("\nMotivo: "+ex.getMessage());
            throw new MimosException(mensagem.toString());
        } finally {
        GerenciadorDeConexao.closeConexao(con, stmt);
    }
}
public void excluirArtesao(Artesao artesao) throws MimosException {
    if (artesao == null){
    String mensagem = "Não foi informado o hospede a ser desativado!";
    throw new MimosException(mensagem);
    }
    con = null;
    stmt = null;
    
    try {
    con = GerenciadorDeConexao.getConexao();
    stmt = con.prepareStatement(SQL_EXCLUIRARTESAO);
    stmt.setLong(1, artesao.getcodArtesao());
    stmt.executeUpdate();
    }catch(SQLException ex){
    StringBuffer mensagem = new StringBuffer("Não foi possível excluir o hospede");
    mensagem.append("\nMotivo: "+ex.getMessage());
    throw new MimosException(mensagem.toString());
    } finally {
    GerenciadorDeConexao.closeConexao(con, stmt);
    }
    }
                                                

public void gravarArtesao(Artesao artesao,String nome)throws MimosException {
    if (artesao.getcodArtesao() == Constante.NOVO){
        incluirArtesao(artesao, nome);
    }
    
}        

public void incluirArtesao (Artesao artesao, String nome) throws MimosException{
    if (artesao == null){
        String mensagem = "não foi informado o hospede a cadastrar";
        throw new MimosException(mensagem);
    }
    try{
        
        con = GerenciadorDeConexao.getConexao();
        String sql = "Select * from empresa where filial = '"+nome+"'";
        stmt = con.prepareStatement (sql);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()==true){
        	artesao.setId_empresa(rs.getLong("ID_EMPRESA"));
        	resultid = artesao.getId_empresa();
        	
        }else{
        	System.out.println("Empresa não encontrado");
        }
        rs.close();
        ps.close();
    }catch(SQLException e){  
        System.out.println("Erro de SQL");  
        e.printStackTrace();  
    } try {  
    	con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement (SQL_INCLUIRARTESAO); 
        GeradorDeChave geradorDeChave = new GeradorDeChave("ARTESAO");
        long codigoartesao = geradorDeChave.getProximoCodigo();
        String status = "ativo";
        stmt.setLong (1,codigoartesao);
        stmt.setString (2,artesao.getSenha());
        stmt.setString (3,artesao.getNome());
        stmt.setString (4,artesao.getUsuario());
        stmt.setDouble(5, artesao.getSalario());
        stmt.setString (6, artesao.getHabilidade());
        stmt.setLong(7, artesao.getId_empresa());
        System.out.println(artesao.getId_empresa());
       
        stmt.executeUpdate();
        
}

    catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("não foi possível incluir o artesão");
        mensagem.append("\nMotivo:"+ex.getMessage());
        throw new MimosException (mensagem.toString());            
    }
    finally{
        GerenciadorDeConexao.closeConexao (con,stmt);
    }
}
public List pesquisarArtesao (String clausulaWhere) throws MimosException {
      clausulaWhere = "%"+clausulaWhere+"%";
      String sql = "select * from artesao where nome like ?";
      List resultado = new ArrayList();
      try{
          con = GerenciadorDeConexao.getConexao();
          stmt = con.prepareStatement(sql);
          stmt.setString(1, clausulaWhere);
          rs = stmt.executeQuery();
          while (rs.next()){
               artesao = criarArtesao(rs);
              resultado.add(artesao);
          }
      }catch (SQLException ex){
          StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
          mensagem.append("\nMOtivo:" +ex);
          
      }finally{
          GerenciadorDeConexao.closeConexao(con,stmt,rs);
      }
      return resultado;
        }
public ArrayList CarregarCombo ()throws MimosException{
	String sql = "select * from empresa";
	ArrayList result = new ArrayList();
	
	try{
		con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()){
        	
        	result.add(rs.getString( 3 ));
        	
        	
        }
	}catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
        mensagem.append("\nMOtivo:" +ex);
        
    }finally{
        GerenciadorDeConexao.closeConexao(con,stmt,rs);
    }
    return result;
      }

	


private Artesao criarArtesao(ResultSet rs)throws MimosException {
    Artesao artesao= new Artesao();
    try{
        artesao.setcodArtesao(rs.getLong("COD_ARTESAO"));
        artesao.setSenha(rs.getString("SENHA"));
        artesao.setNome(rs.getString("NOME"));
        artesao.setUsuario(rs.getString("USUARIO"));
        artesao.setSalario(rs.getDouble("SALARIO"));
        artesao.setHabilidade(rs.getString("HABILIDADE"));
        artesao.setId_empresa(rs.getLong("ID_EMPRESA"));
       }
       catch (SQLException exe){
           StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do artesão");
           mensagem.append("\nMOtivo:" +exe);
           throw new MimosException(mensagem.toString());
       }
 return artesao;   
}

}

