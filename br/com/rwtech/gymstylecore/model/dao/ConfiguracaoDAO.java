// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfiguracaoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.pojo.Configuracao;
import java.sql.*;

public class ConfiguracaoDAO extends BaseDAO
{

    public ConfiguracaoDAO()
    {
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Configuracao pojo = (Configuracao)obj;
        addParametro("valor", pojo.getValor(), 12);
        addParametro("campo", pojo.getCampo(), 12);
        addParametro("descricao", pojo.getDescricao(), 12);
    }
    @Override
    public Configuracao extract(ResultSet rs)
        throws Exception
    {
        Configuracao pojo = null;
        if(rs != null)
        {
            pojo = new Configuracao();
            pojo.setId(rsGetId(rs));
            pojo.setCampo(rs.getString("campo"));
            pojo.setDescricao(rs.getString("descricao"));
            pojo.setValor(rs.getString("valor"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "configuracoes";
    }

    public Configuracao readByCampo(String campo)
    {
        Configuracao pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where campo = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, campo);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo = extract(rs);
            rs.close();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojo;
    }
}
