// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MotivoBloqueioDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.MotivoBloqueio;
import java.sql.ResultSet;

public class MotivoBloqueioDAO extends BaseDAO
{

    public MotivoBloqueioDAO()
    {
    }
    @Override
    public MotivoBloqueio extract(ResultSet rs)
        throws Exception
    {
        MotivoBloqueio pojo = null;
        if(rs != null)
        {
            pojo = new MotivoBloqueio();
            pojo.setId(rsGetId(rs));
            pojo.setMotivo(rs.getString("motivo"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "motivos_bloqueio";
    }
}
