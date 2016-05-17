// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario

public class Log extends POJO
{

    public Log()
    {
    }

    public Log(String descricao, String tipo, Usuario usuario)
    {
        this.tipo = tipo;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public Long getParametro()
    {
        return parametro;
    }

    public void setParametro(Long parametro)
    {
        this.parametro = parametro;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Calendar getData()
    {
        return data;
    }

    public void setData(Calendar data)
    {
        this.data = data;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    private String descricao;
    private String tipo;
    private Calendar data;
    private Usuario usuario;
    private Long parametro;
}
