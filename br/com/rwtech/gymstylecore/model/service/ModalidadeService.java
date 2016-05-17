// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModalidadeService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ModalidadeDAO;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import java.sql.Connection;
import java.util.*;

public class ModalidadeService extends BaseService
{

    public ModalidadeService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getModalidadeDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getModalidadeDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getModalidadeDAO().delete(conn, id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getModalidadeDAO().disabled(conn, id);
    }
    @Override
    public List<Modalidade> readByCriteria(Map input)
    {
        return DaoLocator.getModalidadeDAO().readByCriteria(input);
    }
    @Override
    public Modalidade readById(Long id)
    {
        return (Modalidade)DaoLocator.getModalidadeDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getModalidadeDAO().paginator(input);
    }
    @Override
    public Map readList()
    {
        Map mapa = new HashMap();
        List lista = readByCriteria(new HashMap());
        Modalidade pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getModalidade()))
            pojo = (Modalidade)i$.next();

        return mapa;
    }

}
