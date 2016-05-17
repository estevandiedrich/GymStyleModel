// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProtocoloService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ProtocoloDAO;
import br.com.rwtech.gymstylecore.model.pojo.Protocolo;
import java.util.*;

public class ProtocoloService extends BaseService
{

    public ProtocoloService()
    {
    }
    @Override
    public Protocolo readById(Long id)
    {
        return (Protocolo)DaoLocator.getProtocoloDAO().readById(id);
    }
    @Override
    public List<Protocolo> readByCriteria(Map input)
    {
        return DaoLocator.getProtocoloDAO().readByCriteria(input);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new LinkedHashMap();
        Protocolo pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getProtocolo()))
            pojo = (Protocolo)i$.next();

        return mapa;
    }

}
