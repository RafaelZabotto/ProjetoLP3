package Model.dominio;

import java.time.LocalDate;

public class Beneficiado {

    private int codigo;
    private String nome;
    private LocalDate data_nasc;
    private String profissao_beneficiado;
    private String telefone_beneficiado;
    private int numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String descricao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getProfissao_beneficiado() {
        return profissao_beneficiado;
    }

    public void setProfissao_beneficiado(String profissao_beneficiado) {
        this.profissao_beneficiado = profissao_beneficiado;
    }

    public String getTelefone_beneficiado() {
        return telefone_beneficiado;
    }

    public void setTelefone_beneficiado(String telefone_beneficiado) {
        this.telefone_beneficiado = telefone_beneficiado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
