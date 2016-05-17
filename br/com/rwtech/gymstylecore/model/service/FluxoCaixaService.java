// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FluxoCaixaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.FluxoCaixaDAO;
import br.com.rwtech.gymstylecore.model.dao.ModalidadeDAO;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class FluxoCaixaService extends BaseService
{

    public FluxoCaixaService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getFluxoCaixaDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getFluxoCaixaDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getFluxoCaixaDAO().delete(conn, id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getFluxoCaixaDAO().disabled(conn, id);
    }
    @Override
    public List<FluxoCaixa> readByCriteria(Map input)
    {
        return DaoLocator.getFluxoCaixaDAO().readByCriteria(input);
    }
    @Override
    public FluxoCaixa readById(Long id)
    {
        return (FluxoCaixa)DaoLocator.getFluxoCaixaDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getModalidadeDAO().paginator(input);
    }

    public Long idCaixaAberto()
    {
        return DaoLocator.getFluxoCaixaDAO().idCaixaAberto();
    }

}
