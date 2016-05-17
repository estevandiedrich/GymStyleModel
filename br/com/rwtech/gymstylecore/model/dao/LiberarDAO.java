// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LiberarDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            UsuarioDAO, DispositivoDAO

public class LiberarDAO extends BaseDAO
{

    public LiberarDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "data_hora";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Liberar pojo = (Liberar)obj;
        addParametro("justificativa", pojo.getJustificativa(), 12);
        if(pojo.getOperador() != null)
            addParametro("id_operador_fk", pojo.getOperador().getId(), -1);
        if(pojo.getDispositivo() != null)
            addParametro("id_dispositivos_fk", pojo.getDispositivo().getId(), -1);
        addParametro("data_hora", new Timestamp(System.currentTimeMillis()), 93);
    }
    @Override
    public Liberar extract(ResultSet rs)
        throws Exception
    {
        Liberar pojo = null;
        if(rs != null)
        {
            pojo = new Liberar();
            pojo.setId(rsGetId(rs));
            pojo.setDataHora(CalendarUtil.setTimesTamp(rs.getTimestamp("data_hora")));
            pojo.setJustificativa(rs.getString("justificativa"));
            pojo.setOperador((Usuario)DaoLocator.getUsuarioDAO().readById(Long.valueOf(rs.getLong("id_operador_fk"))));
            pojo.setDispositivo((Dispositivo)DaoLocator.getDispositivoDAO().readById(Long.valueOf(rs.getLong("id_dispositivos_fk"))));
        }
        return pojo;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioJustificativa = (String)input.get("criterioJustificativa");
        if(criterioJustificativa != null && !criterioJustificativa.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("justificativa")).append(" ilike '%").append(ConsultaUtil.normalize(criterioJustificativa)).append("%'").toString());
        return lista;
    }
    @Override
    public String getNameTable()
    {
        return "liberacoes";
    }
}
