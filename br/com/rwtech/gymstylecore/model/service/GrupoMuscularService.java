// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GrupoMuscularService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.GrupoMuscularDAO;
import br.com.rwtech.gymstylecore.model.pojo.GrupoMuscular;
import java.sql.Connection;
import java.util.*;

public class GrupoMuscularService extends BaseService
{

    public GrupoMuscularService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getGrupoMuscularDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getGrupoMuscularDAO().update(conn, pojo);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getGrupoMuscularDAO().disabled(conn, id);
    }
    @Override
    public List<GrupoMuscular> readByCriteria(Map input)
    {
        return DaoLocator.getGrupoMuscularDAO().readByCriteria(input);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getGrupoMuscularDAO().paginator(input);
    }
    @Override
    public GrupoMuscular readById(Long id)
    {
        return (GrupoMuscular)DaoLocator.getGrupoMuscularDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new LinkedHashMap();
        GrupoMuscular pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getGrupoMuscular()))
            pojo = (GrupoMuscular)i$.next();

        return mapa;
    }

}
