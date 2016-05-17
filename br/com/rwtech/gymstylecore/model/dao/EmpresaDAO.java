// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EmpresaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.Empresa;
import java.sql.ResultSet;

public class EmpresaDAO extends BaseDAO
{

    public EmpresaDAO()
    {
    }
    @Override
    public String getNameTable()
    {
        return "empresas";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Empresa pojo = (Empresa)obj;
        addParametro("telefone", pojo.getTelefone(), 12);
        addParametro("bairro", pojo.getBairro(), 12);
        addParametro("cidade", pojo.getCidade(), 12);
        addParametro("cep", pojo.getCep(), 12);
        addParametro("cnpj", pojo.getCnpj(), 12);
        addParametro("endereco", pojo.getEndereco(), 12);
        addParametro("nome_fantasia", pojo.getNomeFantasia(), 12);
        addParametro("razao_social", pojo.getRazaoSocial(), 12);
        addParametro("uf", pojo.getUf(), 12);
    }
    @Override
    public Empresa extract(ResultSet rs)
        throws Exception
    {
        Empresa pojo = null;
        if(rs != null)
        {
            pojo = new Empresa();
            pojo.setId(rsGetId(rs));
            pojo.setRazaoSocial(rs.getString("razao_social"));
            pojo.setTelefone(rs.getString("telefone"));
            pojo.setBairro(rs.getString("bairro"));
            pojo.setCep(rs.getString("cep"));
            pojo.setCidade(rs.getString("cidade"));
            pojo.setCnpj(rs.getString("cnpj"));
            pojo.setEndereco(rs.getString("endereco"));
            pojo.setNomeFantasia(rs.getString("nome_fantasia"));
            pojo.setUf(rs.getString("uf"));
        }
        return pojo;
    }
}
