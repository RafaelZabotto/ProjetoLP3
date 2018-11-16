package Model.dominio;

import java.util.Date;

public class Beneficiado {

    private String nome;
    private Date data_nasc;
    private String possui_dependente;
    private String profissao_beneficiado;
    private Date data_cadastro_bene;
    private String telefone_beneficiado;
    private int numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getPossui_dependente() {
        return possui_dependente;
    }

    public void setPossui_dependente(String possui_dependente) {
        this.possui_dependente = possui_dependente;
    }

    public String getProfissao_beneficiado() {
        return profissao_beneficiado;
    }

    public void setProfissao_beneficiado(String profissao_beneficiado) {
        this.profissao_beneficiado = profissao_beneficiado;
    }

    public Date getData_cadastro_bene() {
        return data_cadastro_bene;
    }

    public void setData_cadastro_bene(Date data_cadastro_bene) {
        this.data_cadastro_bene = data_cadastro_bene;
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
