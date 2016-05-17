// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DuracaoPlanoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.DuracaoPlano;
import java.sql.ResultSet;

public class DuracaoPlanoDAO extends BaseDAO
{

    public DuracaoPlanoDAO()
    {
    }
    @Override
    public DuracaoPlano extract(ResultSet rs)
        throws Exception
    {
        DuracaoPlano pojo = null;
        if(rs != null)
        {
            pojo = new DuracaoPlano();
            pojo.setId(rsGetId(rs));
            pojo.setDuracao(rs.getString("duracao"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "duracoes_plano";
    }
}
