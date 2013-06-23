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

public class ArtesaoDAO {
	 Connection con = null;
	    PreparedStatement stmt = null;
	    Artesao artesao;
	    ResultSet rs = null;
	    
	    private static final String SQL_INCLUIRARTESAO = "insert into artesao (cod_artesao,senha,nome,usuario,salario,habilidade,id_empresa"+
                " values (?,?,?,?,?,?,?)";

private static final String SQL_ALTERARARTESAO = "UPDATE artesao set "
+"senha=?,nome=?,usuario=?,salario=?,"
+"habilidade=?,id_empresa=? "
+"where cod_artesao=?";
private static final String SQL_EXCLUIRARTESAO =  
"Delete artesao where cod_artesao = ?";

public void alterarArtesao(Artesao artesao) throws MimosException {
    if (artesao == null){
    String mensagem = "N�o foi informado o hospede a ser alterado";
    throw new MimosException(mensagem);
    }
    
    con = null;
    stmt =null;
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
            StringBuffer mensagem = new StringBuffer("N�o foi poss�vel atualizar "
            + "os dados do Artes�o");
            mensagem.append("\nMotivo: "+ex.getMessage());
            throw new MimosException(mensagem.toString());
        } finally {
        GerenciadorDeConexao.closeConexao(con, stmt);
    }
}
public void excluirArtesao(Artesao artesao) throws MimosException {
    if (artesao == null){
    String mensagem = "N�o foi informado o hospede a ser desativado!";
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
    StringBuffer mensagem = new StringBuffer("N�o foi poss�vel excluir o hospede");
    mensagem.append("\nMotivo: "+ex.getMessage());
    throw new MimosException(mensagem.toString());
    } finally {
    GerenciadorDeConexao.closeConexao(con, stmt);
    }
    }
                                                

public void gravarArtesao(Artesao artesao)throws MimosException {
    if (artesao.getcodArtesao() == Constante.NOVO){
        incluirArtesao(artesao);
    }
    
}        

public void incluirArtesao (Artesao artesao) throws MimosException{
    if (artesao == null){
        String mensagem = "n�o foi informado o hospede a cadastrar";
        throw new MimosException(mensagem);
    }
    try{
        
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
        stmt.setLong (7, artesao.getId_empresa());
        stmt.executeUpdate();
        
    }
    catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("n�o foi poss�vel incluir o artes�o");
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
          StringBuffer mensagem = new StringBuffer ("n�o foi possivel realizar a pesquisa ");
          mensagem.append("\nMOtivo:" +ex);
          
      }finally{
          GerenciadorDeConexao.closeConexao(con,stmt,rs);
      }
      return resultado;
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
           StringBuffer mensagem = new StringBuffer("N�o foi poss�vel obter os dados do artes�o");
           mensagem.append("\nMOtivo:" +exe);
           throw new MimosException(mensagem.toString());
       }
 return artesao;   
}

}

