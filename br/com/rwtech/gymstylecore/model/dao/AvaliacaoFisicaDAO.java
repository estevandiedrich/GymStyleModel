// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AvaliacaoFisicaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            UsuarioDAO, ProtocoloDAO

public class AvaliacaoFisicaDAO extends BaseDAO
{

    public AvaliacaoFisicaDAO()
    {
    }

    public void create(AvaliacaoFisica pojo, Long idAluno)
    {
        try
        {
            String sql = "INSERT INTO avaliacoes_fisicas(data_avaliacao, descricao, peso, altura,  axilar_media, imc, braco_dir, braco_esq, coxa_dir, coxa_esq, panturrilha_dir, panturrilha_esq, torax, quadril, cintura, abdomen, subescapular, tricipital, peitoral, abdominal, supra_iliaca, coxa, panturrilha, gordura_atual, gordura_ideal, massa_magra, massa_gorda, data_proxima_avaliacao, id_aluno_fk, id_instrutor_fk, id_protocolo_fk)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setDate(++i, new Date(System.currentTimeMillis()));
            pstm.setString(++i, pojo.getDescricao());
            pstm.setDouble(++i, pojo.getPeso().doubleValue());
            pstm.setDouble(++i, pojo.getAltura().doubleValue());
            pstm.setDouble(++i, pojo.getAxilarMedia().doubleValue());
            pstm.setDouble(++i, pojo.getImc().doubleValue());
            pstm.setDouble(++i, pojo.getBracoDir().doubleValue());
            pstm.setDouble(++i, pojo.getBracoEsq().doubleValue());
            pstm.setDouble(++i, pojo.getCoxaDir().doubleValue());
            pstm.setDouble(++i, pojo.getCoxaEsq().doubleValue());
            pstm.setDouble(++i, pojo.getPanturrilhaDir().doubleValue());
            pstm.setDouble(++i, pojo.getPanturrilhaEsq().doubleValue());
            pstm.setDouble(++i, pojo.getTorax().doubleValue());
            pstm.setDouble(++i, pojo.getQuadril().doubleValue());
            pstm.setDouble(++i, pojo.getCintura().doubleValue());
            pstm.setDouble(++i, pojo.getAbdomen().doubleValue());
            pstm.setDouble(++i, pojo.getSubescapular().doubleValue());
            pstm.setDouble(++i, pojo.getTricipital().doubleValue());
            pstm.setDouble(++i, pojo.getPeitoral().doubleValue());
            pstm.setDouble(++i, pojo.getAbdominal().doubleValue());
            pstm.setDouble(++i, pojo.getSupraIliaca().doubleValue());
            pstm.setDouble(++i, pojo.getCoxa().doubleValue());
            pstm.setDouble(++i, pojo.getPanturrilha().doubleValue());
            pstm.setDouble(++i, pojo.getGorduraAtual().doubleValue());
            pstm.setString(++i, pojo.getGorduraIdeal());
            pstm.setDouble(++i, pojo.getMassaMagra().doubleValue());
            pstm.setDouble(++i, pojo.getMassaGorda().doubleValue());
            pstm.setDate(++i, CalendarUtil.setDateSqlCalendar(pojo.getDataProximaAvaliacao()));
            pstm.setLong(++i, idAluno.longValue());
            pstm.setLong(++i, pojo.getInstrutor().getId().longValue());
            pstm.setLong(++i, pojo.getProtocolo().getId().longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public AvaliacaoFisica extract(ResultSet rs)
        throws Exception
    {
        AvaliacaoFisica pojo = null;
        if(rs != null)
        {
            pojo = new AvaliacaoFisica();
            pojo.setId(rsGetId(rs));
            pojo.setDataAvaliacao(CalendarUtil.setDateCalendar(rs.getDate("data_avaliacao")));
            pojo.setDataProximaAvaliacao(CalendarUtil.setDateCalendar(rs.getDate("data_proxima_avaliacao")));
            pojo.setDescricao(rs.getString("descricao"));
            pojo.setPeso(Double.valueOf(rs.getDouble("peso")));
            pojo.setAltura(Double.valueOf(rs.getDouble("altura")));
            pojo.setImc(Double.valueOf(rs.getDouble("imc")));
            pojo.setBracoDir(Double.valueOf(rs.getDouble("braco_dir")));
            pojo.setBracoEsq(Double.valueOf(rs.getDouble("braco_esq")));
            pojo.setCoxaDir(Double.valueOf(rs.getDouble("coxa_dir")));
            pojo.setCoxaEsq(Double.valueOf(rs.getDouble("coxa_esq")));
            pojo.setPanturrilhaDir(Double.valueOf(rs.getDouble("panturrilha_dir")));
            pojo.setPanturrilhaEsq(Double.valueOf(rs.getDouble("panturrilha_esq")));
            pojo.setTorax(Double.valueOf(rs.getDouble("torax")));
            pojo.setCintura(Double.valueOf(rs.getDouble("cintura")));
            pojo.setAbdomen(Double.valueOf(rs.getDouble("abdomen")));
            pojo.setQuadril(Double.valueOf(rs.getDouble("quadril")));
            pojo.setSubescapular(Double.valueOf(rs.getDouble("subescapular")));
            pojo.setTricipital(Double.valueOf(rs.getDouble("tricipital")));
            pojo.setPeitoral(Double.valueOf(rs.getDouble("peitoral")));
            pojo.setAbdominal(Double.valueOf(rs.getDouble("abdominal")));
            pojo.setSupraIliaca(Double.valueOf(rs.getDouble("supra_iliaca")));
            pojo.setCoxa(Double.valueOf(rs.getDouble("coxa")));
            pojo.setPanturrilha(Double.valueOf(rs.getDouble("panturrilha")));
            pojo.setAxilarMedia(Double.valueOf(rs.getDouble("axilar_media")));
            pojo.setGorduraAtual(Double.valueOf(rs.getDouble("gordura_atual")));
            pojo.setGorduraIdeal(rs.getString("gordura_ideal"));
            pojo.setMassaMagra(Double.valueOf(rs.getDouble("massa_magra")));
            pojo.setMassaGorda(Double.valueOf(rs.getDouble("massa_gorda")));
            pojo.setInstrutor((Usuario)DaoLocator.getUsuarioDAO().readById(Long.valueOf(rs.getLong("id_instrutor_fk"))));
            pojo.setProtocolo((Protocolo)DaoLocator.getProtocoloDAO().readById(Long.valueOf(rs.getLong("id_protocolo_fk"))));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "avaliacoes_fisicas";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        String criterioAluno = (String)input.get("criterioAluno");
        if(criterioAluno != null && !criterioAluno.isEmpty())
            lista.add((new StringBuilder()).append("id_aluno_fk = '").append(criterioAluno).append("'").toString());
        String criterioMatricula = (String)input.get("criterioMatricula");
        if(criterioMatricula != null && !criterioMatricula.isEmpty())
            lista.add((new StringBuilder()).append("matricula = '").append(criterioMatricula).append("'").toString());
        return lista;
    }
}
