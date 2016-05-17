// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModoOperacaoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ModoOperacaoDAO;
import br.com.rwtech.gymstylecore.model.pojo.ModoOperacao;
import java.util.*;

public class ModoOperacaoService extends BaseService
{

    public ModoOperacaoService()
    {
    }
    @Override
    public List<ModoOperacao> readByCriteria(Map input)
    {
        return DaoLocator.getModoOperacaoDAO().readByCriteria(input);
    }
    @Override
    public ModoOperacao readById(Long id)
    {
        return (ModoOperacao)DaoLocator.getModoOperacaoDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new HashMap();
        ModoOperacao pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getModoOperacao()))
            pojo = (ModoOperacao)i$.next();

        return mapa;
    }

}
