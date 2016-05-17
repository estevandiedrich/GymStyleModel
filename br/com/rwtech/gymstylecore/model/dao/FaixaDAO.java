// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FaixaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            DiaSemanaDAO, DispositivoDAO

public class FaixaDAO extends BaseDAO
{

    public FaixaDAO()
    {
    }
    @Override
    public void create(Connection conn, Object obj)
    {
    	Faixa pojo = (Faixa)obj;
        super.create(conn, pojo);
        if(pojo.getDispositivos() != null && !pojo.getDispositivos().isEmpty())
        {
            Dispositivo dipositivo;
            for(Iterator i$ = pojo.getDispositivos().iterator(); i$.hasNext(); createFaixasDispositivos(conn, pojo.getId(), dipositivo.getId()))
                dipositivo = (Dispositivo)i$.next();

        }
    }

    public void createFaixasDispositivos(Connection conn, Long faixaFk, Long dispositivoFk)
    {
        try
        {
            String sql = "INSERT INTO faixas_dispositivos(id_faixas_fk, id_dispositivos_fk)VALUES ( ?, ?)";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, faixaFk.longValue());
            pstm.setLong(++i, dispositivoFk.longValue());
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
    	Faixa pojo = (Faixa)obj;
        addParametro("id_dias_semana_fk", pojo.getDiaSemana().getId(), -1);
        addParametro("horario_inicio", pojo.getHorarioInicio(), 92);
        addParametro("horario_fim", pojo.getHorarioFim(), 92);
    }
    @Override
    public String getOrderCampo()
    {
        return "faixas";
    }
    @Override
    public Faixa extract(ResultSet rs)
        throws Exception
    {
        Faixa pojo = null;
        if(rs != null)
        {
            pojo = new Faixa();
            pojo.setId(rsGetId(rs));
            pojo.setDiaSemana((DiaSemana)DaoLocator.getDiaSemanaDAO().readById(Long.valueOf(rs.getLong("id_dias_semana_fk"))));
            pojo.setHorarioInicio(CalendarUtil.setTimeCalendar(rs.getTime("horario_inicio")));
            pojo.setHorarioFim(CalendarUtil.setTimeCalendar(rs.getTime("horario_fim")));
            pojo.setDispositivos(readDispositivos(pojo.getId()));
        }
        return pojo;
    }

    public List readDispositivos(Long idFaixa)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from faixas_dispositivos as f left join dispositivos as d on d.id_dispositivos = f.id_dispositivos_fk where id_faixas_fk = ").append(idFaixa).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(DaoLocator.getDispositivoDAO().extract(rs)));
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
    @Override
    public String getNameTable()
    {
        return "faixas";
    }

}
