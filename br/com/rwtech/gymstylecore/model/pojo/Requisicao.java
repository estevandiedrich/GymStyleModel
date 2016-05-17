// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Requisicao.java

package br.com.rwtech.gymstylecore.model.pojo;

import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import java.util.Calendar;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Usuario

public class Requisicao extends POJO
{

    public Requisicao()
    {
    }

    public Requisicao(Object parametro, Object referencia, Boolean status, Long destino, Usuario operador, TipoRequisicaoResposta tipo)
    {
        this.referencia = referencia;
        this.parametro = parametro;
        this.status = status;
        this.destino = destino;
        this.operador = operador;
        this.tipo = tipo;
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

    public Usuario getOperador()
    {
        return operador;
    }

    public void setOperador(Usuario operador)
    {
        this.operador = operador;
    }

    public Object getParametro()
    {
        return parametro;
    }

    public void setParametro(Object parametro)
    {
        this.parametro = parametro;
    }

    public Object getReferencia()
    {
        return referencia;
    }

    public void setReferencia(Object referencia)
    {
        this.referencia = referencia;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public TipoRequisicaoResposta getTipo()
    {
        return tipo;
    }

    public void setTipo(TipoRequisicaoResposta tipo)
    {
        this.tipo = tipo;
    }

    public static TipoRequisicaoResposta getTipo(Integer valor)
    {
        TipoRequisicaoResposta tipo = null;
        if(valor != null)
            switch(valor.intValue())
            {
            case 0: // '\0'
                tipo = TipoRequisicaoResposta.DESCOBRIR_DISPOSITIVOS_NA_REDE;
                break;

            case 1: // '\001'
                tipo = TipoRequisicaoResposta.CADASTRAR_USUARIO;
                break;

            case 2: // '\002'
                tipo = TipoRequisicaoResposta.ATUALIZAR_USUARIO;
                break;

            case 3: // '\003'
                tipo = TipoRequisicaoResposta.EXCLUIR_USUARIO;
                break;

            case 4: // '\004'
                tipo = TipoRequisicaoResposta.LIBERAR_BLOQUEAR_ACESSO;
                break;

            case 5: // '\005'
                tipo = TipoRequisicaoResposta.VERIFICAR_EVENTOS_OFFLINE;
                break;

            case 6: // '\006'
                tipo = TipoRequisicaoResposta.CONFIGURAR_HORA;
                break;

            case 7: // '\007'
                tipo = TipoRequisicaoResposta.CONFIGURAR_MODO_OPERACAO;
                break;

            case 8: // '\b'
                tipo = TipoRequisicaoResposta.LIBERAR_ACESSO_INDEFINIDAMENTE;
                break;

            case 9: // '\t'
                tipo = TipoRequisicaoResposta.BLOQUEAR_ACESSO_INDEFINIDAMENTE;
                break;

            case 10: // '\n'
                tipo = TipoRequisicaoResposta.LIBERAR_UM_ACESSO;
                break;

            case 11: // '\013'
                tipo = TipoRequisicaoResposta.OBTER_IMPRESSAO_DIGITAL;
                break;

            case 12: // '\f'
                tipo = TipoRequisicaoResposta.CANCELAR_IMPRESSAO_DIGITAL;
                break;

            case 13: // '\r'
                tipo = TipoRequisicaoResposta.CONFIGURAR_ALARME_USUARIO;
                break;

            case 14: // '\016'
                tipo = TipoRequisicaoResposta.CONFIGURAR_ALARME_VIOLACAO;
                break;

            case 15: // '\017'
                tipo = TipoRequisicaoResposta.CONFIGURAR_ALARME_OBSTRUCAO;
                break;

            case 16: // '\020'
                tipo = TipoRequisicaoResposta.CONFIGURAR_ALARME_TAIL_GATE;
                break;

            case 17: // '\021'
                tipo = TipoRequisicaoResposta.CONFIGURAR_ALARME_DE_SOLENOIDE;
                break;

            case 18: // '\022'
                tipo = TipoRequisicaoResposta.CONFIGURAR_ALARME_DE_PASSAGEM;
                break;

            case 19: // '\023'
                tipo = TipoRequisicaoResposta.ATIVIDADE_SUSPEITA;
                break;

            case 20: // '\024'
                tipo = TipoRequisicaoResposta.RESET;
                break;

            case 21: // '\025'
                tipo = TipoRequisicaoResposta.CONFIGURAR_SENTIDO_ENTRADA;
                break;

            case 22: // '\026'
                tipo = TipoRequisicaoResposta.CONFIGURAR_CATRACA;
                break;

            case 23: // '\027'
                tipo = TipoRequisicaoResposta.IMPRIMIR_PAMENTO;
                break;
            }
        return tipo;
    }

    private Calendar dataHora;
    private Object parametro;
    private Object referencia;
    private Boolean status;
    private Long destino;
    private Usuario operador;
    private TipoRequisicaoResposta tipo;
}
