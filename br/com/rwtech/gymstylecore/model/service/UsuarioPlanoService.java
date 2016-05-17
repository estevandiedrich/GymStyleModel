// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioPlanoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPlanoDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPlanoReadDAO;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.StatusPlano;
import java.util.*;

public class UsuarioPlanoService
{

    public UsuarioPlanoService()
    {
    }

    public void create(Plano pojo, Long idUsuario)
    {
        DaoLocator.getUsuarioPlanoDAO().create(ConnectionManager.getInstance().getConnection(), pojo, idUsuario);
    }

    public void update(Plano pojo, Long idUsuario)
    {
        DaoLocator.getUsuarioPlanoDAO().update(ConnectionManager.getInstance().getConnection(), pojo, idUsuario);
    }

    public void finalizarPlano(Long id)
    {
        DaoLocator.getUsuarioPlanoDAO().finalizarPlano(id);
    }

    public void cancelarPlano(Long id, Double valor)
    {
        DaoLocator.getUsuarioPlanoDAO().cancelarPlano(id, valor);
    }

    public Set readDispositivosPlanoUsuario(Long idPlanoUsuario)
    {
        return DaoLocator.getUsuarioPlanoDAO().readDispositivosPlanoUsuarioById(idPlanoUsuario);
    }

    public Plano readUltimoPlano(Long idUsuario)
    {
        return DaoLocator.getUsuarioPlanoDAO().readUltimoPlanoUsuario(idUsuario);
    }

    public List<Plano> readPlanosToUsuario(Long idUsuario, Boolean completo)
    {
        return DaoLocator.getUsuarioPlanoDAO().readPlanos(idUsuario, completo);
    }

    public List readUsuPlaAtivo()
    {
        return DaoLocator.getUsuarioPlanoReadDAO().readUsuPlaAtivo();
    }

    public Map paginatorUsuPla(Map input)
    {
        return DaoLocator.getUsuarioPlanoReadDAO().paginatorUsuPla(input);
    }

    public Map paginatorUsuPlaAbertoAtivo(Map input)
    {
        return DaoLocator.getUsuarioPlanoReadDAO().paginatorUsuPlaAbertoAtivo(input);
    }

    public Map paginatorUsuPlaAbertos(Map input)
    {
        return DaoLocator.getUsuarioPlanoReadDAO().paginatorUsuPlaAbertoAtivo(input);
    }

    public Map paginatorUsuPlaAbeQuitados(Map input)
    {
        return DaoLocator.getUsuarioPlanoReadDAO().paginatorUsuPlaAbeQuitados(input);
    }

    public Usuario readUsuarioByIdPlano(Long idPlanoUsuario)
    {
        return DaoLocator.getUsuarioPlanoDAO().readUsuarioByIdPlano(idPlanoUsuario);
    }

    public Map paginatorUsuPlaQuiAbe(Map input)
    {
        return DaoLocator.getUsuarioPlanoReadDAO().paginatorUsuPlaAbe(input);
    }

    public Long readIdUltimoPlanoUsuario(Long idUsuario)
    {
        return DaoLocator.getUsuarioPlanoDAO().readIdUltimoPlano(idUsuario);
    }

    public StatusPlano readStatusUltimoPlano(Long idUsuario)
    {
        return DaoLocator.getUsuarioPlanoReadDAO().readStatusUltimoPlano(idUsuario);
    }
}
