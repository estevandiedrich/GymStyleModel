// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DispositivoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.DispositivoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import java.sql.Connection;
import java.util.*;

public class DispositivoService extends BaseService
{

    public DispositivoService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getDispositivoDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getDispositivoDAO().update(conn, pojo);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return super.disabled(conn, id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getDispositivoDAO().disabled(conn, id);
    }
    @Override
    public List<Dispositivo> readByCriteria(Map input)
    {
        return DaoLocator.getDispositivoDAO().readByCriteria(input);
    }
    @Override
    public Dispositivo readById(Long id)
    {
        return (Dispositivo)DaoLocator.getDispositivoDAO().readById(id);
    }

    public Dispositivo readByMac(String mac)
    {
        return DaoLocator.getDispositivoDAO().readByMac(mac);
    }

    public Map readListImages()
    {
        Map map = new HashMap();
        List lista = readByCriteria(new HashMap());
        String img = "<img src=\"images/online.png\" />";
        for(Iterator i$ = lista.iterator(); i$.hasNext();)
        {
            Dispositivo pojo = (Dispositivo)i$.next();
            if(pojo.getDispositivo() == null || pojo.getDispositivo().isEmpty())
                map.put(pojo.getId(), (new StringBuilder()).append(img).append(pojo.getEnderecoIp()).toString());
            else
                map.put(pojo.getId(), (new StringBuilder()).append(img).append(pojo.getDispositivo()).toString());
        }

        return map;
    }
    @Override
    public Map readList()
    {
        Map map = new HashMap();
        List lista = readByCriteria(new HashMap());
        for(Iterator i$ = lista.iterator(); i$.hasNext();)
        {
            Dispositivo pojo = (Dispositivo)i$.next();
            if(pojo.getDispositivo() == null || pojo.getDispositivo().isEmpty())
                map.put(pojo.getId(), pojo.getEnderecoIp());
            else
                map.put(pojo.getId(), pojo.getDispositivo());
        }

        return map;
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getDispositivoDAO().paginator(input);
    }

    public void zerarUltimoEvento(Long id)
    {
        DaoLocator.getDispositivoDAO().zerarUltimoEvento(id);
    }
}
