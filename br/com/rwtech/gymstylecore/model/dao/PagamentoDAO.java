// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PagamentoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            ConfiguracaoDAO, UsuarioReadDAO, FormaDePagamentoDAO, UsuarioDAO

public class PagamentoDAO
{

    public PagamentoDAO()
    {
    }

    public void createNewPagamento(Connection conn, Long idUltimaParcela)
    {
        String sql = null;
        sql = "select p.id_pagamentos,p.vencimento,date_part('days',p.vencimento) as dia_vencimento from pagamentos as p where p.id_planos_usuarios_fk = (select id_planos_usuarios_fk from pagamentos where id_pagamentos = ?) order by id_pagamentos limit 2";
        int diaVencimento = 0;
        try
        {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUltimaParcela.longValue());
            ResultSet rs = pstm.executeQuery();
            int i;
            for(i = 0; rs.next(); i++)
                diaVencimento = rs.getInt("dia_vencimento");

            if(i != 2)
                diaVencimento = DaoLocator.getConfiguracaoDAO().readByCampo("diaVencimento").getValorInteiro();
            Pagamento pojo = null;
            Long idPlanosUsuarios = null;
            sql = " select p.*,pl.valor_total from pagamentos as p  left join planos_usuarios as pu  on pu.id_planos_usuarios = p.id_planos_usuarios_fk  left join planos as pl on pl.id_planos = pu.id_planos_fk where true and id_planos_usuarios_fk in (select id_planos_usuarios_fk from pagamentos where id_pagamentos = ?)  order by vencimento desc limit 1";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUltimaParcela.longValue());
            rs = pstm.executeQuery();
            if(rs.next())
            {
                pojo = new Pagamento();
                pojo.setId(Long.valueOf(rs.getLong("id_pagamentos")));
                pojo.setInicioAcesso(CalendarUtil.setDateCalendar(rs.getDate("inicio_acesso")));
                pojo.setFimAcesso(CalendarUtil.setDateCalendar(rs.getDate("fim_acesso")));
                pojo.setPagamento(CalendarUtil.setDateCalendar(rs.getDate("pagamento")));
                pojo.setVencimento(CalendarUtil.setDateCalendar(rs.getDate("vencimento")));
                pojo.setValor(Double.valueOf(rs.getDouble("valor_total")));
                pojo.setTolerancia(Integer.valueOf(rs.getInt("tolerancia")));
                pojo.setNumeroParcela(Integer.valueOf(rs.getInt("numero_parcela")));
                idPlanosUsuarios = Long.valueOf(rs.getLong("id_planos_usuarios_fk"));
            }
            rs.close();
            pstm.close();
            pstm = null;
            pojo.setNumeroParcela(Integer.valueOf(2));
            pojo.getVencimento().add(2, 1);
            pojo.getInicioAcesso().add(2, 1);
            pojo.getVencimento().set(5, diaVencimento);
            pojo.getInicioAcesso().set(5, diaVencimento);
            pojo.getFimAcesso().set(pojo.getVencimento().get(1), pojo.getVencimento().get(2), diaVencimento);
            pojo.getFimAcesso().add(2, 1);
            pojo.getFimAcesso().add(5, pojo.getTolerancia().intValue());
            Calendar dataAtual = Calendar.getInstance();
            dataAtual.set(5, 1);
            if(pojo.getVencimento().before(dataAtual) || pojo.getPagamento() != null)
            {
                pojo.setPagamento(null);
                create(conn, pojo, idPlanosUsuarios);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void create(Connection conn, Pagamento pojo, Long idPlanoUsuarioFk)
    {
        try
        {
            String sql = "INSERT INTO pagamentos(inicio_acesso, fim_acesso, vencimento, pagamento, numero_parcela, tolerancia, desconto, multa, justificativa, valor, valor_pago, postergar, imprimir, imprimir_entrada, id_formas_pagamento_fk, id_planos_usuarios_fk)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) returning id_pagamentos";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getInicioAcesso()));
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getFimAcesso()));
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getVencimento()));
            if(pojo.getPagamento() != null)
                pstm.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
            else
                pstm.setNull(++i, 93);
            pstm.setInt(++i, pojo.getNumeroParcela().intValue());
            pstm.setInt(++i, pojo.getTolerancia().intValue());
            if(pojo.getDesconto() != null)
                pstm.setDouble(++i, pojo.getDesconto().doubleValue());
            else
                pstm.setNull(++i, 8);
            if(pojo.getMulta() != null)
                pstm.setDouble(++i, pojo.getMulta().doubleValue());
            else
                pstm.setNull(++i, 8);
            pstm.setString(++i, pojo.getJustificativa());
            if(pojo.getValor() != null)
                pstm.setDouble(++i, pojo.getValor().doubleValue());
            else
                pstm.setNull(++i, 8);
            if(pojo.getValorPago() != null)
                pstm.setDouble(++i, pojo.getValorPago().doubleValue());
            else
                pstm.setNull(++i, 8);
            if(pojo.getPostergar() != null)
                pstm.setBoolean(++i, pojo.getPostergar().booleanValue());
            else
                pstm.setNull(++i, 16);
            if(pojo.getImprimir() != null)
                pstm.setBoolean(++i, pojo.getImprimir().booleanValue());
            else
                pstm.setBoolean(++i, Boolean.FALSE.booleanValue());
            if(pojo.getImprimirEntrada() != null)
                pstm.setBoolean(++i, pojo.getImprimirEntrada().booleanValue());
            else
                pstm.setBoolean(++i, Boolean.FALSE.booleanValue());
            if(pojo.getFormaDePagamento() != null)
                pstm.setLong(++i, pojo.getFormaDePagamento().getId().longValue());
            else
                pstm.setNull(++i, -5);
            if(idPlanoUsuarioFk != null)
                pstm.setLong(++i, idPlanoUsuarioFk.longValue());
            else
                pstm.setNull(++i, -5);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo.setId(Long.valueOf(rs.getLong("id_pagamentos")));
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void update(Connection conn, Pagamento pojo)
    {
        try
        {
            String sql = "UPDATE pagamentos SET pagamento=?, desconto=?, multa=?, justificativa=?, valor=?,valor_pago=?, imprimir=?,imprimir_entrada=?, id_formas_pagamento_fk=?,  id_funcionarios_fk=? WHERE id_pagamentos = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            if(pojo.getPagamento() != null)
                pstm.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
            else
                pstm.setNull(++i, 93);
            if(pojo.getDesconto() != null)
                pstm.setDouble(++i, pojo.getDesconto().doubleValue());
            else
                pstm.setNull(++i, 8);
            if(pojo.getMulta() != null)
                pstm.setDouble(++i, pojo.getMulta().doubleValue());
            else
                pstm.setNull(++i, 8);
            pstm.setString(++i, pojo.getJustificativa());
            if(pojo.getValor() != null)
                pstm.setDouble(++i, pojo.getValor().doubleValue());
            else
                pstm.setNull(++i, 8);
            if(pojo.getValorPago() != null)
                pstm.setDouble(++i, pojo.getValorPago().doubleValue());
            else
                pstm.setNull(++i, 8);
            pstm.setBoolean(++i, pojo.getImprimir().booleanValue());
            pstm.setBoolean(++i, pojo.getImprimirEntrada().booleanValue());
            if(pojo.getFormaDePagamento() != null)
                pstm.setLong(++i, pojo.getFormaDePagamento().getId().longValue());
            else
                pstm.setNull(++i, -5);
            if(pojo.getFuncionario() != null)
                pstm.setLong(++i, pojo.getFuncionario().getId().longValue());
            else
                pstm.setNull(++i, -5);
            if(pojo.getId() != null)
                pstm.setLong(++i, pojo.getId().longValue());
            else
                pstm.setNull(++i, -5);
            pstm.execute();
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateImprimir(Boolean imprime, int status, Long id)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE pagamentos SET imprimir=?,imprimir_entrada=? WHERE id_pagamentos = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setBoolean(++i, imprime.booleanValue());
            if(status == 1)
                pstm.setBoolean(++i, true);
            if(status == 2)
                pstm.setBoolean(++i, false);
            if(status == 3)
                pstm.setNull(++i, 16);
            pstm.setLong(++i, id.longValue());
            pstm.execute();
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateImprimirEntrada(Boolean status, Long id)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE pagamentos SET imprimir_entrada=? WHERE id_pagamentos = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setBoolean(++i, status.booleanValue());
            pstm.setLong(++i, id.longValue());
            pstm.execute();
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Boolean readStatusImprimirPagamento(Long idPagamento)
    {
        Boolean status = Boolean.FALSE;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select imprimir from pagamentos where id_pagamentos = ").append(idPagamento).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                while(rs.next()) 
                    status = new Boolean(rs.getBoolean("imprimir"));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    public List readPagamentosByIdPlanoUsuario(Long idPlanoUsuario)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from pagamentos where id_planos_usuarios_fk = ").append(idPlanoUsuario).toString();
            sql = (new StringBuilder()).append(sql).append(" order by vencimento").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(extract(rs)));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }

    public Pagamento extract(ResultSet rs)
    {
        Pagamento pojo = new Pagamento();
        try
        {
            pojo.setId(Long.valueOf(rs.getLong("id_pagamentos")));
            pojo.setInicioAcesso(CalendarUtil.setDateCalendar(rs.getDate("inicio_acesso")));
            pojo.setFimAcesso(CalendarUtil.setDateCalendar(rs.getDate("fim_acesso")));
            pojo.setVencimento(CalendarUtil.setDateCalendar(rs.getDate("vencimento")));
            pojo.setPagamento(CalendarUtil.setTimesTamp(rs.getTimestamp("pagamento")));
            pojo.setDesconto(Double.valueOf(rs.getDouble("desconto")));
            pojo.setMulta(Double.valueOf(rs.getDouble("multa")));
            pojo.setValor(Double.valueOf(rs.getDouble("valor")));
            pojo.setValorPago(Double.valueOf(rs.getDouble("valor_pago")));
            pojo.setTolerancia(Integer.valueOf(rs.getInt("tolerancia")));
            pojo.setNumeroParcela(Integer.valueOf(rs.getInt("numero_parcela")));
            pojo.setJustificativa(rs.getString("justificativa"));
            pojo.setImprimir(Boolean.valueOf(rs.getBoolean("imprimir")));
            pojo.setImprimirEntrada(Boolean.valueOf(rs.getBoolean("imprimir_entrada")));
            if(rs.getLong("id_funcionarios_fk") != 0L)
                pojo.setFuncionario(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_funcionarios_fk"))));
            if(rs.getLong("id_formas_pagamento_fk") != 0L)
                pojo.setFormaDePagamento((FormaDePagamento)DaoLocator.getFormaDePagamentoDAO().readById(Long.valueOf(rs.getLong("id_formas_pagamento_fk"))));
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pojo;
    }

    public Pagamento readById(Long id)
    {
        Pagamento pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from pagamentos where id_pagamentos = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
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

    public Long readIdPlanoUsuarioByIdPagamento(Long id)
    {
        Long idExtract = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select id_planos_usuarios_fk from pagamentos where id_pagamentos = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                idExtract = Long.valueOf(rs.getLong("id_planos_usuarios_fk"));
            rs.close();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return idExtract;
    }

    public void deletePagamentosNULLByIdPlanoUsuario(Connection conn, Long idPlanosUsuarios)
    {
        try
        {
            String sql = " delete  from pagamentos where id_planos_usuarios_fk = ? and pagamento ISNULL";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idPlanosUsuarios.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Map paginatorPagamentos(Map input)
    {
        Map map = new HashMap();
        Map pojoPagamento = new HashMap();
        Double valorPagoTotal = new Double(0.0D);
        Double valorTotalAPagar = new Double(0.0D);
        Integer p = Integer.valueOf(1);
        Integer prev = Integer.valueOf(1);
        Integer next = Integer.valueOf(2);
        Integer paginas = Integer.valueOf(0);
        if(input.get("pag.p") != null)
        {
            String pagina = (String)input.get("pag.p");
            if(!pagina.isEmpty())
                p = Integer.valueOf(Integer.parseInt(pagina));
        }
        prev = Integer.valueOf(p.intValue() - 1);
        next = Integer.valueOf(p.intValue() + 1);
        String filtros = "";
        String colunaData = "vencimento";
        String tipo = (String)input.get("tipo");
        if(tipo == null || tipo.isEmpty() || tipo.equalsIgnoreCase("pagamento"))
            colunaData = "pagamento";
        else
            colunaData = "vencimento";
        String realizado = (String)input.get("realizado");
        if(realizado == null || realizado.isEmpty() || realizado.equalsIgnoreCase("true"))
        {
            filtros = (new StringBuilder()).append(filtros).append(" and pagamento is NOT NULL").toString();
        } else
        {
            filtros = (new StringBuilder()).append(filtros).append(" and pagamento is NULL").toString();
            filtros = (new StringBuilder()).append(filtros).append(" and pu.cancelado = false  and pu.finalizado = false  ").toString();
        }
        String criterioPlano = (String)input.get("criterioPlano");
        if(criterioPlano != null && !criterioPlano.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and pu.id_planos_fk = ").append(criterioPlano).toString();
        String criterioFuncionario = (String)input.get("criterioFuncionario");
        if(criterioFuncionario != null && !criterioFuncionario.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and p.id_funcionarios_fk = ").append(criterioFuncionario).toString();
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and u.matricula = ").append(criterioMatricula).toString();
        String inicio = (String)input.get("criterioInicio");
        String fim = (String)input.get("criterioFim");
        if(fim.isEmpty())
            fim = CalendarUtil.getDataCurrente();
        String data = ConsultaUtil.getDataFormatBD(inicio);
        if(data != null)
        {
            String dataDeInicio = (new StringBuilder()).append(data).append(" 00:00:00").toString();
            filtros = (new StringBuilder()).append(filtros).append(" and ").append(colunaData).append(" >= '").append(dataDeInicio).append("'").toString();
        }
        data = ConsultaUtil.getDataFormatBD(fim);
        if(data != null)
        {
            String dataDeFim = (new StringBuilder()).append(data).append(" 23:59:59").toString();
            filtros = (new StringBuilder()).append(filtros).append(" and ").append(colunaData).append(" <= '").append(dataDeFim).append("'").toString();
        }
        if(input.get("criterioNome") != null)
        {
            String criterioNome = (String)input.get("criterioNome");
            if(!criterioNome.isEmpty())
                filtros = (new StringBuilder()).append(filtros).append(" and ").append(BaseDAO.getRemoveAcento("u.nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString();
        }
        String criterioCpf = (String)input.get("criterioCpf");
        if(criterioCpf != null && !criterioCpf.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and (cpf = '").append(Validador.clearCPF(criterioCpf)).append("')").toString();
        List list = new ArrayList();
        try
        {
            String tableJoin = " from pagamentos as p left join formas_pagamento as f on f.id_formas_pagamento = p.id_formas_pagamento_fk left join planos_usuarios as pu on pu.id_planos_usuarios = p.id_planos_usuarios_fk left join usuarios as u on u.id_usuarios = pu.id_usuarios_fk where true";
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT count(id_pagamentos) as tam ";
            sql = (new StringBuilder()).append(sql).append(tableJoin).toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            float tamanho = 0.0F;
            if(rs != null && rs.next())
                tamanho = rs.getInt("tam");
            if(tamanho < 10F)
            {
                paginas = Integer.valueOf(1);
                next = Integer.valueOf(0);
                p = Integer.valueOf(1);
                prev = Integer.valueOf(0);
            } else
            {
                paginas = Integer.valueOf((int)tamanho / 10);
                if(tamanho % 10F != 0.0F)
                    paginas = Integer.valueOf(paginas.intValue() + 1);
            }
            if(p.intValue() > paginas.intValue())
            {
                p = Integer.valueOf(1);
                prev = Integer.valueOf(0);
            }
            if(p == paginas)
                next = Integer.valueOf(0);
            sql = (new StringBuilder()).append("select p.id_pagamentos,p.numero_parcela, p.vencimento, p.pagamento, p.valor_pago, p.valor,p.id_funcionarios_fk, u.nome, f.nome as forma,pu.cancelado, (select sum(valor) ").append(tableJoin).append(filtros).append(" ) as valor_total,( ").append(" select sum(valor_pago) ").append(tableJoin).append(filtros).append(" ) as valor_pago_total").append(tableJoin).append(filtros).toString();
            if(input != null)
            {
                String orderBy = "false";
                map.put("order", "Crescente!");
                map.put("orderBy", Boolean.valueOf(false));
                if(input.get("orderBy") != null)
                {
                    orderBy = (String)input.get("orderBy");
                    if(orderBy.equalsIgnoreCase("true"))
                    {
                        map.put("order", "Decrescente!");
                        map.put("orderBy", Boolean.valueOf(true));
                    }
                }
                if(orderBy.equalsIgnoreCase("true"))
                    sql = (new StringBuilder()).append(sql).append(" order by ").append(colunaData).append(" DESC ").append(",id_pagamentos DESC").toString();
                else
                    sql = (new StringBuilder()).append(sql).append(" order by ").append(colunaData).append(" ASC ").append(",id_pagamentos ASC").toString();
            }
            sql = (new StringBuilder()).append(sql).append(" LIMIT 10 OFFSET (").append(p.intValue() - 1).append(" * 10)").toString();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if(rs != null)
            {
                boolean entrou = false;
                Pagamento pojo;
                for(; rs.next(); list.add(pojo))
                {
                    pojo = new Pagamento();
                    pojo.setId(Long.valueOf(rs.getLong("id_pagamentos")));
                    pojo.setPagamento(CalendarUtil.setTimesTamp(rs.getTimestamp("pagamento")));
                    pojo.setVencimento(CalendarUtil.setTimesTamp(rs.getTimestamp("vencimento")));
                    pojo.setValorPago(Double.valueOf(rs.getDouble("valor_pago")));
                    pojo.setValor(Double.valueOf(rs.getDouble("valor")));
                    pojo.setFuncionario((Usuario)DaoLocator.getUsuarioDAO().readById(Long.valueOf(rs.getLong("id_funcionarios_fk"))));
                    if(!entrou)
                    {
                        valorPagoTotal = Double.valueOf(rs.getDouble("valor_pago_total"));
                        valorTotalAPagar = Double.valueOf(rs.getDouble("valor_total"));
                        entrou = true;
                    }
                    pojoPagamento.put((new StringBuilder()).append("usuario").append(rs.getLong("id_pagamentos")).toString(), rs.getString("nome"));
                    pojoPagamento.put((new StringBuilder()).append("forma").append(rs.getLong("id_pagamentos")).toString(), rs.getString("forma"));
                }

            }
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        map.put("valorPagoTotal", valorPagoTotal);
        map.put("valorTotalAPagar", valorTotalAPagar);
        map.put("pojoPagamento", pojoPagamento);
        map.put("list", list);
        map.put("currentPage", p);
        map.put("p", p);
        if(next.intValue() == 0)
            map.put("next", Boolean.valueOf(false));
        else
            map.put("next", Boolean.valueOf(true));
        map.put("nextPage", next);
        if(prev.intValue() == 0)
            map.put("prev", Boolean.valueOf(false));
        else
            map.put("prev", Boolean.valueOf(true));
        map.put("prevPage", prev);
        map.put("paginas", paginas);
        map.put("qtdePaginas", BaseDAO.getMapSizePage(paginas.intValue()));
        return map;
    }
}
