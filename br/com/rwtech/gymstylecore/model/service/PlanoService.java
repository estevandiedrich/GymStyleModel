// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlanoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.PlanoDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPlanoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import java.sql.Connection;
import java.util.*;

public class PlanoService extends BaseService
{

    public PlanoService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getPlanoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getPlanoDAO().update(conn, pojo);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getPlanoDAO().disabled(conn, id);
    }
    @Override
    public List<Plano> readByCriteria(Map input)
    {
        return DaoLocator.getPlanoDAO().readByCriteria(input);
    }
    @Override
    public Plano readById(Long id)
    {
        return (Plano)DaoLocator.getPlanoDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        Map mapa = new LinkedHashMap();
        List lista = readByCriteria(new HashMap());
        Plano pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getPlano()))
            pojo = (Plano)i$.next();

        return mapa;
    }

    public List<Plano> readPlanosByUser(Long id)
    {
        return DaoLocator.getPlanoDAO().readPlanosByUser(id);
    }

    public Map readPlanoUsuarioByIdPlanoUsuario(Long idPlanoUsuario)
    {
        return DaoLocator.getUsuarioPlanoDAO().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getPlanoDAO().paginator(input);
    }

}
