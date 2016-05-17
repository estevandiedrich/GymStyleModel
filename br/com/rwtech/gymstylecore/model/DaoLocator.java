// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DaoLocator.java

package br.com.rwtech.gymstylecore.model;

import br.com.rwtech.gymstylecore.model.dao.AcessoDAO;
import br.com.rwtech.gymstylecore.model.dao.AparelhoDAO;
import br.com.rwtech.gymstylecore.model.dao.AvaliacaoFisicaDAO;
import br.com.rwtech.gymstylecore.model.dao.BancoDAO;
import br.com.rwtech.gymstylecore.model.dao.CaixaDAO;
import br.com.rwtech.gymstylecore.model.dao.CategoriaDAO;
import br.com.rwtech.gymstylecore.model.dao.ConfiguracaoBoletoDAO;
import br.com.rwtech.gymstylecore.model.dao.ConfiguracaoDAO;
import br.com.rwtech.gymstylecore.model.dao.ContaBancariaDAO;
import br.com.rwtech.gymstylecore.model.dao.DataBaseDAO;
import br.com.rwtech.gymstylecore.model.dao.DedoDAO;
import br.com.rwtech.gymstylecore.model.dao.DiaSemanaDAO;
import br.com.rwtech.gymstylecore.model.dao.DispositivoDAO;
import br.com.rwtech.gymstylecore.model.dao.DuracaoPlanoDAO;
import br.com.rwtech.gymstylecore.model.dao.EmpresaDAO;
import br.com.rwtech.gymstylecore.model.dao.EstadoCivilDAO;
import br.com.rwtech.gymstylecore.model.dao.EventoDAO;
import br.com.rwtech.gymstylecore.model.dao.ExercicioDAO;
import br.com.rwtech.gymstylecore.model.dao.FaixaDAO;
import br.com.rwtech.gymstylecore.model.dao.FichaDAO;
import br.com.rwtech.gymstylecore.model.dao.FluxoCaixaDAO;
import br.com.rwtech.gymstylecore.model.dao.FormaDePagamentoDAO;
import br.com.rwtech.gymstylecore.model.dao.FornecedorDAO;
import br.com.rwtech.gymstylecore.model.dao.FuncionarioDAO;
import br.com.rwtech.gymstylecore.model.dao.GrupoMuscularDAO;
import br.com.rwtech.gymstylecore.model.dao.ImpressaoDigitalDAO;
import br.com.rwtech.gymstylecore.model.dao.ImpressaoDigitalEspelhoDAO;
import br.com.rwtech.gymstylecore.model.dao.LiberarDAO;
import br.com.rwtech.gymstylecore.model.dao.LogDAO;
import br.com.rwtech.gymstylecore.model.dao.ModalidadeDAO;
import br.com.rwtech.gymstylecore.model.dao.ModoOperacaoDAO;
import br.com.rwtech.gymstylecore.model.dao.MotivoBloqueioDAO;
import br.com.rwtech.gymstylecore.model.dao.PagamentoDAO;
import br.com.rwtech.gymstylecore.model.dao.PerfilAcessoDAO;
import br.com.rwtech.gymstylecore.model.dao.PermissaoDAO;
import br.com.rwtech.gymstylecore.model.dao.PlanoDAO;
import br.com.rwtech.gymstylecore.model.dao.ProdutoDAO;
import br.com.rwtech.gymstylecore.model.dao.ProtocoloDAO;
import br.com.rwtech.gymstylecore.model.dao.RedeSocialDAO;
import br.com.rwtech.gymstylecore.model.dao.RegistroCaixaDAO;
import br.com.rwtech.gymstylecore.model.dao.RegistroContaBancariaDAO;
import br.com.rwtech.gymstylecore.model.dao.RequisicaoDAO;
import br.com.rwtech.gymstylecore.model.dao.RespostaDAO;
import br.com.rwtech.gymstylecore.model.dao.SerieDAO;
import br.com.rwtech.gymstylecore.model.dao.TipoUsuarioDAO;
import br.com.rwtech.gymstylecore.model.dao.TreinoDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPermissaoDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPlanoDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioPlanoReadDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioReadDAO;

public class DaoLocator
{

    public DaoLocator()
    {
    }

    public static AcessoDAO getAcessoDAO()
    {
        if(acessoDAO == null)
            acessoDAO = new AcessoDAO();
        return acessoDAO;
    }

    public static AvaliacaoFisicaDAO getAvaliacaoFisicaDAO()
    {
        if(avaliacaoFisicaDAO == null)
            avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
        return avaliacaoFisicaDAO;
    }

    public static AparelhoDAO getAparelhoDAO()
    {
        if(aparelhoDAO == null)
            aparelhoDAO = new AparelhoDAO();
        return aparelhoDAO;
    }

    public static FuncionarioDAO getFuncionarioDAO()
    {
        if(funcionarioDAO == null)
            funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO;
    }

    public static BancoDAO getBancoDAO()
    {
        if(bancoDAO == null)
            bancoDAO = new BancoDAO();
        return bancoDAO;
    }

    public static CaixaDAO getCaixaDAO()
    {
        if(caixaDAO == null)
            caixaDAO = new CaixaDAO();
        return caixaDAO;
    }

    public static ContaBancariaDAO getContaBancariaDAO()
    {
        if(contaBancariaDAO == null)
            contaBancariaDAO = new ContaBancariaDAO();
        return contaBancariaDAO;
    }

    public static ConfiguracaoDAO getConfiguracaoDAO()
    {
        if(configuracaoDAO == null)
            configuracaoDAO = new ConfiguracaoDAO();
        return configuracaoDAO;
    }

    public static CategoriaDAO getCategoriaDAO()
    {
        if(categoriaDAO == null)
            categoriaDAO = new CategoriaDAO();
        return categoriaDAO;
    }

    public static ConfiguracaoBoletoDAO getConfiguracaoBoletoDAO()
    {
        if(configuracaoBoletoDAO == null)
            configuracaoBoletoDAO = new ConfiguracaoBoletoDAO();
        return configuracaoBoletoDAO;
    }

    public static DiaSemanaDAO getDiaSemanaDAO()
    {
        if(diaSemanaDAO == null)
            diaSemanaDAO = new DiaSemanaDAO();
        return diaSemanaDAO;
    }

    public static DispositivoDAO getDispositivoDAO()
    {
        if(dispositivoDAO == null)
            dispositivoDAO = new DispositivoDAO();
        return dispositivoDAO;
    }

    public static DedoDAO getDedoDAO()
    {
        if(dedoDAO == null)
            dedoDAO = new DedoDAO();
        return dedoDAO;
    }

    public static DuracaoPlanoDAO getDuracaoPlanoDAO()
    {
        if(duracaoPlanoDAO == null)
            duracaoPlanoDAO = new DuracaoPlanoDAO();
        return duracaoPlanoDAO;
    }

    public static DataBaseDAO getDataBaseDAO()
    {
        if(dataBaseDAO == null)
            dataBaseDAO = new DataBaseDAO();
        return dataBaseDAO;
    }

    public static EmpresaDAO getEmpresaDAO()
    {
        if(empresaDAO == null)
            empresaDAO = new EmpresaDAO();
        return empresaDAO;
    }

    public static EventoDAO getEventoDAO()
    {
        if(eventoDAO == null)
            eventoDAO = new EventoDAO();
        return eventoDAO;
    }

    public static ExercicioDAO getExercicioDAO()
    {
        if(exercicioDAO == null)
            exercicioDAO = new ExercicioDAO();
        return exercicioDAO;
    }

    public static EstadoCivilDAO getEstadoCivilDAO()
    {
        if(estadoCivilDAO == null)
            estadoCivilDAO = new EstadoCivilDAO();
        return estadoCivilDAO;
    }

    public static FichaDAO getFichaDAO()
    {
        if(fichaDAO == null)
            fichaDAO = new FichaDAO();
        return fichaDAO;
    }

    public static FaixaDAO getFaixaDAO()
    {
        if(faixaDAO == null)
            faixaDAO = new FaixaDAO();
        return faixaDAO;
    }

    public static FluxoCaixaDAO getFluxoCaixaDAO()
    {
        if(fluxoCaixaDAO == null)
            fluxoCaixaDAO = new FluxoCaixaDAO();
        return fluxoCaixaDAO;
    }

    public static FormaDePagamentoDAO getFormaDePagamentoDAO()
    {
        if(formaDePagamentoDAO == null)
            formaDePagamentoDAO = new FormaDePagamentoDAO();
        return formaDePagamentoDAO;
    }

    public static FornecedorDAO getFornecedorDAO()
    {
        if(fornecedorDAO == null)
            fornecedorDAO = new FornecedorDAO();
        return fornecedorDAO;
    }

    public static GrupoMuscularDAO getGrupoMuscularDAO()
    {
        if(grupoMuscularDAO == null)
            grupoMuscularDAO = new GrupoMuscularDAO();
        return grupoMuscularDAO;
    }

    public static ImpressaoDigitalDAO getImpressaoDigitalDAO()
    {
        if(impressaoDigitalDAO == null)
            impressaoDigitalDAO = new ImpressaoDigitalDAO();
        return impressaoDigitalDAO;
    }

    public static ImpressaoDigitalEspelhoDAO getImpressaoDigitalEspelhoDAO()
    {
        if(impressaoDigitalEspelhoDAO == null)
            impressaoDigitalEspelhoDAO = new ImpressaoDigitalEspelhoDAO();
        return impressaoDigitalEspelhoDAO;
    }

    public static LogDAO getLogDAO()
    {
        if(logDAO == null)
            logDAO = new LogDAO();
        return logDAO;
    }

    public static LiberarDAO getLiberarDAO()
    {
        if(liberarDAO == null)
            liberarDAO = new LiberarDAO();
        return liberarDAO;
    }

    public static ModalidadeDAO getModalidadeDAO()
    {
        if(modalidadeDAO == null)
            modalidadeDAO = new ModalidadeDAO();
        return modalidadeDAO;
    }

    public static MotivoBloqueioDAO getMotivoBloqueioDAO()
    {
        if(motivoBloqueioDAO == null)
            motivoBloqueioDAO = new MotivoBloqueioDAO();
        return motivoBloqueioDAO;
    }

    public static ModoOperacaoDAO getModoOperacaoDAO()
    {
        if(modoOperacaoDAO == null)
            modoOperacaoDAO = new ModoOperacaoDAO();
        return modoOperacaoDAO;
    }

    public static ProtocoloDAO getProtocoloDAO()
    {
        if(protocoloDAO == null)
            protocoloDAO = new ProtocoloDAO();
        return protocoloDAO;
    }

    public static PerfilAcessoDAO getPerfilAcessoDAO()
    {
        if(perfilAcessoDAO == null)
            perfilAcessoDAO = new PerfilAcessoDAO();
        return perfilAcessoDAO;
    }

    public static PlanoDAO getPlanoDAO()
    {
        if(planoDAO == null)
            planoDAO = new PlanoDAO();
        return planoDAO;
    }

    public static ProdutoDAO getProdutoDAO()
    {
        if(produtoDAO == null)
            produtoDAO = new ProdutoDAO();
        return produtoDAO;
    }

    public static PagamentoDAO getPagamentoDAO()
    {
        if(pagamentoDAO == null)
            pagamentoDAO = new PagamentoDAO();
        return pagamentoDAO;
    }

    public static PermissaoDAO getPermissaoDAO()
    {
        if(permissaoDAO == null)
            permissaoDAO = new PermissaoDAO();
        return permissaoDAO;
    }

    public static RedeSocialDAO getRedeSocialDAO()
    {
        if(redeSocialDAO == null)
            redeSocialDAO = new RedeSocialDAO();
        return redeSocialDAO;
    }

    public static RespostaDAO getRespostaDAO()
    {
        if(respostaDAO == null)
            respostaDAO = new RespostaDAO();
        return respostaDAO;
    }

    public static RequisicaoDAO getRequisicaoDAO()
    {
        if(requisicaoDAO == null)
            requisicaoDAO = new RequisicaoDAO();
        return requisicaoDAO;
    }

    public static RegistroContaBancariaDAO getRegistroContaBancariaDAO()
    {
        if(registroContaBancariaDAO == null)
            registroContaBancariaDAO = new RegistroContaBancariaDAO();
        return registroContaBancariaDAO;
    }

    public static RegistroCaixaDAO getRegistroCaixaDAO()
    {
        if(registroCaixaDAO == null)
            registroCaixaDAO = new RegistroCaixaDAO();
        return registroCaixaDAO;
    }

    public static SerieDAO getSerieDAO()
    {
        if(serieDAO == null)
            serieDAO = new SerieDAO();
        return serieDAO;
    }

    public static TreinoDAO getTreinoDAO()
    {
        if(treinoDAO == null)
            treinoDAO = new TreinoDAO();
        return treinoDAO;
    }

    public static TipoUsuarioDAO getTipoUsuarioDAO()
    {
        if(tipoUsuarioDAO == null)
            tipoUsuarioDAO = new TipoUsuarioDAO();
        return tipoUsuarioDAO;
    }

    public static UsuarioPlanoDAO getUsuarioPlanoDAO()
    {
        if(usuarioPlanoDAO == null)
            usuarioPlanoDAO = new UsuarioPlanoDAO();
        return usuarioPlanoDAO;
    }

    public static UsuarioPlanoReadDAO getUsuarioPlanoReadDAO()
    {
        if(usuarioPlanoReadDAO == null)
            usuarioPlanoReadDAO = new UsuarioPlanoReadDAO();
        return usuarioPlanoReadDAO;
    }

    public static UsuarioPermissaoDAO getUsuarioPermissaoDAO()
    {
        if(usuarioPermissaoDAO == null)
            usuarioPermissaoDAO = new UsuarioPermissaoDAO();
        return usuarioPermissaoDAO;
    }

    public static UsuarioDAO getUsuarioDAO()
    {
        if(usuarioDAO == null)
            usuarioDAO = new UsuarioDAO();
        return usuarioDAO;
    }

    public static UsuarioReadDAO getUsuarioReadDAO()
    {
        if(usuarioReadDAO == null)
            usuarioReadDAO = new UsuarioReadDAO();
        return usuarioReadDAO;
    }

    private static AcessoDAO acessoDAO;
    private static AvaliacaoFisicaDAO avaliacaoFisicaDAO;
    private static AparelhoDAO aparelhoDAO;
    private static BancoDAO bancoDAO;
    private static CaixaDAO caixaDAO;
    private static CategoriaDAO categoriaDAO;
    private static ContaBancariaDAO contaBancariaDAO;
    private static ConfiguracaoDAO configuracaoDAO;
    private static DiaSemanaDAO diaSemanaDAO;
    private static DispositivoDAO dispositivoDAO;
    private static DedoDAO dedoDAO;
    private static DuracaoPlanoDAO duracaoPlanoDAO;
    private static DataBaseDAO dataBaseDAO;
    private static EmpresaDAO empresaDAO;
    private static EventoDAO eventoDAO;
    private static ExercicioDAO exercicioDAO;
    private static EstadoCivilDAO estadoCivilDAO;
    private static FichaDAO fichaDAO;
    private static FaixaDAO faixaDAO;
    private static FluxoCaixaDAO fluxoCaixaDAO;
    private static FormaDePagamentoDAO formaDePagamentoDAO;
    private static FornecedorDAO fornecedorDAO;
    private static GrupoMuscularDAO grupoMuscularDAO;
    private static ImpressaoDigitalDAO impressaoDigitalDAO;
    private static ImpressaoDigitalEspelhoDAO impressaoDigitalEspelhoDAO;
    private static LogDAO logDAO;
    private static LiberarDAO liberarDAO;
    private static ModalidadeDAO modalidadeDAO;
    private static MotivoBloqueioDAO motivoBloqueioDAO;
    private static ModoOperacaoDAO modoOperacaoDAO;
    private static ProtocoloDAO protocoloDAO;
    private static PerfilAcessoDAO perfilAcessoDAO;
    private static PlanoDAO planoDAO;
    private static ProdutoDAO produtoDAO;
    private static PagamentoDAO pagamentoDAO;
    private static PermissaoDAO permissaoDAO;
    private static RedeSocialDAO redeSocialDAO;
    private static RespostaDAO respostaDAO;
    private static RequisicaoDAO requisicaoDAO;
    private static RegistroContaBancariaDAO registroContaBancariaDAO;
    private static RegistroCaixaDAO registroCaixaDAO;
    private static SerieDAO serieDAO;
    private static TreinoDAO treinoDAO;
    private static TipoUsuarioDAO tipoUsuarioDAO;
    private static UsuarioPlanoDAO usuarioPlanoDAO;
    private static UsuarioPermissaoDAO usuarioPermissaoDAO;
    private static UsuarioPlanoReadDAO usuarioPlanoReadDAO;
    private static UsuarioDAO usuarioDAO;
    private static UsuarioReadDAO usuarioReadDAO;
    private static FuncionarioDAO funcionarioDAO;
    private static ConfiguracaoBoletoDAO configuracaoBoletoDAO;
}
