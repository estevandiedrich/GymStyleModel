// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PagamentoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.PagamentoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import java.util.Map;

public class PagamentoService
{

    public PagamentoService()
    {
    }

    public void createNewPagamento(Long idUtimoPagamento)
    {
        DaoLocator.getPagamentoDAO().createNewPagamento(ConnectionManager.getInstance().getConnection(), idUtimoPagamento);
    }

    public void update(Pagamento pagamento)
    {
        DaoLocator.getPagamentoDAO().update(ConnectionManager.getInstance().getConnection(), pagamento);
    }

    public void updateImprimir(Boolean imprime, int status, Long id)
    {
        DaoLocator.getPagamentoDAO().updateImprimir(imprime, status, id);
    }

    public Boolean readStatusImprimirPagamento(Long id)
    {
        return DaoLocator.getPagamentoDAO().readStatusImprimirPagamento(id);
    }

    public void updateImprimirEntrada(Boolean status, Long id)
    {
        DaoLocator.getPagamentoDAO().updateImprimirEntrada(status, id);
    }

    public void create(Pagamento pagamento, Long idPlanoUsuario)
    {
        DaoLocator.getPagamentoDAO().create(ConnectionManager.getInstance().getConnection(), pagamento, idPlanoUsuario);
    }

    public Pagamento readById(Long id)
    {
        return DaoLocator.getPagamentoDAO().readById(id);
    }

    public Long readIdPlanoUsuarioByIdPagamento(Long id)
    {
        return DaoLocator.getPagamentoDAO().readIdPlanoUsuarioByIdPagamento(id);
    }

    public void deletePagamentosNULLByIdPlanoUsuario(Long idPlanoUsuario)
    {
        DaoLocator.getPagamentoDAO().deletePagamentosNULLByIdPlanoUsuario(ConnectionManager.getInstance().getConnection(), idPlanoUsuario);
    }

    public Map paginatorPagamentos(Map input)
    {
        return DaoLocator.getPagamentoDAO().paginatorPagamentos(input);
    }
}
