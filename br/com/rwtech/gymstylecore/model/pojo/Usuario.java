// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Usuario.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Calendar;
import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Acesso, RedeSocial, EstadoCivil

public class Usuario extends POJO
{

    public Usuario()
    {
        ativoAluno = null;
        ativoFuncionario = null;
        isAdm = null;
        isSecretaria = null;
        isInstrutor = null;
        isAluno = null;
    }

    public Boolean getAtivoFuncionario()
    {
        return ativoFuncionario;
    }

    public void setAtivoFuncionario(Boolean ativoFuncionario)
    {
        this.ativoFuncionario = ativoFuncionario;
    }

    public Boolean isAluno()
    {
        return isAluno;
    }

    public void setIsAluno(Boolean isAluno)
    {
        this.isAluno = isAluno;
    }

    public Boolean getIsAdm()
    {
        return isAdm;
    }

    public void setIsAdm(Boolean isAdm)
    {
        this.isAdm = isAdm;
    }

    public Boolean getIsSecretaria()
    {
        return isSecretaria;
    }

    public void setIsSecretaria(Boolean secretaria)
    {
        isSecretaria = secretaria;
    }

    public Boolean getIsInstrutor()
    {
        return isInstrutor;
    }

    public void setIsInstrutor(Boolean instrutor)
    {
        isInstrutor = instrutor;
    }

    public Acesso getAcesso()
    {
        return acesso;
    }

    public void setAcesso(Acesso acesso)
    {
        this.acesso = acesso;
    }

    public Long getMatricula()
    {
        return matricula;
    }

    public void setMatricula(Long matricula)
    {
        this.matricula = matricula;
    }

    public Boolean getAtivoAluno()
    {
        return ativoAluno;
    }

    public Boolean getFuncionario()
    {
        if(isAdm != null && isInstrutor != null && isSecretaria != null && (isAdm.booleanValue() || isInstrutor.booleanValue() || isSecretaria.booleanValue()))
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public void setAtivoAluno(Boolean ativo)
    {
        ativoAluno = ativo;
    }

    public List<Ficha> getFichas()
    {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas)
    {
        this.fichas = fichas;
    }

    public Boolean getSincronizado()
    {
        return sincronizado;
    }

    public void setSincronizado(Boolean sincronizado)
    {
        this.sincronizado = sincronizado;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    public EstadoCivil getEstadoCivil()
    {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil)
    {
        this.estadoCivil = estadoCivil;
    }

    public Integer getNumeroFilhos()
    {
        return numeroFilhos;
    }

    public void setNumeroFilhos(Integer numeroFilhos)
    {
        this.numeroFilhos = numeroFilhos;
    }

    public String getObservacaoAdd()
    {
        return observacaoAdd;
    }

    public void setObservacao(String observacaoAdd)
    {
        this.observacaoAdd = observacaoAdd;
    }

    public String getProfissao()
    {
        return profissao;
    }

    public void setProfissao(String profissao)
    {
        this.profissao = profissao;
    }

    public RedeSocial getRedeSocial()
    {
        return redeSocial;
    }

    public void setRedeSocial(RedeSocial redeSocial)
    {
        this.redeSocial = redeSocial;
    }

    public List<AvaliacaoFisica> getAvaliacaoFisica()
    {
        return avaliacaoFisica;
    }

    public void setAvaliacaoFisica(List<AvaliacaoFisica> avaliacaoFisica)
    {
        this.avaliacaoFisica = avaliacaoFisica;
    }

    public String getCartaoProximidade()
    {
        return cartaoProximidade;
    }

    public void setCartaoProximidade(String cartaoProximidade)
    {
        this.cartaoProximidade = cartaoProximidade;
    }

    public String getCelular()
    {
        return celular;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public Calendar getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public Calendar getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto(byte foto[])
    {
        this.foto = foto;
    }

    public List<ImpressaoDigital> getImpressoesDigitais()
    {
        return impressoesDigitais;
    }

    public void setImpressoesDigitais(List<ImpressaoDigital> impressoesDigitais)
    {
        this.impressoesDigitais = impressoesDigitais;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public List<Plano> getPlanos()
    {
        return planos;
    }

    public void setPlanos(List<Plano> planos)
    {
        this.planos = planos;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getUf()
    {
        return uf;
    }

    public void setUf(String uf)
    {
        this.uf = uf;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String toString()
    {
        if(usuario != null)
            return usuario.toString();
        else
            return "";
    }

    public Long getTipoUsuario()
    {
        if(isAdm != null && isInstrutor != null && isSecretaria != null && isAluno != null)
        {
            if(isAdm.booleanValue())
                return Long.valueOf(1L);
            if(isInstrutor.booleanValue())
                return Long.valueOf(3L);
            if(isSecretaria.booleanValue())
                return Long.valueOf(4L);
            if(isAluno.booleanValue())
                return Long.valueOf(2L);
        }
        return Long.valueOf(2L);
    }

    private String usuario;
    private Calendar dataNascimento;
    private String cpf;
    private String rg;
    private byte foto[];
    private String sexo;
    private String telefone;
    private String celular;
    private String email;
    private Calendar dataCadastro;
    private String endereco;
    private String cidade;
    private String uf;
    private String cep;
    private String cartaoProximidade;
    private String login;
    private String senha;
    private List<ImpressaoDigital> impressoesDigitais;
    private List<AvaliacaoFisica> avaliacaoFisica;
    private List<Plano> planos;
    private List<Ficha> fichas;
    private String bairro;
    private String complemento;
    private String observacaoAdd;
    private String profissao;
    private Integer numeroFilhos;
    private Boolean sincronizado;
    private Boolean ativoAluno;
    private Boolean ativoFuncionario;
    private Long matricula;
    private Acesso acesso;
    private Boolean isAdm;
    private Boolean isSecretaria;
    private Boolean isInstrutor;
    private Boolean isAluno;
    private RedeSocial redeSocial;
    private EstadoCivil estadoCivil;
}
