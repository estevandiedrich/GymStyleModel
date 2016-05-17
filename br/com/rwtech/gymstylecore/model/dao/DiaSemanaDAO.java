// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DiaSemanaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.pojo.DiaSemana;
import br.com.rwtech.gymstylecore.model.pojo.tipos.DiaDaSemana;
import java.sql.*;

public class DiaSemanaDAO extends BaseDAO
{

    public DiaSemanaDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public DiaSemana extract(ResultSet rs)
        throws Exception
    {
        DiaSemana pojo = null;
        if(rs != null)
        {
            pojo = new DiaSemana();
            pojo.setId(rsGetId(rs));
            pojo.setDiaSemana(rs.getString("nome"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "dias_semana";
    }

    public DiaSemana readByName(String dia)
    {
        String nome = dia;
        if(dia.equalsIgnoreCase(DiaDaSemana.TERCA.toString().toLowerCase()))
            nome = "Ter\347a";
        else
        if(dia.equalsIgnoreCase(DiaDaSemana.SABADO.toString().toLowerCase()))
            nome = "S\341bado";
        DiaSemana pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where nome ilike '%").append(nome).append("%'").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
                pojo = extract(rs);
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojo;
    }
}
