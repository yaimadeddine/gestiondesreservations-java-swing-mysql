package entities;

public class LoginConn {

    private static int count;
    private int id;
    private String login;
    private String password;

    public LoginConn(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public LoginConn(String login, String password) {
        this.id = ++count;
        this.login = login;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  login ;
    }
}
