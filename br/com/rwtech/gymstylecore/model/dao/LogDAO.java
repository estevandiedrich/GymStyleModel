// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            DataBaseDAO, UsuarioReadDAO

public class LogDAO extends BaseDAO
{

    public LogDAO()
    {
        lista = new ArrayList<Log>();
    }
    @Override
    public void create(Connection conn, Object obj)
    {
    	Log pojo = (Log)obj;
        lista.add(pojo);
        int i = 0;
        do
        {
            if(lista.isEmpty())
                break;
            Log log = (Log)lista.get(i);
            if(log.getDescricao() != null && !log.getDescricao().isEmpty() && log.getDescricao().startsWith("Restaurou Backup"))
                do
                    DaoLocator.getDataBaseDAO();
                while(DataBaseDAO.processoRodando(DataBase.RESTORE).booleanValue());
            super.create(conn, log);
            if(!lista.isEmpty())
                lista.remove(i);
        } while(true);
    }
    @Override
    public String getOrderCampo()
    {
        return "data_log";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Log pojo = (Log)obj;
        addParametro("descricao", pojo.getDescricao(), 12);
        addParametro("data_log", new Timestamp(System.currentTimeMillis()), 93);
        addParametro("tipo", pojo.getTipo(), 12);
        addParametro("parametro", pojo.getParametro(), -5);
        if(pojo.getUsuario() != null)
            addParametro("id_usuario_fk", pojo.getUsuario().getId(), -5);
    }
    @Override
    public Log extract(ResultSet rs)
        throws Exception
    {
        Log pojo = null;
        if(rs != null)
        {
            pojo = new Log();
            pojo.setData(CalendarUtil.setTimesTamp(rs.getTimestamp("data_log")));
            pojo.setId(rsGetId(rs));
            pojo.setDescricao(rs.getString("descricao"));
            pojo.setTipo(rs.getString("tipo"));
            pojo.setUsuario(DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usuario_fk"))));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "log_aplicacao";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioDescricao = (String)input.get("criterioDescricao");
        if(criterioDescricao != null && !criterioDescricao.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("descricao")).append(" ilike '%").append(ConsultaUtil.normalize(criterioDescricao)).append("%'").toString());
        String criterioTipo = (String)input.get("criterioTipo");
        if(criterioTipo != null && !criterioTipo.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("tipo")).append(" ilike '%").append(ConsultaUtil.normalize(criterioTipo)).append("%'").toString());
        String criterioUsuario = (String)input.get("criterioUsuario");
        if(criterioUsuario != null && !criterioUsuario.isEmpty())
            lista.add((new StringBuilder()).append("id_usuario_fk = '").append(criterioUsuario).append("'").toString());
        String criterioInicio = (String)input.get("criterioInicio");
        if(criterioInicio != null && !criterioInicio.isEmpty())
        {
            String data[] = criterioInicio.split("/");
            try
            {
                lista.add((new StringBuilder()).append("data_log >= '").append(data[2]).append("-").append(data[1]).append("-").append(data[0]).append(" 00:00:00'").toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        String criterioFim = (String)input.get("criterioFim");
        if(criterioFim != null && !criterioFim.isEmpty())
        {
            String data[] = criterioFim.split("/");
            try
            {
                lista.add((new StringBuilder()).append("data_log <= '").append(data[2]).append("-").append(data[1]).append("-").append(data[0]).append(" 23:59:59'").toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return lista;
    }

    private List<Log> lista;
}
