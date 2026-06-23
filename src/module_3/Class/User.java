package module_3.Class;

public class User {
    private String userName, idUser, password, email, name;
    private int status;
    private Role role;
    private Empresa empresa;

    public User() {}
    public User(String userName, String idUser, String password, String email, String name, int status, Role role, Empresa empresa){
        this.userName=userName;
        this.idUser=idUser;
        this.password=password;
        this.email=email;
        this.status=status;
        this.role=role;
        this.empresa=empresa;
        this.name=name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
