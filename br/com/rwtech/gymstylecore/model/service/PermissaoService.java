// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PermissaoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.PermissaoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import java.util.*;

public class PermissaoService
{

    public PermissaoService()
    {
    }

    public void create(List lista)
    {
        DaoLocator.getPermissaoDAO().create(lista);
    }

    public void update(Permissao permissao)
    {
        DaoLocator.getPermissaoDAO().update(permissao);
    }
    
    public List<Permissao> readbyCriteria(Boolean ativo, String grupo)
    {
        return DaoLocator.getPermissaoDAO().readbyCriteria(ativo, grupo);
    }

    public Map readPermissaoAgrupado(Boolean ativo, String grupo)
    {
        List list = DaoLocator.getPermissaoDAO().readbyCriteria(ativo, grupo);
        Map map = new LinkedHashMap();
        Permissao per;
        for(Iterator i$ = list.iterator(); i$.hasNext(); map.put(per.getDescricao(), per))
            per = (Permissao)i$.next();

        return map;
    }

    public List<Permissao> readbyCriteria()
    {
        return DaoLocator.getPermissaoDAO().readbyCriteria(null, null);
    }
    
    public List<Permissao> readbyCriteria(Boolean ativo)
    {
        return DaoLocator.getPermissaoDAO().readbyCriteria(ativo, null);
    }

    public Permissao readByName(String name)
    {
        return DaoLocator.getPermissaoDAO().readByName(name);
    }

    public Boolean ativo(String name)
    {
        Permissao per = DaoLocator.getPermissaoDAO().readByName(name);
        if(per != null)
            return per.getAtivo();
        else
            return Boolean.valueOf(false);
    }

    public List<Permissao> readDescricaoByGrupoCadastro()
    {
        return DaoLocator.getPermissaoDAO().readDescricaoByGrupoCadastro();
    }
}
