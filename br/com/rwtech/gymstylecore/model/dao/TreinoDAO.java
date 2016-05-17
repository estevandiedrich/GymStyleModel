// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreinoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            SerieDAO

public class TreinoDAO extends BaseDAO
{

    public TreinoDAO()
    {
    }

    public void create(Connection conn, Treino pojo, Long idFicha)
    {
        try
        {
            String sql = "INSERT INTO treinos(nome, treino_domingo, treino_segunda, treino_terca, treino_quarta, treino_quinta, treino_sexta, treino_sabado, id_fichas_fk)    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) returning id_treinos;";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setString(++i, pojo.getNome());
            pstm.setBoolean(++i, pojo.getTreinaDomingo().booleanValue());
            pstm.setBoolean(++i, pojo.getTreinaSegunda().booleanValue());
            pstm.setBoolean(++i, pojo.getTreinaTerca().booleanValue());
            pstm.setBoolean(++i, pojo.getTreinaQuarta().booleanValue());
            pstm.setBoolean(++i, pojo.getTreinaQuinta().booleanValue());
            pstm.setBoolean(++i, pojo.getTreinaSexta().booleanValue());
            pstm.setBoolean(++i, pojo.getTreinaSabado().booleanValue());
            pstm.setLong(++i, idFicha.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo.setId(Long.valueOf(rs.getLong("id_treinos")));
            if(pojo.getId() != null)
            {
                Serie serie;
                for(Iterator i$ = pojo.getSeries().iterator(); i$.hasNext(); DaoLocator.getSerieDAO().create(conn, serie, pojo.getId()))
                    serie = (Serie)i$.next();

            }
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public Boolean delete(Connection conn, Long idFicha)
    {
        try
        {
            String sql = "DELETE FROM treinos WHERE id_fichas_fk = ?;";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, idFicha.longValue());
            pstm.execute();
            pstm.close();
            return Boolean.valueOf(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return Boolean.valueOf(false);
    }
    @Override
    public Treino extract(ResultSet rs)
        throws Exception
    {
        Treino pojo = null;
        if(rs != null)
        {
            pojo = new Treino();
            pojo.setId(rsGetId(rs));
            pojo.setTreinaDomingo(Boolean.valueOf(rs.getBoolean("treino_domingo")));
            pojo.setTreinaSegunda(Boolean.valueOf(rs.getBoolean("treino_segunda")));
            pojo.setTreinaTerca(Boolean.valueOf(rs.getBoolean("treino_terca")));
            pojo.setTreinaQuarta(Boolean.valueOf(rs.getBoolean("treino_quarta")));
            pojo.setTreinaQuinta(Boolean.valueOf(rs.getBoolean("treino_quinta")));
            pojo.setTreinaSexta(Boolean.valueOf(rs.getBoolean("treino_sexta")));
            pojo.setTreinaSabado(Boolean.valueOf(rs.getBoolean("treino_sabado")));
            pojo.setNome(rs.getString("nome"));
            pojo.setSeries(DaoLocator.getSerieDAO().readByIdTreino(pojo.getId()));
        }
        return pojo;
    }

    public List readByIdFicha(Long idFicha, Integer dia)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from treinos as t  where true  and id_fichas_fk = ").append(idFicha).toString();
            if(dia != null && dia.intValue() > 0)
                switch(dia.intValue())
                {
                case 1: // '\001'
                    sql = (new StringBuilder()).append(sql).append(" and treino_domingo = true").toString();
                    break;

                case 2: // '\002'
                    sql = (new StringBuilder()).append(sql).append(" and treino_segunda = true").toString();
                    break;

                case 3: // '\003'
                    sql = (new StringBuilder()).append(sql).append(" and treino_terca = true").toString();
                    break;

                case 4: // '\004'
                    sql = (new StringBuilder()).append(sql).append(" and treino_quarta = true").toString();
                    break;

                case 5: // '\005'
                    sql = (new StringBuilder()).append(sql).append(" and treino_quinta = true").toString();
                    break;

                case 6: // '\006'
                    sql = (new StringBuilder()).append(sql).append(" and treino_sexta = true").toString();
                    break;

                case 7: // '\007'
                    sql = (new StringBuilder()).append(sql).append(" and treino_sabado = true").toString();
                    break;
                }
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
        return "treinos";
    }
}
