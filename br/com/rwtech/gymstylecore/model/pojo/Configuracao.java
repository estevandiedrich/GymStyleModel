// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Configuracao.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class Configuracao extends POJO
{

    public Configuracao()
    {
    }

    public String getCampo()
    {
        return campo;
    }

    public void setCampo(String campo)
    {
        this.campo = campo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getValor()
    {
        return valor;
    }

    public int getValorInteiro()
    {
        valor = valor.replace(" ", "");
        valor = valor.replace(" ", "");
        valor = valor.replace(" ", "");
        return Integer.valueOf(valor).intValue();
    }

    public void setValor(String valor)
    {
        this.valor = valor;
    }

    private String campo;
    private String descricao;
    private String valor;
}
