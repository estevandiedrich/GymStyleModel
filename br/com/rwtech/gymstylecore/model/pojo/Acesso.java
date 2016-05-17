// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Acesso.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Dispositivo

public class Acesso extends POJO
    implements Cloneable
{

    public Acesso()
    {
    }

    public Boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }

    public Boolean getLivre()
    {
        return livre;
    }

    public void setLivre(Boolean livre)
    {
        this.livre = livre;
    }

    public List<Faixa> getFaixas()
    {
        return faixas;
    }

    public void setFaixas(List<Faixa> faixas)
    {
        this.faixas = faixas;
    }

    public List<Dispositivo> getDispositivos()
    {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos)
    {
        this.dispositivos = dispositivos;
    }

    public boolean equals(Acesso obj)
    {
        boolean igual = true;
        if(livre != obj.getLivre())
            return false;
        Iterator it = dispositivos.iterator();
        Iterator it2 = obj.getDispositivos().iterator();
        while(it.hasNext())
        {
        	Dispositivo dis = (Dispositivo)it.next();
        	while(it2.hasNext())
        	{
        		Dispositivo diss = (Dispositivo)it2.next();
        		if(dis.getId() != diss.getId())
        		{
        			igual = false;
        			break;
        		}
        	}
        	
        }
        return igual;
    }

    public Acesso clone()
    {
        try
        {
            return (Acesso)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Boolean livre;
    private Boolean ativo;
    private List<Faixa> faixas;
    private List<Dispositivo> dispositivos;
}
