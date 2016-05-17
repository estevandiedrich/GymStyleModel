// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfiguracaoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ConfiguracaoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Configuracao;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ConfiguracaoService extends BaseService
{

    public ConfiguracaoService()
    {
    }
    @Override
    public void create(Connection conn,Object obj)
    {
    	
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getConfiguracaoDAO().update(conn, pojo);
    }
    @Override
    public List<Configuracao> readByCriteria(Map input)
    {
        return DaoLocator.getConfiguracaoDAO().readByCriteria(input);
    }
    @Override
    public Configuracao readById(Long id)
    {
        return (Configuracao)DaoLocator.getConfiguracaoDAO().readById(id);
    }

    public Configuracao readByCampo(String campo)
    {
        return DaoLocator.getConfiguracaoDAO().readByCampo(campo);
    }
}
