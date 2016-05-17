// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FormaPagamentoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.FormaDePagamentoDAO;
import br.com.rwtech.gymstylecore.model.pojo.FormaDePagamento;
import java.util.*;

public class FormaPagamentoService extends BaseService
{

    public FormaPagamentoService()
    {
    }
    @Override
    public List<FormaDePagamento> readByCriteria(Map input)
    {
        return DaoLocator.getFormaDePagamentoDAO().readByCriteria(input);
    }
    @Override
    public FormaDePagamento readById(Long id)
    {
        return (FormaDePagamento)DaoLocator.getFormaDePagamentoDAO().readById(id);
    }
    @Override
    public Map readList()
    {
        List lista = readByCriteria(new HashMap());
        Map mapa = new LinkedHashMap();
        FormaDePagamento pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getFormaDePagamento()))
            pojo = (FormaDePagamento)i$.next();

        return mapa;
    }

}
