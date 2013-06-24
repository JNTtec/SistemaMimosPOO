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
import mimos.modelo.Venda;
import mimos.modelo.Produto;

public class VendaDAO {
	Connection con = null;
    PreparedStatement stmt = null;
    Venda venda;
    ResultSet rs = null;
    private static final String SQL_INCLUIRVENDA = "insert into venda (cod_venda,valor_total,desconto_valor,valor_pago,valor_troco,venda_status,data_venda,cod_vendedor,cod_cliente,cod_pedido,cod_produto)"+
            " values (?,?,?,?,?,?,?,?,?,?,?)";
   private static final String SQL_ALTERARVENDA = "UPDATE venda set "
   		+"venda_status=?"
   		+"where cod_venda=?";
  
   public void alterarVenda(Venda venda) throws MimosException {
	   if (venda == null){
		   	 String mensagem = "Não foi informado a venda ser alterado";
		   	 throw new MimosException(mensagem);
		   	 }
		   	 con = null;
		   	 stmt =null;
		   	 try{
		   	     con = GerenciadorDeConexao.getConexao();
		   	     stmt = con.prepareStatement(SQL_ALTERARVENDA);        
		   	     stmt.setString(1, venda.getStatus());
		   	     stmt.setDouble(2, venda.getVenda());
		   	     stmt.executeUpdate();
		   	     
		   	     }catch(SQLException ex){
		   	         StringBuffer mensagem = new StringBuffer("Não foi possível atualizar "
		   	         + "os dados da Venda");
		   	         mensagem.append("\nMotivo: "+ex.getMessage());
		   	         throw new MimosException(mensagem.toString());
		   	     } finally {
		   	     GerenciadorDeConexao.closeConexao(con, stmt);
		   	 }
		   	}
   public void gravarVenda(Venda venda)throws MimosException {
   	System.out.println(venda.getVenda());
       if (venda.getVenda() == Constante.NOVO){
           incluirVenda(venda);
           System.out.println("passou");
         
       
   }
       
   }
   public void incluirVenda (Venda venda) throws MimosException{
       if (venda== null){
           String mensagem = "não foi informado o produto a cadastrar";
           throw new MimosException(mensagem);
       }
       try{
           
           con = GerenciadorDeConexao.getConexao();
           stmt = con.prepareStatement (SQL_INCLUIRVENDA);
          
           GeradorDeChave geradorDeChave = new GeradorDeChave("VENDA");
           long codigovenda = geradorDeChave.getProximoCodigo();
           
           
           stmt.setLong (1 ,codigovenda);
           stmt.setDouble (2 ,venda.getValor_total());
           stmt.setDouble (3 ,venda.getDesconto_valor());
           stmt.setDouble (4 ,venda.getValor_pago());
           stmt.setDouble (5 ,venda.getValor_troco());
           stmt.setString (6 ,venda.getStatus());
           java.util.Date dataVenda = venda.getData_venda();
           stmt.setDate (7, new Date (dataVenda.getTime()));
           stmt.setLong(8,  venda.getCod_vendedor());
           stmt.setLong(9 ,venda.getCod_cliente());
           stmt.setLong(10, venda.getCod_pedido());
           stmt.setLong(11, venda.getCod_produto());
           stmt.executeUpdate();
           
       }
       catch (SQLException ex){
           StringBuffer mensagem = new StringBuffer ("não foi possível registrar a venda!!");
           mensagem.append("\nMotivo:"+ex.getMessage());
           throw new MimosException (mensagem.toString());            
       }
       finally{
           GerenciadorDeConexao.closeConexao (con,stmt);
       }
   }
   public List pesquisarVenda (int codigo) throws MimosException {
       String sql = "select * from produto where venda = ?";
       List resultado = new ArrayList();
       try{
           con = GerenciadorDeConexao.getConexao();
           stmt = con.prepareStatement(sql);
           stmt.setLong(1, venda.getVenda());
           rs = stmt.executeQuery();
           while (rs.next()){
               venda = criarVenda(rs);
               resultado.add(venda);
           }
       }catch (SQLException ex){
           StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
           mensagem.append("\nMOtivo:" +ex);
           
       }finally{
           GerenciadorDeConexao.closeConexao(con,stmt,rs);
       }
       return resultado;
         } 
   private Venda criarVenda(ResultSet rs)throws MimosException {
       Venda venda= new Venda();
       try{
           venda.setVenda(rs.getLong("COD_VENDA"));
           venda.setValor_total(rs.getDouble("VALOR_TOTAL"));
           venda.setDesconto_valor(rs.getDouble("DESCONTO_VALOR"));
           venda.setValor_pago(rs.getDouble("VALOR_PAGO"));
           venda.setValor_troco(rs.getDouble("VALOR_TROCO"));
           venda.setStatus(rs.getString("VENDA_STATUS"));
           venda.setData_venda(rs.getDate("DATA_VENDA"));           
           venda.setCod_vendedor(rs.getLong("COD_VENDEDOR"));
           venda.setCod_cliente(rs.getLong("COD_CLIENTE"));
           venda.setCod_pedido(rs.getLong("COD_PEDIDO"));
           venda.setCod_produto(rs.getLong("COD_PRODUTO"));
           
           
           }
          catch (SQLException exe){
              StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da empresa");
              mensagem.append("\nMOtivo:" +exe);
              throw new MimosException(mensagem.toString());
          }
    return venda;   
   }

}
