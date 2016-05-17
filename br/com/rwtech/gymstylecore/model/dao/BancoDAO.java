// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BancoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Banco;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.ResultSet;
import java.util.*;

public class BancoDAO extends BaseDAO
{

    public BancoDAO()
    {
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Banco pojo = (Banco)obj;
        addParametro("codigo", pojo.getCodigo(), 12);
        addParametro("codigo", pojo.getCodigo(), 12);
        addParametro("nome", pojo.getNome(), 12);
        addParametro("sigla", pojo.getSigla(), 12);
        addParametro("site", pojo.getSite(), 12);
    }
    @Override
    public String getNameTable()
    {
        return "bancos";
    }
    @Override
    public String getOrderCampo()
    {
        return "codigo";
    }
    @Override
    public Banco extract(ResultSet rs)
        throws Exception
    {
        Banco pojo = null;
        if(rs != null)
        {
            pojo = new Banco();
            pojo.setId(rsGetId(rs));
            pojo.setNome(rs.getString("nome"));
            pojo.setCodigo(rs.getString("codigo"));
            pojo.setSigla(rs.getString("sigla"));
            pojo.setSite(rs.getString("site"));
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
        String criterioCodigo = (String)input.get("criterioCodigo");
        if(criterioCodigo != null && !criterioCodigo.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("codigo")).append(" ilike '%").append(ConsultaUtil.normalize(criterioCodigo)).append("%'").toString());
        return lista;
    }

}
