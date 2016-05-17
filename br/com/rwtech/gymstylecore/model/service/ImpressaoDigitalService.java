// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImpressaoDigitalService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.dao.ImpressaoDigitalDAO;
import br.com.rwtech.gymstylecore.model.dao.ImpressaoDigitalEspelhoDAO;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImpressaoDigitalService extends BaseService
{

    public ImpressaoDigitalService()
    {
        ATUALIZANDO = Boolean.valueOf(false);
    }

    public void create(ImpressaoDigital pojo, Long idUsuario)
    {
        DaoLocator.getImpressaoDigitalDAO().create(ConnectionManager.getInstance().getConnection(), pojo, idUsuario);
    }

    public void create(List list, Long idUsuario)
    {
        DaoLocator.getImpressaoDigitalDAO().create(list, idUsuario);
    }
    @Override
    public List<ImpressaoDigital> readByCriteria(Map input)
    {
        return DaoLocator.getImpressaoDigitalDAO().readByCriteria(input);
    }
    @Override
    public ImpressaoDigital readById(Long id)
    {
        return (ImpressaoDigital)DaoLocator.getImpressaoDigitalDAO().readById(id);
    }

    public List<ImpressaoDigital> readDigitaisUsuario(Long idUsuario)
    {
        return DaoLocator.getImpressaoDigitalDAO().readDigitaisUsuario(idUsuario);
    }

    public Boolean readDigitalByIdDedo(Long idDedo, Long idUsuario)
    {
        return DaoLocator.getImpressaoDigitalDAO().readDigitalByIdDedo(idDedo, idUsuario);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getImpressaoDigitalDAO().delete(conn, id);
    }

    public Boolean deleteDigitaisUsuario(Long idUsuario)
    {
        return DaoLocator.getImpressaoDigitalDAO().deleteDigitaisUsuario(ConnectionManager.getInstance().getConnection(), idUsuario);
    }

    public void atualizarDigitais(Long idUsuario, List digitais)
    {
        ATUALIZANDO = Boolean.valueOf(false);
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            ATUALIZANDO = Boolean.valueOf(true);
            DaoLocator.getImpressaoDigitalDAO().deleteDigitaisUsuario(conn, idUsuario);
            ImpressaoDigital aux;
            for(Iterator i$ = digitais.iterator(); i$.hasNext(); ServiceLocator.getImpressaoDigitalService().create(aux, idUsuario))
                aux = (ImpressaoDigital)i$.next();

            DaoLocator.getImpressaoDigitalEspelhoDAO().deleteDigitaisUsuario(conn, idUsuario);
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ATUALIZANDO = Boolean.valueOf(false);
    }

    public void preDigitais(Long idUsuario)
    {
        for(int cont = 0; ATUALIZANDO.booleanValue() && cont++ < 4;)
            try
            {
                Thread.sleep(200L);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(ImpressaoDigitalService.class.getName()).log(Level.SEVERE, null, ex);
            }

        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            DaoLocator.getImpressaoDigitalEspelhoDAO().deleteDigitaisUsuario(conn, idUsuario);
            List digitais = DaoLocator.getImpressaoDigitalDAO().readDigitaisUsuario(idUsuario);
            ImpressaoDigital digital;
            for(Iterator i$ = digitais.iterator(); i$.hasNext(); DaoLocator.getImpressaoDigitalEspelhoDAO().create(conn, digital, idUsuario))
                digital = (ImpressaoDigital)i$.next();

        }
        catch(Exception ex)
        {
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean ATUALIZANDO;
}
