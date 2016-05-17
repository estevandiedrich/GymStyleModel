// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroContaBancariaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.dao.RegistroContaBancariaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.RegistroCaixa;
import br.com.rwtech.gymstylecore.model.pojo.RegistroContaBancaria;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.sql.Connection;
import java.util.*;

public class RegistroContaBancariaService extends BaseService
{

    public RegistroContaBancariaService()
    {
    }

    public void create(RegistroContaBancaria pojo, Long fk)
    {
        DaoLocator.getRegistroContaBancariaDAO().create(pojo, fk);
    }

    public void update(RegistroContaBancaria pojo, Long fk)
    {
        DaoLocator.getRegistroContaBancariaDAO().update(pojo, fk);
    }

    public void updateRegContaBancariaFk(Long idRegContaBancaria, Long idRegFluxoCaixafk)
    {
        DaoLocator.getRegistroContaBancariaDAO().updateRegContaBancariaFk(idRegContaBancaria, idRegFluxoCaixafk);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getRegistroContaBancariaDAO().delete(conn, id);
    }

    public Boolean deleteByRegCaixa(Long id)
    {
        return DaoLocator.getRegistroContaBancariaDAO().deleteByIdRegFluxoCaixa(ConnectionManager.getInstance().getConnection(), id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getRegistroContaBancariaDAO().disabled(conn, id);
    }
    @Override
    public List<RegistroCaixa> readByCriteria(Map input)
    {
        return DaoLocator.getRegistroContaBancariaDAO().readByCriteria(input);
    }
    @Override
    public RegistroContaBancaria readById(Long id)
    {
        return (RegistroContaBancaria)DaoLocator.getRegistroContaBancariaDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getRegistroContaBancariaDAO().paginator(input);
    }

    public void registrarPagamento(Pagamento pojo, Long idFluxoContaBancaria, String nomeUsuario)
    {
        RegistroContaBancaria registro = new RegistroContaBancaria();
        registro.setDataHora(Calendar.getInstance());
        registro.setDescricao((new StringBuilder()).append("PGTO - ").append(nomeUsuario).append(" Parcela (").append(CalendarUtil.getDateCalendar(pojo.getVencimento())).append(") - ").append(nomeUsuario).append("").toString());
        registro.setEntrada(Boolean.TRUE);
        registro.setRetirada(Boolean.FALSE);
        registro.setFormaDePagamento(pojo.getFormaDePagamento());
        registro.setEdit(Boolean.valueOf(false));
        registro.setValor(pojo.getValorPago());
        DaoLocator.getRegistroContaBancariaDAO().create(registro, idFluxoContaBancaria);
    }

}
