// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImpressaoDigitalEspelhoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.dao.ImpressaoDigitalEspelhoDAO;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ImpressaoDigitalEspelhoService extends BaseService
{

    public ImpressaoDigitalEspelhoService()
    {
    }

    public void create(ImpressaoDigital pojo, Long idUsuario)
    {
        DaoLocator.getImpressaoDigitalEspelhoDAO().create(ConnectionManager.getInstance().getConnection(), pojo, idUsuario);
    }
    @Override
    public List<ImpressaoDigital> readByCriteria(Map input)
    {
        return DaoLocator.getImpressaoDigitalEspelhoDAO().readByCriteria(input);
    }
    @Override
    public ImpressaoDigital readById(Long id)
    {
        return (ImpressaoDigital)DaoLocator.getImpressaoDigitalEspelhoDAO().readById(id);
    }

    public Boolean readDigitalByIdDedo(Long idDedo, Long idUsuario)
    {
        return DaoLocator.getImpressaoDigitalEspelhoDAO().readDigitalByIdDedo(idDedo, idUsuario);
    }

    public List readDigitaisUsuario(Long idUsuario)
    {
        return DaoLocator.getImpressaoDigitalEspelhoDAO().readDigitaisUsuario(idUsuario);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getImpressaoDigitalEspelhoDAO().delete(conn, id);
    }

    public Boolean deleteDigitaisUsuario(Long idUsuario)
    {
        return DaoLocator.getImpressaoDigitalEspelhoDAO().deleteDigitaisUsuario(ConnectionManager.getInstance().getConnection(), idUsuario);
    }

}
