// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AvaliacaoFisicaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.AvaliacaoFisicaDAO;
import br.com.rwtech.gymstylecore.model.pojo.AvaliacaoFisica;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AvaliacaoFisicaService extends BaseService
{

    public AvaliacaoFisicaService()
    {
    }
    public void create(AvaliacaoFisica pojo, Long idAluno)
    {
        DaoLocator.getAvaliacaoFisicaDAO().create(pojo, idAluno);
    }
    
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getAvaliacaoFisicaDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getAvaliacaoFisicaDAO().delete(conn, id);
    }
    @Override
    public List<AvaliacaoFisica> readByCriteria(Map input)
    {
        return DaoLocator.getAvaliacaoFisicaDAO().readByCriteria(input);
    }
    @Override
    public AvaliacaoFisica readById(Long id)
    {
        return (AvaliacaoFisica)DaoLocator.getAvaliacaoFisicaDAO().readById(id);
    }
}
