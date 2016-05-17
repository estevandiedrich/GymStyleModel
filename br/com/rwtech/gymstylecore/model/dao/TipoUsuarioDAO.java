// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TipoUsuarioDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import java.sql.ResultSet;

public class TipoUsuarioDAO extends BaseDAO
{

    public TipoUsuarioDAO()
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
        return "tipos_usuarios";
    }
    @Override
    public TipoUsuario extract(ResultSet rs)
        throws Exception
    {
        TipoUsuario pojo = null;
        if(rs != null)
        {
            pojo = new TipoUsuario();
            pojo.setId(rsGetId(rs));
            pojo.setTipoUsuario(rs.getString("nome"));
        }
        return pojo;
    }
}
