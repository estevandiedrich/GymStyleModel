// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfiguracaoBoletoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.pojo.ConfiguracaoBoleto;
import java.sql.ResultSet;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

public class ConfiguracaoBoletoDAO extends BaseDAO
{

    public ConfiguracaoBoletoDAO()
    {
    }
    @Override
    public String getNameTable()
    {
        return "configuracao_boleto";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	ConfiguracaoBoleto pojo = (ConfiguracaoBoleto)obj;
        if(pojo.getTituloAceite() != null)
            addParametro("banco", pojo.getBanco().toString(), 12);
        addParametro("banco_agencia", pojo.getBancoAgencia(), 12);
        addParametro("banco_agencia_variacao", pojo.getBancoAgenciaVariacao(), 12);
        addParametro("banco_numero", pojo.getBancoNumero(), 12);
        addParametro("banco_numero_digito", pojo.getBancoNumeroDigito(), 12);
        addParametro("banco_carteira", pojo.getBancoCarteira(), 12);
        addParametro("boleto_instrucao_1", pojo.getBoletoInstrucao1(), 12);
        addParametro("boleto_instrucao_2", pojo.getBoletoInstrucao2(), 12);
        addParametro("boleto_instrucao_3", pojo.getBoletoInstrucao3(), 12);
        addParametro("boleto_instrucao_4", pojo.getBoletoInstrucao4(), 12);
        addParametro("boleto_instrucao_5", pojo.getBoletoInstrucao5(), 12);
        addParametro("boleto_instrucao_6", pojo.getBoletoInstrucao6(), 12);
        addParametro("boleto_instrucao_7", pojo.getBoletoInstrucao7(), 12);
        addParametro("boleto_instrucao_8", pojo.getBoletoInstrucao8(), 12);
        addParametro("boleto_instrucao_sacado", pojo.getBoletoInstrucaoSacado(), 12);
        addParametro("boleto_local_pagamento", pojo.getBoletoLocalPagamento(), 12);
        addParametro("cedente_cnpj", pojo.getCedenteCnpj(), 12);
        addParametro("cedente_razao_social", pojo.getCedenteRazaoSocial(), 12);
        if(pojo.getTituloAceite() != null)
            addParametro("titulo_aceite", pojo.getTituloAceite().toString(), 12);
        addParametro("titulo_acrescimo", pojo.getTituloAcrescimo(), 8);
        addParametro("titulo_data_documento", pojo.getTituloDataDocumento(), 91);
        addParametro("titulo_data_vencimento", pojo.getTituloDataVencimento(), 91);
        addParametro("titulo_deducao", pojo.getTituloDeducao(), 8);
        addParametro("titulo_desconto", pojo.getTituloDesconto(), 8);
        addParametro("titulo_nosso_numero", pojo.getTituloNossoNumero(), 12);
        addParametro("titulo_digito_nosso_numero", pojo.getTituloDigitoNossoNumero(), 12);
        addParametro("titulo_mora", pojo.getTituloMora(), 8);
        addParametro("titulo_numero_do_documento", pojo.getTituloNumeroDoDocumento(), 12);
        if(pojo.getTituloTipoDocumento() != null)
            addParametro("titulo_tipo_documento", pojo.getTituloTipoDocumento().toString(), 12);
        addParametro("titulo_valor", pojo.getTituloValor(), 8);
        addParametro("titulo_valor_cobrado", pojo.getTituloValorCobrado(), 8);
        addParametro("sacado_nome", pojo.getSacadoNome(), 12);
        addParametro("sacado_cpf", pojo.getSacadoCpf(), 12);
        addParametro("sacado_endereco_bairro", pojo.getSacadoEnderecoBairro(), 12);
        addParametro("sacado_endereco_cep", pojo.getSacadoEnderecoCep(), 12);
        addParametro("sacado_endereco_localidade", pojo.getSacadoEnderecoLocalidade(), 12);
        addParametro("sacado_endereco_logradouro", pojo.getSacadoEnderecoLogradouro(), 12);
        addParametro("sacado_endereco_numero", pojo.getSacadoEnderecoNumero(), 12);
        addParametro("sacado_endereco_uf", pojo.getSacadoEnderecoUf(), 12);
    }
    @Override
    public ConfiguracaoBoleto extract(ResultSet rs)
        throws Exception
    {
        ConfiguracaoBoleto pojo = null;
        if(rs != null)
        {
            pojo = new ConfiguracaoBoleto();
            pojo.setId(rsGetId(rs));
            pojo.setBanco(rs.getString("banco"));
            pojo.setBancoAgencia(rs.getString("banco_agencia"));
            pojo.setBancoAgenciaVariacao(rs.getString("banco_agencia_variacao"));
            pojo.setBancoCarteira(rs.getString("banco_carteira"));
            pojo.setBancoNumero(rs.getString("banco_numero"));
            pojo.setBancoNumeroDigito(rs.getString("banco_numero_digito"));
            pojo.setBoletoInstrucao1(rs.getString("boleto_instrucao_1"));
            pojo.setBoletoInstrucao2(rs.getString("boleto_instrucao_2"));
            pojo.setBoletoInstrucao3(rs.getString("boleto_instrucao_3"));
            pojo.setBoletoInstrucao4(rs.getString("boleto_instrucao_4"));
            pojo.setBoletoInstrucao5(rs.getString("boleto_instrucao_5"));
            pojo.setBoletoInstrucao6(rs.getString("boleto_instrucao_6"));
            pojo.setBoletoInstrucao7(rs.getString("boleto_instrucao_7"));
            pojo.setBoletoInstrucao8(rs.getString("boleto_instrucao_8"));
            pojo.setBoletoInstrucaoSacado(rs.getString("boleto_instrucao_sacado"));
            pojo.setBoletoLocalPagamento(rs.getString("boleto_local_pagamento"));
            pojo.setTituloAceite(org.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite.valueOf(rs.getString("titulo_aceite")));
            pojo.setTituloAcrescimo(Double.valueOf(rs.getDouble("titulo_acrescimo")));
            pojo.setTituloDeducao(Double.valueOf(rs.getDouble("titulo_deducao")));
            pojo.setTituloDesconto(Double.valueOf(rs.getDouble("titulo_desconto")));
            pojo.setTituloDigitoNossoNumero(rs.getString("titulo_digito_nosso_numero"));
            pojo.setTituloMora(Double.valueOf(rs.getDouble("titulo_mora")));
            pojo.setTituloNossoNumero(rs.getString("titulo_nosso_numero"));
            pojo.setTituloNumeroDoDocumento(rs.getString("titulo_numero_do_documento"));
            pojo.setTituloTipoDocumento(TipoDeTitulo.valueOf(rs.getString("titulo_tipo_documento")));
            pojo.setTituloValor(Double.valueOf(rs.getDouble("titulo_valor")));
            pojo.setTituloValorCobrado(Double.valueOf(rs.getDouble("titulo_valor_cobrado")));
            pojo.setCedenteCnpj(rs.getString("cedente_cnpj"));
            pojo.setCedenteRazaoSocial(rs.getString("cedente_razao_social"));
            pojo.setSacadoCpf(rs.getString("sacado_cpf"));
            pojo.setSacadoNome(rs.getString("sacado_nome"));
            pojo.setSacadoEnderecoBairro(rs.getString("sacado_endereco_bairro"));
            pojo.setSacadoEnderecoCep(rs.getString("sacado_endereco_cep"));
            pojo.setSacadoEnderecoLocalidade(rs.getString("sacado_endereco_localidade"));
            pojo.setSacadoEnderecoLogradouro(rs.getString("sacado_endereco_logradouro"));
            pojo.setSacadoEnderecoNumero(rs.getString("sacado_endereco_numero"));
            pojo.setSacadoEnderecoUf(rs.getString("sacado_endereco_uf"));
        }
        return pojo;
    }

    private final String CEDENTE_RAZAO_SOCIAL = "cedente_razao_social";
    private final String CEDENTE_CNPJ = "cedente_cnpj";
    private final String SACADO_NOME = "sacado_nome";
    private final String SACADO_CPF = "sacado_cpf";
    private final String SACADO_ENDERECO_UF = "sacado_endereco_uf";
    private final String SACADO_ENDERECO_LOCALIDADE = "sacado_endereco_localidade";
    private final String SACADO_ENDERECO_CEP = "sacado_endereco_cep";
    private final String SACADO_ENDERECO_BAIRRO = "sacado_endereco_bairro";
    private final String SACADO_ENDERECO_LOGRADOURO = "sacado_endereco_logradouro";
    private final String SACADO_ENDERECO_NUMERO = "sacado_endereco_numero";
    private final String BANCO = "banco";
    private final String BANCO_NUMERO = "banco_numero";
    private final String BANCO_NUMERO_DIGITO = "banco_numero_digito";
    private final String BANCO_AGENCIA = "banco_agencia";
    private final String BANCO_AGENCIA_VARIACAO = "banco_agencia_variacao";
    private final String BANCO_CARTEIRA = "banco_carteira";
    private final String TITULO_NUMERO_DO_DOCUMENTO = "titulo_numero_do_documento";
    private final String TITULO_NOSSO_NUMERO = "titulo_nosso_numero";
    private final String TITULO_DIGITO_NOSSO_NUMERO = "titulo_digito_nosso_numero";
    private final String TITULO_VALOR = "titulo_valor";
    private final String TITULO_DESCONTO = "titulo_desconto";
    private final String TITULO_DEDUCAO = "titulo_deducao";
    private final String TITULO_MORA = "titulo_mora";
    private final String TITULO_ACRESCIMO = "titulo_acrescimo";
    private final String TITULO_VALOR_COBRADO = "titulo_valor_cobrado";
    private final String TITULO_DATA_DOCUMENTO = "titulo_data_documento";
    private final String TITULO_DATA_VENCIMENTO = "titulo_data_vencimento";
    private final String TITULO_TIPO_DOCUMENTO = "titulo_tipo_documento";
    private final String TITULO_ACEITE = "titulo_aceite";
    private final String BOLETO_LOCAL_PAGAMENTO = "boleto_local_pagamento";
    private final String BOLETO_INSTRUCAO_SACADO = "boleto_instrucao_sacado";
    private final String BOLETO_INSTRUCAO_1 = "boleto_instrucao_1";
    private final String BOLETO_INSTRUCAO_2 = "boleto_instrucao_2";
    private final String BOLETO_INSTRUCAO_3 = "boleto_instrucao_3";
    private final String BOLETO_INSTRUCAO_4 = "boleto_instrucao_4";
    private final String BOLETO_INSTRUCAO_5 = "boleto_instrucao_5";
    private final String BOLETO_INSTRUCAO_6 = "boleto_instrucao_6";
    private final String BOLETO_INSTRUCAO_7 = "boleto_instrucao_7";
    private final String BOLETO_INSTRUCAO_8 = "boleto_instrucao_8";
}
