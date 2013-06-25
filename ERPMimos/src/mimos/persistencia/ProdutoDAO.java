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
import mimos.modelo.Produto;
public class ProdutoDAO {
	Connection con = null;
    PreparedStatement stmt = null;
    Produto produto;
    ResultSet rs = null;
    private static final String SQL_INCLUIRPRODUTO = "insert into produto (cod_produto,descricao,preco,margem_lucro_perc,preco_venda,quantidade)"+
             " values (?,?,?,?,?,?)";
    private static final String SQL_ALTERARPRODUTO = "UPDATE produto set "
    		+"descricao=?,preco=?,margem_lucro_perc=?,preco_venda=?,quantidade=?"
    		+"where cod_produto = ?";
    private static final String SQL_EXCLUIRPRODUTO = "Delete from produto where cod_produto = ?";
    public void alterarProduto(Produto produto) throws MimosException {
   	 if (produto == null){
   	 String mensagem = "Não foi informado o produto a ser alterado";
   	 throw new MimosException(mensagem);
   	 }
   	 con = null;
   	 stmt =null;
   	 try{
   	     con = GerenciadorDeConexao.getConexao();
   	     stmt = con.prepareStatement(SQL_ALTERARPRODUTO);        
   	     stmt.setString(1, produto.getDescricao());
   	     stmt.setDouble(2, produto.getPreco());
   	     stmt.setDouble(3, produto.getMargemLucro());
   	     stmt.setDouble(4, produto.getPrecoVenda());
   	     stmt.setDouble(5, produto.getQuantidade());
   	     stmt.setDouble(6, produto.getCodigoProd());
   	     stmt.executeUpdate();
   	     
   	     }catch(SQLException ex){
   	         StringBuffer mensagem = new StringBuffer("Não foi possível atualizar "
   	         + "os dados da Empresa");
   	         mensagem.append("\nMotivo: "+ex.getMessage());
   	         throw new MimosException(mensagem.toString());
   	     } finally {
   	     GerenciadorDeConexao.closeConexao(con, stmt);
   	     System.out.println("por aqui");
   	 }
   	}
    public void excluirProduto(Produto produto) throws MimosException {
        if (produto == null){
        String mensagem = "Não foi informado a empresa a ser excluida";
        throw new MimosException(mensagem);
        }
        con = null;
        stmt = null;
        try {
        con = GerenciadorDeConexao.getConexao();
        stmt = con.prepareStatement(SQL_EXCLUIRPRODUTO);
        stmt.setLong(1, produto.getCodigoProd());
        stmt.executeUpdate();
        }catch(SQLException ex){
        StringBuffer mensagem = new StringBuffer("Não foi possível excluir o produto");
        mensagem.append("\nMotivo: "+ex.getMessage());
        throw new MimosException(mensagem.toString());
        } finally {
        GerenciadorDeConexao.closeConexao(con, stmt);
        }
        }
    public void gravarProduto(Produto produto)throws MimosException {
    	System.out.println(produto.getCodigoProd());
        if (produto.getCodigoProd() == Constante.NOVO){
            incluirProduto(produto);
            System.out.println("passou");
          
        
    }
        
    }
        public void incluirProduto (Produto produto) throws MimosException{
	        if (produto== null){
	            String mensagem = "não foi informado o produto a cadastrar";
	            throw new MimosException(mensagem);
	        }
	        try{
	            
	            con = GerenciadorDeConexao.getConexao();
	            stmt = con.prepareStatement (SQL_INCLUIRPRODUTO);
	           
	            GeradorDeChave geradorDeChave = new GeradorDeChave("PRODUTO");
	            long codigoproduto = geradorDeChave.getProximoCodigo();
	            
	            
	            stmt.setLong (1 ,codigoproduto);
	            stmt.setString (2 ,produto.getDescricao());
	            stmt.setDouble (3 ,produto.getPreco());
	            stmt.setDouble (4 ,produto.getMargemLucro());
	            stmt.setDouble (5 ,produto.getPrecoVenda());
	            stmt.setDouble (6 ,produto.getQuantidade());
	            
	            stmt.executeUpdate();
	            
	        }
	        catch (SQLException ex){
	            StringBuffer mensagem = new StringBuffer ("não foi possível incluir o produto");
	            mensagem.append("\nMotivo:"+ex.getMessage());
	            throw new MimosException (mensagem.toString());            
	        }
	        finally{
	            GerenciadorDeConexao.closeConexao (con,stmt);
	        }
	    }
        public List pesquisarProduto (String clausulaWhere) throws MimosException {
	        clausulaWhere = "%"+clausulaWhere+"%";
	        String sql = "select * from produto where descricao like ?";
	        List resultado = new ArrayList();
	        try{
	            con = GerenciadorDeConexao.getConexao();
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, clausulaWhere);
	            rs = stmt.executeQuery();
	            while (rs.next()){
	                produto = criarProduto(rs);
	                resultado.add(produto);
	            }
	        }catch (SQLException ex){
	            StringBuffer mensagem = new StringBuffer ("não foi possivel realizar a pesquisa ");
	            mensagem.append("\nMOtivo:" +ex);
	            
	        }finally{
	            GerenciadorDeConexao.closeConexao(con,stmt,rs);
	        }
	        return resultado;
	          } 
	    private Produto criarProduto(ResultSet rs)throws MimosException {
	        Produto produto= new Produto();
	        try{
	            produto.setCodigoProd(rs.getLong("COD_PRODUTO"));
	            produto.setDescricao(rs.getString("DESCRICAO"));
	            produto.setPreco(rs.getDouble("PRECO"));
	            produto.setMargemLucro(rs.getDouble("MARGEM_LUCRO_PERC"));
	            produto.setPrecoVenda(rs.getDouble("PRECO_VENDA"));
	            produto.setQuantidade(rs.getDouble("QUANTIDADE"));
	           }
	           catch (SQLException exe){
	               StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da empresa");
	               mensagem.append("\nMOtivo:" +exe);
	               throw new MimosException(mensagem.toString());
	           }
	     return produto;   
	    }

	    
        
    } 
	
