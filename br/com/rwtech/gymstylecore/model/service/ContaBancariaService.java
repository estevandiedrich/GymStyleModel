// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContaBancariaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ContaBancariaDAO;
import br.com.rwtech.gymstylecore.model.dao.RegistroContaBancariaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Banco;
import br.com.rwtech.gymstylecore.model.pojo.ContaBancaria;
import java.sql.Connection;
import java.util.*;

public class ContaBancariaService extends BaseService
{

    public ContaBancariaService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getContaBancariaDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getContaBancariaDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getContaBancariaDAO().delete(conn, id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getContaBancariaDAO().disabled(conn, id);
    }
    @Override
    public List<ContaBancaria> readByCriteria(Map input)
    {
        return DaoLocator.getContaBancariaDAO().readByCriteria(input);
    }
    @Override
    public ContaBancaria readById(Long id)
    {
        return (ContaBancaria)DaoLocator.getContaBancariaDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getContaBancariaDAO().paginator(input);
    }
    @Override
    public Map readList()
    {
        Map map = new HashMap();
        map.put("criterioAtivo", "true");
        List lista = readByCriteria(map);
        Map mapa = new LinkedHashMap();
        ContaBancaria pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), (new StringBuilder()).append(pojo.getBanco().getNome()).append(" - Ag:").append(pojo.getAgencia()).append(" Cc:").append(pojo.getNumeroConta()).toString()))
            pojo = (ContaBancaria)i$.next();

        return mapa;
    }

    public Double readtValorTotalContaBancaria(Long idContaBancaria)
    {
        return DaoLocator.getRegistroContaBancariaDAO().getValorTotalContaBancaria(idContaBancaria);
    }

    public Double readValorPeriodoContaBancaria(Map input)
    {
        return DaoLocator.getRegistroContaBancariaDAO().readValorPeriodoContaBancaria(input);
    }
}
