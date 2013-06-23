package mimos.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
   
public void excluirItem(ItemPedido item) throws JavaHotelException {
   if (item == null){
   String mensagem = "N�o foi informado a reserva a ser exclu�da!";
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
   StringBuffer mensagem = new StringBuffer("N�o foi poss�vel excluir a reserva");
   mensagem.append("\nMotivo: "+ex.getMessage());
   throw new MimosException(mensagem.toString());
   } finally {
   GerenciadorDeConexao.closeConexao(con, stmt);
   }
}
public void gravarItem(ItemPedido item)throws JavaHotelException {
    
    if (item.getCod_pedido() == Constante.NOVO){
        System.out.println("Ak tamb�m");
        incluirItem(item);
        
    }      
    
    
}  
public void incluirItem (ItemPedido item) throws MimosException{
    if (item == null){
        String mensagem = "n�o foi informado o item a ser cadastrado";
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
        java.util.Date dataInicial = reserva.getDataInicio();
        stmt.setDate (4, new Date (dataInicial.getTime()));
        java.util.Date dataFinal = reserva.getDataFinal();
        stmt.setDate (5, new Date (dataFinal.getTime()));            
        stmt.executeUpdate();
        this.incluirQuartos(reserva);
        
    }
    catch (SQLException ex){
        StringBuffer mensagem = new StringBuffer ("n�o foi poss�vel incluir a reserva");
        mensagem.append("\nMotivo:"+ex.getMessage());
        throw new JavaHotelException (mensagem.toString());            
    }
    finally{
        GerenciadorDeConexao.closeConexao (con,stmt);
    }
}

}
