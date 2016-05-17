// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Plano.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, DuracaoPlano

public class Plano extends POJO
{

    public Plano()
    {
    }

    public Integer getQtdeAcessos()
    {
        return qtdeAcessos;
    }

    public void setQtdeAcessos(Integer qtdeAcessos)
    {
        this.qtdeAcessos = qtdeAcessos;
    }

    public Boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }

    public Boolean getCancelado()
    {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado)
    {
        this.cancelado = cancelado;
    }

    public Integer getDescontoPercentual()
    {
        return descontoPercentual;
    }

    public void setDescontoPercentual(Integer descontoPercentual)
    {
        this.descontoPercentual = descontoPercentual;
    }

    public Double getDescontoReal()
    {
        return descontoReal;
    }

    public void setDescontoReal(Double descontoReal)
    {
        this.descontoReal = descontoReal;
    }

    public DuracaoPlano getDuracaoPlano()
    {
        return duracaoPlano;
    }

    public void setDuracaoPlano(DuracaoPlano duracaoPlano)
    {
        this.duracaoPlano = duracaoPlano;
    }

    public Boolean getFinalizado()
    {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado)
    {
        this.finalizado = finalizado;
    }

    public Long getIdPlanoUsuario()
    {
        return idPlanoUsuario;
    }

    public void setIdPlanoUsuario(Long idPlanoUsuario)
    {
        this.idPlanoUsuario = idPlanoUsuario;
    }

    public List<Modalidade> getModalidades()
    {
        return modalidades;
    }

    public void setModalidades(List<Modalidade> modalidades)
    {
        this.modalidades = modalidades;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public List<Pagamento> getPagamentos()
    {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos)
    {
        this.pagamentos = pagamentos;
    }

    public String getPlano()
    {
        return plano;
    }

    public void setPlano(String plano)
    {
        this.plano = plano;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public Double getValorCancelado()
    {
        return valorCancelado;
    }

    public void setValorCancelado(Double valorCancelado)
    {
        this.valorCancelado = valorCancelado;
    }

    public Double getValorMatricula()
    {
        return valorMatricula;
    }

    public void setValorMatricula(Double valorMatricula)
    {
        this.valorMatricula = valorMatricula;
    }

    public Double getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal)
    {
        this.valorTotal = valorTotal;
    }

    private Long idPlanoUsuario;
    private String plano;
    private Double valorTotal;
    private Double valorMatricula;
    private Double valor;
    private Integer descontoPercentual;
    private Double descontoReal;
    private String observacao;
    private Boolean ativo;
    private List<Modalidade> modalidades;
    private DuracaoPlano duracaoPlano;
    private List<Pagamento> pagamentos;
    private Double valorCancelado;
    private Boolean cancelado;
    private Boolean finalizado;
    private Integer qtdeAcessos;
}
