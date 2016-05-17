// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EventoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.EventoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Evento;
import java.util.List;
import java.util.Map;

public class EventoService extends BaseService
{

    public EventoService()
    {
    }
    @Override
    public List<Evento> readByCriteria(Map input)
    {
        return DaoLocator.getEventoDAO().readByCriteria(input);
    }

    public Evento readUltimoEventoUsuario(Long idUsuario)
    {
        return DaoLocator.getEventoDAO().readUltimoEventoUsuario(idUsuario);
    }
    @Override
    public Evento readById(Long id)
    {
        return (Evento)DaoLocator.getEventoDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getEventoDAO().paginator(input);
    }

    public List readUltimosEventos()
    {
        return DaoLocator.getEventoDAO().readUltimosEventos();
    }

}
