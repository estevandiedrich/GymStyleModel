// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedeSocialService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.RedeSocialDAO;
import br.com.rwtech.gymstylecore.model.pojo.RedeSocial;
import java.util.*;

public class RedeSocialService extends BaseService
{

    public RedeSocialService()
    {
    }
    @Override
    public List<RedeSocial> readByCriteria(Map input)
    {
        return DaoLocator.getRedeSocialDAO().readByCriteria(input);
    }
    @Override
    public RedeSocial readById(Long id)
    {
        return (RedeSocial)DaoLocator.getRedeSocialDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new LinkedHashMap();
        RedeSocial pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getRedeSocial()))
            pojo = (RedeSocial)i$.next();

        return mapa;
    }

}
