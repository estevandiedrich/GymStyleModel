// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FormaDePagamento.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class FormaDePagamento extends POJO
{

    public FormaDePagamento()
    {
    }

    public FormaDePagamento(Long id, String formaDePagamento)
    {
        setId(id);
        this.formaDePagamento = formaDePagamento;
    }

    public String getFormaDePagamento()
    {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento)
    {
        this.formaDePagamento = formaDePagamento;
    }

    public String getImage()
    {
        if(formaDePagamento.equalsIgnoreCase("Boleto"))
            image = "<img src='images/boleto.png' class='information' title='Boleto!'/>";
        else
        if(formaDePagamento.equalsIgnoreCase("Cheque"))
            image = "<img src='images/cheque.png' class='information' title='Cheque!'/>";
        else
        if(formaDePagamento.equalsIgnoreCase("Dinheiro"))
            image = "<img src='images/money.png' class='information' title='Dinheiro!'/>";
        else
        if(formaDePagamento.equalsIgnoreCase("Cart\343o (D\351bito)"))
            image = "<img src='images/cartaoDebito.png' class='information' title='Cart\343o (D\351bito)!'/>";
        else
        if(formaDePagamento.equalsIgnoreCase("Cart\343o (Cr\351dito)"))
            image = "<img src='images/cartaoCredito.png' class='information' title='Cart\343o (Cr\351dito)!'/>";
        else
            image = "<img src='images/cartaoCredito.png' class='information' title='Cart\343o!'/>";
        return image;
    }

    private String formaDePagamento;
    private String image;
}
