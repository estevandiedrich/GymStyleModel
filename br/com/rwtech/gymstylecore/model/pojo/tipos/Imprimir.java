// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Imprimir.java

package br.com.rwtech.gymstylecore.model.pojo.tipos;


public enum Imprimir
{
	ENTRADA(1),
    SAIDA(2),
    NAO_IMPRIMIR(3);
	private int valor;
	
	Imprimir(int valor)
	{
		this.valor = valor;
	}
	
    public int getValor()
    {
        return valor;
    }
}
