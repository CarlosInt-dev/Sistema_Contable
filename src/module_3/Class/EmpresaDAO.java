package module_3.Class;
import java.sql.*;
public class EmpresaDAO {
    public void addEmpresa(Empresa empresa){
        String sql = "INSERT INTO empresa (id_empresa, nombre_comercial, razon_social, nrc, nit, giro ) VALUES(?, ?, ?, ?, ?)";
    }

}
