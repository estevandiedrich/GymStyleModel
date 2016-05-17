// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TipoUsuarioService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.TipoUsuarioDAO;
import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import java.util.*;

public class TipoUsuarioService extends BaseService
{

    public TipoUsuarioService()
    {
    }
    @Override
    public List<TipoUsuario> readByCriteria(Map input)
    {
        return DaoLocator.getTipoUsuarioDAO().readByCriteria(input);
    }
    @Override
    public TipoUsuario readById(Long id)
    {
        return (TipoUsuario)DaoLocator.getTipoUsuarioDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new LinkedHashMap();
        TipoUsuario pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getTipoUsuario()))
            pojo = (TipoUsuario)i$.next();

        return mapa;
    }

}
