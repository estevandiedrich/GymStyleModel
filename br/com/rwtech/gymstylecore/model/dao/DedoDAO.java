// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DedoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Dedo;
import java.sql.ResultSet;

public class DedoDAO extends BaseDAO
{

    public DedoDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "dedo";
    }
    @Override
    public Dedo extract(ResultSet rs)
        throws Exception
    {
        Dedo pojo = null;
        if(rs != null)
        {
            pojo = new Dedo();
            pojo.setId(rsGetId(rs));
            pojo.setDedo(rs.getString("dedo"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "dedos";
    }
}
