package module_3.Class;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    //Metodo que probablemente no se usará
    public void addEmpresa(Empresa empresa){
        String sql = "INSERT INTO empresa (nombre_comercial, razon_social, nrc, nit, giro ) VALUES(?, ?, ?, ?, ?)";
        try(Connection con = Conection.conect()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empresa.getName());
            ps.setString(2, empresa.getSocialReason());
            ps.setString(3, empresa.getNrc());
            ps.setString(4, empresa.getNit());
            ps.setString(5, empresa.getGiro());
            ps.executeUpdate();
            return;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return;
        }
    }

    public List<Empresa> searchEmpresa(){
        List<Empresa> bussinesList = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try(Connection con = Conection.conect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){
                Empresa bussines= new Empresa(
                        rs.getString("nombre_comercial"),
                        Integer.parseInt(rs.getString("id_empresa")),
                        rs.getString("razon_social"),
                        rs.getString("nrc"),
                        rs.getString("nit"),
                        rs.getString("giro")
                );
                bussinesList.add(bussines);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return bussinesList;
    }

}
