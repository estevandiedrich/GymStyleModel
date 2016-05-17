// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.dao.LogDAO;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class LogService extends BaseService
{

    public LogService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getLogDAO().create(ConnectionManagerLog.getInstance().getConnection(), pojo);
    }
    @Override
    public void update(Connection conn,Object obj)
    {
    	
    }
    @Override
    public Log readById(Long id)
    {
        return (Log)DaoLocator.getLogDAO().readById(id);
    }
    @Override
    public List<Log> readByCriteria(Map input)
    {
        return DaoLocator.getLogDAO().readByCriteria(input);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getLogDAO().paginator(input);
    }

}
