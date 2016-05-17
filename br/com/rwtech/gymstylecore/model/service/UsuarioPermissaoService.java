// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioPermissaoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPermissaoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import java.util.List;
import java.util.Set;

public class UsuarioPermissaoService
{

    public UsuarioPermissaoService()
    {
    }

    public void drop()
    {
        DaoLocator.getUsuarioPermissaoDAO().drop();
    }

    public void create(List lista, Long id)
    {
        DaoLocator.getUsuarioPermissaoDAO().create(lista, id);
    }

    public void update(Permissao pojo)
    {
        DaoLocator.getUsuarioPermissaoDAO().update(pojo);
    }
    
    public List readByCriteria(Boolean ativo, String grupo)
    {
        return DaoLocator.getUsuarioPermissaoDAO().readByCriteria(ativo, grupo);
    }

    public void deleteByIdUsuario(Long id)
    {
        DaoLocator.getUsuarioPermissaoDAO().deleteByIdUsuario(id);
    }

    public List readByIdUsuario(Long id)
    {
        List list = DaoLocator.getUsuarioPermissaoDAO().readByIdUsuario(id);
        return list;
    }

    public Set readDescricaoByGrupoCadastro()
    {
        return DaoLocator.getUsuarioPermissaoDAO().readDescricaoByGrupoCadastro();
    }

    public Permissao readByName(String per)
    {
        return DaoLocator.getUsuarioPermissaoDAO().readByName(per);
    }
}
