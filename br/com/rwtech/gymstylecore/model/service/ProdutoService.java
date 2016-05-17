// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProdutoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ProdutoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Produto;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import java.sql.Connection;
import java.util.*;

public class ProdutoService extends BaseService
{

    public ProdutoService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getProdutoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getProdutoDAO().update(conn, pojo);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getProdutoDAO().disabled(conn, id);
    }
    @Override
    public List<Produto> readByCriteria(Map input)
    {
        return DaoLocator.getProdutoDAO().readByCriteria(input);
    }
    @Override
    public Produto readById(Long id)
    {
        return (Produto)DaoLocator.getProdutoDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getProdutoDAO().paginator(input);
    }

    public Long readNextCodigo()
    {
        return DaoLocator.getProdutoDAO().readNextCodigo();
    }

    public boolean exist(String codigo, Long idProduto)
    {
        Map map = new HashMap();
        map.put(Filtro.CRITERIO_CODIGO, codigo);
        map.put(Filtro.CRITERIO_ATIVO, "true");
        if(idProduto != null && idProduto.longValue() > 0L)
            map.put(Filtro.CRITERIO_DIFERENTE_ID, idProduto.toString());
        if(DaoLocator.getProdutoDAO().readByCriteria(map).isEmpty())
            return Boolean.FALSE.booleanValue();
        else
            return Boolean.TRUE.booleanValue();
    }

}
