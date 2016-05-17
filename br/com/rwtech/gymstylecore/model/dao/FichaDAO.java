// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FichaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            TreinoDAO, UsuarioReadDAO

public class FichaDAO extends BaseDAO
{

    public FichaDAO()
    {
    }

    public void create(Long idUsuario, Ficha pojo)
    {
        Connection conn = null;
        try
        {
            System.out.println((new StringBuilder()).append("ID DO USUARIO AO SALVAR FICHA: ").append(idUsuario).toString());
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            int i = 0;
            String sql = "UPDATE usuarios_fichas SET ativa = false WHERE id_usuarios_fk = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(++i, idUsuario.longValue());
            pstm.execute();
            sql = "INSERT INTO usuarios_fichas(periodo_inicial, periodo_final, descricao, ativa, id_instrutor_fk, id_usuarios_fk)  VALUES (?, ?, ?, ?, ?, ?) returning id_usuarios_fichas;";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getPeriodoInicial()));
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getPeriodoFinal()));
            pstm.setString(++i, pojo.getDescricao());
            pstm.setBoolean(++i, pojo.getAtiva().booleanValue());
            pstm.setLong(++i, pojo.getInstrutor().getId().longValue());
            pstm.setLong(++i, idUsuario.longValue());
            ResultSet rs = pstm.executeQuery();
            Long idFicha = null;
            if(rs.next())
            {
                idFicha = Long.valueOf(rs.getLong("id_usuarios_fichas"));
                pojo.setId(idFicha);
            }
            pstm.close();
            if(idFicha != null)
            {
                Treino treino;
                for(Iterator i$ = pojo.getTreinos().iterator(); i$.hasNext(); DaoLocator.getTreinoDAO().create(conn, treino, idFicha))
                    treino = (Treino)i$.next();

            }
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
        }
    }

    public void update(Long idUsuario, Ficha pojo)
    {
        Connection conn = null;
        try
        {
            System.out.println((new StringBuilder()).append("ID DO USUARIO AO ALTERAR FICHA: ").append(idUsuario).toString());
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "UPDATE usuarios_fichas SET ativa = false WHERE id_usuarios_fk = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(++i, idUsuario.longValue());
            pstm.execute();
            sql = "UPDATE usuarios_fichas SET periodo_inicial=?, periodo_final=?,  descricao=?, id_instrutor_fk=?, id_usuarios_fk=?, ativa=? WHERE id_usuarios_fichas=?;";
            pstm = conn.prepareStatement(sql);
            i = 0;
            Long idFicha = pojo.getId();
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getPeriodoInicial()));
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getPeriodoFinal()));
            pstm.setString(++i, pojo.getDescricao());
            pstm.setLong(++i, pojo.getInstrutor().getId().longValue());
            pstm.setLong(++i, idUsuario.longValue());
            pstm.setBoolean(++i, pojo.getAtiva().booleanValue());
            pstm.setLong(++i, idFicha.longValue());
            pstm.execute();
            pstm.close();
            if(idFicha != null)
            {
                DaoLocator.getTreinoDAO().delete(conn, idFicha);
                Treino treino;
                for(Iterator i$ = pojo.getTreinos().iterator(); i$.hasNext(); DaoLocator.getTreinoDAO().create(conn, treino, idFicha))
                    treino = (Treino)i$.next();

            }
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
        }
    }

    public void updateStatusAtiva(Long idUsuario, Long idFicha, Boolean status)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = null;
            String sql = "";
            int i = 0;
            if(status.booleanValue())
            {
                sql = "UPDATE usuarios_fichas SET ativa = false WHERE id_usuarios_fk = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setLong(++i, idUsuario.longValue());
                pstm.execute();
            }
            i = 0;
            sql = "UPDATE usuarios_fichas set ativa = ? WHERE id_usuarios_fichas = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setBoolean(++i, status.booleanValue());
            pstm.setLong(++i, idFicha.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public Ficha extract(ResultSet rs)
        throws Exception
    {
        Ficha pojo = null;
        if(rs != null)
        {
            pojo = new Ficha();
            pojo.setId(rsGetId(rs));
            pojo.setInstrutor(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_instrutor_fk"))));
            pojo.setData(CalendarUtil.setTimesTamp(rs.getTimestamp("data")));
            pojo.setPeriodoInicial(CalendarUtil.setDateCalendar(rs.getDate("periodo_inicial")));
            pojo.setPeriodoFinal(CalendarUtil.setDateCalendar(rs.getDate("periodo_final")));
            pojo.setDescricao(rs.getString("descricao"));
            pojo.setAtiva(Boolean.valueOf(rs.getBoolean("ativa")));
            pojo.setTreinos(DaoLocator.getTreinoDAO().readByIdFicha(pojo.getId(), null));
        }
        return pojo;
    }

    public List readFichasByIdUsuario(Long idUsuario)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from usuarios_fichas where id_usuarios_fk = ").append(idUsuario).toString();
            sql = (new StringBuilder()).append(sql).append(" order by id_usuarios_fichas DESC").toString();
            Statement stm = conn.createStatement();
            ResultSet rs;
            Ficha pojo;
            for(rs = stm.executeQuery(sql); rs.next(); lista.add(pojo))
            {
                pojo = new Ficha();
                pojo.setId(Long.valueOf(rs.getLong("id_usuarios_fichas")));
                pojo.setAtiva(Boolean.valueOf(rs.getBoolean("ativa")));
                pojo.setData(CalendarUtil.setTimesTamp(rs.getTimestamp("data")));
                pojo.setInstrutor(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_instrutor_fk"))));
                pojo.setPeriodoInicial(CalendarUtil.setDateCalendar(rs.getDate("periodo_inicial")));
                pojo.setPeriodoFinal(CalendarUtil.setDateCalendar(rs.getDate("periodo_final")));
                pojo.setTreinos(DaoLocator.getTreinoDAO().readByIdFicha(pojo.getId(), null));
            }

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

    public Ficha readUltimaFichaUsuario(Long idUsuario, Integer diaSemana)
    {
        Ficha pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from usuarios_fichas where id_usuarios_fichas = (select max(id_usuarios_fichas) from usuarios_fichas where id_usuarios_fk = ").append(idUsuario).append(")").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
            {
                pojo = new Ficha();
                pojo.setId(Long.valueOf(rs.getLong("id_usuarios_fichas")));
                pojo.setData(CalendarUtil.setTimesTamp(rs.getTimestamp("data")));
                pojo.setAtiva(Boolean.valueOf(rs.getBoolean("ativa")));
                pojo.setInstrutor(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usuarios_fichas"))));
                pojo.setPeriodoInicial(CalendarUtil.setDateCalendar(rs.getDate("periodo_inicial")));
                pojo.setPeriodoFinal(CalendarUtil.setDateCalendar(rs.getDate("periodo_final")));
                pojo.setTreinos(DaoLocator.getTreinoDAO().readByIdFicha(pojo.getId(), diaSemana));
            }
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
    @Override
    public String getNameTable()
    {
        return "usuarios_fichas";
    }
}
