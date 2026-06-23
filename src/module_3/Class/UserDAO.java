 package module_3.Class;
 import javax.swing.*;
 import java.sql.*;
 import java.util.*;

 
 
public class UserDAO {

    public void insertUser(User user, String Role){
        String sql = "INSERT INTO usuarios(id_empresa, nombre, usuario, clave, rol) VALUES(?,?,?,SHA2(?,256),?)";
        try(Connection con = Conection.conect()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(user.getEmpresa().getIdCorrelative()));
            ps.setString(2, user.getName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());
            ps.setString(5, Role);
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    public User validateLogIn(String usr, String pass){
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = SHA2(?,256)";

        try(Connection con = Conection.conect()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usr);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User user = new User();
                user.setIdUser(rs.getString("id_usuario"));
                user.setName(rs.getString("nombre"));
                user.setUserName(rs.getString("usuario"));
                user.setPassword(rs.getString("clave"));
                return user;
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
    }
    
}
