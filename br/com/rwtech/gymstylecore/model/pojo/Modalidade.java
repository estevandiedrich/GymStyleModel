// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Modalidade.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, PerfilAcesso, Usuario

public class Modalidade extends POJO
{

    public Modalidade()
    {
    }

    public Boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }

    public Integer getQtdeAcessos()
    {
        return qtdeAcessos;
    }

    public void setQtdeAcessos(Integer qtdeAcessos)
    {
        this.qtdeAcessos = qtdeAcessos;
    }

    public Usuario getInstrutor()
    {
        return instrutor;
    }

    public void setInstrutor(Usuario instrutor)
    {
        this.instrutor = instrutor;
    }

    public String getModalidade()
    {
        return modalidade;
    }

    public void setModalidade(String modalidade)
    {
        this.modalidade = modalidade;
    }

    public PerfilAcesso getPerfilAcesso()
    {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso)
    {
        this.perfilAcesso = perfilAcesso;
    }

    public Double getValor1()
    {
        return valor1;
    }

    public void setValor1(Double valor1)
    {
        this.valor1 = valor1;
    }

    public Double getValor2()
    {
        return valor2;
    }

    public void setValor2(Double valor2)
    {
        this.valor2 = valor2;
    }

    public Double getValor3()
    {
        return valor3;
    }

    public void setValor3(Double valor3)
    {
        this.valor3 = valor3;
    }

    public Double getValor4()
    {
        return valor4;
    }

    public void setValor4(Double valor4)
    {
        this.valor4 = valor4;
    }

    public Double getValor5()
    {
        return valor5;
    }

    public void setValor5(Double valor5)
    {
        this.valor5 = valor5;
    }

    public Double getValor6()
    {
        return valor6;
    }

    public void setValor6(Double valor6)
    {
        this.valor6 = valor6;
    }

    public Double getValor7()
    {
        return valor7;
    }

    public void setValor7(Double valor7)
    {
        this.valor7 = valor7;
    }

    private String modalidade;
    private Double valor1;
    private Double valor2;
    private Double valor3;
    private Double valor4;
    private Double valor5;
    private Double valor6;
    private Double valor7;
    private Integer qtdeAcessos;
    private PerfilAcesso perfilAcesso;
    private Usuario instrutor;
    private Boolean ativo;
}
