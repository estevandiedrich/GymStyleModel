// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Evento.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, MotivoBloqueio, Dispositivo, Usuario

public class Evento extends POJO
{

    public Evento()
    {
    }

    public Boolean getEntrada()
    {
        return entrada;
    }

    public void setEntrada(Boolean entrada)
    {
        this.entrada = entrada;
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

    public MotivoBloqueio getMotivo()
    {
        return motivo;
    }

    public void setMotivo(MotivoBloqueio motivo)
    {
        this.motivo = motivo;
    }

    public Boolean getOffline()
    {
        return offline;
    }

    public void setOffline(Boolean offline)
    {
        this.offline = offline;
    }

    public Integer getPosicao()
    {
        return posicao;
    }

    public void setPosicao(Integer posicao)
    {
        this.posicao = posicao;
    }

    public Boolean getRealizado()
    {
        return realizado;
    }

    public void setRealizado(Boolean realizado)
    {
        this.realizado = realizado;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    private Calendar dataHora;
    private Boolean realizado;
    private Boolean offline;
    private Boolean entrada;
    private Integer posicao;
    private MotivoBloqueio motivo;
    private Dispositivo dispositivo;
    private Usuario usuario;
}
