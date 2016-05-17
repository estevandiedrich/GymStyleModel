// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ficha.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;
import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario

public class Ficha extends POJO
{

    public Ficha()
    {
    }

    public Boolean getAtiva()
    {
        return ativa;
    }

    public void setAtiva(Boolean ativa)
    {
        this.ativa = ativa;
    }

    public Calendar getData()
    {
        return data;
    }

    public void setData(Calendar data)
    {
        this.data = data;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Usuario getInstrutor()
    {
        return instrutor;
    }

    public void setInstrutor(Usuario instrutor)
    {
        this.instrutor = instrutor;
    }

    public Calendar getPeriodoFinal()
    {
        return periodoFinal;
    }

    public void setPeriodoFinal(Calendar periodoFinal)
    {
        this.periodoFinal = periodoFinal;
    }

    public Calendar getPeriodoInicial()
    {
        return periodoInicial;
    }

    public void setPeriodoInicial(Calendar periodoInicial)
    {
        this.periodoInicial = periodoInicial;
    }

    public List<Treino> getTreinos()
    {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos)
    {
        this.treinos = treinos;
    }

    private Boolean ativa;
    private Calendar data;
    private Calendar periodoInicial;
    private Calendar periodoFinal;
    private Usuario instrutor;
    private String descricao;
    private List<Treino> treinos;
}
