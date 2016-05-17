// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExercicioDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.BaseDAO;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.ResultSet;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            AparelhoDAO, GrupoMuscularDAO

public class ExercicioDAO extends BaseDAO
{

    public ExercicioDAO()
    {
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Exercicio pojo = (Exercicio)obj;
        addParametro("descricao", pojo.getDescricao(), 12);
        addParametro("nome", pojo.getExercicio(), 12);
        addParametro("id_grupos_musculares_fk", pojo.getGrupoMuscular().getId(), -5);
    }
    @Override
    public Exercicio extract(ResultSet rs)
        throws Exception
    {
        Exercicio pojo = null;
        if(rs != null)
        {
            pojo = new Exercicio();
            pojo.setId(rsGetId(rs));
            pojo.setExercicio(rs.getString("nome"));
            pojo.setDescricao(rs.getString("descricao"));
            pojo.setAparelho((Aparelho)DaoLocator.getAparelhoDAO().readById(Long.valueOf(rs.getLong("id_aparelhos_fk"))));
            pojo.setGrupoMuscular((GrupoMuscular)DaoLocator.getGrupoMuscularDAO().readById(Long.valueOf(rs.getLong("id_grupos_musculares_fk"))));
        }
        return pojo;
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "exercicios";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        String criterioExercicio = (String)input.get("criterioExercicio");
        if(criterioExercicio != null && !criterioExercicio.isEmpty())
        {
            lista.add((new StringBuilder()).append(" nome ='").append(criterioExercicio).append("'").toString());
            String criterioExercicioId = (String)input.get("criterioExercicioId");
            if(criterioExercicioId != null && !criterioExercicioId.isEmpty())
                lista.add((new StringBuilder()).append(" id_exercicios != '").append(criterioExercicioId).append("'").toString());
        }
        String criterioGrupoMuscular = (String)input.get("criterioGrupoMuscular");
        if(criterioGrupoMuscular != null && !criterioGrupoMuscular.isEmpty())
            lista.add((new StringBuilder()).append("id_grupos_musculares_fk   = '").append(criterioGrupoMuscular).append("'").toString());
        String criterioAtivo = (String)input.get("criterioAtivo");
        if(criterioAtivo != null && !criterioAtivo.isEmpty())
            lista.add("ativo = true");
        return lista;
    }
}
