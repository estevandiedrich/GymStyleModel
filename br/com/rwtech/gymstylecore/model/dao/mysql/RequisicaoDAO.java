// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequisicaoDAO.java

package br.com.rwtech.gymstylecore.model.dao.mysql;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.dao.DispositivoDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioDAO;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequisicaoDAO extends BaseDaoMySQL
{

    public RequisicaoDAO()
    {
    }

    public void update(Requisicao pojo)
    {
        String sql = "UPDATE requisicoes SET parametro=?, status=?, destino=?, referencia=? WHERE id_requisicao=?;";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            if(pojo.getParametro() != null)
                pstm.setLong(++i, ((Long)pojo.getParametro()).longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setBoolean(++i, pojo.getStatus().booleanValue());
            if(pojo.getDestino() != null)
                pstm.setLong(++i, pojo.getDestino().longValue());
            else
                pstm.setNull(++i, -5);
            if(pojo.getReferencia() != null)
                pstm.setLong(++i, ((Long)pojo.getReferencia()).longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setLong(++i, pojo.getId().longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void clearRequisicoesByIdUser(Long idUsuario)
    {
        String sql = "delete from requisicoes WHERE id_operador_fk = ?;";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, idUsuario.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void reenviarRequisicao(Long idRequisicao)
    {
        String sql = "UPDATE requisicoes SET status = false WHERE id_requisicao = ?";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, idRequisicao.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void buscarDispositivos(Requisicao requisicao)
    {
        String sql = "INSERT INTO REQUISICOES (data_hora, status, id_operador_fk, tipo_requisicao_fk) VALUES (?,?,?,?)";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
            pstm.setBoolean(++i, false);
            pstm.setLong(++i, requisicao.getOperador().getId().longValue());
            pstm.setLong(++i, requisicao.getTipo().ordinal());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DispositivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarStatusDispositivo(Requisicao requisicao)
    {
        String sql = "INSERT INTO REQUISICOES (data_hora, status, id_operador_fk, tipo_requisicao_fk, destino,parametro) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
            pstm.setBoolean(++i, requisicao.getStatus().booleanValue());
            pstm.setLong(++i, requisicao.getOperador().getId().longValue());
            pstm.setLong(++i, requisicao.getTipo().ordinal());
            pstm.setLong(++i, requisicao.getDestino().longValue());
            pstm.setLong(++i, ((Long)requisicao.getParametro()).longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DispositivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cadastrarDigital(Requisicao requisicao)
    {
        String sql = "INSERT INTO REQUISICOES (data_hora, parametro, referencia, status, id_operador_fk, tipo_requisicao_fk, destino) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
            pstm.setLong(++i, ((Long)requisicao.getParametro()).longValue());
            pstm.setLong(++i, ((Long)requisicao.getReferencia()).longValue());
            pstm.setBoolean(++i, requisicao.getStatus().booleanValue());
            pstm.setLong(++i, requisicao.getOperador().getId().longValue());
            pstm.setLong(++i, requisicao.getTipo().ordinal());
            pstm.setLong(++i, requisicao.getDestino().longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DispositivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Requisicao create(Requisicao requisicao)
    {
        String sql = "INSERT INTO REQUISICOES (data_hora, parametro, referencia, status, id_operador_fk, tipo_requisicao_fk, destino) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstm = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
            try
            {
                pstm.setLong(++i, ((Long)requisicao.getParametro()).longValue());
            }
            catch(Exception e)
            {
                pstm.setNull(i, -5);
            }
            try
            {
                pstm.setLong(++i, ((Long)requisicao.getReferencia()).longValue());
            }
            catch(Exception e)
            {
                pstm.setNull(i, -5);
            }
            pstm.setBoolean(++i, requisicao.getStatus().booleanValue());
            try
            {
                pstm.setLong(++i, requisicao.getOperador().getId().longValue());
            }
            catch(Exception e)
            {
                pstm.setNull(i, -5);
            }
            try
            {
                pstm.setLong(++i, requisicao.getTipo().ordinal());
            }
            catch(Exception e)
            {
                pstm.setNull(i, -5);
            }
            try
            {
                pstm.setLong(++i, requisicao.getDestino().longValue());
            }
            catch(Exception e)
            {
                pstm.setNull(i, -5);
            }
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                requisicao.setId(Long.valueOf(rs.getLong("id_requisicao")));
            pstm.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return requisicao;
    }

    public List readByReferencia(Long idReferencia)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from requisicoes where referencia = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idReferencia.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs != null)
                for(; rs.next(); lista.add(extract(rs)));
            rs.close();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }

    public Requisicao readById(Long id)
    {
        Requisicao pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from requisicoes where id_requisicao = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs != null)
                while(rs.next()) 
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

    public Requisicao extract(ResultSet rs)
        throws Exception
    {
        Requisicao pojo = null;
        if(rs != null)
        {
            pojo = new Requisicao();
            pojo.setId(Long.valueOf(rs.getLong("id_requisicao")));
            pojo.setDataHora(CalendarUtil.setDateCalendar(rs.getDate("data_hora")));
            pojo.setDestino(Long.valueOf(rs.getLong("destino")));
            pojo.setOperador((Usuario)DaoLocator.getUsuarioDAO().readById(Long.valueOf(rs.getLong("id_operador_fk"))));
            pojo.setParametro(Long.valueOf(rs.getLong("parametro")));
            pojo.setReferencia(Long.valueOf(rs.getLong("referencia")));
            pojo.setStatus(Boolean.valueOf(rs.getBoolean("status")));
            pojo.setTipo(Requisicao.getTipo(Integer.valueOf(rs.getInt("tipo_requisicao_fk"))));
        }
        return pojo;
    }

    public String getNameTable()
    {
        return "requisicoes";
    }
}
