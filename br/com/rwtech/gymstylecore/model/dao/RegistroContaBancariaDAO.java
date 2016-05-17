// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroContaBancariaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            FormaDePagamentoDAO, ContaBancariaDAO, UsuarioReadDAO

public class RegistroContaBancariaDAO extends BaseDAO
{

    public RegistroContaBancariaDAO()
    {
    }

    public void create(RegistroContaBancaria pojo, Long idContaBancariaFk)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "INSERT INTO reg_contas_bancarias(data_hora, valor, descricao, entrada, retirada, id_formas_pagamento_fk, id_reg_caixas_fk, id_contas_bancarias_fk, edit,id_usu_fk_reg ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id_reg_contas_bancarias";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setTimestamp(++i, new Timestamp(pojo.getDataHora().getTimeInMillis()));
            pstm.setDouble(++i, pojo.getValor().doubleValue());
            pstm.setString(++i, pojo.getDescricao());
            pstm.setBoolean(++i, pojo.getEntrada().booleanValue());
            pstm.setBoolean(++i, pojo.getRetirada().booleanValue());
            if(pojo.getFormaDePagamento() != null)
                pstm.setLong(++i, pojo.getFormaDePagamento().getId().longValue());
            else
                pstm.setNull(++i, -5);
            if(pojo.getRegistroCaixa() != null)
                pstm.setLong(++i, pojo.getRegistroCaixa().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setLong(++i, idContaBancariaFk.longValue());
            pstm.setBoolean(++i, pojo.getEdit().booleanValue());
            if(pojo.getUsuarioRegistrou() != null)
                pstm.setLong(++i, pojo.getUsuarioRegistrou().getId().longValue());
            else
                pstm.setNull(++i, -5);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo.setId(rsGetId(rs));
            pstm.close();
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(Exception ex)
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

    public void update(RegistroContaBancaria pojo, Long idContaBancariaFk)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "UPDATE reg_contas_bancarias SET data_hora = ?, valor = ?, descricao = ?, entrada = ?, retirada = ?, id_formas_pagamento_fk=?, id_reg_caixas_fk=?, id_contas_bancarias_fk=?, edit=?, id_usu_fk_reg=? WHERE id_reg_contas_bancarias=?";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setTimestamp(++i, new Timestamp(pojo.getDataHora().getTimeInMillis()));
            pstm.setDouble(++i, pojo.getValor().doubleValue());
            pstm.setString(++i, pojo.getDescricao());
            pstm.setBoolean(++i, pojo.getEntrada().booleanValue());
            pstm.setBoolean(++i, pojo.getRetirada().booleanValue());
            if(pojo.getFormaDePagamento() != null)
                pstm.setLong(++i, pojo.getFormaDePagamento().getId().longValue());
            else
                pstm.setNull(++i, -5);
            if(pojo.getRegistroCaixa() != null && pojo.getRegistroCaixa().longValue() != 0L)
                pstm.setLong(++i, pojo.getRegistroCaixa().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setLong(++i, idContaBancariaFk.longValue());
            pstm.setBoolean(++i, pojo.getEdit().booleanValue());
            if(pojo.getUsuarioRegistrou() != null)
                pstm.setLong(++i, pojo.getUsuarioRegistrou().getId().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setLong(++i, pojo.getId().longValue());
            pstm.execute();
            pstm.close();
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

    public void updateRegContaBancariaFk(Long idRegContaBancaria, Long idRegFluxoCaixaFk)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = " UPDATE reg_contas_bancarias SET id_reg_caixas_fk=? WHERE id_reg_contas_bancarias = ?;";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setLong(++i, idRegFluxoCaixaFk.longValue());
            pstm.setLong(++i, idRegContaBancaria.longValue());
            pstm.execute();
            pstm.close();
            conn.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public String getNameTable()
    {
        return "reg_contas_bancarias";
    }
    @Override
    public RegistroContaBancaria extract(ResultSet rs)
        throws Exception
    {
        RegistroContaBancaria pojo = null;
        if(rs != null)
        {
            pojo = new RegistroContaBancaria();
            pojo.setId(rsGetId(rs));
            pojo.setDataHora(CalendarUtil.setTimesTamp(rs.getTimestamp("data_hora")));
            pojo.setEntrada(Boolean.valueOf(rs.getBoolean("entrada")));
            pojo.setRetirada(Boolean.valueOf(rs.getBoolean("retirada")));
            pojo.setEdit(Boolean.valueOf(rs.getBoolean("edit")));
            pojo.setDescricao(rs.getString("descricao"));
            if(rs.getLong("id_formas_pagamento_fk") != 0L)
                pojo.setFormaDePagamento((FormaDePagamento)DaoLocator.getFormaDePagamentoDAO().readById(Long.valueOf(rs.getLong("id_formas_pagamento_fk"))));
            if(rs.getLong("id_contas_bancarias_fk") != 0L)
                pojo.setContaBancaria((ContaBancaria)DaoLocator.getContaBancariaDAO().readById(Long.valueOf(rs.getLong("id_contas_bancarias_fk"))));
            if(rs.getLong("id_usu_fk_reg") != 0L)
                pojo.setUsuarioRegistrou(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usu_fk_reg"))));
            pojo.setRegistroCaixa(Long.valueOf(rs.getLong("id_reg_caixas_fk")));
            pojo.setValor(Double.valueOf(rs.getDouble("valor")));
        }
        return pojo;
    }

    public String getORDER_CAMPO()
    {
        return "data_hora";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioConta = (String)input.get("criterioConta");
        if(criterioConta != null && !criterioConta.isEmpty())
            lista.add((new StringBuilder()).append("id_contas_bancarias_fk = ").append(criterioConta).toString());
        String criterioInicio = (String)input.get("criterioInicio");
        if(criterioInicio != null && !criterioInicio.isEmpty())
        {
            String data = ConsultaUtil.getDataFormatBD(criterioInicio);
            if(data != null)
                lista.add((new StringBuilder()).append("data_hora >= '").append(data).append(" 00:00:00'").toString());
        }
        String criterioFim = (String)input.get("criterioFim");
        if(criterioFim != null && !criterioFim.isEmpty())
        {
            String data = ConsultaUtil.getDataFormatBD(criterioFim);
            if(data != null)
                lista.add((new StringBuilder()).append("data_hora <= '").append(data).append(" 23:59:59'").toString());
        }
        return lista;
    }

    public Double getValorTotalContaBancaria(Long idContaBancaria)
    {
        Double total = new Double(0.0D);
        String sql = (new StringBuilder()).append("select (select sum(valor) as total from reg_contas_bancarias where true and (entrada = true) and (id_contas_bancarias_fk = ").append(idContaBancaria).append(")) as entrada,").append("(select sum(valor) as total from reg_contas_bancarias where true and (retirada = true) and (id_contas_bancarias_fk = ").append(idContaBancaria).append("))as retirada").toString();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
            {
                Double entrada = Double.valueOf(rs.getDouble("entrada"));
                Double retirada = Double.valueOf(rs.getDouble("retirada"));
                total = Double.valueOf(entrada.doubleValue() - retirada.doubleValue());
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(RegistroContaBancariaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public Double readValorPeriodoContaBancaria(Map input)
    {
        Double total = new Double(0.0D);
        String filtros = "";
        for(Iterator i$ = getFiltros(input).iterator(); i$.hasNext();)
        {
            String aux = (String)i$.next();
            filtros = (new StringBuilder()).append(filtros).append(" and (").append(aux).append(")").toString();
        }

        String sql = (new StringBuilder()).append("select  (select sum(valor) as total from reg_contas_bancarias where true and (entrada = true)").append(filtros).append(") as entrada,").append("(select sum(valor) as total from reg_contas_bancarias where true and (retirada = true)").append(filtros).append(")as retirada").toString();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
            {
                Double entrada = Double.valueOf(rs.getDouble("entrada"));
                Double retirada = Double.valueOf(rs.getDouble("retirada"));
                total = Double.valueOf(entrada.doubleValue() - retirada.doubleValue());
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(RegistroContaBancariaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public Boolean deleteByIdRegFluxoCaixa(Connection conn, Long id)
    {
        try
        {
            String sql = "delete from reg_contas_bancarias where id_reg_contas_bancarias = (select  id_reg_contas_bancarias_fk from reg_caixas where id_reg_caixas = ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
}
