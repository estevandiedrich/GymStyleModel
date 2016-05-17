// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EstadoCivilService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.EstadoCivilDAO;
import br.com.rwtech.gymstylecore.model.pojo.EstadoCivil;
import java.util.*;

public class EstadoCivilService extends BaseService
{

    public EstadoCivilService()
    {
    }
    @Override
    public List<EstadoCivil> readByCriteria(Map input)
    {
        return DaoLocator.getEstadoCivilDAO().readByCriteria(input);
    }
    @Override
    public EstadoCivil readById(Long id)
    {
        return (EstadoCivil)DaoLocator.getEstadoCivilDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new LinkedHashMap();
        EstadoCivil pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getEstadoCivil()))
            pojo = (EstadoCivil)i$.next();

        return mapa;
    }

}
