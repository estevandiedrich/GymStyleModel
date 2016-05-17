// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContaBancariaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.pojo.Banco;
import br.com.rwtech.gymstylecore.model.pojo.ContaBancaria;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.ResultSet;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            BancoDAO

public class ContaBancariaDAO extends BaseDAO
{

    public ContaBancariaDAO()
    {
    }

    public String getORDER_CAMPO()
    {
        return "nome";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	ContaBancaria pojo = (ContaBancaria)obj;
        addParametro("agencia", pojo.getAgencia(), 12);
        addParametro("numero_conta", pojo.getNumeroConta(), 12);
        addParametro("titular", pojo.getTitular(), 12);
        addParametro("id_bancos_fk", pojo.getBanco().getId(), -5);
    }
    @Override
    public String getNameTable()
    {
        return "contas_bancarias";
    }
    @Override
    public ContaBancaria extract(ResultSet rs)
        throws Exception
    {
        ContaBancaria pojo = null;
        if(rs != null)
        {
            pojo = new ContaBancaria();
            pojo.setId(rsGetId(rs));
            pojo.setAgencia(rs.getString("agencia"));
            pojo.setNumeroConta(rs.getString("numero_conta"));
            pojo.setTitular(rs.getString("titular"));
            pojo.setBanco((Banco)DaoLocator.getBancoDAO().readById(Long.valueOf(rs.getLong("id_bancos_fk"))));
        }
        return pojo;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioAtivo = (String)input.get("criterioAtivo");
        if(criterioAtivo != null && !criterioAtivo.isEmpty())
            lista.add((new StringBuilder()).append("ativo = ").append(criterioAtivo).toString());
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append("id_bancos_fk in(select id_bancos from bancos where true and ").append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%')").toString());
        String criterioTitular = (String)input.get("criterioTitular");
        if(criterioTitular != null && !criterioTitular.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("titular")).append(" ilike '%").append(ConsultaUtil.normalize(criterioTitular)).append("%'").toString());
        return lista;
    }

}
