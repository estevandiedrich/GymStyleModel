// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EventoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.pojo.tipos.Motivo;
import br.com.rwtech.gymstylecore.model.util.*;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            UsuarioReadDAO, DispositivoDAO, MotivoBloqueioDAO, Restaurador, 
//            UsuarioDAO

public class EventoDAO extends BaseDAO
{

    public EventoDAO()
    {
    }
    @Override
    public String getNameTable()
    {
        return "eventos";
    }
    @Override
    public String getOrderCampo()
    {
        return "data_hora";
    }
    @Override
    public Evento extract(ResultSet rs)
        throws Exception
    {
        Evento pojo = null;
        if(rs != null)
        {
            pojo = new Evento();
            pojo.setId(rsGetId(rs));
            pojo.setDataHora(CalendarUtil.setTimesTamp(rs.getTimestamp("data_hora")));
            pojo.setOffline(Boolean.valueOf(rs.getBoolean("offline")));
            pojo.setEntrada(Boolean.valueOf(rs.getBoolean("entrada")));
            pojo.setRealizado(Boolean.valueOf(rs.getBoolean("realizado")));
            pojo.setPosicao(Integer.valueOf(rs.getInt("posicao")));
            pojo.setUsuario(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usuarios_fk"))));
            pojo.setDispositivo((Dispositivo)DaoLocator.getDispositivoDAO().readById(Long.valueOf(rs.getLong("id_dispositivos_fk"))));
            pojo.setMotivo((MotivoBloqueio)DaoLocator.getMotivoBloqueioDAO().readById(Long.valueOf(rs.getLong("motivo"))));
            if(pojo.getMotivo().getMotivo().equalsIgnoreCase(Motivo.LIBERADO.toString()) && pojo.getUsuario() == null)
            {
                pojo.setMotivo(new MotivoBloqueio());
                pojo.getMotivo().setMotivo("Passagem Liberada na Catraca");
            }
        }
        return pojo;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        String criterioDescricao = (String)input.get("criterioDescricao");
        if(criterioDescricao != null && !criterioDescricao.isEmpty())
            if(criterioDescricao.equals("0"))
            {
                lista.add((new StringBuilder()).append(getRemoveAcento("m.motivo")).append(" = 'Liberado'").toString());
                lista.add(" cpf isnull ");
            } else
            {
                lista.add((new StringBuilder()).append("e.motivo = '").append(criterioDescricao).append("'").toString());
            }
        String criterioInicio = (String)input.get("criterioInicio");
        if(criterioInicio != null && !criterioInicio.isEmpty())
        {
            String data = ConsultaUtil.getDataFormatBD(criterioInicio);
            if(data != null)
                lista.add((new StringBuilder()).append("data_hora >= '").append(data).append(" 00:00:00'").toString());
        }
        String criterioFim = (String)input.get("criterioFim");
        if(criterioFim != null && !criterioFim.isEmpty())
        {
            String data = ConsultaUtil.getDataFormatBD(criterioFim);
            if(data != null)
                lista.add((new StringBuilder()).append("data_hora <= '").append(data).append(" 23:59:59'").toString());
        }
        String criterioDataInicio = (String)input.get("criterioDataInicio");
        if(criterioDataInicio != null && !criterioDataInicio.isEmpty())
            lista.add((new StringBuilder()).append("data_hora >= '").append(criterioDataInicio).append("'").toString());
        String criterioDataFim = (String)input.get("criterioDataFim");
        if(criterioDataFim != null && !criterioDataFim.isEmpty())
            lista.add((new StringBuilder()).append("data_hora <= '").append(criterioDataFim).append("'").toString());
        String criterioCpf = (String)input.get("criterioCpf");
        if(criterioCpf != null && !criterioCpf.isEmpty())
            lista.add((new StringBuilder()).append("cpf = '").append(Validador.clearCPF(criterioCpf)).append("'").toString());
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
            lista.add((new StringBuilder()).append("matricula = ").append(criterioMatricula).toString());
        String criterioCatraca = (String)input.get("criterioCatraca");
        if(criterioCatraca != null && !criterioCatraca.isEmpty())
            lista.add((new StringBuilder()).append("id_dispositivos_fk = '").append(criterioCatraca).append("'").toString());
        return lista;
    }
    @Override
    public Map paginator(Map input)
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
        List listaCriterios = getFiltros(input);
        String filtros = "";
        for(int i = 0; i < listaCriterios.size(); i++)
            filtros = (new StringBuilder()).append(filtros).append(" and (").append((String)listaCriterios.get(i)).append(")").toString();

        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT count(id_eventos) as tam FROM eventos as e left join usuarios as u on u.id_usuarios = e.id_usuarios_fk  left join motivos_bloqueio as m on m.id_motivos_bloqueio = e.motivo where true ";
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            sql = (new StringBuilder()).append(sql).append(" and (m.id_motivos_bloqueio <> '7') and (m.id_motivos_bloqueio <> '12')").toString();
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
            sql = "SELECT * FROM eventos as e left join usuarios as u on u.id_usuarios = e.id_usuarios_fk  left join motivos_bloqueio as m on m.id_motivos_bloqueio = e.motivo where true ";
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            sql = (new StringBuilder()).append(sql).append(" and (m.id_motivos_bloqueio <> '7') and (m.id_motivos_bloqueio <> '12')").toString();
            sql = (new StringBuilder()).append(sql).append(" order by ").append(ORDER_CAMPO).toString();
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
                for(; rs.next(); list.add(extract(rs)));
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

    public List readUltimosEventos()
    {
        List lista = new ArrayList();
        if(!Restaurador.RESTAURANDO.booleanValue())
            try
            {
                Connection conn = ConnectionManagerTwo.getInstance().getConnection();
                Calendar data = Calendar.getInstance();
                data.set(11, 0);
                data.set(12, 0);
                data.set(13, 0);
                String sql = " select e.id_eventos as e_id_eventos,e.data_hora as e_data_hora,d.modo_acesso as modo_acesso,e.realizado as e_realizado, e.entrada as e_entrada,e.offline as e_offline,e.posicao as e_posicao,  u.id_usuarios,u.nome,u.matricula, u.sincronizado, u.cartao_proximidade, u.aluno,  u.ativo_aluno, u.adm, u.instrutor, u.secretaria,  d.id_dispositivos,d.nome,d.nome as d_nome,d.endereco_ip,d.endereco_mac,d.online,d.imprime,d.entrada_dir_esq,d.porta,d.id_modos_operacao_fk, m.id_motivos_bloqueio,m.motivo from eventos as e left join usuarios as u on u.id_usuarios = e.id_usuarios_fk left join motivos_bloqueio as m on m.id_motivos_bloqueio= e.motivo left join dispositivos as d on d.id_dispositivos= e.id_dispositivos_fk where true";
                sql = (new StringBuilder()).append(sql).append(" and (e.data_hora >= '").append(data.getTime()).append("')").toString();
                data = Calendar.getInstance();
                sql = (new StringBuilder()).append(sql).append(" and (e.data_hora <= '").append(data.getTime()).append("')").toString();
                sql = (new StringBuilder()).append(sql).append(" and (e.motivo <> '7')").toString();
                sql = (new StringBuilder()).append(sql).append(" and (e.motivo <> '12')").toString();
                sql = (new StringBuilder()).append(sql).append(" order by e.data_hora").toString();
                sql = (new StringBuilder()).append(sql).append(" DESC ").toString();
                sql = (new StringBuilder()).append(sql).append(" LIMIT 6 OFFSET 0").toString();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if(rs != null)
                    do
                    {
                        if(!rs.next())
                            break;
                        Evento pojo = null;
                        if(rs != null)
                        {
                            pojo = new Evento();
                            pojo.setId(Long.valueOf(rs.getLong("e_id_eventos")));
                            pojo.setDataHora(CalendarUtil.setTimesTamp(rs.getTimestamp("e_data_hora")));
                            pojo.setOffline(Boolean.valueOf(rs.getBoolean("e_offline")));
                            pojo.setEntrada(Boolean.valueOf(rs.getBoolean("e_entrada")));
                            pojo.setRealizado(Boolean.valueOf(rs.getBoolean("e_realizado")));
                            pojo.setPosicao(Integer.valueOf(rs.getInt("e_posicao")));
                            Usuario user = DaoLocator.getUsuarioDAO().extractSimple(rs);
                            user.setIsAluno(Boolean.valueOf(rs.getBoolean("aluno")));
                            user.setAtivoAluno(Boolean.valueOf(rs.getBoolean("ativo_aluno")));
                            user.setIsAdm(Boolean.valueOf(rs.getBoolean("adm")));
                            user.setIsInstrutor(Boolean.valueOf(rs.getBoolean("instrutor")));
                            user.setIsSecretaria(Boolean.valueOf(rs.getBoolean("secretaria")));
                            pojo.setUsuario(user);
                            pojo.setDispositivo(DaoLocator.getDispositivoDAO().extract(rs));
                            pojo.getDispositivo().setDispositivo(rs.getString("d_nome"));
                            pojo.setMotivo(DaoLocator.getMotivoBloqueioDAO().extract(rs));
                            if(pojo.getMotivo().getMotivo().equalsIgnoreCase(Motivo.LIBERADO.toString()) && (pojo.getUsuario() == null || pojo.getUsuario().getUsuario() == null))
                            {
                                pojo.setMotivo(new MotivoBloqueio());
                                pojo.getMotivo().setMotivo("Passagem Liberada na Catraca");
                            }
                            lista.add(pojo);
                        }
                    } while(true);
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

    public Evento readUltimoEventoUsuario(Long idUsuario)
    {
        Evento pojo = null;
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select e.id_eventos as e_id_eventos,e.data_hora as e_data_hora,e.realizado as e_realizado,  e.entrada as e_entrada,e.offline as e_offline,e.posicao as e_posicao,  u.id_usuarios,u.nome, u.matricula, u.sincronizado, u.cartao_proximidade, d.id_dispositivos,d.nome,d.modo_acesso,d.nome as d_nome,d.endereco_ip,d.endereco_mac,d.online,d.imprime,d.entrada_dir_esq,d.porta,d.id_modos_operacao_fk, m.id_motivos_bloqueio,m.motivo from eventos as e left join usuarios as u on u.id_usuarios = e.id_usuarios_fk left join motivos_bloqueio as m on m.id_motivos_bloqueio= e.motivo left join dispositivos as d on d.id_dispositivos= e.id_dispositivos_fk where true and id_eventos = (SELECT max(id_eventos) FROM eventos where id_usuarios_fk = ").append(idUsuario).append(" and realizado = true)").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                do
                {
                    if(!rs.next())
                        break;
                    if(rs != null)
                    {
                        pojo = new Evento();
                        pojo.setId(Long.valueOf(rs.getLong("e_id_eventos")));
                        pojo.setDataHora(CalendarUtil.setTimesTamp(rs.getTimestamp("e_data_hora")));
                        pojo.setOffline(Boolean.valueOf(rs.getBoolean("e_offline")));
                        pojo.setEntrada(Boolean.valueOf(rs.getBoolean("e_entrada")));
                        pojo.setRealizado(Boolean.valueOf(rs.getBoolean("e_realizado")));
                        pojo.setPosicao(Integer.valueOf(rs.getInt("e_posicao")));
                        pojo.setUsuario(DaoLocator.getUsuarioDAO().extractSimple(rs));
                        pojo.setDispositivo(DaoLocator.getDispositivoDAO().extract(rs));
                        pojo.getDispositivo().setDispositivo(rs.getString("d_nome"));
                        pojo.setMotivo(DaoLocator.getMotivoBloqueioDAO().extract(rs));
                    }
                } while(true);
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
}
