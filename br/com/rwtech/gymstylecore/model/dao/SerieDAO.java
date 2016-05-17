// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SerieDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            ExercicioDAO

public class SerieDAO extends BaseDAO
{

    public SerieDAO()
    {
    }

    public void create(Connection conn, Serie pojo, Long idTreino)
    {
        try
        {
            String sql = "INSERT INTO series(series, repeticoes, carga, ordem, id_treinos_fk, id_exercicios_fk) VALUES (?, ?, ?, ?, ?, ?) returning id_series;";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setString(++i, pojo.getSerie());
            pstm.setString(++i, pojo.getRepeticao());
            pstm.setString(++i, pojo.getCarga());
            pstm.setInt(++i, pojo.getOrdem().intValue());
            pstm.setLong(++i, idTreino.longValue());
            pstm.setLong(++i, pojo.getExercicio().getId().longValue());
            pstm.execute();
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public Serie extract(ResultSet rs)
        throws Exception
    {
        Serie pojo = null;
        if(rs != null)
        {
            pojo = new Serie();
            pojo.setId(rsGetId(rs));
            pojo.setCarga(rs.getString("carga"));
            pojo.setRepeticao(rs.getString("repeticoes"));
            pojo.setSerie(rs.getString("series"));
            pojo.setOrdem(Integer.valueOf(rs.getInt("ordem")));
            pojo.setExercicio((Exercicio)DaoLocator.getExercicioDAO().readById(Long.valueOf(rs.getLong("id_exercicios_fk"))));
        }
        return pojo;
    }

    public List readByIdTreino(Long idTreino)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from series as s  where true and id_treinos_fk = ").append(idTreino).append(" order by ordem").toString();
            Statement stm = conn.createStatement();
            ResultSet rs;
            for(rs = stm.executeQuery(sql); rs.next(); lista.add(extract(rs)));
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }
    @Override
    public String getNameTable()
    {
        return "series";
    }
}
