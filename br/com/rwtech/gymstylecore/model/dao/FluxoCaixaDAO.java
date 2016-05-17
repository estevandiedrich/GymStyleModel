// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FluxoCaixaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            CaixaDAO, UsuarioReadDAO, RegistroCaixaDAO

public class FluxoCaixaDAO extends BaseDAO
{

    public FluxoCaixaDAO()
    {
    }
    @Override
    public String getNameTable()
    {
        return "fluxos_caixas";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	FluxoCaixa pojo = (FluxoCaixa)obj;
        addParametro("abertura", new Timestamp(pojo.getAbertura().getTimeInMillis()), 93);
        if(pojo.getFechamento() != null)
            addParametro("fechamento", new Timestamp(pojo.getFechamento().getTimeInMillis()), 93);
        else
            addParametro("fechamento", null, 93);
        addParametro("valor_inicial", pojo.getValorInicial(), 8);
        addParametro("valor_final", pojo.getValorFinal(), 8);
        addParametro("vi_cartao", pojo.getViCartao(), 8);
        addParametro("vi_cheque", pojo.getViCheque(), 8);
        addParametro("vi_deposito", pojo.getViDeposito(), 8);
        addParametro("vi_dinheiro", pojo.getViDinheiro(), 8);
        addParametro("vi_boleto", pojo.getViBoleto(), 8);
        addParametro("vf_boleto", pojo.getVfBoleto(), 8);
        addParametro("vf_cartao", pojo.getVfCartao(), 8);
        addParametro("vf_cheque", pojo.getVfCheque(), 8);
        addParametro("vf_dinheiro", pojo.getVfDinheiro(), 8);
        addParametro("vf_deposito", pojo.getVfDeposito(), 8);
        if(pojo.getCaixa() != null)
            addParametro("id_caixa_fk", pojo.getCaixa().getId(), -5);
        else
            addParametro("id_caixa_fk", null, -5);
        if(pojo.getUsuarioAbriu() != null)
            addParametro("id_usu_fk_abriu", pojo.getUsuarioAbriu().getId(), -5);
        else
            addParametro("id_usu_fk_abriu", null, -5);
        if(pojo.getUsuarioFechou() != null)
            addParametro("id_usu_fk_fechou", pojo.getUsuarioFechou().getId(), -5);
        else
            addParametro("id_usu_fk_fechou", null, -5);
    }
    @Override
    public FluxoCaixa extract(ResultSet rs)
        throws Exception
    {
        FluxoCaixa pojo = null;
        if(rs != null)
        {
            pojo = new FluxoCaixa();
            pojo.setId(rsGetId(rs));
            pojo.setAbertura(CalendarUtil.setTimesTamp(rs.getTimestamp("abertura")));
            pojo.setFechamento(CalendarUtil.setTimesTamp(rs.getTimestamp("fechamento")));
            pojo.setValorInicial(Double.valueOf(rs.getDouble("valor_inicial")));
            pojo.setValorFinal(Double.valueOf(rs.getDouble("valor_final")));
            pojo.setViDinheiro(Double.valueOf(rs.getDouble("vi_dinheiro")));
            pojo.setViCheque(Double.valueOf(rs.getDouble("vi_cheque")));
            pojo.setViCartao(Double.valueOf(rs.getDouble("vi_cartao")));
            pojo.setViBoleto(Double.valueOf(rs.getDouble("vi_boleto")));
            pojo.setViDeposito(Double.valueOf(rs.getDouble("vi_deposito")));
            pojo.setVfDinheiro(Double.valueOf(rs.getDouble("vf_dinheiro")));
            pojo.setVfCheque(Double.valueOf(rs.getDouble("vf_cheque")));
            pojo.setVfCartao(Double.valueOf(rs.getDouble("vf_cartao")));
            pojo.setVfBoleto(Double.valueOf(rs.getDouble("vf_boleto")));
            pojo.setVfDeposito(Double.valueOf(rs.getDouble("vf_deposito")));
            pojo.setCaixa((Caixa)DaoLocator.getCaixaDAO().readById(Long.valueOf(rs.getLong("id_caixa_fk"))));
            if(rs.getLong("id_usu_fk_abriu") != 0L)
                pojo.setUsuarioAbriu(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usu_fk_abriu"))));
            if(rs.getLong("id_usu_fk_fechou") != 0L)
                pojo.setUsuarioFechou(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usu_fk_fechou"))));
            pojo.setRegistros(readRegistros(pojo.getId()));
        }
        return pojo;
    }

    public List readRegistros(Long idFluxoCaixa)
    {
        Map map = new HashMap();
        map.put("criterioIdFluxoCaixaFk", idFluxoCaixa.toString());
        map.put("orderBy", "DESC");
        return DaoLocator.getRegistroCaixaDAO().readByCriteria(map);
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioDia = (String)input.get("criterioDia");
        if(criterioDia != null && !criterioDia.isEmpty())
        {
            String data = ConsultaUtil.getDataFormatBD(criterioDia);
            if(data != null)
                lista.add((new StringBuilder()).append("( (abertura >= '").append(data).append(" 00:00:00') and (abertura <= '").append(data).append(" 23:59:59')").append(" or ").append("  (id_fluxos_caixas in ").append("   (select  id_fluxos_caixas_fk from reg_caixas where true ").append("    and (data_hora >= '").append(data).append(" 00:00:00') and (data_hora <= '").append(data).append(" 23:59:59') ").append("    group by id_fluxos_caixas_fk order by id_fluxos_caixas_fk").append("   )").append("  )").append(")").toString());
        }
        String criterioUltimo = (String)input.get("criterioUltimo");
        if(criterioUltimo != null && !criterioUltimo.isEmpty())
            lista.add(" id_fluxos_caixas in (select max(id_fluxos_caixas) from fluxos_caixas)");
        return lista;
    }

    public Long idCaixaAberto()
    {
        Long idFluxoCaixa = Long.valueOf(0L);
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select id_fluxos_caixas, fechamento from fluxos_caixas where true and id_fluxos_caixas = (select max(id_fluxos_caixas) from fluxos_caixas)";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next() && rs.getTimestamp("fechamento") == null)
                idFluxoCaixa = Long.valueOf(rs.getLong("id_fluxos_caixas"));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return idFluxoCaixa;
    }
}
