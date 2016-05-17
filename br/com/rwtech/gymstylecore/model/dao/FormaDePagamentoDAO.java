// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FormaDePagamentoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.FormaDePagamento;
import java.sql.ResultSet;

public class FormaDePagamentoDAO extends BaseDAO
{

    public FormaDePagamentoDAO()
    {
    }
    @Override
    public FormaDePagamento extract(ResultSet rs)
        throws Exception
    {
        FormaDePagamento pojo = null;
        if(rs != null)
        {
            pojo = new FormaDePagamento();
            pojo.setId(rsGetId(rs));
            pojo.setFormaDePagamento(rs.getString("nome"));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "formas_pagamento";
    }
}
