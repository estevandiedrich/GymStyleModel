// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioReadDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            UsuarioDAO

public class UsuarioReadDAO extends UsuarioDAO
{

    public UsuarioReadDAO()
    {
    }

    public List readByCriteriaSimple(Map input)
    {
        List lista = new ArrayList();
        List listaCriterios = getFiltros(input);
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where true").toString();
            for(int i = 0; i < listaCriterios.size(); i++)
                sql = (new StringBuilder()).append(sql).append(" and (").append((String)listaCriterios.get(i)).append(")").toString();

            sql = (new StringBuilder()).append(sql).append(" order by ").append(ORDER_CAMPO).toString();
            if(input != null)
                if(input.get("orderBy") != null)
                    sql = (new StringBuilder()).append(sql).append(" DESC ").toString();
                else
                    sql = (new StringBuilder()).append(sql).append(" ASC ").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(extractSimple(rs)));
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

    public Usuario readByIdSimple(Long id)
    {
        Usuario pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select id_usuarios, nome, matricula, sincronizado, cartao_proximidade from ").append(TABLE).append(" where id_").append(TABLE).append(" = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo = extractSimple(rs);
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

    public Long readNextMatricula()
    {
        Long pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select (max(matricula)+1) as matricula from usuarios where true ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
                pojo = Long.valueOf(rs.getLong("matricula"));
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

    public Usuario readUserByCartaoProximidade(String cartao, Long id)
    {
        List list = new ArrayList();
        for(; cartao.charAt(0) == '0'; cartao = cartao.substring(1, cartao.length()));
        list.add(cartao);
        for(; cartao.length() < 10; list.add(cartao))
            cartao = (new StringBuilder()).append("0").append(cartao).toString();

        int i = 0;
        String caso = "";
        for(; i < list.size(); i++)
        {
            caso = (new StringBuilder()).append(caso).append("cartao_proximidade = '").append((String)list.get(i)).append("'").toString();
            if(i != list.size() - 1)
                caso = (new StringBuilder()).append(caso).append(" or ").toString();
        }

        Usuario pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select id_usuarios, nome,matricula, sincronizado, cartao_proximidade, ativo_aluno, ativo_funcionario, aluno, secretaria, instrutor, adm from ").append(TABLE).append(" where true and (").append(caso).append(")").toString();
            if(id != null)
                sql = (new StringBuilder()).append(sql).append(" and id_usuarios != ").append(id).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
            {
                pojo = extractSimple(rs);
                pojo.setIsAdm(Boolean.valueOf(rs.getBoolean("adm")));
                pojo.setIsAluno(Boolean.valueOf(rs.getBoolean("aluno")));
                pojo.setIsInstrutor(Boolean.valueOf(rs.getBoolean("instrutor")));
                pojo.setIsSecretaria(Boolean.valueOf(rs.getBoolean("secretaria")));
                pojo.setAtivoAluno(Boolean.valueOf(rs.getBoolean("ativo_aluno")));
                pojo.setAtivoFuncionario(Boolean.valueOf(rs.getBoolean("ativo_funcionario")));
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

    public List login(String login)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where true").toString();
            sql = (new StringBuilder()).append(sql).append(" and (login = '").append(login).append("')").toString();
            sql = (new StringBuilder()).append(sql).append(" and (ativo_aluno = true or ativo_funcionario = true)").toString();
            sql = (new StringBuilder()).append(sql).append(" order by id_usuarios").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(extract(rs, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false))));
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
}
