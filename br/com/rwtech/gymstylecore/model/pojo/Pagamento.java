// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pagamento.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario, FormaDePagamento

public class Pagamento extends POJO
{

    public Pagamento()
    {
    }

    public Usuario getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario)
    {
        this.funcionario = funcionario;
    }

    public Boolean getImprimir()
    {
        return imprimir;
    }

    public void setImprimir(Boolean imprimir)
    {
        this.imprimir = imprimir;
    }

    public Boolean getImprimirEntrada()
    {
        return imprimirEntrada;
    }

    public void setImprimirEntrada(Boolean imprimirEntrada)
    {
        this.imprimirEntrada = imprimirEntrada;
    }

    public Boolean getPostergar()
    {
        return postergar;
    }

    public void setPostergar(Boolean postergar)
    {
        this.postergar = postergar;
    }

    public Double getDesconto()
    {
        return desconto;
    }

    public void setDesconto(Double desconto)
    {
        this.desconto = desconto;
    }

    public Calendar getFimAcesso()
    {
        return fimAcesso;
    }

    public void setFimAcesso(Calendar fimAcesso)
    {
        this.fimAcesso = fimAcesso;
    }

    public FormaDePagamento getFormaDePagamento()
    {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento)
    {
        this.formaDePagamento = formaDePagamento;
    }

    public Calendar getInicioAcesso()
    {
        return inicioAcesso;
    }

    public void setInicioAcesso(Calendar inicioAcesso)
    {
        this.inicioAcesso = inicioAcesso;
    }

    public String getJustificativa()
    {
        return justificativa;
    }

    public void setJustificativa(String justificativa)
    {
        this.justificativa = justificativa;
    }

    public Double getMulta()
    {
        return multa;
    }

    public void setMulta(Double multa)
    {
        this.multa = multa;
    }

    public Integer getNumeroParcela()
    {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela)
    {
        this.numeroParcela = numeroParcela;
    }

    public Calendar getPagamento()
    {
        return pagamento;
    }

    public void setPagamento(Calendar pagamento)
    {
        this.pagamento = pagamento;
    }

    public Integer getTolerancia()
    {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia)
    {
        this.tolerancia = tolerancia;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public Double getValorPago()
    {
        return valorPago;
    }

    public void setValorPago(Double valorPago)
    {
        this.valorPago = valorPago;
    }

    public Calendar getVencimento()
    {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento)
    {
        this.vencimento = vencimento;
    }

    private Calendar inicioAcesso;
    private Calendar fimAcesso;
    private Calendar vencimento;
    private Calendar pagamento;
    private Integer numeroParcela;
    private Integer tolerancia;
    private Double desconto;
    private Double multa;
    private String justificativa;
    private Double valor;
    private Double valorPago;
    private Boolean postergar;
    private Boolean imprimir;
    private Boolean imprimirEntrada;
    private Usuario funcionario;
    private FormaDePagamento formaDePagamento;
}
