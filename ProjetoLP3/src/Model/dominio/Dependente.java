package Model.dominio;

import java.util.Date;

public class Dependente {

    private String nome;
    private String parentesco;
    private Date data_nasc;
    private String profissa_dependente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getProfissa_dependente() {
        return profissa_dependente;
    }

    public void setProfissa_dependente(String profissa_dependente) {
        this.profissa_dependente = profissa_dependente;
    }
}
