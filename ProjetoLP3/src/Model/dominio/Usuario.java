package Model.dominio;

public class Usuario {

    private String nome;
    private String cpf;
    private String telefone;
    private String nome_login_usuario;
    private String senha_usuario;



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_login_usuario() {
        return nome_login_usuario;
    }

    public void setNome_login_usuario(String nome_login_usuario) {
        this.nome_login_usuario = nome_login_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

}
