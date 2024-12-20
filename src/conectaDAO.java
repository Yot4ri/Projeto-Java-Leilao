
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    Connection conn;
    Statement st;
    
    public Connection connectDB(){
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leilao?user=root&password=");
            st = conn.createStatement();
            
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public void desconectar(){
        try{
            conn.close();
        }catch(SQLException ex){
           
        }
    }
}
