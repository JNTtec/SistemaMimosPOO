package mimos.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mimos.excecao.MimosException;

public class GeradorDeChave {
	
	  private static final byte INCREMENTO = 1;
	    private Connection con;
	    private String tabela;
	    private long proximoCodigo;
	    private long maximoCodigo;

	    public GeradorDeChave(String tabela) throws 
	            MimosException{
	        this.con = GerenciadorDeConexao.getConexao();
	        this.tabela = tabela;
	        proximoCodigo = 0;
	        maximoCodigo = 0;
	        
	        try{
	            con.setAutoCommit(false);
	        }catch(SQLException ex){
	            StringBuffer mensagem = new StringBuffer("N�o foi poss�vel "
	                    + "desligar a confirma��o autom�tica");
	            mensagem.append("\nMotivo: "+ ex.getMessage());
	            throw new MimosException(mensagem.toString());
	        }
	    }
	    
	    public synchronized long getProximoCodigo() throws MimosException 
	    {
	        if(proximoCodigo==maximoCodigo){
	            reservarCodigo();
	        }
	        return proximoCodigo;
	    }

	    private void reservarCodigo() throws MimosException {
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        long proximoCodigoNovo;
	        String sql = "select  proximocodigo from chaves where tabela = ? "
	                + "for update";
	        try{
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, tabela);
	            rs = stmt.executeQuery();
	            rs.next();
	            proximoCodigoNovo = rs.getLong("proximoCodigo");
	        }catch(SQLException ex){
	            StringBuffer mensagem = new StringBuffer("N�o foi poss�vel "
	                    + "gerar o c�digo");
	            mensagem.append("\nMotivo: "+ex.getMessage());
	            throw  new MimosException(mensagem.toString());
	        }long maximoCodigoNovo = proximoCodigoNovo + INCREMENTO;
	        stmt = null;
	        rs = null;
	        
	        try {
	            sql = "UPDATE chaves SET proximoCodigo = ? WHERE tabela = ? ";
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, maximoCodigoNovo);
	            stmt.setString(2, tabela);
	            stmt.executeUpdate();
	            con.commit();
	            proximoCodigo = proximoCodigoNovo;
	            maximoCodigo = maximoCodigoNovo;
	        } catch ( SQLException exc){
	            StringBuffer mensagem = new StringBuffer("N�o foi poss�vel "
	                    + "gerar o c�digo");
	            mensagem.append("\nMotivo: " + exc.getMessage());
	            throw new MimosException(mensagem.toString());
	        } finally {
	            GerenciadorDeConexao.closeConexao(con, stmt, rs);
	        }
	        
	    }
	    
	}