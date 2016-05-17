// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CaixaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Caixa;
import java.sql.ResultSet;
import java.util.*;

public class CaixaDAO extends BaseDAO
{

    public CaixaDAO()
    {
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Caixa pojo = (Caixa)obj;
        addParametro("nome", pojo.getNome(), 12);
    }
    @Override
    public String getNameTable()
    {
        return "caixas";
    }
    @Override
    public Caixa extract(ResultSet rs)
        throws Exception
    {
        Caixa pojo = null;
        if(rs != null)
        {
            pojo = new Caixa();
            pojo.setId(rsGetId(rs));
            pojo.setNome(rs.getString("nome"));
        }
        return pojo;
    }
    public String getORDER_CAMPO()
    {
        return "nome";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        return lista;
    }
}
