// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CaixaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.CaixaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Caixa;
import java.sql.Connection;
import java.util.*;

public class CaixaService extends BaseService
{

    public CaixaService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getCaixaDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getCaixaDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getCaixaDAO().delete(conn, id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getCaixaDAO().disabled(conn, id);
    }
    @Override
    public List<Caixa> readByCriteria(Map input)
    {
        return DaoLocator.getCaixaDAO().readByCriteria(input);
    }
    @Override
    public Caixa readById(Long id)
    {
        return (Caixa)DaoLocator.getCaixaDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getCaixaDAO().paginator(input);
    }
    @Override
    public Map readList()
    {
        Map mapa = new HashMap();
        List lista = readByCriteria(new HashMap());
        Caixa pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getNome()))
            pojo = (Caixa)i$.next();

        return mapa;
    }
}
