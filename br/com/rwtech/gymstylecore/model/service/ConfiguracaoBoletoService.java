// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfiguracaoBoletoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ConfiguracaoBoletoDAO;
import br.com.rwtech.gymstylecore.model.pojo.ConfiguracaoBoleto;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ConfiguracaoBoletoService extends BaseService
{

    public ConfiguracaoBoletoService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getConfiguracaoBoletoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getConfiguracaoBoletoDAO().update(conn, pojo);
    }

    public ConfiguracaoBoleto read()
    {
        return (ConfiguracaoBoleto)DaoLocator.getConfiguracaoBoletoDAO().readById(Long.valueOf(1L));
    }
    @Override
    public List<ConfiguracaoBoleto> readByCriteria(Map input)
    {
        return DaoLocator.getConfiguracaoBoletoDAO().readByCriteria(input);
    }
    @Override
    public ConfiguracaoBoleto readById(Long id)
    {
        return read();
    }
}
