// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModoOperacaoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.ModoOperacao;
import java.sql.ResultSet;

public class ModoOperacaoDAO extends BaseDAO
{

    public ModoOperacaoDAO()
    {
    }
    @Override
    public ModoOperacao extract(ResultSet rs)
        throws Exception
    {
        ModoOperacao pojo = null;
        if(rs != null)
        {
            pojo = new ModoOperacao();
            pojo.setId(rsGetId(rs));
            pojo.setModoOperacao(rs.getString("nome"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "modos_operacao";
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }

}
