// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroCaixaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.pojo.report.RegistroCaixaReport;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            FormaDePagamentoDAO, PagamentoDAO, RegistroContaBancariaDAO, UsuarioReadDAO

public class RegistroCaixaDAO extends BaseDAO
{

    public RegistroCaixaDAO()
    {
    }

    public void create(RegistroCaixa pojo, Long idFluxoCaixaFk)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "INSERT INTO reg_caixas(data_hora, valor, descricao, entrada, retirada, id_formas_pagamento_fk,  id_parcela_fk, id_produto_fk, id_contas_pagar_fk, id_contas_receber_fk,  id_fluxos_caixas_fk, id_reg_contas_bancarias_fk, edit, id_usu_fk_reg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id_reg_caixas";
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
            if(pojo.getParcela() != null)
                pstm.setLong(++i, pojo.getParcela().getId().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setNull(++i, -5);
            pstm.setNull(++i, -5);
            pstm.setNull(++i, -5);
            pstm.setLong(++i, idFluxoCaixaFk.longValue());
            if(pojo.getRegistroContaBancaria() != null)
                pstm.setLong(++i, pojo.getRegistroContaBancaria().getId().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setBoolean(++i, pojo.getEdit().booleanValue());
            if(pojo.getUsuarioRegistrou() != null)
                pstm.setLong(++i, pojo.getUsuarioRegistrou().getId().longValue());
            else
                pstm.setNull(++i, -5);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo.setId(Long.valueOf(rs.getLong("id_reg_caixas")));
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

    public void update(RegistroCaixa pojo, Long idFluxoCaixaFk)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = " UPDATE reg_caixas SET data_hora=?, valor=?, descricao=?, entrada=?,  retirada=?, id_formas_pagamento_fk=?, id_parcela_fk=?, id_produto_fk=?,  id_contas_pagar_fk=?, id_contas_receber_fk=?, id_fluxos_caixas_fk=?,  id_reg_contas_bancarias_fk=?, edit=?, id_usu_fk_reg=? WHERE id_reg_caixas=?;";
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
            if(pojo.getParcela() != null)
                pstm.setLong(++i, pojo.getParcela().getId().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.setNull(++i, -5);
            pstm.setNull(++i, -5);
            pstm.setNull(++i, -5);
            pstm.setLong(++i, idFluxoCaixaFk.longValue());
            if(pojo.getRegistroContaBancaria() != null)
                pstm.setLong(++i, pojo.getRegistroContaBancaria().getId().longValue());
            else
                pstm.setNull(++i, -5);
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

    public void updateRegContaBancariaFk(Long idContaBancariaFk, Long idRegFluxoCaixa)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = " UPDATE reg_caixas SET id_reg_contas_bancarias_fk=? WHERE id_reg_caixas=?;";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setLong(++i, idContaBancariaFk.longValue());
            pstm.setLong(++i, idRegFluxoCaixa.longValue());
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
        return "reg_caixas";
    }
    @Override
    public RegistroCaixa extract(ResultSet rs)
        throws Exception
    {
        RegistroCaixa pojo = null;
        if(rs != null)
        {
            pojo = new RegistroCaixa();
            pojo.setId(rsGetId(rs));
            pojo.setDataHora(CalendarUtil.setTimesTamp(rs.getTimestamp("data_hora")));
            pojo.setEntrada(Boolean.valueOf(rs.getBoolean("entrada")));
            pojo.setRetirada(Boolean.valueOf(rs.getBoolean("retirada")));
            pojo.setDescricao(rs.getString("descricao"));
            pojo.setEdit(Boolean.valueOf(rs.getBoolean("edit")));
            if(rs.getLong("id_formas_pagamento_fk") != 0L)
                pojo.setFormaDePagamento((FormaDePagamento)DaoLocator.getFormaDePagamentoDAO().readById(Long.valueOf(rs.getLong("id_formas_pagamento_fk"))));
            else
                pojo.setFormaDePagamento(new FormaDePagamento(Long.valueOf(1L), "Dinheiro"));
            if(rs.getLong("id_parcela_fk") != 0L)
                pojo.setParcela(DaoLocator.getPagamentoDAO().readById(Long.valueOf(rs.getLong("id_parcela_fk"))));
            if(rs.getLong("id_reg_contas_bancarias_fk") != 0L)
                pojo.setRegistroContaBancaria((RegistroContaBancaria)DaoLocator.getRegistroContaBancariaDAO().readById(Long.valueOf(rs.getLong("id_reg_contas_bancarias_fk"))));
            if(rs.getLong("id_usu_fk_reg") != 0L)
                pojo.setUsuarioRegistrou(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usu_fk_reg"))));
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
        String criterioIdFluxoCaixa = (String)input.get("criterioIdFluxoCaixaFk");
        if(criterioIdFluxoCaixa != null && !criterioIdFluxoCaixa.isEmpty())
            lista.add((new StringBuilder()).append(" id_fluxos_caixas_fk = ").append(criterioIdFluxoCaixa).toString());
        return lista;
    }

    public int getDias(int ano, int mes)
    {
        mes--;
        int meses[] = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 
            30, 31
        };
        if(ano % 400 == 0)
            meses[1] = 29;
        else
        if(ano % 4 == 0 && ano % 100 != 0)
            meses[1] = 29;
        return meses[mes];
    }

    public List readRegistrosByMes(int mes, int ano)
    {
        List lista = new ArrayList();
        int qtdeDias = getDias(ano, mes);
        Connection conn = ConnectionManager.getInstance().getConnection();
        int i = 1;
        RegistroCaixaReport regCxReportAnterior = new RegistroCaixaReport(i, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.0D), Boolean.TRUE);
        while(i <= qtdeDias) 
        {
            RegistroCaixaReport regCxReport = new RegistroCaixaReport(i, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.0D), Boolean.TRUE);
            regCxReport.setSaldoInicial(regCxReportAnterior.getSaldoAcumulado());
            try
            {
                String sql = (new StringBuilder()).append(" select (select sum(valor) from reg_caixas where  DATE_PART('DAY',(data_hora)) = ").append(i).append(" and DATE_PART('MONTH',(data_hora)) =  ").append(mes).append(" and DATE_PART('YEAR',(data_hora)) = ").append(ano).append(" and entrada = true) as valorEntrada,").append(" (select sum(valor) from reg_caixas where DATE_PART('DAY',(data_hora)) = ").append(i).append(" and DATE_PART('MONTH',(data_hora)) =  ").append(mes).append(" and DATE_PART('YEAR',(data_hora)) = ").append(ano).append(" and retirada = true) as valorRetirada").toString();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if(rs.next())
                {
                    regCxReport.setEntrada(Double.valueOf(rs.getDouble("valorEntrada")));
                    regCxReport.setRetirada(Double.valueOf(rs.getDouble("valorRetirada")));
                }
                regCxReport.setSaldoDia(Double.valueOf(regCxReport.getEntrada().doubleValue() - regCxReport.getRetirada().doubleValue()));
                regCxReport.setSaldoAcumulado(Double.valueOf((regCxReport.getSaldoInicial().doubleValue() + regCxReport.getEntrada().doubleValue()) - regCxReport.getRetirada().doubleValue()));
                regCxReport.setAcimaDataAtual(acimaDataAtual(regCxReport.getDia(), mes, ano));
                lista.add(regCxReport);
                regCxReportAnterior = regCxReport;
                i++;
                rs.close();
                stm.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        conn = null;
        return lista;
    }

    public Boolean acimaDataAtual(int dia, int mes, int ano)
    {
        Calendar cal = Calendar.getInstance();
        Calendar atual = Calendar.getInstance();
        cal.set(ano, --mes, dia);
        if(cal.after(atual))
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public Boolean deleteByIdRegContaBancaria(Connection conn, Long id)
    {
        try
        {
            String sql = "delete from reg_caixas where id_reg_caixas = (select id_reg_caixas_fk from reg_contas_bancarias where id_reg_contas_bancarias = ?)";
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
