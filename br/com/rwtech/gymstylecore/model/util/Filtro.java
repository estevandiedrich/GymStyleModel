// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Filtro.java

package br.com.rwtech.gymstylecore.model.util;


// Referenced classes of package br.com.rwtech.gymstylecore.model.util:
//            ConsultaUtil

public class Filtro
{

    public Filtro()
    {
    }

    public static String criterioNome(String criterioNome)
    {
        String sql = "";
        if(criterioNome != null && !criterioNome.isEmpty())
            sql = (new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString();
        return sql;
    }

    public static String criterioAtivo(String criterioAtivo)
    {
        String sql = "";
        if(criterioAtivo != null && !criterioAtivo.isEmpty())
            sql = "ativo = true";
        return sql;
    }

    public static String getRemoveAcento(String coluna)
    {
        return (new StringBuilder()).append("removeacento(").append(coluna).append(")").toString();
    }

    public static String CRITERIO_CODIGO = "criterioCodigo";
    public static String CRITERIO_NOME = "criterioNome";
    public static String CRITERIO_ATIVO = "criterioAtivo";
    public static String CRITERIO_ATIVO_ALUNO = "criterioAtivoAluno";
    public static String CRITERIO_ATIVO_FUNCIONARIO = "criterioAtivoFuncionario";
    public static String CRITERIO_ATIVOS = "criterioAtivos";
    public static String CRITERIO_DIFERENTE_ID = "criterioDiferenteId";
    public static String CRITERIO_INICIO = "criterioInicio";
    public static String CRITERIO_FIM = "criterioFim";
    public static String CRITERIO_CATEGORIA = "criterioCategoria";
    public static String CRITERIO_TIPO_USUARIO = "criterioTipoUsuario";

}
