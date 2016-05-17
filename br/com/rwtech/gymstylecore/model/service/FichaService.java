// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FichaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.FichaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class FichaService extends BaseService
{

    public FichaService()
    {
    }

    public void create(Long idUsuario, Ficha pojo)
    {
        DaoLocator.getFichaDAO().create(idUsuario, pojo);
    }

    public void update(Long idUsuario, Ficha pojo)
    {
        DaoLocator.getFichaDAO().update(idUsuario, pojo);
    }
    @Override
    public List<Ficha> readByCriteria(Map input)
    {
        return DaoLocator.getFichaDAO().readByCriteria(input);
    }
    @Override
    public Ficha readById(Long id)
    {
        return (Ficha)DaoLocator.getFichaDAO().readById(id);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getFichaDAO().delete(conn, id);
    }

    public List readFichasByIdUsuario(Long idUsuario)
    {
        return DaoLocator.getFichaDAO().readFichasByIdUsuario(idUsuario);
    }

    public void updateStatusAtiva(Long idUsuario, Long idFicha, Boolean status)
    {
        DaoLocator.getFichaDAO().updateStatusAtiva(idUsuario, idFicha, status);
    }

}
