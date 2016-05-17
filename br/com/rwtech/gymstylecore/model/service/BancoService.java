// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BancoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.BancoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Banco;
import java.sql.Connection;
import java.util.*;

public class BancoService extends BaseService
{

    public BancoService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getBancoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getBancoDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getBancoDAO().delete(conn, id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getBancoDAO().disabled(conn, id);
    }
    @Override
    public List<Banco> readByCriteria(Map input)
    {
        return DaoLocator.getBancoDAO().readByCriteria(input);
    }
    @Override
    public Banco readById(Long id)
    {
        return (Banco)DaoLocator.getBancoDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getBancoDAO().paginator(input);
    }
    @Override
    public Map readList()
    {
        Map mapa = new HashMap();
        List lista = readByCriteria(new HashMap());
        Banco pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getNome()))
            pojo = (Banco)i$.next();

        return mapa;
    }
}
