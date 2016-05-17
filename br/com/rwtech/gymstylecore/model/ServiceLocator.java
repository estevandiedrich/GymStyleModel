// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceLocator.java

package br.com.rwtech.gymstylecore.model;

import br.com.rwtech.gymstylecore.model.service.AcessoService;
import br.com.rwtech.gymstylecore.model.service.AparelhoService;
import br.com.rwtech.gymstylecore.model.service.AvaliacaoFisicaService;
import br.com.rwtech.gymstylecore.model.service.BancoService;
import br.com.rwtech.gymstylecore.model.service.CaixaService;
import br.com.rwtech.gymstylecore.model.service.CategoriaService;
import br.com.rwtech.gymstylecore.model.service.ConfiguracaoBoletoService;
import br.com.rwtech.gymstylecore.model.service.ConfiguracaoService;
import br.com.rwtech.gymstylecore.model.service.ContaBancariaService;
import br.com.rwtech.gymstylecore.model.service.DataBaseService;
import br.com.rwtech.gymstylecore.model.service.DiaSemanaService;
import br.com.rwtech.gymstylecore.model.service.DispositivoService;
import br.com.rwtech.gymstylecore.model.service.DuracaoPlanoService;
import br.com.rwtech.gymstylecore.model.service.EmpresaService;
import br.com.rwtech.gymstylecore.model.service.EstadoCivilService;
import br.com.rwtech.gymstylecore.model.service.EventoService;
import br.com.rwtech.gymstylecore.model.service.ExercicioService;
import br.com.rwtech.gymstylecore.model.service.FichaService;
import br.com.rwtech.gymstylecore.model.service.FluxoCaixaService;
import br.com.rwtech.gymstylecore.model.service.FormaPagamentoService;
import br.com.rwtech.gymstylecore.model.service.FornecedorService;
import br.com.rwtech.gymstylecore.model.service.FuncionarioService;
import br.com.rwtech.gymstylecore.model.service.GrupoMuscularService;
import br.com.rwtech.gymstylecore.model.service.ImpressaoDigitalEspelhoService;
import br.com.rwtech.gymstylecore.model.service.ImpressaoDigitalService;
import br.com.rwtech.gymstylecore.model.service.LiberarService;
import br.com.rwtech.gymstylecore.model.service.LogService;
import br.com.rwtech.gymstylecore.model.service.ModalidadeService;
import br.com.rwtech.gymstylecore.model.service.ModoOperacaoService;
import br.com.rwtech.gymstylecore.model.service.PagamentoService;
import br.com.rwtech.gymstylecore.model.service.PerfilAcessoService;
import br.com.rwtech.gymstylecore.model.service.PermissaoService;
import br.com.rwtech.gymstylecore.model.service.PlanoService;
import br.com.rwtech.gymstylecore.model.service.ProdutoService;
import br.com.rwtech.gymstylecore.model.service.ProtocoloService;
import br.com.rwtech.gymstylecore.model.service.RedeSocialService;
import br.com.rwtech.gymstylecore.model.service.RegistroCaixaService;
import br.com.rwtech.gymstylecore.model.service.RegistroContaBancariaService;
import br.com.rwtech.gymstylecore.model.service.RequisicaoService;
import br.com.rwtech.gymstylecore.model.service.RespostaService;
import br.com.rwtech.gymstylecore.model.service.TipoUsuarioService;
import br.com.rwtech.gymstylecore.model.service.UsuarioPermissaoService;
import br.com.rwtech.gymstylecore.model.service.UsuarioPlanoService;
import br.com.rwtech.gymstylecore.model.service.UsuarioService;

public class ServiceLocator
{

    public ServiceLocator()
    {
    }

    public static AcessoService getAcessoService()
    {
        if(acessoService == null)
            acessoService = new AcessoService();
        return acessoService;
    }

    public static AparelhoService getAparelhoService()
    {
        if(aparelhoService == null)
            aparelhoService = new AparelhoService();
        return aparelhoService;
    }

    public static AvaliacaoFisicaService getAvaliacaoFisicaService()
    {
        if(avaliacaoFisicaService == null)
            avaliacaoFisicaService = new AvaliacaoFisicaService();
        return avaliacaoFisicaService;
    }

    public static BancoService getBancoService()
    {
        if(bancoService == null)
            bancoService = new BancoService();
        return bancoService;
    }

    public static ConfiguracaoService getConfiguracaoService()
    {
        if(configuracaoService == null)
            configuracaoService = new ConfiguracaoService();
        return configuracaoService;
    }

    public static ConfiguracaoBoletoService getConfiguracaoBoletoService()
    {
        if(configuracaoBoletoService == null)
            configuracaoBoletoService = new ConfiguracaoBoletoService();
        return configuracaoBoletoService;
    }

    public static CaixaService getCaixaService()
    {
        if(caixaService == null)
            caixaService = new CaixaService();
        return caixaService;
    }

    public static CategoriaService getCategoriaService()
    {
        if(categoriaService == null)
            categoriaService = new CategoriaService();
        return categoriaService;
    }

    public static ContaBancariaService getContaBancariaService()
    {
        if(contaBancariaService == null)
            contaBancariaService = new ContaBancariaService();
        return contaBancariaService;
    }

    public static DataBaseService getDataBaseService()
    {
        if(dataBaseService == null)
            dataBaseService = new DataBaseService();
        return dataBaseService;
    }

    public static DispositivoService getDispositivoService()
    {
        if(dispositivoService == null)
            dispositivoService = new DispositivoService();
        return dispositivoService;
    }

    public static DiaSemanaService getDiaSemanaService()
    {
        if(diaSemanaService == null)
            diaSemanaService = new DiaSemanaService();
        return diaSemanaService;
    }

    public static DuracaoPlanoService getDuracaoPlanoService()
    {
        if(duracaoPlanoService == null)
            duracaoPlanoService = new DuracaoPlanoService();
        return duracaoPlanoService;
    }

    public static ProdutoService getProdutoService()
    {
        if(produtoService == null)
            produtoService = new ProdutoService();
        return produtoService;
    }

    public static EventoService getEventoService()
    {
        if(eventoService == null)
            eventoService = new EventoService();
        return eventoService;
    }

    public static ExercicioService getExercicioService()
    {
        if(exercicioService == null)
            exercicioService = new ExercicioService();
        return exercicioService;
    }

    public static EstadoCivilService getEstadoCivilService()
    {
        if(estadoCivilService == null)
            estadoCivilService = new EstadoCivilService();
        return estadoCivilService;
    }

    public static EmpresaService getEmpresaService()
    {
        if(empresaService == null)
            empresaService = new EmpresaService();
        return empresaService;
    }

    public static FluxoCaixaService getFluxoCaixaService()
    {
        if(fluxoCaixaService == null)
            fluxoCaixaService = new FluxoCaixaService();
        return fluxoCaixaService;
    }

    public static FormaPagamentoService getFormaPagamentoService()
    {
        if(formaPagamentoService == null)
            formaPagamentoService = new FormaPagamentoService();
        return formaPagamentoService;
    }

    public static GrupoMuscularService getGrupoMuscularService()
    {
        if(grupoMuscularService == null)
            grupoMuscularService = new GrupoMuscularService();
        return grupoMuscularService;
    }

    public static FichaService getFichaService()
    {
        if(fichaService == null)
            fichaService = new FichaService();
        return fichaService;
    }

    public static FornecedorService getFornecedorService()
    {
        if(fornecedorService == null)
            fornecedorService = new FornecedorService();
        return fornecedorService;
    }

    public static ImpressaoDigitalService getImpressaoDigitalService()
    {
        if(impressaoDigitalService == null)
            impressaoDigitalService = new ImpressaoDigitalService();
        return impressaoDigitalService;
    }

    public static ImpressaoDigitalEspelhoService getImpressaoDigitalEspelhoService()
    {
        if(impressaoDigitalEspelhoService == null)
            impressaoDigitalEspelhoService = new ImpressaoDigitalEspelhoService();
        return impressaoDigitalEspelhoService;
    }

    public static LogService getLogService()
    {
        if(logService == null)
            logService = new LogService();
        return logService;
    }

    public static LiberarService getLiberarService()
    {
        if(liberarService == null)
            liberarService = new LiberarService();
        return liberarService;
    }

    public static ModoOperacaoService getModoOperacaoService()
    {
        if(modoOperacaoService == null)
            modoOperacaoService = new ModoOperacaoService();
        return modoOperacaoService;
    }

    public static ModalidadeService getModalidadeService()
    {
        if(modalidadeService == null)
            modalidadeService = new ModalidadeService();
        return modalidadeService;
    }

    public static PerfilAcessoService getPerfilAcessoService()
    {
        if(perfilAcessoService == null)
            perfilAcessoService = new PerfilAcessoService();
        return perfilAcessoService;
    }

    public static PlanoService getPlanoService()
    {
        if(planoService == null)
            planoService = new PlanoService();
        return planoService;
    }

    public static PagamentoService getPagamentoService()
    {
        if(pagamentoService == null)
            pagamentoService = new PagamentoService();
        return pagamentoService;
    }

    public static PermissaoService getPermissaoService()
    {
        if(permissaoService == null)
            permissaoService = new PermissaoService();
        return permissaoService;
    }

    public static ProtocoloService getProtocoloService()
    {
        if(protocoloService == null)
            protocoloService = new ProtocoloService();
        return protocoloService;
    }

    public static RequisicaoService getRequisicaoService()
    {
        if(requisicaoService == null)
            requisicaoService = new RequisicaoService();
        return requisicaoService;
    }

    public static RedeSocialService getRedeSocialService()
    {
        if(redeSocialService == null)
            redeSocialService = new RedeSocialService();
        return redeSocialService;
    }

    public static RespostaService getRespostaService()
    {
        if(respostaService == null)
            respostaService = new RespostaService();
        return respostaService;
    }

    public static RegistroContaBancariaService getRegistroContaBancariaService()
    {
        if(registroContaBancariaService == null)
            registroContaBancariaService = new RegistroContaBancariaService();
        return registroContaBancariaService;
    }

    public static RegistroCaixaService getRegistroCaixaService()
    {
        if(registroCaixaService == null)
            registroCaixaService = new RegistroCaixaService();
        return registroCaixaService;
    }

    public static TipoUsuarioService getTipoUsuarioService()
    {
        if(tipoUsuarioService == null)
            tipoUsuarioService = new TipoUsuarioService();
        return tipoUsuarioService;
    }

    public static UsuarioPlanoService getUsuarioPlanoService()
    {
        if(usuarioPlanoService == null)
            usuarioPlanoService = new UsuarioPlanoService();
        return usuarioPlanoService;
    }

    public static UsuarioPermissaoService getUsuarioPermissaoService()
    {
        if(usuarioPermissaoService == null)
            usuarioPermissaoService = new UsuarioPermissaoService();
        return usuarioPermissaoService;
    }

    public static UsuarioService getUsuarioService()
    {
        if(usuarioService == null)
            usuarioService = new UsuarioService();
        return usuarioService;
    }

    public static FuncionarioService getFuncionarioService()
    {
        if(funcionarioService == null)
            funcionarioService = new FuncionarioService();
        return funcionarioService;
    }

    private static AcessoService acessoService;
    private static AvaliacaoFisicaService avaliacaoFisicaService;
    private static AparelhoService aparelhoService;
    private static BancoService bancoService;
    private static ConfiguracaoService configuracaoService;
    private static ConfiguracaoBoletoService configuracaoBoletoService;
    private static CaixaService caixaService;
    private static CategoriaService categoriaService;
    private static ContaBancariaService contaBancariaService;
    private static DataBaseService dataBaseService;
    private static DiaSemanaService diaSemanaService;
    private static DispositivoService dispositivoService;
    private static DuracaoPlanoService duracaoPlanoService;
    private static EventoService eventoService;
    private static ExercicioService exercicioService;
    private static EstadoCivilService estadoCivilService;
    private static EmpresaService empresaService;
    private static FormaPagamentoService formaPagamentoService;
    private static FluxoCaixaService fluxoCaixaService;
    private static FichaService fichaService;
    private static FornecedorService fornecedorService;
    private static GrupoMuscularService grupoMuscularService;
    private static ImpressaoDigitalService impressaoDigitalService;
    private static ImpressaoDigitalEspelhoService impressaoDigitalEspelhoService;
    private static LogService logService;
    private static LiberarService liberarService;
    private static ModoOperacaoService modoOperacaoService;
    private static ModalidadeService modalidadeService;
    private static ProtocoloService protocoloService;
    private static PlanoService planoService;
    private static ProdutoService produtoService;
    private static PagamentoService pagamentoService;
    private static PerfilAcessoService perfilAcessoService;
    private static PermissaoService permissaoService;
    private static RegistroCaixaService registroCaixaService;
    private static RegistroContaBancariaService registroContaBancariaService;
    private static RequisicaoService requisicaoService;
    private static RespostaService respostaService;
    private static RedeSocialService redeSocialService;
    private static TipoUsuarioService tipoUsuarioService;
    private static UsuarioPlanoService usuarioPlanoService;
    private static UsuarioPermissaoService usuarioPermissaoService;
    private static UsuarioService usuarioService;
    private static FuncionarioService funcionarioService;
}
