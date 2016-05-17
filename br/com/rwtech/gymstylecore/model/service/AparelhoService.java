// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AparelhoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.AparelhoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Aparelho;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AparelhoService extends BaseService
{

    public AparelhoService()
    {
    }
    @Override
    public List<Aparelho> readByCriteria(Map input)
    {
        return DaoLocator.getAparelhoDAO().readByCriteria(input);
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getAparelhoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getAparelhoDAO().update(conn, pojo);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getAparelhoDAO().disabled(conn, id);
    }
    @Override
    public Aparelho readById(Long id)
    {
        return (Aparelho)DaoLocator.getAparelhoDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getAparelhoDAO().paginator(input);
    }
}
