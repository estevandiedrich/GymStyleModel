// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AvaliacaoFisica.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario, Protocolo

public class AvaliacaoFisica extends POJO
{

    public AvaliacaoFisica()
    {
    }

    public Protocolo getProtocolo()
    {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo)
    {
        this.protocolo = protocolo;
    }

    public Double getAxilarMedia()
    {
        return axilarMedia;
    }

    public void setAxilarMedia(Double auxilarMedia)
    {
        axilarMedia = auxilarMedia;
    }

    public Calendar getDataProximaAvaliacao()
    {
        return dataProximaAvaliacao;
    }

    public void setDataProximaAvaliacao(Calendar dataProximaAvaliacao)
    {
        this.dataProximaAvaliacao = dataProximaAvaliacao;
    }

    public Double getAbdomen()
    {
        return abdomen;
    }

    public void setAbdomen(Double abdomen)
    {
        this.abdomen = abdomen;
    }

    public Double getAbdominal()
    {
        return abdominal;
    }

    public void setAbdominal(Double abdominal)
    {
        this.abdominal = abdominal;
    }

    public Double getAltura()
    {
        return altura;
    }

    public void setAltura(Double altura)
    {
        this.altura = altura;
    }

    public Double getBracoDir()
    {
        return bracoDir;
    }

    public void setBracoDir(Double bracoDir)
    {
        this.bracoDir = bracoDir;
    }

    public Double getBracoEsq()
    {
        return bracoEsq;
    }

    public void setBracoEsq(Double bracoEsq)
    {
        this.bracoEsq = bracoEsq;
    }

    public Double getCintura()
    {
        return cintura;
    }

    public void setCintura(Double cintura)
    {
        this.cintura = cintura;
    }

    public Double getCoxa()
    {
        return coxa;
    }

    public void setCoxa(Double coxa)
    {
        this.coxa = coxa;
    }

    public Double getCoxaDir()
    {
        return coxaDir;
    }

    public void setCoxaDir(Double coxaDir)
    {
        this.coxaDir = coxaDir;
    }

    public Double getCoxaEsq()
    {
        return coxaEsq;
    }

    public void setCoxaEsq(Double coxaEsq)
    {
        this.coxaEsq = coxaEsq;
    }

    public Calendar getDataAvaliacao()
    {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Calendar dataAvaliacao)
    {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Double getGorduraAtual()
    {
        return gorduraAtual;
    }

    public void setGorduraAtual(Double gorduraAtual)
    {
        this.gorduraAtual = gorduraAtual;
    }

    public String getGorduraIdeal()
    {
        return gorduraIdeal;
    }

    public void setGorduraIdeal(String gorduraIdeal)
    {
        this.gorduraIdeal = gorduraIdeal;
    }

    public Double getImc()
    {
        return imc;
    }

    public void setImc(Double imc)
    {
        this.imc = imc;
    }

    public Usuario getInstrutor()
    {
        return instrutor;
    }

    public void setInstrutor(Usuario instrutor)
    {
        this.instrutor = instrutor;
    }

    public Double getMassaGorda()
    {
        return massaGorda;
    }

    public void setMassaGorda(Double massaGorda)
    {
        this.massaGorda = massaGorda;
    }

    public Double getMassaMagra()
    {
        return massaMagra;
    }

    public void setMassaMagra(Double massaMagra)
    {
        this.massaMagra = massaMagra;
    }

    public Double getPanturrilha()
    {
        return panturrilha;
    }

    public void setPanturrilha(Double panturrilha)
    {
        this.panturrilha = panturrilha;
    }

    public Double getPanturrilhaDir()
    {
        return panturrilhaDir;
    }

    public void setPanturrilhaDir(Double panturrilhaDir)
    {
        this.panturrilhaDir = panturrilhaDir;
    }

    public Double getPanturrilhaEsq()
    {
        return panturrilhaEsq;
    }

    public void setPanturrilhaEsq(Double panturrilhaEsq)
    {
        this.panturrilhaEsq = panturrilhaEsq;
    }

    public Double getPeitoral()
    {
        return peitoral;
    }

    public void setPeitoral(Double peitoral)
    {
        this.peitoral = peitoral;
    }

    public Double getPeso()
    {
        return peso;
    }

    public void setPeso(Double peso)
    {
        this.peso = peso;
    }

    public Double getQuadril()
    {
        return quadril;
    }

    public void setQuadril(Double quadril)
    {
        this.quadril = quadril;
    }

    public Double getSubescapular()
    {
        return subescapular;
    }

    public void setSubescapular(Double subescapular)
    {
        this.subescapular = subescapular;
    }

    public Double getSupraIliaca()
    {
        return supraIliaca;
    }

    public void setSupraIliaca(Double supraIliaca)
    {
        this.supraIliaca = supraIliaca;
    }

    public Double getTorax()
    {
        return torax;
    }

    public void setTorax(Double torax)
    {
        this.torax = torax;
    }

    public Double getTricipital()
    {
        return tricipital;
    }

    public void setTricipital(Double tricipital)
    {
        this.tricipital = tricipital;
    }

    private Calendar dataAvaliacao;
    private String descricao;
    private Double peso;
    private Double altura;
    private Double imc;
    private Double bracoDir;
    private Double bracoEsq;
    private Double coxaDir;
    private Double coxaEsq;
    private Double panturrilhaDir;
    private Double panturrilhaEsq;
    private Double torax;
    private Double quadril;
    private Double cintura;
    private Double abdomen;
    private Double subescapular;
    private Double tricipital;
    private Double peitoral;
    private Double abdominal;
    private Double supraIliaca;
    private Double coxa;
    private Double panturrilha;
    private Double axilarMedia;
    private Double gorduraAtual;
    private String gorduraIdeal;
    private Double massaMagra;
    private Double massaGorda;
    private Calendar dataProximaAvaliacao;
    private Usuario instrutor;
    private Protocolo protocolo;
}
