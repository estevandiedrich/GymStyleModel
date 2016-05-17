// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroCaixa.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario, FormaDePagamento, Pagamento, 
//            RegistroContaBancaria

public class RegistroCaixa extends POJO
{

    public RegistroCaixa()
    {
        edit = Boolean.TRUE;
        dentroPeriodo = Boolean.FALSE;
    }

    public Usuario getUsuarioRegistrou()
    {
        return usuarioRegistrou;
    }

    public void setUsuarioRegistrou(Usuario usuarioRegistrou)
    {
        this.usuarioRegistrou = usuarioRegistrou;
    }

    public Boolean getDentroPeriodo()
    {
        return dentroPeriodo;
    }

    public void setDentroPeriodo(Boolean dentro)
    {
        dentroPeriodo = dentro;
    }

    public Boolean getEdit()
    {
        return edit;
    }

    public void setEdit(Boolean edit)
    {
        this.edit = edit;
    }

    public Calendar getDataHora()
    {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora)
    {
        this.dataHora = dataHora;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Boolean getEntrada()
    {
        return entrada;
    }

    public void setEntrada(Boolean entrada)
    {
        this.entrada = entrada;
    }

    public Boolean getRetirada()
    {
        return retirada;
    }

    public void setRetirada(Boolean retirada)
    {
        this.retirada = retirada;
    }

    public FormaDePagamento getFormaDePagamento()
    {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento)
    {
        this.formaDePagamento = formaDePagamento;
    }

    public Pagamento getParcela()
    {
        return parcela;
    }

    public void setParcela(Pagamento parcela)
    {
        this.parcela = parcela;
    }

    public RegistroContaBancaria getRegistroContaBancaria()
    {
        return registroContaBancaria;
    }

    public void setRegistroContaBancaria(RegistroContaBancaria registroContaBancaria)
    {
        this.registroContaBancaria = registroContaBancaria;
    }

    private Usuario usuarioRegistrou;
    private Calendar dataHora;
    private Double valor;
    private String descricao;
    private Boolean entrada;
    private Boolean retirada;
    private Boolean edit;
    private Boolean dentroPeriodo;
    private FormaDePagamento formaDePagamento;
    private Pagamento parcela;
    private RegistroContaBancaria registroContaBancaria;
}
