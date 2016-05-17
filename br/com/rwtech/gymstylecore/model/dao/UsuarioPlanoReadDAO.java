// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioPlanoReadDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.*;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            UsuarioDAO

public class UsuarioPlanoReadDAO
{

    public UsuarioPlanoReadDAO()
    {
        USUARIO_ATIVO = " and aluno = true and ativo_aluno = true";
        print = Boolean.valueOf(false);
    }

    public List readUsuPlaAtivo()
    {
        List lista = new ArrayList();
        try
        {
        	Connection conn = ConnectionManager.getInstance().getConnection();
        	String sql = (new StringBuilder()).append("select u.* from usuarios as u where true and id_usuarios in ( select pu.id_usuarios_fk from planos_usuarios as pu inner join pagamentos as p on pu.id_planos_usuarios = p.id_planos_usuarios_fk where true  and ((pu.cancelado = false) )  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios  where id_usuarios_fk = u.id_usuarios))").append(USUARIO_ATIVO).append(" and pagamento is not null and fim_acesso >= date(now()))  order by nome ASC  ").toString();
        	print("sql readUsuPlaAtivo:", sql);
        	Statement stm = conn.createStatement();
        	ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(DaoLocator.getUsuarioDAO().extractSimple(rs)));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
        return lista;
    }

    public Map paginatorUsuPlaAbe(Map input)
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
        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("SELECT count(u.id_usuarios) as tam from usuarios as u left join planos_usuarios as pu on pu.id_usuarios_fk = u.id_usuarios where true  and ((pu.cancelado = false))  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk))").append(USUARIO_ATIVO).toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            Statement stm = conn.createStatement();
            print("sql paginatorUsuPlaAbe: ", sql);
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
            sql = (new StringBuilder()).append(" select u.id_usuarios,u.matricula, u.sincronizado, u.cartao_proximidade, u.nome from usuarios as u  left join planos_usuarios as pu on pu.id_usuarios_fk = u.id_usuarios  where true  and ((pu.cancelado = false))  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = u.id_usuarios))").append(USUARIO_ATIVO).toString();
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
            print("sql paginatorUsuPlaAbe: ", sql);
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); list.add(DaoLocator.getUsuarioDAO().extractSimple(rs)));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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

    public Map paginatorUsuPla(Map input)
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
        String criterioNaoSincronizado = (String)input.get("criterioNaoSincronizado");
        if(criterioNaoSincronizado != null && !criterioNaoSincronizado.isEmpty() && criterioNaoSincronizado.equalsIgnoreCase("true"))
            filtros = (new StringBuilder()).append(filtros).append(" and (u.sincronizado = 'false')").toString();
        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("SELECT count(u.id_usuarios) as tam from usuarios as u left join planos_usuarios as pu on pu.id_usuarios_fk = u.id_usuarios where true  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk))").append(USUARIO_ATIVO).toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            Statement stm = conn.createStatement();
            print("sql paginatorUsuPla: ", sql);
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
            sql = (new StringBuilder()).append(" select u.* from usuarios as u  left join planos_usuarios as pu on pu.id_usuarios_fk = u.id_usuarios  where true  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk))").append(USUARIO_ATIVO).toString();
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
            print("sql paginatorUsuPla: ", sql);
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

    public Map paginatorUsuPlaAbertoAtivo(Map input)
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
        String criterioDebitoDia = (String)input.get("criterioDebitoDia");
        String sqlAtivos = "";
        if(criterioDebitoDia != null && !criterioDebitoDia.isEmpty())
        {
            if(criterioDebitoDia.equalsIgnoreCase("1"))
                sqlAtivos = (new StringBuilder()).append(sqlAtivos).append(" and pagamento is not null and fim_acesso >= date(now())").toString();
            else
            if(criterioDebitoDia.equalsIgnoreCase("0"))
                sqlAtivos = (new StringBuilder()).append(sqlAtivos).append("  and (date(now()) - fim_acesso < 30) and pu.id_usuarios_fk not in(  select pu.id_usuarios_fk from planos_usuarios as pu inner join pagamentos as p on pu.id_planos_usuarios = p.id_planos_usuarios_fk  where true   and ((pu.cancelado = false) )  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk)) and ( (pagamento is not null and fim_acesso >= date(now()))))").toString();
        } else
        {
            String criterioAtivos = (String)input.get("criterioAtivos");
            sqlAtivos = "";
            if(criterioAtivos != null && !criterioAtivos.isEmpty())
                sqlAtivos = " and pagamento is not null and fim_acesso >= date(now())";
        }
        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("SELECT count(u.id_usuarios) as tam from usuarios as u where true and id_usuarios in ( select pu.id_usuarios_fk from planos_usuarios as pu  inner join pagamentos as p on pu.id_planos_usuarios = p.id_planos_usuarios_fk where true   and ((pu.cancelado = false) ) and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk)) ").append(sqlAtivos).append(")").append(USUARIO_ATIVO).toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            Statement stm = conn.createStatement();
            print("sql paginatorUsuPlaAbertoAtivo: ", sql);
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
            sql = (new StringBuilder()).append(" select u.* from usuarios as u where true and id_usuarios in ( select pu.id_usuarios_fk from planos_usuarios as pu inner join pagamentos as p on pu.id_planos_usuarios = p.id_planos_usuarios_fk where true   and ((pu.cancelado = false) )  and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk)) ").append(sqlAtivos).append(")").append(USUARIO_ATIVO).toString();
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
            print("sql paginatorUsuPlaAbertoAtivo: ", sql);
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

    public Map paginatorUsuPlaAbeQuitados(Map input)
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
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and matricula = ").append(criterioMatricula).toString();
        String criterioCpf = (String)input.get("criterioCpf");
        if(criterioCpf != null && !criterioCpf.isEmpty())
            filtros = (new StringBuilder()).append(filtros).append(" and (cpf = '").append(Validador.clearCPF(criterioCpf)).append("')").toString();
        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("SELECT count(u.id_usuarios) as tam from usuarios as u left join planos_usuarios as pu on pu.id_usuarios_fk = u.id_usuarios where true  and ((pu.cancelado = false)) and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk))").append(USUARIO_ATIVO).toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            Statement stm = conn.createStatement();
            print("sql paginatorUsuPlaAbeQuitados: ", sql);
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
            sql = (new StringBuilder()).append("select u.* from usuarios as u left join planos_usuarios as pu on pu.id_usuarios_fk = u.id_usuarios  where true  and ((pu.cancelado = false)) and(id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = pu.id_usuarios_fk))").append(USUARIO_ATIVO).toString();
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
            print("sql paginatorUsuPlaAbeQuitados: ", sql);
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

    public StatusPlano readStatusUltimoPlano(Long idUsuario)
    {
        StatusPlano status = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select pu.*,u.nome,u.ativo_aluno, (select count(id_pagamentos) from pagamentos where true and id_planos_usuarios_fk = pu.id_planos_usuarios and pagamento is not null and fim_acesso >= date(now()) )as qtde from planos_usuarios as pu  left join usuarios as u on u.id_usuarios = pu.id_usuarios_fk where true and id_planos_usuarios = (select max(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = ").append(idUsuario).append(")").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Boolean quitado = Boolean.valueOf(false);
            Boolean cancelado = Boolean.valueOf(false);
            Boolean cadastroAtivo = Boolean.valueOf(false);
            int qtde = 0;
            if(rs != null)
                if(rs.next())
                {
                    quitado = Boolean.valueOf(rs.getBoolean("finalizado"));
                    cadastroAtivo = Boolean.valueOf(rs.getBoolean("ativo_aluno"));
                    cancelado = Boolean.valueOf(rs.getBoolean("cancelado"));
                    qtde = rs.getInt("qtde");
                } else
                {
                    status = StatusPlano.NAO_CONTEM;
                }
            rs.close();
            stm = null;
            if(status == null)
                if(!cadastroAtivo.booleanValue())
                    status = StatusPlano.NAO_CONTEM;
                else
                if(cancelado.booleanValue())
                    status = StatusPlano.CANCELADO;
                else
                if(quitado.booleanValue())
                    status = StatusPlano.QUITADO;
                else
                if(qtde == 0)
                    status = StatusPlano.FORA_PERIODO_ACESSO;
                else
                    status = StatusPlano.ABERTO;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    private void print(String label, String text)
    {
        if(print.booleanValue())
            System.out.println((new StringBuilder()).append(label != null ? label : "").append(text).toString());
    }

    private String USUARIO_ATIVO;
    private Boolean print;
}
