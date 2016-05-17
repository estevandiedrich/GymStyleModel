// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PerfilAcessoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Faixa;
import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            FaixaDAO

public class PerfilAcessoDAO extends BaseDAO
{

    public PerfilAcessoDAO()
    {
    }
    @Override
    public void create(Connection conn, Object obj)
    {
    	PerfilAcesso pojo = (PerfilAcesso)obj;
        super.create(conn, pojo);
        Faixa faixa;
        for(Iterator i$ = pojo.getFaixas().iterator(); i$.hasNext(); createPerfilAcessoFaixas(conn, pojo.getId(), faixa.getId()))
        {
            faixa = (Faixa)i$.next();
            DaoLocator.getFaixaDAO().create(conn, faixa);
        }

    }
    @Override
    public void update(Connection conn, Object obj)
    {
    	PerfilAcesso pojo = (PerfilAcesso)obj;
        super.update(conn, pojo);
        deleteFaixasPerfilAcesso(conn, pojo.getId());
        Faixa faixa;
        for(Iterator i$ = pojo.getFaixas().iterator(); i$.hasNext(); createPerfilAcessoFaixas(conn, pojo.getId(), faixa.getId()))
        {
            faixa = (Faixa)i$.next();
            DaoLocator.getFaixaDAO().create(conn, faixa);
        }

    }

    public void createPerfilAcessoFaixas(Connection conn, Long perfilAcessoFk, Long faixaFk)
    {
        try
        {
            String sql = "INSERT INTO perfis_acesso_faixas(id_perfis_acesso_fk, id_faixas_fk)VALUES ( ?, ?)";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, perfilAcessoFk.longValue());
            pstm.setLong(++i, faixaFk.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	PerfilAcesso pojo = (PerfilAcesso)obj;
        addParametro("nome", pojo.getPerfilAcesso(), 12);
    }
    @Override
    public PerfilAcesso extract(ResultSet rs)
        throws Exception
    {
        PerfilAcesso pojo = null;
        if(rs != null)
        {
            pojo = new PerfilAcesso();
            pojo.setId(rsGetId(rs));
            pojo.setPerfilAcesso(rs.getString("nome"));
            pojo.setFaixas(readFaixas(pojo.getId()));
        }
        return pojo;
    }

    public List readFaixas(Long idPerfilAcesso)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from perfis_acesso_faixas as p left join faixas as f on f.id_faixas = p.id_faixas_fk where id_perfis_acesso_fk = ").append(idPerfilAcesso).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(DaoLocator.getFaixaDAO().extract(rs)));
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e) { }
        return lista;
    }
    @Override
    public String getNameTable()
    {
        return "perfis_acesso";
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        deleteFaixasPerfilAcesso(conn, id);
        return super.delete(conn, id);
    }

    public void deleteFaixasPerfilAcesso(Connection conn, Long id)
    {
        try
        {
            String sql = "DELETE FROM perfis_acesso_faixas WHERE id_perfis_acesso_fk = ? returning id_faixas_fk";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            for(ResultSet rs = pstm.executeQuery(); rs.next(); DaoLocator.getFaixaDAO().delete(conn, Long.valueOf(rs.getLong("id_faixas_fk"))));
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PerfilAcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
            lista.add((new StringBuilder()).append("matricula = '").append(criterioMatricula).append("'").toString());
        return lista;
    }
}
