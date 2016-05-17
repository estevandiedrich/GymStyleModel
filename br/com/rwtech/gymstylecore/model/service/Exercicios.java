// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exercicios.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.ExercicioDAO;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.pojo.GrupoMuscular;
import java.util.*;

public class Exercicios
{

    public Exercicios()
    {
    }

    public static List getExercicios()
    {
        return exercicios;
    }

    public static List getPeitoral()
    {
        return peitoral;
    }

    public static List getAbdomen()
    {
        return abdomen;
    }

    public static List getAdutores()
    {
        return adutores;
    }

    public static List getAbdutores()
    {
        return abdutores;
    }

    public static List getSoleos()
    {
        return soleos;
    }

    public static List getGastrocnemicos()
    {
        return gastrocnemicos;
    }

    public static List getGluteos()
    {
        return gluteos;
    }

    public static List getIsquiotibiais()
    {
        return isquiotibiais;
    }

    public static List getQuadriceps()
    {
        return quadriceps;
    }

    public static List getAnteBraco()
    {
        return antebraco;
    }

    public static List getBiceps()
    {
        return biceps;
    }

    public static List getTriceps()
    {
        return triceps;
    }

    public static List getDeltoide()
    {
        return deltoide;
    }

    public static List getTrapezio()
    {
        return trapezio;
    }

    public static List getDorsal()
    {
        return dorsal;
    }

    public static void refresh()
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
        exercicios = DaoLocator.getExercicioDAO().readByCriteria(new HashMap());
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

    private static List exercicios;
    private static List peitoral;
    private static List dorsal;
    private static List trapezio;
    private static List deltoide;
    private static List triceps;
    private static List biceps;
    private static List antebraco;
    private static List quadriceps;
    private static List isquiotibiais;
    private static List gluteos;
    private static List adutores;
    private static List abdutores;
    private static List gastrocnemicos;
    private static List soleos;
    private static List abdomen;
}
