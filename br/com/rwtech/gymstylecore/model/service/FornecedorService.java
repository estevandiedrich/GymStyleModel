// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FornecedorService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.FornecedorDAO;
import br.com.rwtech.gymstylecore.model.pojo.Fornecedor;
import java.sql.Connection;
import java.util.*;

public class FornecedorService extends BaseService
{

    public FornecedorService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getFornecedorDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getFornecedorDAO().update(conn, pojo);
    }

    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getFornecedorDAO().delete(conn, id);
    }

    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getFornecedorDAO().disabled(conn, id);
    }
    @Override
    public List<Fornecedor> readByCriteria(Map input)
    {
        return DaoLocator.getFornecedorDAO().readByCriteria(input);
    }
    @Override
    public Fornecedor readById(Long id)
    {
        return (Fornecedor)DaoLocator.getFornecedorDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getFornecedorDAO().paginator(input);
    }
    @Override
    public Map readList()
    {
        Map mapa = new HashMap();
        List lista = readByCriteria(new HashMap());
        Fornecedor pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getNome()))
            pojo = (Fornecedor)i$.next();

        return mapa;
    }

}
