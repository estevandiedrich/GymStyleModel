// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroCaixaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.dao.RegistroCaixaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.RegistroCaixa;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.sql.Connection;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.service:
//            PagamentoService

public class RegistroCaixaService extends BaseService
{

    public RegistroCaixaService()
    {
    }

    public void create(RegistroCaixa pojo, Long fk)
    {
        DaoLocator.getRegistroCaixaDAO().create(pojo, fk);
    }

    public void update(RegistroCaixa pojo, Long fk)
    {
        DaoLocator.getRegistroCaixaDAO().update(pojo, fk);
    }

    public void updateRegContaBancariaFk(Long idContaBancariaFk, Long idRegFluxoCaixaFk)
    {
        DaoLocator.getRegistroCaixaDAO().updateRegContaBancariaFk(idContaBancariaFk, idRegFluxoCaixaFk);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getRegistroCaixaDAO().delete(conn, id);
    }

    public Boolean deleteByRegContaBancaria(Long id)
    {
        return DaoLocator.getRegistroCaixaDAO().deleteByIdRegContaBancaria(ConnectionManager.getInstance().getConnection(), id);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getRegistroCaixaDAO().disabled(conn, id);
    }
    @Override
    public List<RegistroCaixa> readByCriteria(Map input)
    {
        return DaoLocator.getRegistroCaixaDAO().readByCriteria(input);
    }

    public List readRegistrosByMes(int mes, int ano)
    {
        return DaoLocator.getRegistroCaixaDAO().readRegistrosByMes(mes, ano);
    }
    @Override
    public RegistroCaixa readById(Long id)
    {
        return (RegistroCaixa)DaoLocator.getRegistroCaixaDAO().readById(id);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getRegistroCaixaDAO().paginator(input);
    }

    public void registrarPagamento(Long idPagamento, Long idFluxoCaixa, String nomeUsuario)
    {
        Pagamento pojo = ServiceLocator.getPagamentoService().readById(idPagamento);
        RegistroCaixa registro = new RegistroCaixa();
        registro.setDataHora(Calendar.getInstance());
        registro.setDescricao((new StringBuilder()).append("PGTO - ").append(nomeUsuario).append(" Parcela (").append(CalendarUtil.getDateCalendar(pojo.getVencimento())).append(") - ").append(nomeUsuario).append("").toString());
        registro.setEntrada(Boolean.TRUE);
        registro.setRetirada(Boolean.FALSE);
        registro.setFormaDePagamento(pojo.getFormaDePagamento());
        registro.setParcela(pojo);
        registro.setRegistroContaBancaria(null);
        registro.setEdit(Boolean.valueOf(false));
        registro.setValor(pojo.getValorPago());
        DaoLocator.getRegistroCaixaDAO().create(registro, idFluxoCaixa);
    }

    public Map getMeses()
    {
        Map map = new HashMap();
        int i = 1;
        map.put(Long.valueOf(i++), "Janeiro");
        map.put(Long.valueOf(i++), "Fevereiro");
        map.put(Long.valueOf(i++), "Mar\347o");
        map.put(Long.valueOf(i++), "Abril");
        map.put(Long.valueOf(i++), "Maio");
        map.put(Long.valueOf(i++), "Junho");
        map.put(Long.valueOf(i++), "Julho");
        map.put(Long.valueOf(i++), "Agosto");
        map.put(Long.valueOf(i++), "Setembro");
        map.put(Long.valueOf(i++), "Outubro");
        map.put(Long.valueOf(i++), "Novembro");
        map.put(Long.valueOf(i++), "Dezembro");
        return map;
    }

    public Map getAnos()
    {
        Map map = new LinkedHashMap();
        for(int i = 2000; i <= 2050; i++)
            map.put(Long.valueOf(i), String.valueOf(i));

        return map;
    }

}
