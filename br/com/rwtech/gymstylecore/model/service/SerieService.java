// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SerieService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.SerieDAO;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import java.util.List;
import java.util.Map;

public class SerieService extends BaseService
{

    public SerieService()
    {
    }
    @Override
    public List<Serie> readByCriteria(Map input)
    {
        return DaoLocator.getSerieDAO().readByCriteria(input);
    }
    @Override
    public Serie readById(Long id)
    {
        return (Serie)DaoLocator.getSerieDAO().readById(id);
    }

}
