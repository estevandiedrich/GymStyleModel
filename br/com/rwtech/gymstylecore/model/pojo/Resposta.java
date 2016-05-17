// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Resposta.java

package br.com.rwtech.gymstylecore.model.pojo;

import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class Resposta extends POJO
{

    public Resposta()
    {
    }

    public Calendar getDataHora()
    {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora)
    {
        this.dataHora = dataHora;
    }

    public Long getDestino()
    {
        return destino;
    }

    public void setDestino(Long destino)
    {
        this.destino = destino;
    }

    public String getMensagemErro()
    {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro)
    {
        this.mensagemErro = mensagemErro;
    }

    public TipoRequisicaoResposta getTipoResposta()
    {
        return tipoResposta;
    }

    public void setTipoResposta(TipoRequisicaoResposta tipoResposta)
    {
        this.tipoResposta = tipoResposta;
    }

    private Long destino;
    private String mensagemErro;
    private Calendar dataHora;
    private TipoRequisicaoResposta tipoResposta;
}
