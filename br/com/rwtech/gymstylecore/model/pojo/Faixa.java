// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Faixa.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;
import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, DiaSemana

public class Faixa extends POJO
{

    public Faixa()
    {
    }

    public List<Dispositivo> getDispositivos()
    {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos)
    {
        this.dispositivos = dispositivos;
    }

    public DiaSemana getDiaSemana()
    {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana)
    {
        this.diaSemana = diaSemana;
    }

    public Calendar getHorarioFim()
    {
        return horarioFim;
    }

    public void setHorarioFim(Calendar horarioFim)
    {
        this.horarioFim = horarioFim;
    }

    public Calendar getHorarioInicio()
    {
        return horarioInicio;
    }

    public void setHorarioInicio(Calendar horarioInicio)
    {
        this.horarioInicio = horarioInicio;
    }

    private DiaSemana diaSemana;
    private Calendar horarioInicio;
    private Calendar horarioFim;
    private List<Dispositivo> dispositivos;
}
