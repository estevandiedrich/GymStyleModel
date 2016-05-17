// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LiberarService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.LiberarDAO;
import br.com.rwtech.gymstylecore.model.pojo.Liberar;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class LiberarService extends BaseService
{

    public LiberarService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getLiberarDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getLiberarDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getLiberarDAO().delete(conn, id);
    }
    @Override
    public List<Liberar> readByCriteria(Map input)
    {
        return DaoLocator.getLiberarDAO().readByCriteria(input);
    }
    @Override
    public Liberar readById(Long id)
    {
        return (Liberar)DaoLocator.getLiberarDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getLiberarDAO().paginator(input);
    }

}
