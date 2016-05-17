// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequisicaoService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.dao.RequisicaoDAO;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.service:
//            RespostaService

public class RequisicaoService
{

    public RequisicaoService()
    {
    }

    public Requisicao create(Requisicao requisicao)
    {
        return DaoLocator.getRequisicaoDAO().create(requisicao);
    }

    public void update(Requisicao requisicao)
    {
        DaoLocator.getRequisicaoDAO().update(requisicao);
    }

    public void clearRequisicoesByIdUser(Long idUsuario)
    {
        DaoLocator.getRequisicaoDAO().clearRequisicoesByIdUser(idUsuario);
    }

    public List<Requisicao> readRequisicaoByReferencia(Long idReferencia)
    {
        return DaoLocator.getRequisicaoDAO().readByReferencia(idReferencia);
    }

    public void reenviarRequisicao(Long idRequisicao)
    {
        DaoLocator.getRequisicaoDAO().reenviarRequisicao(idRequisicao);
    }

    public Requisicao readById(Long id)
    {
        return DaoLocator.getRequisicaoDAO().readById(id);
    }

    public Boolean verificaRespReqTipoExcluirUsuario(Long id)
    {
        int i = 0;
        do
        {
            if(i >= 16)
                break;
            Resposta pojo = ServiceLocator.getRespostaService().readByDestino(id);
            if(pojo != null)
            {
                if(pojo.getMensagemErro() == null || pojo.getMensagemErro().isEmpty())
                    return Boolean.valueOf(true);
                if(pojo.getMensagemErro().toString().equals("USUARIO_NAO_ENCONTRADO"))
                    return Boolean.valueOf(true);
                break;
            }
            Contador contador = Contador.getInstance();
            contador.run();
            i++;
        } while(true);
        return Boolean.valueOf(false);
    }
}
