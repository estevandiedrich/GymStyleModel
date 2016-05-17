// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Aparelho.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class Aparelho extends POJO
{

    public Aparelho()
    {
    }

    public String getAparelho()
    {
        return aparelho;
    }

    public void setAparelho(String aparelho)
    {
        this.aparelho = aparelho;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    private String aparelho;
    private String descricao;
}
