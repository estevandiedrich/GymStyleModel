// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RespostaDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.sql.*;

public class RespostaDAO
{

    public RespostaDAO()
    {
    }

    public Resposta readByDestino(Long id)
    {
        Resposta pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from respostas where destino = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo = extract(rs);
            rs.close();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojo;
    }

    public Resposta extract(ResultSet rs)
        throws Exception
    {
        Resposta pojo = null;
        if(rs != null)
        {
            pojo = new Resposta();
            pojo.setId(Long.valueOf(rs.getLong("id_respostas")));
            pojo.setDataHora(CalendarUtil.setDateCalendar(rs.getDate("data_hora")));
            pojo.setMensagemErro(rs.getString("mensagem_erro"));
            pojo.setTipoResposta(getTipo(rs.getInt("tipo_resposta_fk")));
        }
        return pojo;
    }

    private TipoRequisicaoResposta getTipo(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return TipoRequisicaoResposta.DESCOBRIR_DISPOSITIVOS_NA_REDE;

        case 1: // '\001'
            return TipoRequisicaoResposta.CADASTRAR_USUARIO;

        case 2: // '\002'
            return TipoRequisicaoResposta.ATUALIZAR_USUARIO;

        case 3: // '\003'
            return TipoRequisicaoResposta.EXCLUIR_USUARIO;

        case 4: // '\004'
            return TipoRequisicaoResposta.LIBERAR_BLOQUEAR_ACESSO;

        case 5: // '\005'
            return TipoRequisicaoResposta.VERIFICAR_EVENTOS_OFFLINE;

        case 6: // '\006'
            return TipoRequisicaoResposta.CONFIGURAR_HORA;

        case 7: // '\007'
            return TipoRequisicaoResposta.CONFIGURAR_MODO_OPERACAO;

        case 8: // '\b'
            return TipoRequisicaoResposta.LIBERAR_ACESSO_INDEFINIDAMENTE;

        case 9: // '\t'
            return TipoRequisicaoResposta.BLOQUEAR_ACESSO_INDEFINIDAMENTE;

        case 10: // '\n'
            return TipoRequisicaoResposta.LIBERAR_UM_ACESSO;

        case 11: // '\013'
            return TipoRequisicaoResposta.OBTER_IMPRESSAO_DIGITAL;

        case 12: // '\f'
            return TipoRequisicaoResposta.CANCELAR_IMPRESSAO_DIGITAL;

        case 13: // '\r'
            return TipoRequisicaoResposta.CONFIGURAR_ALARME_USUARIO;

        case 14: // '\016'
            return TipoRequisicaoResposta.CONFIGURAR_ALARME_VIOLACAO;

        case 15: // '\017'
            return TipoRequisicaoResposta.CONFIGURAR_ALARME_OBSTRUCAO;

        case 16: // '\020'
            return TipoRequisicaoResposta.CONFIGURAR_ALARME_TAIL_GATE;

        case 17: // '\021'
            return TipoRequisicaoResposta.CONFIGURAR_ALARME_DE_SOLENOIDE;

        case 18: // '\022'
            return TipoRequisicaoResposta.CONFIGURAR_ALARME_DE_PASSAGEM;

        case 19: // '\023'
            return TipoRequisicaoResposta.ATIVIDADE_SUSPEITA;

        case 20: // '\024'
            return TipoRequisicaoResposta.RESET;

        case 21: // '\025'
            return TipoRequisicaoResposta.CONFIGURAR_SENTIDO_ENTRADA;

        case 22: // '\026'
            return TipoRequisicaoResposta.CONFIGURAR_CATRACA;
        }
        return null;
    }
}
