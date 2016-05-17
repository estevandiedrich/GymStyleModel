// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuncionarioService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.AcessoDAO;
import br.com.rwtech.gymstylecore.model.dao.FuncionarioDAO;
import br.com.rwtech.gymstylecore.model.pojo.Acesso;
import br.com.rwtech.gymstylecore.model.util.StatusAcesso;
import java.util.Map;
import java.util.Set;

public class FuncionarioService
{

    public FuncionarioService()
    {
    }

    public Set getDispositivosByAcessoIdUsuario(Long idUsuario)
    {
        return DaoLocator.getFuncionarioDAO().getDispositivosByAcessoIdUsuario(idUsuario.longValue());
    }

    public Boolean disabledFuncionario(Long id)
    {
        return DaoLocator.getFuncionarioDAO().disabledFuncionario(id);
    }

    public StatusAcesso contemAcesso(Long idUsuario)
    {
        Acesso acesso = DaoLocator.getAcessoDAO().readByIdUsuario(idUsuario);
        if(acesso == null)
            return StatusAcesso.NAO_CONTEM;
        if(acesso.getAtivo().booleanValue())
            return StatusAcesso.ATIVO;
        if(!acesso.getAtivo().booleanValue())
            return StatusAcesso.CANCELADO;
        else
            return StatusAcesso.NAO_CONTEM;
    }

    public Map paginatorFuncionarioComAcesso(Map input)
    {
        return DaoLocator.getFuncionarioDAO().paginatorFuncionarioComAcesso(input);
    }
}
