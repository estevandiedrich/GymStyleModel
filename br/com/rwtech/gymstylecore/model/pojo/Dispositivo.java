// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Dispositivo.java

package br.com.rwtech.gymstylecore.model.pojo;

import br.com.rwtech.gymstylecore.model.pojo.tipos.ModoAcesso;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, ModoOperacao

public class Dispositivo extends POJO
{

    public ModoAcesso getModoAcesso()
    {
        return modoAcesso;
    }

    public void setModoAcesso(ModoAcesso modoAcesso)
    {
        this.modoAcesso = modoAcesso;
    }

    public Boolean getImprime()
    {
        return imprime;
    }

    public void setImprime(Boolean imprime)
    {
        this.imprime = imprime;
    }

    public Integer getEntradaDirEsq()
    {
        return entradaDirEsq;
    }

    public void setEntradaDirEsq(Integer entradaDirEsq)
    {
        this.entradaDirEsq = entradaDirEsq;
    }

    public ModoOperacao getModoOperacao()
    {
        return modoOperacao;
    }

    public void setModoOperacao(ModoOperacao modoOperacao)
    {
        this.modoOperacao = modoOperacao;
    }

    public Integer getPorta()
    {
        return porta;
    }

    public void setPorta(Integer porta)
    {
        this.porta = porta;
    }

    public Dispositivo()
    {
    }

    public String getEnderecoIp()
    {
        return enderecoIp;
    }

    public Boolean foraDaRede()
    {
        if(enderecoIp != null && enderecoIp.equalsIgnoreCase("0.0.0.0"))
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public void setEnderecoIp(String enderecoIp)
    {
        this.enderecoIp = enderecoIp;
    }

    public String getDispositivo()
    {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo)
    {
        this.dispositivo = dispositivo;
    }

    public Boolean getOnline()
    {
        return online;
    }

    public void setOnline(Boolean online)
    {
        this.online = online;
    }

    public String toString()
    {
        if(dispositivo != null)
            return getDispositivo().toString();
        else
            return enderecoIp.toString();
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Dispositivo other = (Dispositivo)obj;
        if(dispositivo != null ? !dispositivo.equals(other.dispositivo) : other.dispositivo != null)
            return false;
        return enderecoIp != null ? enderecoIp.equals(other.enderecoIp) : other.enderecoIp == null;
    }

    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    public String getMac()
    {
        return mac;
    }

    public void setMac(String mac)
    {
        this.mac = mac;
    }

    private String dispositivo;
    private Boolean online;
    private String enderecoIp;
    private String mac;
    private Integer porta;
    private ModoOperacao modoOperacao;
    private Integer entradaDirEsq;
    private Boolean imprime;
    private ModoAcesso modoAcesso;
}
