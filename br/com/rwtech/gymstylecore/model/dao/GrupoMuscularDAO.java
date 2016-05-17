// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GrupoMuscularDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.GrupoMuscular;
import java.sql.ResultSet;

public class GrupoMuscularDAO extends BaseDAO
{

    public GrupoMuscularDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "grupos_musculares";
    }
    @Override
    public GrupoMuscular extract(ResultSet rs)
        throws Exception
    {
        GrupoMuscular pojo = null;
        if(rs != null)
        {
            pojo = new GrupoMuscular();
            pojo.setId(rsGetId(rs));
            pojo.setGrupoMuscular(rs.getString("nome"));
        }
        return pojo;
    }
}
