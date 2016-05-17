// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.*;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.Base64;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            UsuarioPlanoDAO, EstadoCivilDAO, RedeSocialDAO, ImpressaoDigitalDAO, 
//            FichaDAO

public class UsuarioDAO extends BaseDAO
{

    public UsuarioDAO()
    {
    }
    @Override
    public void update(Connection conn, Object obj)
    {
    	Usuario pojo = (Usuario)obj;
        super.update(conn, pojo);
        if(pojo.getPlanos() != null && !pojo.getPlanos().isEmpty())
            DaoLocator.getUsuarioPlanoDAO().create(conn, (Plano)pojo.getPlanos().get(0), pojo.getId());
    }

    public void updateSincronizado(Long id, Boolean status)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE usuarios SET sincronizado = ? WHERE id_usuarios = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            pstm.setBoolean(1, status.booleanValue());
            pstm.setLong(2, id.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateAllSincronizadoFalse()
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE usuarios SET sincronizado = false";
            Statement pstm = conn.createStatement();
            pstm.execute(sql);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void atualizarCartaoCatraca(Long idUsuario)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = " update usuarios set cartao_proximidade_catraca =  (select cartao_proximidade from usuarios where id_usuarios = ?)  where id_usuarios = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUsuario.longValue());
            pstm.setLong(2, idUsuario.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateCartaoProximidade(Long idUsuario, String cartao)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = " update usuarios set cartao_proximidade = ? where id_usuarios = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cartao);
            pstm.setLong(2, idUsuario.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateLoginSenha(Long idUsuario, String login, String senha)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = " update usuarios set login = ?, senha = ? where id_usuarios = ?";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, login);
            pstm.setString(2, toMd5(senha));
            pstm.setLong(3, idUsuario.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Usuario pojo = (Usuario)obj;
        addParametro("nome", Validador.clearCharEspacoDuplo(pojo.getUsuario()), 12);
        addParametro("data_nascimento", pojo.getDataNascimento(), 91);
        addParametro("cpf", Validador.clearCPF(pojo.getCpf()), 12);
        addParametro("rg", Validador.clearCharEspacoDuplo(pojo.getRg()), 12);
        addParametro("sexo", pojo.getSexo(), 12);
        if(pojo.getFoto() != null)
        {
            String foto = Base64.encodeBytes(pojo.getFoto());
            byte foto2[] = pojo.getFoto();
            int j = 0;
            int lar = 200;
            int alt = 240;
            do
            {
                if(foto.length() <= 10000)
                    break;
                foto2 = ImageManipulation.diminuiResolucaoImagem(lar, alt, pojo.getFoto());
                foto = Base64.encodeBytes(foto2);
                if(++j > 5)
                    break;
                lar -= 20;
                alt -= 20;
            } while(true);
            if(foto.length() < 10000)
                addParametro("foto", foto, 12);
            else
                System.out.println("FOTO NAO pode ser carregada");
        }
        addParametro("endereco", Validador.clearCharEspacoDuplo(pojo.getEndereco()), 12);
        addParametro("complemento", Validador.clearCharEspacoDuplo(pojo.getComplemento()), 12);
        addParametro("bairro", Validador.clearCharEspacoDuplo(pojo.getBairro()), 12);
        addParametro("cidade", Validador.clearCharEspacoDuplo(pojo.getCidade()), 12);
        addParametro("uf", pojo.getUf(), 12);
        addParametro("cep", Validador.clearCharEspacoDuplo(pojo.getCep()), 12);
        addParametro("telefone", Validador.limparEspaco(pojo.getTelefone()), 12);
        addParametro("celular", Validador.limparEspaco(pojo.getCelular()), 12);
        addParametro("email", Validador.limparEspaco(pojo.getEmail()), 12);
        addParametro("numero_filhos", pojo.getNumeroFilhos(), 4);
        if(pojo.getMatricula() != null)
            addParametro("matricula", pojo.getMatricula(), -5);
        addParametro("observacao", Validador.clearCharEspacoDuplo(pojo.getObservacaoAdd()), 12);
        addParametro("profissao", Validador.clearCharEspacoDuplo(pojo.getProfissao()), 12);
        if(pojo.getIsAdm() != null)
            addParametro(ADM, pojo.getIsAdm(), 16);
        if(pojo.getIsSecretaria() != null)
            addParametro(SECRETARIA, pojo.getIsSecretaria(), 16);
        if(pojo.getIsInstrutor() != null)
            addParametro(INSTRUTOR, pojo.getIsInstrutor(), 16);
        if(pojo.isAluno() != null)
            addParametro(ALUNO, pojo.isAluno(), 16);
        if(pojo.getAtivoAluno() != null)
            addParametro("ativo_aluno", pojo.getAtivoAluno(), 16);
        if(pojo.getAtivoFuncionario() != null)
            addParametro("ativo_funcionario", pojo.getAtivoFuncionario(), 16);
        if(pojo.getEstadoCivil() != null)
            addParametro("estado_civil_fk", pojo.getEstadoCivil().getId(), -5);
        else
            addParametro("estado_civil_fk", null, -5);
        if(pojo.getRedeSocial() != null)
            addParametro("rede_social_fk", pojo.getRedeSocial().getId(), -5);
        else
            addParametro("rede_social_fk", null, -5);
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "usuarios";
    }
    @Override
    public Usuario extractSimple(ResultSet rs)
        throws Exception
    {
        Usuario pojo = null;
        if(rs != null)
        {
            pojo = new Usuario();
            pojo.setId(rsGetId(rs));
            pojo.setUsuario(rs.getString("nome"));
            pojo.setMatricula(Long.valueOf(rs.getLong("matricula")));
            pojo.setCartaoProximidade(rs.getString("cartao_proximidade"));
            pojo.setSincronizado(Boolean.valueOf(rs.getBoolean("sincronizado")));
        }
        return pojo;
    }
    @Override
    public Usuario extract(ResultSet rs)
        throws Exception
    {
        return extract(rs, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
    }

    public Usuario extractFichas(ResultSet rs)
        throws Exception
    {
        return extract(rs, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
    }

    public Usuario extractPlanos(ResultSet rs)
        throws Exception
    {
        return extract(rs, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
    }

    public Usuario extractAvaliacoes(ResultSet rs)
        throws Exception
    {
        return extract(rs, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
    }

    public Usuario extract(ResultSet rs, Boolean fichas, Boolean avaliacoes, Boolean digitais, Boolean planos)
        throws Exception
    {
        Usuario pojo = null;
        if(rs != null)
        {
            pojo = new Usuario();
            pojo.setId(rsGetId(rs));
            if(rs.getString("matricula") != null)
                pojo.setMatricula(Long.valueOf(rs.getLong("matricula")));
            pojo.setDataNascimento(CalendarUtil.setDateCalendar(rs.getDate("data_nascimento")));
            pojo.setDataCadastro(CalendarUtil.setDateCalendar(rs.getDate("data_cadastro")));
            pojo.setUsuario(rs.getString("nome"));
            pojo.setCpf(Validador.formatterCPF(rs.getString("cpf")));
            pojo.setRg(rs.getString("rg"));
            String foto = rs.getString("foto");
            if(foto != null && !foto.isEmpty())
                pojo.setFoto(Base64.decode(foto));
            pojo.setSexo(rs.getString("sexo"));
            pojo.setTelefone(ConsultaUtil.clearString(rs.getString("telefone")));
            pojo.setCelular(ConsultaUtil.clearString(rs.getString("celular")));
            pojo.setEndereco(rs.getString("endereco"));
            pojo.setComplemento(rs.getString("complemento"));
            pojo.setBairro(rs.getString("bairro"));
            pojo.setProfissao(rs.getString("profissao"));
            pojo.setObservacao(rs.getString("observacao"));
            pojo.setNumeroFilhos(Integer.valueOf(rs.getInt("numero_filhos")));
            pojo.setCidade(rs.getString("cidade"));
            pojo.setUf(rs.getString("uf"));
            pojo.setCep(rs.getString("cep"));
            pojo.setCartaoProximidade(rs.getString("cartao_proximidade"));
            pojo.setEmail(rs.getString("email"));
            pojo.setLogin(rs.getString("login"));
            pojo.setSenha(rs.getString("senha"));
            pojo.setEstadoCivil((EstadoCivil)DaoLocator.getEstadoCivilDAO().readById(Long.valueOf(rs.getLong("estado_civil_fk"))));
            pojo.setRedeSocial((RedeSocial)DaoLocator.getRedeSocialDAO().readById(Long.valueOf(rs.getLong("rede_social_fk"))));
            if(digitais.booleanValue())
            {
                Map filtro = new HashMap();
                filtro.put("criterioUsuario", pojo.getId().toString());
                pojo.setImpressoesDigitais(DaoLocator.getImpressaoDigitalDAO().readByCriteria(filtro));
            }
            if(planos.booleanValue())
                pojo.setPlanos(DaoLocator.getUsuarioPlanoDAO().readPlanos(pojo.getId(), Boolean.FALSE));
            if(fichas.booleanValue())
                pojo.setFichas(DaoLocator.getFichaDAO().readFichasByIdUsuario(pojo.getId()));
            pojo.setSincronizado(Boolean.valueOf(rs.getBoolean("sincronizado")));
            pojo.setAtivoAluno(Boolean.valueOf(rs.getBoolean("ativo_aluno")));
            pojo.setAtivoFuncionario(Boolean.valueOf(rs.getBoolean("ativo_funcionario")));
            pojo.setIsAdm(Boolean.valueOf(rs.getBoolean("adm")));
            pojo.setIsSecretaria(Boolean.valueOf(rs.getBoolean("secretaria")));
            pojo.setIsInstrutor(Boolean.valueOf(rs.getBoolean("instrutor")));
            pojo.setIsAluno(Boolean.valueOf(rs.getBoolean("aluno")));
        }
        return pojo;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioUsuario = (String)input.get("criterioNome");
        if(criterioUsuario != null && !criterioUsuario.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioUsuario)).append("%'").toString());
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
        {
            lista.add((new StringBuilder()).append("matricula = '").append(criterioMatricula).append("'").toString());
            String criterioMatriculaId = (String)input.get("criterioMatriculaId");
            if(criterioMatriculaId != null && !criterioMatriculaId.isEmpty())
                lista.add((new StringBuilder()).append("id_usuarios != '").append(criterioMatriculaId).append("'").toString());
        }
        String criterioCpf = (String)input.get("criterioCpf");
        if(criterioCpf != null && !criterioCpf.isEmpty())
        {
            lista.add((new StringBuilder()).append("cpf = '").append(Validador.clearCPF(criterioCpf)).append("'").toString());
            String idUsuario = (String)input.get("criterioCpfIdUsuario");
            if(idUsuario != null && !idUsuario.isEmpty())
                lista.add((new StringBuilder()).append("id_usuarios <> '").append(idUsuario).append("'").toString());
        }
        if(input.get("criterioCartaoProximidade") != null)
        {
            List list = (List)input.get("criterioCartaoProximidade");
            int i = 0;
            String caso = "";
            for(; i < list.size(); i++)
            {
                caso = (new StringBuilder()).append(caso).append("cartao_proximidade = '").append((String)list.get(i)).append("'").toString();
                if(i != list.size() - 1)
                    caso = (new StringBuilder()).append(caso).append(" or ").toString();
            }

            lista.add((new StringBuilder()).append("(").append(caso).append(")").toString());
            lista.add((new StringBuilder()).append("id_usuarios != '").append((String)input.get("criterioCartaoIdUsuario")).append("'").toString());
        }
        String criterioAlunoOuFuncionario = (String)input.get("criterioAlunoOuFuncionario");
        if(criterioAlunoOuFuncionario != null && !criterioAlunoOuFuncionario.isEmpty() && !criterioAlunoOuFuncionario.equalsIgnoreCase("truefalse"))
            lista.add("ativo_aluno = true or ativo_funcionario = true");
        String criterioAtivoAluno = (String)input.get("criterioAtivoAluno");
        if(criterioAtivoAluno != null && !criterioAtivoAluno.isEmpty() && !criterioAtivoAluno.equalsIgnoreCase("truefalse"))
        {
            lista.add("aluno = true");
            lista.add((new StringBuilder()).append("ativo_aluno = ").append(criterioAtivoAluno).toString());
        }
        String criterioAtivoFuncionario = (String)input.get("criterioAtivoFuncionario");
        if(criterioAtivoFuncionario != null && !criterioAtivoFuncionario.isEmpty() && !criterioAtivoFuncionario.equalsIgnoreCase("truefalse"))
            lista.add((new StringBuilder()).append("ativo_funcionario = ").append(criterioAtivoFuncionario).toString());
        String criterioLogin = (String)input.get("criterioLogin");
        if(criterioLogin != null && !criterioLogin.isEmpty())
        {
            lista.add((new StringBuilder()).append(" login = '").append(criterioLogin).append("'").toString());
            String idUsuario = (String)input.get("criterioIdUsuario");
            if(idUsuario != null && !idUsuario.isEmpty())
                lista.add((new StringBuilder()).append("id_usuarios <> '").append(idUsuario).append("'").toString());
        }
        String criterioTipoDiferente = (String)input.get("criterioTipoDiferente");
        if(criterioTipoDiferente != null)
            lista.add((new StringBuilder()).append(" id_tipos_usuarios_fk <> '").append(criterioTipoDiferente).append("'").toString());
        String criterioTipoUsuario = (String)input.get("criterioTipoUsuario");
        if(criterioTipoUsuario != null && !criterioTipoUsuario.isEmpty())
        {
            if(criterioTipoUsuario.equalsIgnoreCase("aluno"))
                lista.add(" aluno = true");
            if(criterioTipoUsuario.equalsIgnoreCase("funcionario"))
                lista.add("(adm = true <> secretaria = true <> instrutor = true)");
        }
        Boolean criterioPendencias = (Boolean)input.get("criterioPendencias");
        if(criterioPendencias != null && criterioPendencias.booleanValue())
            lista.add("(((cartao_proximidade is null or cartao_proximidade ='') and (0 = (select count(id_digitais) from digitais where id_usuarios_fk = id_usuarios))))");
        return lista;
    }

    public String toMd5(String input)
    {
        if(input != null && !input.equalsIgnoreCase(""))
            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte messageDigest[] = md.digest(input.getBytes());
                BigInteger number = new BigInteger(1, messageDigest);
                return number.toString(16);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        else
            return "";
    }

    public Boolean disabledAluno(Long id)
    {
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" update ").append(TABLE).append(" set ativo_aluno = false, matricula = null where id_").append(TABLE).append(" = ?;").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
            return Boolean.valueOf(true);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            return Boolean.valueOf(false);
        }
    }

    private static String ADM = "adm";
    private static String SECRETARIA = "secretaria";
    private static String INSTRUTOR = "instrutor";
    private static String ALUNO = "aluno";

}
