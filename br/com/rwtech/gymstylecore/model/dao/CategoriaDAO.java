// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CategoriaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Categoria;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import java.sql.ResultSet;
import java.util.*;

public class CategoriaDAO extends BaseDAO
{

    public CategoriaDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "categorias";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Categoria pojo = (Categoria)obj;
        addParametro("nome", pojo.getNome(), 12);
        addParametro("ativo", pojo.getAtivo(), 16);
    }
    @Override
    public Categoria extract(ResultSet rs)
        throws Exception
    {
        Categoria pojo = null;
        if(rs != null)
        {
            pojo = new Categoria();
            pojo.setId(rsGetId(rs));
            pojo.setNome(rs.getString("nome"));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
        }
        return pojo;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioUsuario = (String)input.get(Filtro.CRITERIO_NOME);
        if(criterioUsuario != null && !criterioUsuario.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioUsuario)).append("%'").toString());
        String criterioDiferenteId = (String)input.get(Filtro.CRITERIO_DIFERENTE_ID);
        if(criterioDiferenteId != null && !criterioDiferenteId.isEmpty())
            lista.add((new StringBuilder()).append(" id_categorias <>").append(criterioDiferenteId).toString());
        String criterioAtivo = (String)input.get(Filtro.CRITERIO_ATIVO);
        if(criterioAtivo != null && !criterioAtivo.isEmpty())
            lista.add((new StringBuilder()).append(" ativo = ").append(criterioAtivo).toString());
        return lista;
    }
}
