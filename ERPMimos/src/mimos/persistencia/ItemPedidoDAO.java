package mimos.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import mimos.constantes.Constante;
import mimos.excecao.MimosException;
import mimos.modelo.Empresa;
import mimos.modelo.ItemPedido;
public class ItemPedidoDAO {
	Connection con = null;
    PreparedStatement stmt = null;
    ItemPedido item;
    ResultSet rs = null;
    private static final String SQL_INCLUIRITEM = "insert into item_pedido (cod_pedido,data_pedido,valor, cod_produto"+
            " values (?,?,?,?)";
   private static final String SQL_ALTERARITEM = "UPDATE item_pedido set "
   		+"data_pedido=?,valor=?, cod_produto=?"
   		+"where cod_pedido=?";
   private static final String SQL_EXCLUIRITEM =  
   		"Delete empresa where cod_pedido = ?";
   
public void excluirItem(ItemPedido item) throws MimosException {
   if (item == null){
   String mensagem = "Não foi informado a reserva a ser excluída!";
   throw new MimosException(mensagem);
   }
   con = null;
   stmt = null;
   try {
   con = GerenciadorDeConexao.getConexao();
   stmt = con.prepareStatement(SQL_EXCLUIRITEM);
   stmt.setLong(1, item.getCod_pedido());
   stmt.executeUpdate();
   }catch(SQLException ex){
   StringBuffer mensagem = new StringBuffer("Não foi possível excluir a reserva");
   mensagem.append("\nMotivo: "+ex.getMessage());
   throw new MimosException(mensagem.toString());
   } finally {
   GerenciadorDeConexao.closeConexao(con, stmt);
   }
}
public void gravarItem(ItemPedido item)throws MimosException {
    
    if (item.getCod_pedido() == Constante.NOVO){
        System.out.println("Ak também");
        incluirItem(item);
        
    }      
    
    
}  
public void incluirItem (ItemPedido item) throws MimosException{
    if (item == null){
        String mensagem = "não foi informado o item a ser cadastrado";
        throw new MimosException(mensagem);
    }
    try{ 
        System.out.println("chamou !!");
        con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement (SQL_INCLUIRITEM);
        GeradorDeChave geradorDeChave = new GeradorDeChave("reserva");
        long codigoitem = geradorDeChave.getProximoCodigo();
        System.out.println("valor da chave "+ codigoitem);
        item.setCod_pedido(codigoitem);
        stmt.setLong (1,codigoitem);
        java.util.Date dataPedido = item.getDatapedido();
        stmt.setDate (2, new Date (dataPedido.getTime()));
        stmt.setDouble(3, item.getValor());
        stmt.setLong (4, item.getCod_produto());            
        stmt.executeUpdate();
        this.incluirItem(item);
        
    }
    catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("não foi possível incluir o item");
        mensagem.append("\nMotivo:"+ex.getMessage());
        throw new MimosException (mensagem.toString());            
    }
    finally{
        GerenciadorDeConexao.closeConexao (con,stmt);
    }
}
public List pesquisarItem (long id) throws MimosException {
    String sql = "select * from item_pedido where cod_pedido = ?";
    List resultado = new ArrayList();
    try{
        con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement(sql);
        stmt.setLong(1, item.getCod_pedido());
        rs = stmt.executeQuery();
        while (rs.next()){
             item = criarItem(rs);
            resultado.add(item);
        }
    }catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
        mensagem.append("\nMOtivo:" +ex);
        
    }finally{
        GerenciadorDeConexao.closeConexao(con,stmt,rs);
    }
    return resultado;
      } 
private ItemPedido criarItem(ResultSet rs)throws MimosException {
    ItemPedido item= new ItemPedido();
    try{
        item.setCod_pedido(rs.getLong("ID_EMPRESA"));
        item.setDatapedido(rs.getDate("DATA_PEDIDO"));
        item.setValor(rs.getDouble("VALOR"));
        item.setCod_produto(rs.getLong("COD_PRODUTO"));
       }
       catch (SQLException exe){
           StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da empresa");
           mensagem.append("\nMOtivo:" +exe);
           throw new MimosException(mensagem.toString());
       }
 return item;   
}



}
