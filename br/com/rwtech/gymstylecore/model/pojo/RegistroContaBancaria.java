// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroContaBancaria.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario, FormaDePagamento, ContaBancaria

public class RegistroContaBancaria extends POJO
{

    public RegistroContaBancaria()
    {
        edit = Boolean.TRUE;
    }

    public Usuario getUsuarioRegistrou()
    {
        return usuarioRegistrou;
    }

    public void setUsuarioRegistrou(Usuario usuarioRegistrou)
    {
        this.usuarioRegistrou = usuarioRegistrou;
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

    public Boolean getEdit()
    {
        return edit;
    }

    public void setEdit(Boolean edit)
    {
        this.edit = edit;
    }

    public FormaDePagamento getFormaDePagamento()
    {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento)
    {
        this.formaDePagamento = formaDePagamento;
    }

    public Long getRegistroCaixa()
    {
        return registroCaixa;
    }

    public void setRegistroCaixa(Long registroCaixa)
    {
        this.registroCaixa = registroCaixa;
    }

    public ContaBancaria getContaBancaria()
    {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria)
    {
        this.contaBancaria = contaBancaria;
    }

    private Usuario usuarioRegistrou;
    private Calendar dataHora;
    private Double valor;
    private String descricao;
    private Boolean entrada;
    private Boolean retirada;
    private Boolean edit;
    private FormaDePagamento formaDePagamento;
    private Long registroCaixa;
    private ContaBancaria contaBancaria;
}
