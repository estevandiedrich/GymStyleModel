// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EstadoCivilDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.EstadoCivil;
import java.sql.ResultSet;

public class EstadoCivilDAO extends BaseDAO
{

    public EstadoCivilDAO()
    {
    }
    @Override
    public EstadoCivil extract(ResultSet rs)
        throws Exception
    {
        EstadoCivil pojo = null;
        if(rs != null)
        {
            pojo = new EstadoCivil();
            pojo.setId(rsGetId(rs));
            pojo.setEstadoCivil(rs.getString("nome"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "estados_civis";
    }
}
