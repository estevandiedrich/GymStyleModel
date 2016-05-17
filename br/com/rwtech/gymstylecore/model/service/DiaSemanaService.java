// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DiaSemanaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.DiaSemanaDAO;
import br.com.rwtech.gymstylecore.model.pojo.DiaSemana;
import java.util.*;

public class DiaSemanaService extends BaseService
{

    public DiaSemanaService()
    {
    }
    @Override
    public List<DiaSemana> readByCriteria(Map input)
    {
        return DaoLocator.getDiaSemanaDAO().readByCriteria(input);
    }
    @Override
    public DiaSemana readById(Long id)
    {
        return (DiaSemana)DaoLocator.getDiaSemanaDAO().readById(id);
    }

    public DiaSemana readByName(String nome)
    {
        return DaoLocator.getDiaSemanaDAO().readByName(nome);
    }
    @Override
    public Map readList()
    {
        Map mapa = new HashMap();
        List lista = readByCriteria(new HashMap());
        DiaSemana pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getDiaSemana()))
            pojo = (DiaSemana)i$.next();

        return mapa;
    }
}
