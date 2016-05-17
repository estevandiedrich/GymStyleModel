// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlanoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            PerfilAcessoDAO, ModalidadeDAO

public class PlanoDAO extends BaseDAO
{

    public PlanoDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "planos";
    }
    @Override
    public void create(Connection conn,Object obj)
    {
    	Plano pojo = (Plano)obj;
        super.create(conn, pojo);
        Modalidade modalidade;
        for(Iterator i$ = pojo.getModalidades().iterator(); i$.hasNext(); createPlanoModalidade(conn, pojo.getId(), modalidade.getId(), modalidade.getQtdeAcessos()))
            modalidade = (Modalidade)i$.next();

    }
    @Override
    public void update(Connection conn, Object obj)
    {
    	Plano pojo = (Plano)obj;
        super.update(conn, pojo);
        deletePlanoModalidade(conn, pojo.getId());
        Modalidade modalidade;
        for(Iterator i$ = pojo.getModalidades().iterator(); i$.hasNext(); createPlanoModalidade(conn, pojo.getId(), modalidade.getId(), modalidade.getQtdeAcessos()))
            modalidade = (Modalidade)i$.next();

    }

    public void createPlanoModalidade(Connection conn, Long planoFk, Long modalidadeFk, Integer qtdeAcessoSemana)
    {
        try
        {
            String sql = "INSERT INTO plano_modalidade(id_planos_fk, id_modalidades_fk, qtde_acesso_semana)VALUES (?, ?, ?);";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, planoFk.longValue());
            pstm.setLong(++i, modalidadeFk.longValue());
            pstm.setInt(++i, qtdeAcessoSemana.intValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deletePlanoModalidade(Connection conn, Long id)
    {
        try
        {
            String sql = "DELETE FROM plano_modalidade WHERE id_planos_fk = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PerfilAcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Plano pojo = (Plano)obj;
        addParametro("nome", pojo.getPlano(), 12);
        addParametro("valor_matricula", pojo.getValorMatricula(), 8);
        addParametro("valor", pojo.getValor(), 8);
        addParametro("valor_total", pojo.getValorTotal(), 8);
        addParametro("desconto_percentual", pojo.getDescontoPercentual(), 4);
        addParametro("desconto_real", pojo.getDescontoReal(), 8);
        addParametro("observacao", pojo.getObservacao(), 12);
        addParametro("ativo", pojo.getAtivo(), 16);
    }
    @Override
    public Plano extract(ResultSet rs)
        throws Exception
    {
        Plano pojo = null;
        if(rs != null)
        {
            pojo = new Plano();
            pojo.setId(rsGetId(rs));
            pojo.setPlano(rs.getString("nome"));
            pojo.setObservacao(rs.getString("observacao"));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
            pojo.setDescontoPercentual(Integer.valueOf(rs.getInt("desconto_percentual")));
            pojo.setDescontoReal(Double.valueOf(rs.getDouble("desconto_real")));
            pojo.setValorMatricula(Double.valueOf(rs.getDouble("valor_matricula")));
            pojo.setValor(Double.valueOf(rs.getDouble("valor")));
            pojo.setValorTotal(Double.valueOf(rs.getDouble("valor_total")));
            pojo.setModalidades(readModalidades(pojo.getId()));
            pojo.setQtdeAcessos(Integer.valueOf(getQtdeAcessosTotal(pojo.getModalidades())));
        }
        return pojo;
    }
    @Override
    public Plano extractSimple(ResultSet rs)
        throws Exception
    {
        Plano pojo = null;
        if(rs != null)
        {
            pojo = new Plano();
            pojo.setId(rsGetId(rs));
            pojo.setPlano(rs.getString("nome"));
            pojo.setObservacao(rs.getString("observacao"));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
            pojo.setDescontoPercentual(Integer.valueOf(rs.getInt("desconto_percentual")));
            pojo.setDescontoReal(Double.valueOf(rs.getDouble("desconto_real")));
            pojo.setValorMatricula(Double.valueOf(rs.getDouble("valor_matricula")));
            pojo.setValor(Double.valueOf(rs.getDouble("valor")));
            pojo.setValorTotal(Double.valueOf(rs.getDouble("valor_total")));
        }
        return pojo;
    }

    public int getQtdeAcessosTotal(List lista)
    {
        int qtde = 0;
        if(lista != null)
        {
            for(Iterator it = lista.iterator(); it.hasNext();)
            {
                Modalidade modalidade = (Modalidade)it.next();
                qtde += modalidade.getQtdeAcessos().intValue();
            }

        }
        return qtde;
    }

    public List readModalidades(Long idPerfilAcesso)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from plano_modalidade as p left join modalidades as m on m.id_modalidades = p.id_modalidades_fk where id_planos_fk = ").append(idPerfilAcesso).append(" order by id_modalidades_fk").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
            {
                Modalidade mod;
                for(; rs.next(); lista.add(mod))
                {
                    mod = DaoLocator.getModalidadeDAO().extract(rs);
                    mod.setQtdeAcessos(Integer.valueOf(rs.getInt("qtde_acesso_semana")));
                }

            }
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e) { }
        return lista;
    }

    public List readPlanosByUser(Long idUsuario)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from planos_usuarios as pu left join planos as p on p.id_planos = pu.id_planos_fk  where true and id_planos_fk = ").append(idUsuario).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(DaoLocator.getPlanoDAO().extract(rs)));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e) { }
        return lista;
    }

    public void ajustarPlanos(Long idModalidade)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from plano_modalidade where id_modalidades_fk = ").append(idModalidade).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
            {
                Long idPlano;
                for(; rs.next(); ajustarValorTotalPlano(idPlano))
                    idPlano = Long.valueOf(rs.getLong("id_planos_fk"));

            }
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e) { }
    }

    public void ajustarValorTotalPlano(Long idPlano)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select p.*,(  select sum(m.valor) from planos as p left join plano_modalidade as pm on pm.id_planos_fk = p.id_planos  left join modalidades as m on m.id_modalidades = pm.id_modalidades_fk  where id_planos = ").append(idPlano).append(") as valor_modalidade").append(" from planos as p").append(" left join plano_modalidade as pm on pm.id_planos_fk = p.id_planos").append(" left join modalidades as m on m.id_modalidades = pm.id_modalidades_fk").append(" where id_planos = ").append(idPlano).append(" group by id_planos").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null && rs.next())
            {
                Plano plano = extract(rs);
                plano.setValor(Double.valueOf(rs.getDouble("valor_modalidade")));
                plano.setValorTotal(Double.valueOf(plano.getValor().doubleValue() - plano.getDescontoReal().doubleValue() - calculaDescontoPercentual(plano.getValor(), plano.getDescontoPercentual()).doubleValue()));
                update(conn, plano);
            }
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Double calculaDescontoPercentual(Double valor, Integer percentual)
    {
        Double result = null;
        if(percentual != null)
        {
            Double perc = new Double(percentual.intValue());
            perc = Double.valueOf(perc.doubleValue() / 100D);
            result = Double.valueOf(valor.doubleValue() * perc.doubleValue());
        }
        if(result == null)
            result = new Double(0.0D);
        return result;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        lista.add("ativo = true");
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        return lista;
    }
}
