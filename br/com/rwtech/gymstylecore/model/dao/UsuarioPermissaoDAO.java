// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioPermissaoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.ConnectionManagerTwo;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import java.sql.*;
import java.util.*;

public class UsuarioPermissaoDAO
{

    public UsuarioPermissaoDAO()
    {
    }

    public void drop()
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            if(conn != null)
            {
                PreparedStatement pstm = null;
                int i = 0;
                String sql = "DROP TABLE permissoes CASCADE";
                pstm = conn.prepareStatement(sql);
                pstm.execute();
                pstm.close();
                conn.commit();
                conn.close();
                conn = null;
            }
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

    public void create(List lista, Long id)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            if(conn != null)
            {
                PreparedStatement pstm = null;
                int i = 0;
                String sql = (new StringBuilder()).append(" DELETE FROM usuario_permissoes where id_usuario_fk = ").append(id).toString();
                pstm = conn.prepareStatement(sql);
                pstm.execute();
                sql = "";
                for(Iterator i$ = lista.iterator(); i$.hasNext();)
                {
                    Permissao per = (Permissao)i$.next();
                    String aux = (new StringBuilder()).append("INSERT INTO usuario_permissoes(id_usuario_fk, nome_fk) VALUES ('").append(id).append("', '").append(per.getNome()).append("');").toString();
                    sql = (new StringBuilder()).append(sql).append(aux).toString();
                }

                pstm = conn.prepareStatement(sql);
                pstm.execute();
                pstm.close();
                conn.commit();
                conn.close();
                conn = null;
            }
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

    public void update(Permissao pojo)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "UPDATE permissoes SET nome = ?, ativo = ?  WHERE nome = ?;";
            pstm = conn.prepareStatement(sql);
            pstm.setString(++i, pojo.getNome());
            pstm.setBoolean(++i, pojo.getAtivo().booleanValue());
            pstm.setString(++i, pojo.getNome());
            pstm.execute();
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

    public List readByCriteria(Boolean ativo, String grupo)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = " select * from permissoes where true";
            if(ativo != null)
                sql = (new StringBuilder()).append(sql).append(" and ativo = ").append(ativo).toString();
            if(grupo != null && !grupo.isEmpty())
                sql = (new StringBuilder()).append(sql).append(" and grupo = '").append(grupo).append("'").toString();
            Statement stm = conn.createStatement();
            ResultSet rs;
            Permissao pojo;
            for(rs = stm.executeQuery(sql); rs.next(); lista.add(pojo))
            {
                pojo = new Permissao();
                pojo.setNome(rs.getString("nome"));
                pojo.setDescricao(rs.getString("descricao"));
                pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
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

    public List readByIdUsuario(Long id)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from usuario_permissoes as up left join permissoes as p on up.nome_fk= p.nome where id_usuario_fk = ").append(id).toString();
            Statement stm = conn.createStatement();
            ResultSet rs;
            Permissao pojo;
            for(rs = stm.executeQuery(sql); rs.next(); lista.add(pojo))
            {
                pojo = new Permissao();
                pojo.setNome(rs.getString("nome"));
                pojo.setDescricao(rs.getString("descricao"));
                pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
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

    public Set readDescricaoByGrupoCadastro()
    {
        Set lista = new HashSet();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = " select descricao from permissoes where true";
            sql = (new StringBuilder()).append(sql).append(" and grupo = 'cadastro'").toString();
            Statement stm = conn.createStatement();
            ResultSet rs;
            for(rs = stm.executeQuery(sql); rs.next(); lista.add(rs.getString("descricao")));
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

    public Permissao readByName(String per)
    {
        Permissao pojo = null;
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = " select * from permissoes where true";
            if(per != null)
                sql = (new StringBuilder()).append(sql).append(" and nome = '").append(per).append("'").toString();
            Statement stm = conn.createStatement();
            ResultSet rs;
            for(rs = stm.executeQuery(sql); rs.next(); pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo"))))
            {
                pojo = new Permissao();
                pojo.setNome(rs.getString("nome"));
                pojo.setDescricao(rs.getString("descricao"));
            }

            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojo;
    }

    public void deleteByIdUsuario(Long id)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "DELETE FROM usuario_permissoes WHERE id_usuario_fk = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(++i, id.longValue());
            pstm.execute();
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
}
