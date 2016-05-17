// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfiguracaoBoleto.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class ConfiguracaoBoleto extends POJO
{

    public ConfiguracaoBoleto()
    {
    }

    public String getCedenteRazaoSocial()
    {
        return cedenteRazaoSocial;
    }

    public void setCedenteRazaoSocial(String cedenteRazaoSocial)
    {
        this.cedenteRazaoSocial = cedenteRazaoSocial;
    }

    public String getCedenteCnpj()
    {
        if(cedenteCnpj != null)
        {
            cedenteCnpj = cedenteCnpj.replace(".", "");
            cedenteCnpj = cedenteCnpj.replace(".", "");
            cedenteCnpj = cedenteCnpj.replace(".", "");
            cedenteCnpj = cedenteCnpj.replace("/", "");
            cedenteCnpj = cedenteCnpj.replace("-", "");
            cedenteCnpj = cedenteCnpj.replace(".", "");
        }
        return cedenteCnpj;
    }

    public void setCedenteCnpj(String cedenteCnpj)
    {
        this.cedenteCnpj = cedenteCnpj;
    }

    public String getSacadoNome()
    {
        return sacadoNome;
    }

    public void setSacadoNome(String sacadoNome)
    {
        this.sacadoNome = sacadoNome;
    }

    public String getSacadoCpf()
    {
        return sacadoCpf;
    }

    public void setSacadoCpf(String sacadoCpf)
    {
        this.sacadoCpf = sacadoCpf;
    }

    public String getSacadoEnderecoUf()
    {
        return sacadoEnderecoUf;
    }

    public void setSacadoEnderecoUf(String sacadoEnderecoUf)
    {
        this.sacadoEnderecoUf = sacadoEnderecoUf;
    }

    public String getSacadoEnderecoLocalidade()
    {
        return sacadoEnderecoLocalidade;
    }

    public void setSacadoEnderecoLocalidade(String sacadoEnderecoLocalidade)
    {
        this.sacadoEnderecoLocalidade = sacadoEnderecoLocalidade;
    }

    public String getSacadoEnderecoCep()
    {
        return sacadoEnderecoCep;
    }

    public void setSacadoEnderecoCep(String sacadoEnderecoCep)
    {
        this.sacadoEnderecoCep = sacadoEnderecoCep;
    }

    public String getSacadoEnderecoBairro()
    {
        return sacadoEnderecoBairro;
    }

    public void setSacadoEnderecoBairro(String sacadoEnderecoBairro)
    {
        this.sacadoEnderecoBairro = sacadoEnderecoBairro;
    }

    public String getSacadoEnderecoLogradouro()
    {
        return sacadoEnderecoLogradouro;
    }

    public void setSacadoEnderecoLogradouro(String sacadoEnderecoLogradouro)
    {
        this.sacadoEnderecoLogradouro = sacadoEnderecoLogradouro;
    }

    public String getSacadoEnderecoNumero()
    {
        return sacadoEnderecoNumero;
    }

    public void setSacadoEnderecoNumero(String sacadoEnderecoNumero)
    {
        this.sacadoEnderecoNumero = sacadoEnderecoNumero;
    }

    public BancosSuportados getBanco()
    {
        return banco;
    }

    public void setBanco(String banco)
    {
        if(banco != null)
            try
            {
                this.banco = BancosSuportados.valueOf(banco);
            }
            catch(Exception e) { }
    }

    public void setBanco(BancosSuportados banco)
    {
        this.banco = banco;
    }

    public String getBancoNumero()
    {
        return bancoNumero;
    }

    public void setBancoNumero(String bancoNumero)
    {
        this.bancoNumero = bancoNumero;
    }

    public String getBancoNumeroDigito()
    {
        return bancoNumeroDigito;
    }

    public void setBancoNumeroDigito(String bancoNumeroDigito)
    {
        this.bancoNumeroDigito = bancoNumeroDigito;
    }

    public String getBancoAgencia()
    {
        return bancoAgencia;
    }

    public Integer getBancoAgenciaInt()
    {
        if(bancoAgencia != null)
            return Integer.valueOf(bancoAgencia);
        else
            return Integer.valueOf(0);
    }

    public void setBancoAgencia(String bancoAgencia)
    {
        this.bancoAgencia = bancoAgencia;
    }

    public String getBancoAgenciaVariacao()
    {
        return bancoAgenciaVariacao;
    }

    public void setBancoAgenciaVariacao(String bancoAgenciaVariacao)
    {
        this.bancoAgenciaVariacao = bancoAgenciaVariacao;
    }

    public String getBancoCarteira()
    {
        return bancoCarteira;
    }

    public void setBancoCarteira(String bancoCarteira)
    {
        this.bancoCarteira = bancoCarteira;
    }

    public String getTituloNumeroDoDocumento()
    {
        return tituloNumeroDoDocumento;
    }

    public void setTituloNumeroDoDocumento(String tituloNumeroDoDocumento)
    {
        this.tituloNumeroDoDocumento = tituloNumeroDoDocumento;
    }

    public String getTituloNossoNumero()
    {
        return tituloNossoNumero;
    }

    public void setTituloNossoNumero(String tituloNossoNumero)
    {
        this.tituloNossoNumero = tituloNossoNumero;
    }

    public String getTituloDigitoNossoNumero()
    {
        return tituloDigitoNossoNumero;
    }

    public void setTituloDigitoNossoNumero(String tituloDigitoNossoNumero)
    {
        this.tituloDigitoNossoNumero = tituloDigitoNossoNumero;
    }

    public Double getTituloValor()
    {
        return tituloValor;
    }

    public void setTituloValor(Double tituloValor)
    {
        this.tituloValor = tituloValor;
    }

    public Double getTituloDesconto()
    {
        return tituloDesconto;
    }

    public void setTituloDesconto(Double tituloDesconto)
    {
        this.tituloDesconto = tituloDesconto;
    }

    public Double getTituloDeducao()
    {
        return tituloDeducao;
    }

    public void setTituloDeducao(Double tituloDeducao)
    {
        this.tituloDeducao = tituloDeducao;
    }

    public Double getTituloMora()
    {
        return tituloMora;
    }

    public void setTituloMora(Double tituloMora)
    {
        this.tituloMora = tituloMora;
    }

    public Double getTituloAcrescimo()
    {
        return tituloAcrescimo;
    }

    public void setTituloAcrescimo(Double tituloAcrescimo)
    {
        this.tituloAcrescimo = tituloAcrescimo;
    }

    public Double getTituloValorCobrado()
    {
        return tituloValorCobrado;
    }

    public void setTituloValorCobrado(Double tituloValorCobrado)
    {
        this.tituloValorCobrado = tituloValorCobrado;
    }

    public Date getTituloDataDocumento()
    {
        return tituloDataDocumento;
    }

    public void setTituloDataDocumento(Date tituloDataDocumento)
    {
        this.tituloDataDocumento = tituloDataDocumento;
    }

    public Date getTituloDataVencimento()
    {
        return tituloDataVencimento;
    }

    public void setTituloDataVencimento(Date tituloDataVencimento)
    {
        this.tituloDataVencimento = tituloDataVencimento;
    }

    public TipoDeTitulo getTituloTipoDocumento()
    {
        return tituloTipoDocumento;
    }

    public void setTituloTipoDocumento(TipoDeTitulo tituloTipoDocumento)
    {
        this.tituloTipoDocumento = tituloTipoDocumento;
    }

    public org.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite getTituloAceite()
    {
        return tituloAceite;
    }

    public void setTituloAceite(org.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite tituloAceite)
    {
        this.tituloAceite = tituloAceite;
    }

    public String getBoletoLocalPagamento()
    {
        return boletoLocalPagamento;
    }

    public void setBoletoLocalPagamento(String boletoLocalPagamento)
    {
        this.boletoLocalPagamento = boletoLocalPagamento;
    }

    public String getBoletoInstrucaoSacado()
    {
        return boletoInstrucaoSacado;
    }

    public void setBoletoInstrucaoSacado(String boletoInstrucaoSacado)
    {
        this.boletoInstrucaoSacado = boletoInstrucaoSacado;
    }

    public String getBoletoInstrucao1()
    {
        return boletoInstrucao1;
    }

    public void setBoletoInstrucao1(String boletoInstrucao1)
    {
        this.boletoInstrucao1 = boletoInstrucao1;
    }

    public String getBoletoInstrucao2()
    {
        return boletoInstrucao2;
    }

    public void setBoletoInstrucao2(String boletoInstrucao2)
    {
        this.boletoInstrucao2 = boletoInstrucao2;
    }

    public String getBoletoInstrucao3()
    {
        return boletoInstrucao3;
    }

    public void setBoletoInstrucao3(String boletoInstrucao3)
    {
        this.boletoInstrucao3 = boletoInstrucao3;
    }

    public String getBoletoInstrucao4()
    {
        return boletoInstrucao4;
    }

    public void setBoletoInstrucao4(String boletoInstrucao4)
    {
        this.boletoInstrucao4 = boletoInstrucao4;
    }

    public String getBoletoInstrucao5()
    {
        return boletoInstrucao5;
    }

    public void setBoletoInstrucao5(String boletoInstrucao5)
    {
        this.boletoInstrucao5 = boletoInstrucao5;
    }

    public String getBoletoInstrucao6()
    {
        return boletoInstrucao6;
    }

    public void setBoletoInstrucao6(String boletoInstrucao6)
    {
        this.boletoInstrucao6 = boletoInstrucao6;
    }

    public String getBoletoInstrucao7()
    {
        return boletoInstrucao7;
    }

    public void setBoletoInstrucao7(String boletoInstrucao7)
    {
        this.boletoInstrucao7 = boletoInstrucao7;
    }

    public String getBoletoInstrucao8()
    {
        return boletoInstrucao8;
    }

    public void setBoletoInstrucao8(String boletoInstrucao8)
    {
        this.boletoInstrucao8 = boletoInstrucao8;
    }

    private String cedenteRazaoSocial;
    private String cedenteCnpj;
    private String sacadoNome;
    private String sacadoCpf;
    private String sacadoEnderecoUf;
    private String sacadoEnderecoLocalidade;
    private String sacadoEnderecoCep;
    private String sacadoEnderecoBairro;
    private String sacadoEnderecoLogradouro;
    private String sacadoEnderecoNumero;
    private BancosSuportados banco;
    private String bancoNumero;
    private String bancoNumeroDigito;
    private String bancoAgencia;
    private String bancoAgenciaVariacao;
    private String bancoCarteira;
    private String tituloNumeroDoDocumento;
    private String tituloNossoNumero;
    private String tituloDigitoNossoNumero;
    private Double tituloValor;
    private Double tituloDesconto;
    private Double tituloDeducao;
    private Double tituloMora;
    private Double tituloAcrescimo;
    private Double tituloValorCobrado;
    private Date tituloDataDocumento;
    private Date tituloDataVencimento;
    private TipoDeTitulo tituloTipoDocumento;
    private org.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite tituloAceite;
    private String boletoLocalPagamento;
    private String boletoInstrucaoSacado;
    private String boletoInstrucao1;
    private String boletoInstrucao2;
    private String boletoInstrucao3;
    private String boletoInstrucao4;
    private String boletoInstrucao5;
    private String boletoInstrucao6;
    private String boletoInstrucao7;
    private String boletoInstrucao8;
}
