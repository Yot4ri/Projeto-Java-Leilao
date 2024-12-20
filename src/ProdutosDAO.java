import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String sql = null;
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        
        if (conn != null){
            sql= "INSERT INTO Itens (nome, preco, status) VALUES (? ,? ,?) ";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getValor());
                stmt.setString(3, produto.getStatus()); 
                
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar o produto" + e.getMessage()); //e.getMessage() Utiliza a mensagem de erro padrão do SQL
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Falha ao conectar ao banco de dados");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

