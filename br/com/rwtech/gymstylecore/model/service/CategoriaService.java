// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CategoriaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.CategoriaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Categoria;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import java.sql.Connection;
import java.util.*;

public class CategoriaService extends BaseService
{

    public CategoriaService()
    {
    }
    @Override
    public Categoria readById(Long id)
    {
        return (Categoria)DaoLocator.getCategoriaDAO().readById(id);
    }
    @Override
    public List<Categoria> readByCriteria(Map input)
    {
        return DaoLocator.getCategoriaDAO().readByCriteria(input);
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getCategoriaDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getCategoriaDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getCategoriaDAO().delete(conn, id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getCategoriaDAO().paginator(input);
    }
    @Override
    public Map readList()
    {
        Map filterMap = new HashMap();
        filterMap.put(Filtro.CRITERIO_ATIVO, "true");
        List lista = readByCriteria(filterMap);
        Map mapa = new LinkedHashMap();
        Categoria pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getNome()))
            pojo = (Categoria)i$.next();

        return mapa;
    }
    
    public boolean exist(String categoria, Long idCategoria)
    {
        Map map = new HashMap();
        map.put(Filtro.CRITERIO_NOME, categoria);
        map.put(Filtro.CRITERIO_ATIVO, "true");
        if(idCategoria != null && idCategoria.longValue() > 0L)
            map.put(Filtro.CRITERIO_DIFERENTE_ID, idCategoria.toString());
        if(DaoLocator.getCategoriaDAO().readByCriteria(map).isEmpty())
            return Boolean.FALSE.booleanValue();
        else
            return Boolean.TRUE.booleanValue();
    }
}
