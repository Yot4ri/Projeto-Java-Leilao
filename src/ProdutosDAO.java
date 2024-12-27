import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> vendido = new ArrayList<>();
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
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Falha ao conectar ao banco de dados");
        }
    }
    
    public int atualizar(ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        
        if (conn != null){
            sql= "UPDATE Itens SET status = ? WHERE Id = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1,"Vendido");
                stmt.setInt(2, produto.getId());
                
                stmt.executeUpdate();          
                JOptionPane.showMessageDialog(null, "Venda feita com sucesso!");
                return 1;
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Falha ao vender o produto" + ex.getMessage()); //ex.getMessage() Utiliza a mensagem de erro padrão do SQL
                return 0;
            }finally {
                try {
                    conn.close();
                    return 1;
                    }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
                } 
            }
        }else{
            JOptionPane.showMessageDialog(null, "Falha ao conectar ao banco de dados");
            return 0;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
         if (conn != null) {
        sql = "SELECT id, nome, preco, status FROM Itens";
        try (Statement stmt = conn.createStatement(); 
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("preco"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);
                }
            }
        catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao listar produtos;");
                }
        } 
        
        return listagem;
    }
    
        public ArrayList<ProdutosDTO> listarVendas(){
        
        conn = new conectaDAO().connectDB();
        
         if (conn != null) {
        sql = "SELECT id, nome, preco, status FROM Itens WHERE Status = 'Vendido'";
        try (Statement stmt = conn.createStatement(); 
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("preco"));
                produto.setStatus(rs.getString("status"));
                
                vendido.add(produto);
                }
            }
        catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao listar produtos;");
                }
        } 
        
        return vendido;
    }
        
        public void desconectar (){
        try{
            conn.close();
        } catch (SQLException ex){
            //Pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
    
}

