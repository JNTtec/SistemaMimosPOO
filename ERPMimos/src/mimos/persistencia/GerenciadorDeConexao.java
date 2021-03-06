package mimos.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mimos.excecao.MimosException;

public class GerenciadorDeConexao {
	private static final String URL = "jdbc:mysql://localhost:3306/artesao";
    private static final String USER = "root";
    private static final String PASSWORD = "FATEC";

    static Connection getConexao() throws MimosException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("N�o foi possivel"
                    + "estabelecer conexao com o banco de dados");
            mensagem.append("\nMotivo:" + ex.getMessage());
            throw new MimosException(mensagem.toString());
        }

    }

    static void closeConexao(Connection con) throws MimosException {
        closeConexao(con, null, null);
    }

    static void closeConexao(Connection con, PreparedStatement stmt)
            throws MimosException {
        closeConexao(con, stmt, null);
    }

    static void closeConexao(Connection con, PreparedStatement stmt,
            ResultSet rs) throws MimosException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("N�o foi Possivel"
                    + "finalizar a conex�o com o banco de dados.");
            mensagem.append("\nMotivo:" + ex.getMessage());
            throw new MimosException(mensagem.toString());

        }
    }
}


