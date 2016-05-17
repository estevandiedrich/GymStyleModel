// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Liberar.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario, Dispositivo

public class Liberar extends POJO
{

    public Liberar()
    {
    }

    public Calendar getDataHora()
    {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora)
    {
        this.dataHora = dataHora;
    }

    public Dispositivo getDispositivo()
    {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo)
    {
        this.dispositivo = dispositivo;
    }

    public String getJustificativa()
    {
        return justificativa;
    }

    public void setJustificativa(String justificativa)
    {
        this.justificativa = justificativa;
    }

    public Usuario getOperador()
    {
        return operador;
    }

    public void setOperador(Usuario operador)
    {
        this.operador = operador;
    }

    private String justificativa;
    private Calendar dataHora;
    private Usuario operador;
    private Dispositivo dispositivo;
}
