package module_3.Class;

public class Role {
    private String roleName;
    private int idRole;
    public Role(){}
    public Role(String roleName, int idRole){
        this.idRole=idRole;
        this.roleName=roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
