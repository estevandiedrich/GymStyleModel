// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProtocoloDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Protocolo;
import java.sql.ResultSet;

public class ProtocoloDAO extends BaseDAO
{

    public ProtocoloDAO()
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
        return "protocolos";
    }
    @Override
    public Protocolo extract(ResultSet rs)
        throws Exception
    {
        Protocolo pojo = null;
        if(rs != null)
        {
            pojo = new Protocolo();
            pojo.setId(rsGetId(rs));
            pojo.setProtocolo(rs.getString("nome"));
        }
        return pojo;
    }
}
