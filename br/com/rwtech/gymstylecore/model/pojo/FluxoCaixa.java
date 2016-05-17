// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FluxoCaixa.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, RegistroCaixa, Usuario, Caixa

public class FluxoCaixa extends POJO
{

    public FluxoCaixa()
    {
        valorInicial = new Double(0.0D);
        valorFinal = new Double(0.0D);
        viDinheiro = new Double(0.0D);
        viCheque = new Double(0.0D);
        viCartao = new Double(0.0D);
        viBoleto = new Double(0.0D);
        viDeposito = new Double(0.0D);
        vfDinheiro = new Double(0.0D);
        vfCheque = new Double(0.0D);
        vfCartao = new Double(0.0D);
        vfBoleto = new Double(0.0D);
        vfDeposito = new Double(0.0D);
        valorEntrada = new Double(0.0D);
        valorRetirada = new Double(0.0D);
    }

    public Usuario getUsuarioAbriu()
    {
        return usuarioAbriu;
    }

    public void setUsuarioAbriu(Usuario usuarioAbriu)
    {
        this.usuarioAbriu = usuarioAbriu;
    }

    public Usuario getUsuarioFechou()
    {
        return usuarioFechou;
    }

    public void setUsuarioFechou(Usuario usuarioFechou)
    {
        this.usuarioFechou = usuarioFechou;
    }

    public void setValorEntrada(Double valorEntrada)
    {
        this.valorEntrada = valorEntrada;
    }

    public void setValorRetirada(Double valorRetirada)
    {
        this.valorRetirada = valorRetirada;
    }

    public void setValorInicial(Double valorInicial)
    {
        this.valorInicial = valorInicial;
    }

    public void setValorFinal(Double valorFinal)
    {
        this.valorFinal = valorFinal;
    }

    public void setViDinheiro(Double viDinheiro)
    {
        this.viDinheiro = viDinheiro;
    }

    public void setViCheque(Double viCheque)
    {
        this.viCheque = viCheque;
    }

    public void setViCartao(Double viCartao)
    {
        this.viCartao = viCartao;
    }

    public void setViBoleto(Double viBoleto)
    {
        this.viBoleto = viBoleto;
    }

    public void setViDeposito(Double viDeposito)
    {
        this.viDeposito = viDeposito;
    }

    public void setVfDinheiro(Double vfDinheiro)
    {
        this.vfDinheiro = vfDinheiro;
    }

    public void setVfCheque(Double vfCheque)
    {
        this.vfCheque = vfCheque;
    }

    public void setVfCartao(Double vfCartao)
    {
        this.vfCartao = vfCartao;
    }

    public void setVfBoleto(Double vfBoleto)
    {
        this.vfBoleto = vfBoleto;
    }

    public void setVfDeposito(Double vfDeposito)
    {
        this.vfDeposito = vfDeposito;
    }

    public void setCaixa(Caixa caixa)
    {
        this.caixa = caixa;
    }

    public void setAbertura(Calendar abertura)
    {
        this.abertura = abertura;
    }

    public void setFechamento(Calendar fechamento)
    {
        this.fechamento = fechamento;
    }

    public Double getVfDeposito()
    {
        return vfDeposito;
    }

    public Double getViCheque()
    {
        return viCheque;
    }

    public Double getViCartao()
    {
        return viCartao;
    }

    public Double getViBoleto()
    {
        return viBoleto;
    }

    public Double getViDeposito()
    {
        return viDeposito;
    }

    public Double getValorFinal()
    {
        valorFinal = Double.valueOf((valorInicial.doubleValue() + valorEntrada.doubleValue()) - valorRetirada.doubleValue());
        return valorFinal;
    }

    public Double getViDinheiro()
    {
        return viDinheiro;
    }

    public Double getVfDinheiro()
    {
        return vfDinheiro;
    }

    public Double getVfCheque()
    {
        return vfCheque;
    }

    public Double getVfCartao()
    {
        return vfCartao;
    }

    public Calendar getAbertura()
    {
        return abertura;
    }

    public Calendar getFechamento()
    {
        return fechamento;
    }

    public Double getValorInicial()
    {
        return valorInicial;
    }

    public Double getVfBoleto()
    {
        return vfBoleto;
    }

    public Double getValorRetirada()
    {
        return valorRetirada;
    }

    public Caixa getCaixa()
    {
        return caixa;
    }

    public Double getValorEntrada()
    {
        return valorEntrada;
    }

    public List<RegistroCaixa> getRegistros()
    {
        return registros;
    }

    public void setRegistros(List<RegistroCaixa> registros)
    {
        valorEntrada = new Double(0.0D);
        valorRetirada = new Double(0.0D);
        Iterator i$ = registros.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            RegistroCaixa regCaixa = (RegistroCaixa)i$.next();
            if(regCaixa.getEntrada().booleanValue())
            {
                FluxoCaixa fluxocaixa = this;
                fluxocaixa.valorEntrada = Double.valueOf(fluxocaixa.valorEntrada.doubleValue() + regCaixa.getValor().doubleValue());
            } else
            if(regCaixa.getRetirada().booleanValue())
            {
                FluxoCaixa fluxocaixa1 = this;
                fluxocaixa1.valorRetirada = Double.valueOf(fluxocaixa1.valorRetirada.doubleValue() + regCaixa.getValor().doubleValue());
            }
        } while(true);
        this.registros = registros;
        setValorFinal(Double.valueOf(valorEntrada.doubleValue() - valorRetirada.doubleValue()));
    }

    private Calendar abertura;
    private Calendar fechamento;
    private Usuario usuarioAbriu;
    private Usuario usuarioFechou;
    private Double valorInicial;
    private Double valorFinal;
    private Double viDinheiro;
    private Double viCheque;
    private Double viCartao;
    private Double viBoleto;
    private Double viDeposito;
    private Double vfDinheiro;
    private Double vfCheque;
    private Double vfCartao;
    private Double vfBoleto;
    private Double vfDeposito;
    private Caixa caixa;
    private List<RegistroCaixa> registros;
    private Double valorEntrada;
    private Double valorRetirada;
}
