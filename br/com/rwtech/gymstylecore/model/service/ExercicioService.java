// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExercicioService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ExercicioDAO;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.pojo.GrupoMuscular;
import java.sql.Connection;
import java.util.*;

public class ExercicioService extends BaseService
{

    public ExercicioService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getExercicioDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getExercicioDAO().update(conn, pojo);
    }
    @Override
    public Boolean disabled(Connection conn, Long id)
    {
        return DaoLocator.getExercicioDAO().disabled(conn, id);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getExercicioDAO().delete(conn, id);
    }
    @Override
    public List<Exercicio> readByCriteria(Map input)
    {
        return DaoLocator.getExercicioDAO().readByCriteria(input);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getExercicioDAO().paginator(input);
    }
    @Override
    public Exercicio readById(Long id)
    {
        return (Exercicio)DaoLocator.getExercicioDAO().readById(id);
    }

    public Boolean readExists(String exercicio, Long id, String idGrupoMusculares)
    {
        Map map = new HashMap();
        map.put("criterioExercicio", exercicio);
        map.put("criterioGrupoMuscular", idGrupoMusculares);
        if(id != null)
            map.put("criterioExercicioId", id.toString());
        if(!readByCriteria(map).isEmpty())
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public void ajusteExercicios(List exercicios, List peitoral, List dorsal, List trapezio, List deltoide, List triceps, List biceps, 
            List antebraco, List quadriceps, List isquiotibiais, List gluteos, List adutores, List abdutores, List gastrocnemicos, 
            List soleos, List abdomen)
    {
        peitoral = new ArrayList();
        dorsal = new ArrayList();
        trapezio = new ArrayList();
        deltoide = new ArrayList();
        triceps = new ArrayList();
        biceps = new ArrayList();
        antebraco = new ArrayList();
        quadriceps = new ArrayList();
        isquiotibiais = new ArrayList();
        gluteos = new ArrayList();
        adutores = new ArrayList();
        abdutores = new ArrayList();
        gastrocnemicos = new ArrayList();
        soleos = new ArrayList();
        abdomen = new ArrayList();
        if(exercicios == null)
            exercicios = readByCriteria(new HashMap());
        Iterator i$ = exercicios.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Exercicio exercicio = (Exercicio)i$.next();
            String grupo = "";
            if(exercicio != null && exercicio.getGrupoMuscular() != null)
            {
                grupo = exercicio.getGrupoMuscular().getGrupoMuscular();
                if(grupo.startsWith("Peitoral"))
                    peitoral.add(exercicio);
                else
                if(grupo.startsWith("Dorsal"))
                    dorsal.add(exercicio);
                else
                if(grupo.startsWith("Trap\351zio"))
                    trapezio.add(exercicio);
                else
                if(grupo.startsWith("Delt\363ide"))
                    deltoide.add(exercicio);
                else
                if(grupo.startsWith("Tr\355ceps"))
                    triceps.add(exercicio);
                else
                if(grupo.startsWith("B\355ceps"))
                    biceps.add(exercicio);
                else
                if(grupo.startsWith("Ante"))
                    antebraco.add(exercicio);
                else
                if(grupo.startsWith("Quadr\355ceps"))
                    quadriceps.add(exercicio);
                else
                if(grupo.startsWith("Isquiotibiais"))
                    isquiotibiais.add(exercicio);
                else
                if(grupo.startsWith("Gl\372teos"))
                    gluteos.add(exercicio);
                else
                if(grupo.startsWith("Adutores"))
                    adutores.add(exercicio);
                else
                if(grupo.startsWith("Abdutores"))
                    abdutores.add(exercicio);
                else
                if(grupo.startsWith("Gastrocn\352micos"))
                    gastrocnemicos.add(exercicio);
                else
                if(grupo.startsWith("S\363leo"))
                    soleos.add(exercicio);
                else
                if(grupo.startsWith("Abdomen"))
                    abdomen.add(exercicio);
            }
        } while(true);
    }

}
