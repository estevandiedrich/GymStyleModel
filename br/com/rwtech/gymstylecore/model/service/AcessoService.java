// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AcessoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.AcessoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Acesso;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Faixa;

import java.util.List;

public class AcessoService
{

    public AcessoService()
    {
    }

    public void create(Acesso pojo, Long idAcesso)
    {
        DaoLocator.getAcessoDAO().create(pojo, idAcesso);
    }

    public void deleteFaixasAcesso(Long idAcesso)
    {
        DaoLocator.getAcessoDAO().deleteFaixasAcesso(ConnectionManager.getInstance().getConnectionForTransaction(), idAcesso);
    }

    public void deleteDispositivosAcesso(Long idAcesso)
    {
        DaoLocator.getAcessoDAO().deleteDispositivosAcesso(ConnectionManager.getInstance().getConnectionForTransaction(), idAcesso);
    }

    public void update(Acesso pojo)
    {
        DaoLocator.getAcessoDAO().update(pojo);
    }

    public void delete(Long idUsuario, Long idAcesso)
    {
        DaoLocator.getAcessoDAO().delete(idUsuario, idAcesso);
    }

    public Acesso readById(Long idAcesso)
    {
        return (Acesso)DaoLocator.getAcessoDAO().readById(idAcesso);
    }

    public Acesso readByIdUsuario(Long idUsuario)
    {
        return DaoLocator.getAcessoDAO().readByIdUsuario(idUsuario);
    }

    public List<Faixa> readFaixasById(Long idAcesso)
    {
        return DaoLocator.getAcessoDAO().getFaixas(idAcesso);
    }

    public List<Dispositivo> readDispositivosById(Long idAcesso)
    {
        return DaoLocator.getAcessoDAO().getDispositivos(idAcesso);
    }
}
