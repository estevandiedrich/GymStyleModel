// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FornecedorDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Fornecedor;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import java.sql.ResultSet;
import java.util.*;

public class FornecedorDAO extends BaseDAO
{

    public FornecedorDAO()
    {
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Fornecedor pojo = (Fornecedor)obj;
        addParametro("nome", pojo.getNome(), 12);
        addParametro("cidade", pojo.getCidade(), 12);
        addParametro("estado", pojo.getEstado(), 12);
        addParametro("telefone", pojo.getTelefone(), 12);
        addParametro("ativo", pojo.getAtivo(), 16);
    }
    @Override
    public Fornecedor extract(ResultSet rs)
        throws Exception
    {
        Fornecedor pojo = null;
        if(rs != null)
        {
            pojo = new Fornecedor();
            pojo.setId(rsGetId(rs));
            pojo.setNome(rs.getString("nome"));
            pojo.setCidade(rs.getString("cidade"));
            pojo.setEstado(rs.getString("estado"));
            pojo.setTelefone(rs.getString("telefone"));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
        }
        return pojo;
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "fornecedores";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        lista.add(Filtro.criterioNome((String)input.get(Filtro.CRITERIO_NOME)));
        lista.add(Filtro.criterioAtivo((String)input.get(Filtro.CRITERIO_ATIVO)));
        return lista;
    }
}
