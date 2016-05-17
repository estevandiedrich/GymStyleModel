// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IdentificacaoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.service:
//            DispositivoService

public class IdentificacaoService
{

    public IdentificacaoService()
    {
    }

    public static Map getOpcoes()
    {
        Map mapa = new LinkedHashMap();
        mapa.put("H", "Hamster");
        mapa.put("C", "Catraca");
        return mapa;
    }

    public static Map getDedos()
    {
        Map mapa = new LinkedHashMap();
        mapa.put(POL_DIR, POL_DIR);
        mapa.put(IND_DIR, IND_DIR);
        mapa.put(MED_DIR, MED_DIR);
        mapa.put(ANE_DIR, ANE_DIR);
        mapa.put(MIN_DIR, MIN_DIR);
        mapa.put(POL_ESQ, POL_ESQ);
        mapa.put(IND_ESQ, IND_ESQ);
        mapa.put(MED_ESQ, MED_ESQ);
        mapa.put(ANE_ESQ, ANE_ESQ);
        mapa.put(MIN_ESQ, MIN_ESQ);
        return mapa;
    }

    public static Map getDispositivos()
    {
        Map map = new HashMap();
        map.put("criterioOnline", "true");
        List lista = ServiceLocator.getDispositivoService().readByCriteria(map);
        Map mapa = new LinkedHashMap();
        Dispositivo pojo;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); mapa.put(pojo.getId(), pojo.getDispositivo()))
        {
            pojo = (Dispositivo)i$.next();
            if(pojo.getDispositivo() == null || pojo.getDispositivo().isEmpty())
                pojo.setDispositivo(pojo.getEnderecoIp());
        }

        return mapa;
    }

    private static String POL_DIR = "Polegar Direito";
    private static String IND_DIR = "Indicador Direito";
    private static String MED_DIR = "M\351dio Direito";
    private static String ANE_DIR = "Anelar Direito";
    private static String MIN_DIR = "M\355nimo Direito";
    private static String POL_ESQ = "Polegar Esquerdo";
    private static String IND_ESQ = "Indicador Esquerdo";
    private static String MED_ESQ = "M\351dio Esquerdo";
    private static String ANE_ESQ = "Anelar Esquerdo";
    private static String MIN_ESQ = "M\355nimo Esquerdo";

}
