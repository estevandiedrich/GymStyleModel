// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegistroCaixaReport.java

package br.com.rwtech.gymstylecore.model.pojo.report;


public class RegistroCaixaReport
{

    public RegistroCaixaReport()
    {
        acimaDataAtual = Boolean.valueOf(false);
    }

    public RegistroCaixaReport(int dia, Double saldoInicial, Double entrada, Double retirada, Double saldoDia, Double saldoAcumulado, Boolean acimaDataAtual)
    {
        this.acimaDataAtual = Boolean.valueOf(false);
        this.dia = dia;
        this.saldoInicial = saldoInicial;
        this.entrada = entrada;
        this.retirada = retirada;
        this.saldoDia = saldoDia;
        this.saldoAcumulado = saldoAcumulado;
        this.acimaDataAtual = acimaDataAtual;
    }

    public Boolean getAcimaDataAtual()
    {
        return acimaDataAtual;
    }

    public void setAcimaDataAtual(Boolean acimaDataAtual)
    {
        this.acimaDataAtual = acimaDataAtual;
    }

    public int getDia()
    {
        return dia;
    }

    public void setDia(int dia)
    {
        this.dia = dia;
    }

    public Double getSaldoInicial()
    {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial)
    {
        this.saldoInicial = saldoInicial;
    }

    public Double getEntrada()
    {
        return entrada;
    }

    public void setEntrada(Double entrada)
    {
        this.entrada = entrada;
    }

    public Double getRetirada()
    {
        return retirada;
    }

    public void setRetirada(Double retirada)
    {
        this.retirada = retirada;
    }

    public Double getSaldoDia()
    {
        return saldoDia;
    }

    public void setSaldoDia(Double saldoDia)
    {
        this.saldoDia = saldoDia;
    }

    public Double getSaldoAcumulado()
    {
        return saldoAcumulado;
    }

    public void setSaldoAcumulado(Double saldoAcumulado)
    {
        this.saldoAcumulado = saldoAcumulado;
    }

    private int dia;
    private Boolean acimaDataAtual;
    private Double saldoInicial;
    private Double entrada;
    private Double retirada;
    private Double saldoDia;
    private Double saldoAcumulado;
}
