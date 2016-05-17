// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PermissaoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.ConnectionManagerTwo;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import java.sql.*;
import java.util.*;

public class PermissaoDAO
{

    public PermissaoDAO()
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

    public void create(List lista)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            if(conn != null)
            {
                PreparedStatement pstm = null;
                int i = 0;
                String sql = " CREATE TABLE IF NOT EXISTS permissoes ( nome VARCHAR(100), descricao VARCHAR(300), grupo VARCHAR(100),ativo BOOLEAN DEFAULT TRUE, CONSTRAINT PERMISSOES_PK PRIMARY KEY (nome)); CREATE TABLE IF NOT EXISTS usuario_permissoes ( id_usuario_permissoes BIGSERIAL NOT NULL,id_usuario_fk BIGINT, nome_fk VARCHAR(100), CONSTRAINT USUARIO_PERMISSOES_PK PRIMARY KEY (id_usuario_permissoes), CONSTRAINT USUARIO_PERMISSOES_USUARIO_FK FOREIGN KEY (id_usuario_fk) REFERENCES usuarios(id_usuarios) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT USUARIO_PERMISSOES_PERMISSOES_FK FOREIGN KEY (nome_fk) REFERENCES permissoes(nome) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE);";
                pstm = conn.prepareStatement(sql);
                pstm.execute();
                sql = "";
                for(Iterator i$ = lista.iterator(); i$.hasNext();)
                {
                    Permissao per = (Permissao)i$.next();
                    String aux = (new StringBuilder()).append("INSERT INTO permissoes(nome,descricao,grupo,ativo) SELECT '").append(per.getNome()).append("','").append(per.getDescricao()).append("','").append(per.getGrupo()).append("','").append(per.getAtivo()).append("' WHERE NOT EXISTS (SELECT nome FROM permissoes WHERE nome = '").append(per.getNome()).append("');").toString();
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

    public List readbyCriteria(Boolean ativo, String grupo)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = " select * from permissoes where true";
            if(ativo != null)
                sql = (new StringBuilder()).append(sql).append(" and ativo = ").append(ativo).toString();
            if(grupo != null && !grupo.isEmpty())
            {
                sql = (new StringBuilder()).append(sql).append(" and grupo = '").append(grupo).append("'").toString();
                sql = (new StringBuilder()).append(sql).append(" order by descricao").toString();
            }
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

    public List readDescricaoByGrupoCadastro()
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = " select descricao from permissoes where true and grupo = 'cadastro' group by descricao order by descricao";
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
}
