// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContaBancaria.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Banco

public class ContaBancaria extends POJO
{

    public ContaBancaria()
    {
    }

    public List<RegistroCaixa> getRegistros()
    {
        return registros;
    }

    public void setRegistros(List<RegistroCaixa> registros)
    {
        this.registros = registros;
    }

    public String getAgencia()
    {
        return agencia;
    }

    public void setAgencia(String agencia)
    {
        this.agencia = agencia;
    }

    public Integer getNumeroAgencia()
    {
        if(agencia.isEmpty())
            return Integer.valueOf(0);
        else
            return Integer.valueOf(agencia);
    }

    public String getTitular()
    {
        return titular;
    }

    public void setTitular(String titular)
    {
        this.titular = titular;
    }

    public String getNumeroConta()
    {
        return numeroConta;
    }

    public Integer getValorNumeroConta()
    {
        if(numeroConta.isEmpty())
            return Integer.valueOf(0);
        else
            return Integer.valueOf(numeroConta);
    }

    public void setNumeroConta(String numeroConta)
    {
        this.numeroConta = numeroConta;
    }

    public Banco getBanco()
    {
        return banco;
    }

    public void setBanco(Banco banco)
    {
        this.banco = banco;
    }

    private String agencia;
    private String titular;
    private String numeroConta;
    private Banco banco;
    private List<RegistroCaixa> registros;
}
