// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreinoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.TreinoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import java.util.List;
import java.util.Map;

public class TreinoService extends BaseService
{

    public TreinoService()
    {
    }
    @Override
    public List<Treino> readByCriteria(Map input)
    {
        return DaoLocator.getTreinoDAO().readByCriteria(input);
    }
    @Override
    public Treino readById(Long id)
    {
        return (Treino)DaoLocator.getTreinoDAO().readById(id);
    }

}
