// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DuracaoPlanoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.DuracaoPlanoDAO;
import br.com.rwtech.gymstylecore.model.pojo.DuracaoPlano;
import java.util.*;

public class DuracaoPlanoService extends BaseService
{

    public DuracaoPlanoService()
    {
    }
    @Override
    public List<DuracaoPlano> readByCriteria(Map input)
    {
        return DaoLocator.getDuracaoPlanoDAO().readByCriteria(input);
    }
    @Override
    public DuracaoPlano readById(Long id)
    {
        return (DuracaoPlano)DaoLocator.getDuracaoPlanoDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        SortedMap mapa = new TreeMap();
        List lista = readByCriteria(new HashMap());
        DuracaoPlano pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getDuracao()))
            pojo = (DuracaoPlano)i$.next();

        return mapa;
    }

}
