// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuncionarioDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            AcessoDAO, DispositivoDAO, UsuarioDAO

public class FuncionarioDAO
{

    public FuncionarioDAO()
    {
        TABLE = "usuarios";
    }

    public Set getDispositivosByAcessoIdUsuario(long idUsuario)
    {
        Set pojos = new HashSet();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from funcionarios_acesso as f left join acesso_dispositivos as a on a.id_acesso_fk = f.id_funcionarios_acesso left join dispositivos as d on d.id_dispositivos = a.id_dispositivos_fk where true and d.id_dispositivos > 0 and f.id_funcionarios_acesso = (").append(AcessoDAO.SQL_MAX_ID_ACESSO).append(")").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUsuario);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojos.add(DaoLocator.getDispositivoDAO().extract(rs));
            rs.close();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojos;
    }

    public Boolean disabledFuncionario(Long id)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("update ").append(TABLE).append(" set ativo_funcionario = false where id_").append(TABLE).append(" = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public Map paginatorFuncionarioComAcesso(Map input)
    {
        Map map = new HashMap();
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
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and (").append(BaseDAO.getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%')").toString();
        String criterioCpf = (String)input.get("criterioCpf");
        if(criterioCpf != null && !criterioCpf.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and (cpf = '").append(Validador.clearCPF(criterioCpf)).append("')").toString();
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and (matricula = '").append(criterioMatricula).append("')").toString();
        String criterioSincronizado = (String)input.get("criterioNaoSincronizado");
        if(criterioSincronizado != null && !criterioSincronizado.isEmpty() && criterioSincronizado.equalsIgnoreCase("true"))
            filtros = (new StringBuilder()).append(filtros).append(" and (u.sincronizado = 'false')").toString();
        filtros = (new StringBuilder()).append(filtros).append(" and (u.ativo_funcionario = 'true')").toString();
        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select count(u.id_usuarios)as tam from usuarios as u  left join funcionarios_acesso as f on f.id_usuario_fk = u.id_usuarios where true and id_funcionarios_acesso in (SELECT max(id_funcionarios_acesso) FROM funcionarios_acesso WHERE funcionarios_acesso.id_usuario_fk = u.id_usuarios )";
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
            sql = " select u.* from usuarios as u  left join funcionarios_acesso as f on f.id_usuario_fk = u.id_usuarios where true and id_funcionarios_acesso in (SELECT max(id_funcionarios_acesso) FROM funcionarios_acesso WHERE funcionarios_acesso.id_usuario_fk = u.id_usuarios )";
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            sql = (new StringBuilder()).append(sql).append(" order by nome").toString();
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
                    sql = (new StringBuilder()).append(sql).append(" DESC ").toString();
                else
                    sql = (new StringBuilder()).append(sql).append(" ASC ").toString();
            }
            sql = (new StringBuilder()).append(sql).append(" LIMIT 10 OFFSET (").append(p.intValue() - 1).append(" * 10)").toString();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); list.add(DaoLocator.getUsuarioDAO().extract(rs)));
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        map.put(BaseReadDAO.LIST, list);
        map.put(BaseReadDAO.CURRENTE_PAGE, p);
        map.put(BaseReadDAO.P, p);
        if(next.intValue() == 0)
            map.put(BaseReadDAO.NEXT, Boolean.valueOf(false));
        else
            map.put(BaseReadDAO.NEXT, Boolean.valueOf(true));
        map.put(BaseReadDAO.NEXT_PAGE, next);
        if(prev.intValue() == 0)
            map.put(BaseReadDAO.PREV, Boolean.valueOf(false));
        else
            map.put(BaseReadDAO.PREV, Boolean.valueOf(true));
        map.put(BaseReadDAO.PREV_PAGE, prev);
        map.put(BaseReadDAO.PAGINAS, paginas);
        map.put(BaseReadDAO.QTDE_PAGINAS, BaseDAO.getMapSizePage(paginas.intValue()));
        return map;
    }

    private String TABLE;
}
