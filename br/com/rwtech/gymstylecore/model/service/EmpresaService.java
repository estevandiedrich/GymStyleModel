// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EmpresaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.EmpresaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Empresa;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class EmpresaService extends BaseService
{

    public EmpresaService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getEmpresaDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getEmpresaDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getEmpresaDAO().delete(conn, id);
    }
    @Override
    public List<Empresa> readByCriteria(Map input)
    {
        return DaoLocator.getEmpresaDAO().readByCriteria(input);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getEmpresaDAO().paginator(input);
    }
    @Override
    public Empresa readById(Long id)
    {
        return (Empresa)DaoLocator.getEmpresaDAO().readById(id);
    }

}
