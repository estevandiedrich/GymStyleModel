// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Validador.java

package br.com.rwtech.gymstylecore.model.util;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.UsuarioDAO;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class Validador
{

    public Validador()
    {
    }

    private static int calcularDigito(String str, int peso[])
    {
        int soma = 0;
        for(int indice = str.length() - 1; indice >= 0; indice--)
        {
            int digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[(peso.length - str.length()) + indice];
        }

        soma = 11 - soma % 11;
        return soma <= 9 ? soma : 0;
    }

    public static boolean isValidCPF(String cpf)
    {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("-", "");
        if(cpf == null || cpf.length() != 11)
            return false;
        if(cpf != null && cpf.equalsIgnoreCase("00000000000"))
        {
            return false;
        } else
        {
            Integer digito1 = Integer.valueOf(calcularDigito(cpf.substring(0, 9), pesoCPF));
            Integer digito2 = Integer.valueOf(calcularDigito((new StringBuilder()).append(cpf.substring(0, 9)).append(digito1).toString(), pesoCPF));
            return cpf.equals((new StringBuilder()).append(cpf.substring(0, 9)).append(digito1.toString()).append(digito2.toString()).toString());
        }
    }

    public static boolean existCPF(String cpf, Long idUsuario)
    {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace(" ", "");
        cpf = cpf.replace(" ", "");
        cpf = cpf.replace(" ", "");
        cpf = cpf.replace(" ", "");
        cpf = cpf.replace(" ", "");
        if(!cpf.isEmpty())
        {
            Map mapa = new HashMap();
            mapa.put("criterioCpf", cpf);
            mapa.put("criterioAtivo", "truefalse");
            if(idUsuario != null && idUsuario.longValue() != -1L)
                mapa.put("criterioCpfIdUsuario", idUsuario.toString());
            else
                mapa.put("criterioCpfIdUsuario", "");
            List usuarios = DaoLocator.getUsuarioDAO().readByCriteria(mapa);
            if(usuarios != null && !usuarios.isEmpty())
                return true;
        }
        return false;
    }

    public static boolean existCartaoProximidade(String cartao, Long idUsuario)
    {
        List lista = new ArrayList();
        int cont = 0;
        do
        {
            if(!cartao.isEmpty() && cartao.charAt(0) == '0')
                if(cartao.length() > 1)
                    cartao = cartao.substring(1, cartao.length());
                else
                if(cartao.length() == 1)
                    cartao = "";
            cont++;
        } while(!cartao.isEmpty() && cont <= 10);
        if(cartao.isEmpty())
            return true;
        lista.add(cartao);
        for(; cartao.length() < 10; lista.add(cartao))
            cartao = (new StringBuilder()).append("0").append(cartao).toString();

        Map mapa = new HashMap();
        mapa.put("criterioCartaoProximidade", lista);
        mapa.put("criterioCartaoIdUsuario", idUsuario.toString());
        List usuarios = DaoLocator.getUsuarioDAO().readByCriteria(mapa);
        return usuarios != null && !usuarios.isEmpty();
    }

    public static String clearCPF(String cpf)
    {
        if(cpf != null)
        {
            cpf = cpf.replace(".", "");
            cpf = cpf.replace(".", "");
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            cpf = cpf.replace("-", "");
            cpf = cpf.replace(" ", "");
            cpf = cpf.replace(" ", "");
            cpf = cpf.replace(" ", "");
        }
        return cpf;
    }

    public static String limparEspaco(String aux)
    {
        if(aux != null)
        {
            aux = aux.trim();
            if(aux.isEmpty())
                return null;
        }
        return aux;
    }

    public static String clearCharEspacoDuplo(String aux)
    {
        if(aux != null)
        {
            if(aux != null)
                aux = aux.replace("  ", "");
            if(aux.isEmpty() || aux.length() == 1)
                return null;
        }
        return aux;
    }

    public static String formatterCPF(String cpf)
    {
        cpf = clearCPF(cpf);
        if(cpf != null && !cpf.isEmpty())
            cpf = (new StringBuilder()).append(cpf.substring(0, 3)).append(".").append(cpf.substring(3, 6)).append(".").append(cpf.substring(6, 9)).append("-").append(cpf.substring(9, 11)).toString();
        return cpf;
    }

    public static boolean isValidCNPJ(String cnpj)
    {
        cnpj = cnpj.replaceAll(".", "");
        cnpj = cnpj.replaceAll("-", "");
        if(cnpj == null || cnpj.length() != 14)
        {
            return false;
        } else
        {
            Integer digito1 = Integer.valueOf(calcularDigito(cnpj.substring(0, 12), pesoCNPJ));
            Integer digito2 = Integer.valueOf(calcularDigito((new StringBuilder()).append(cnpj.substring(0, 12)).append(digito1).toString(), pesoCNPJ));
            return cnpj.equals((new StringBuilder()).append(cnpj.substring(0, 12)).append(digito1.toString()).append(digito2.toString()).toString());
        }
    }

    public static boolean isValidName(String nome)
    {
        return nome != null && nome.matches("^[[ ]|\\p{L}*]+$");
    }

    public static boolean isValidEmail(String email)
    {
        return email != null && email.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
    }

    public static boolean isValidData(String data)
    {
        return data != null && data.matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
    }

    public static boolean isValidMoney(String moeda)
    {
        return moeda != null && moeda.matches("^(\\d{1,3}){1}(\\.\\d{3})*(\\,\\d{2})?$");
    }

    public static boolean isValidHour(String hora)
    {
        return hora != null && hora.matches("^([0-1][0-9]|[2][0-3])(:([0-5][0-9])){1,2}$");
    }

    public static Double getMoney(String val)
    {
        Double valor = null;
        if(val != null)
            if(isValidMoney(val))
            {
                val = val.replace(".", "");
                val = val.replace(",", ".");
                val = val.replace(" ", "");
                if(val.isEmpty())
                    valor = new Double(0.0D);
                else
                    valor = new Double(val);
            } else
            {
                valor = new Double(0.0D);
            }
        return valor;
    }

    public static BigDecimal getBigDecimal(String val)
    {
        BigDecimal valor = null;
        if(val != null)
            if(isValidMoney(val))
            {
                val = val.replace(".", "");
                val = val.replace(",", ".");
                val = val.replace(" ", "");
                if(val.isEmpty())
                    valor = new BigDecimal(0);
                else
                    valor = new BigDecimal(val);
            } else
            {
                valor = new BigDecimal(0);
            }
        return valor;
    }

    public static String getMoney(Double val)
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        double valor = Double.parseDouble(val.toString());
        String valAux = nf.format(valor);
        valAux = valAux.replace("R$ ", "");
        return valAux;
    }

    public static Integer getInteger(String val)
    {
        Integer valor = null;
        if(val != null)
            if(val.isEmpty() || val.equalsIgnoreCase("-1"))
                valor = new Integer(0);
            else
                valor = new Integer(val);
        return valor;
    }

    private static final int pesoCPF[] = {
        11, 10, 9, 8, 7, 6, 5, 4, 3, 2
    };
    private static final int pesoCNPJ[] = {
        6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 
        4, 3, 2
    };

}
