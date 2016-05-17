// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Treino.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class Treino extends POJO
{

    public Treino()
    {
        treinaDomingo = Boolean.FALSE;
        treinaSegunda = Boolean.FALSE;
        treinaTerca = Boolean.FALSE;
        treinaQuarta = Boolean.FALSE;
        treinaQuinta = Boolean.FALSE;
        treinaSexta = Boolean.FALSE;
        treinaSabado = Boolean.FALSE;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public List<Serie> getSeries()
    {
        return series;
    }

    public void setSeries(List<Serie> series)
    {
        this.series = series;
    }

    public Boolean getTreinaDomingo()
    {
        return treinaDomingo;
    }

    public void setTreinaDomingo(Boolean treinaDomingo)
    {
        this.treinaDomingo = treinaDomingo;
    }

    public Boolean getTreinaQuarta()
    {
        return treinaQuarta;
    }

    public void setTreinaQuarta(Boolean treinaQuarta)
    {
        this.treinaQuarta = treinaQuarta;
    }

    public Boolean getTreinaQuinta()
    {
        return treinaQuinta;
    }

    public void setTreinaQuinta(Boolean treinaQuinta)
    {
        this.treinaQuinta = treinaQuinta;
    }

    public Boolean getTreinaSabado()
    {
        return treinaSabado;
    }

    public void setTreinaSabado(Boolean treinaSabado)
    {
        this.treinaSabado = treinaSabado;
    }

    public Boolean getTreinaSegunda()
    {
        return treinaSegunda;
    }

    public void setTreinaSegunda(Boolean treinaSegunda)
    {
        this.treinaSegunda = treinaSegunda;
    }

    public Boolean getTreinaSexta()
    {
        return treinaSexta;
    }

    public void setTreinaSexta(Boolean treinaSexta)
    {
        this.treinaSexta = treinaSexta;
    }

    public Boolean getTreinaTerca()
    {
        return treinaTerca;
    }

    public void setTreinaTerca(Boolean treinaTerca)
    {
        this.treinaTerca = treinaTerca;
    }

    private String nome;
    private Boolean treinaDomingo;
    private Boolean treinaSegunda;
    private Boolean treinaTerca;
    private Boolean treinaQuarta;
    private Boolean treinaQuinta;
    private Boolean treinaSexta;
    private Boolean treinaSabado;
    private List<Serie> series;
}
