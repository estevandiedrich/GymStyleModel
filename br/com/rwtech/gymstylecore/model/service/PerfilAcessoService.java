// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PerfilAcessoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.PerfilAcessoDAO;
import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import java.sql.Connection;
import java.util.*;

public class PerfilAcessoService extends BaseService
{

    public PerfilAcessoService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getPerfilAcessoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getPerfilAcessoDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getPerfilAcessoDAO().delete(conn, id);
    }
    @Override
    public List<PerfilAcesso> readByCriteria(Map input)
    {
        return DaoLocator.getPerfilAcessoDAO().readByCriteria(input);
    }
    @Override
    public PerfilAcesso readById(Long id)
    {
        return (PerfilAcesso)DaoLocator.getPerfilAcessoDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        Map mapa = new LinkedHashMap();
        List lista = readByCriteria(new HashMap());
        PerfilAcesso pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getPerfilAcesso()))
            pojo = (PerfilAcesso)i$.next();

        return mapa;
    }

}
