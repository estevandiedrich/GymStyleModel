// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedeSocialDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.RedeSocial;
import java.sql.ResultSet;

public class RedeSocialDAO extends BaseDAO
{

    public RedeSocialDAO()
    {
    }
    @Override
    public RedeSocial extract(ResultSet rs)
        throws Exception
    {
        RedeSocial pojo = null;
        if(rs != null)
        {
            pojo = new RedeSocial();
            pojo.setId(rsGetId(rs));
            pojo.setRedeSocial(rs.getString("nome"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "redes_sociais";
    }
}
