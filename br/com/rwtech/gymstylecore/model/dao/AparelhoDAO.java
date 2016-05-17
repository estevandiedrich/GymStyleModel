// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AparelhoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Aparelho;
import java.sql.ResultSet;

public class AparelhoDAO extends BaseDAO
{

    public AparelhoDAO()
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
        return "aparelhos";
    }
    @Override
    public Aparelho extract(ResultSet rs)
        throws Exception
    {
        Aparelho pojo = null;
        if(rs != null)
        {
            pojo = new Aparelho();
            pojo.setId(rsGetId(rs));
            pojo.setAparelho(rs.getString("nome"));
            pojo.setDescricao(rs.getString("descricao"));
        }
        return pojo;
    }

}
