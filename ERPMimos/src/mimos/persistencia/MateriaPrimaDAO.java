package mimos.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mimos.constantes.Constante;
import mimos.excecao.MimosException;
import mimos.modelo.MateriaPrima;
public class MateriaPrimaDAO {
	Connection con = null;
    PreparedStatement stmt = null;
    MateriaPrima materia;
    ResultSet rs = null;
    private static final String SQL_INCLUIRMATERIA = "insert into materia_prima (cod_materia_prima,nome,tipo,descricao,cod_fornecedor)"+
            " valu5es (?,?,?,?,?)";
   private static final String SQL_ALTERARMATERIA = "UPDATE materia_prima set "
   		+"nome=?,tipo=?,descricao=?,cod_fornecedor=?"
   		+"where cod_materia_prima=?";
   private static final String SQL_EXCLUIRMATERIA =  
   		"Delete materiaprima where cod_produto= ?";
   public void alterarMateria(MateriaPrima materia) throws MimosException {
	   	 if (materia == null){
	   	 String mensagem = "Não foi informado a materia a ser alterada";
	   	 throw new MimosException(mensagem);
	   	 }
	   	 con = null;
	   	 stmt =null;
	   	 try{
	   	     con = GerenciadorDeConexao.getConexao();
	   	     stmt = con.prepareStatement(SQL_ALTERARMATERIA);        
	   	     stmt.setString(1, materia.getNome());
	   	     stmt.setString(2, materia.getTipo());
	   	     stmt.setString(3, materia.getDescricao());
	   	     stmt.setLong(4, materia.getCod_fornecedor());
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
   public void excluirMateria(MateriaPrima materia) throws MimosException {
       if (materia == null){
       String mensagem = "Não foi informado a matéria-prima a ser excluida";
       throw new MimosException(mensagem);
       }
       con = null;
       stmt = null;
       try {
       con = GerenciadorDeConexao.getConexao();
       stmt = con.prepareStatement(SQL_EXCLUIRMATERIA);
       stmt.setLong(1, materia.getCod_materia_prima());
       stmt.executeUpdate();
       }catch(SQLException ex){
       StringBuffer mensagem = new StringBuffer("Não foi possível excluir a matéria-Prima");
       mensagem.append("\nMotivo: "+ex.getMessage());
       throw new MimosException(mensagem.toString());
       } finally {
       GerenciadorDeConexao.closeConexao(con, stmt);
       }
       }
   public void gravarMateria(MateriaPrima materia)throws MimosException {
   	
   	System.out.println(materia.getCod_materia_prima());
       if (materia.getCod_materia_prima() == Constante.NOVO){
           incluirMateria(materia);
           System.out.println("passou");
         
       
   }
   }
   public void incluirMateria (MateriaPrima materia) throws MimosException{
       if (materia== null){
           String mensagem = "não foi informado a materia prima a cadastrar";
           throw new MimosException(mensagem);
       }
       try{
           
           con = GerenciadorDeConexao.getConexao();
           stmt = con.prepareStatement (SQL_INCLUIRMATERIA);
          
           GeradorDeChave geradorDeChave = new GeradorDeChave("PRODUTO");
           long codigomateria = geradorDeChave.getProximoCodigo();
           
         
           stmt.setLong (1 ,codigomateria);
           stmt.setString (2 ,materia.getNome());
           stmt.setString (3 ,materia.getTipo());
           stmt.setString (4 ,materia.getDescricao());
           stmt.setLong (5 ,materia.getCod_fornecedor());
           
           stmt.executeUpdate();
           
       }
       catch (SQLException ex){
           StringBuffer mensagem = new StringBuffer ("não foi possível incluir a materia-prima");
           mensagem.append("\nMotivo:"+ex.getMessage());
           throw new MimosException (mensagem.toString());            
       }
       finally{
           GerenciadorDeConexao.closeConexao (con,stmt);
       }
   }
   public List pesquisarMateria (String clausulaWhere) throws MimosException {
       clausulaWhere = "%"+clausulaWhere+"%";
       String sql = "select * from materia_prima where nome like ?";
       List resultado = new ArrayList();
       try{
           con = GerenciadorDeConexao.getConexao();
           stmt = con.prepareStatement(sql);
           stmt.setString(1, clausulaWhere);
           rs = stmt.executeQuery();
           while (rs.next()){
               materia= criarMateria(rs);
               resultado.add(materia);
           }
       }catch (SQLException ex){
           StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
           mensagem.append("\nMOtivo:" +ex);
           
       }finally{
           GerenciadorDeConexao.closeConexao(con,stmt,rs);
       }
       return resultado;
         } 
   private MateriaPrima criarMateria(ResultSet rs)throws MimosException {
       MateriaPrima materia= new MateriaPrima();
       try{
           materia.setCod_materia_prima(rs.getLong("COD_MATERIA_PRIMA"));
           materia.setNome(rs.getString("NOME"));
           materia.setTipo(rs.getString("TIPO"));
           materia.setDescricao(rs.getString("DESCRICAO"));
           materia.setCod_fornecedor(rs.getLong("COD_FORNECEDOR"));
          }
          catch (SQLException exe){
              StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do produto");
              mensagem.append("\nMOtivo:" +exe);
              throw new MimosException(mensagem.toString());
          }
    return materia;   
   }

   
}
