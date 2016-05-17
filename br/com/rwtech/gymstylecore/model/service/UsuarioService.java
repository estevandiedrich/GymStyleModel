// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.BaseService;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.UsuarioDAO;
import br.com.rwtech.gymstylecore.model.dao.UsuarioReadDAO;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import java.sql.Connection;
import java.util.*;

public class UsuarioService extends BaseService
{

    public UsuarioService()
    {
    }
    @Override
    public void create(Connection conn, Object pojo)
    {
        DaoLocator.getUsuarioDAO().create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object pojo)
    {
        DaoLocator.getUsuarioDAO().update(conn, pojo);
    }

    public void atualizarCartaoCatraca(Long idUsuario)
    {
        DaoLocator.getUsuarioDAO().atualizarCartaoCatraca(idUsuario);
    }

    public void updateCartaoProximidade(Long idUsuario, String cartao)
    {
        DaoLocator.getUsuarioDAO().updateCartaoProximidade(idUsuario, cartao);
    }

    public void updateLoginSenha(Long idUsuario, String login, String senha)
    {
        DaoLocator.getUsuarioDAO().updateLoginSenha(idUsuario, login, senha);
    }
    @Override
    public Boolean delete(Connection conn, Long id)
    {
        return DaoLocator.getUsuarioDAO().delete(conn, id);
    }

    public Boolean disabledAluno(Long id)
    {
        return DaoLocator.getUsuarioDAO().disabledAluno(id);
    }
    @Override
    public List<Usuario> readByCriteria(Map input)
    {
        return DaoLocator.getUsuarioDAO().readByCriteria(input);
    }

    public List<Usuario> readByCriteriaSimple(Map input)
    {
        return DaoLocator.getUsuarioReadDAO().readByCriteriaSimple(input);
    }
    @Override
    public Map paginator(Map input)
    {
        return DaoLocator.getUsuarioDAO().paginator(input);
    }
    @Override
    public Usuario readById(Long id)
    {
        return (Usuario)DaoLocator.getUsuarioDAO().readById(id);
    }

    public Long readNextMatricula()
    {
        return DaoLocator.getUsuarioReadDAO().readNextMatricula();
    }

    public Usuario readByIdSimple(Long id)
    {
        return DaoLocator.getUsuarioReadDAO().readByIdSimple(id);
    }

    public Usuario readUserByCartaoProximidade(String cartao, Long id)
    {
        return DaoLocator.getUsuarioReadDAO().readUserByCartaoProximidade(cartao, id);
    }

    public Usuario login(String login, String senha)
    {
    	Usuario usuario = null;
    	if(login == null || senha == null || login.isEmpty() || senha.isEmpty())
    		return usuario;
    	List usuarios = DaoLocator.getUsuarioReadDAO().login(login);
    	Iterator it = usuarios.iterator();
    	while(it.hasNext())
    	{
    		Usuario pojo = (Usuario)it.next();
    		if(pojo.getSenha().equalsIgnoreCase(DaoLocator.getUsuarioDAO().toMd5(senha)))
    		{
    			usuario = pojo;
    			break;
    		}
    	}
//******************************************************************************//    	
//        Usuario usuario;
//label0:
//        {
//            usuario = null;
//            if(login == null || senha == null || login.isEmpty() || senha.isEmpty())
//                break label0;
//            List usuarios = DaoLocator.getUsuarioReadDAO().login(login);
//            Iterator i$ = usuarios.iterator();
//            Usuario pojo;
//            do
//            {
//                if(!i$.hasNext())
//                    break label0;
//                pojo = (Usuario)i$.next();
//            } while(!pojo.getSenha().equalsIgnoreCase(DaoLocator.getUsuarioDAO().toMd5(senha)));
//            usuario = pojo;
//        }
        return usuario;
    }

    public Boolean verificaLogin(String login, String idUsuario)
    {
        if(login != null && !login.isEmpty())
        {
            Map map = new HashMap();
            map.put("criterioLogin", login);
            map.put("criterioIdUsuario", idUsuario);
            List usuarios = readByCriteria(map);
            if(usuarios != null && !usuarios.isEmpty())
                return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public List<Usuario> readMatricula(String matricula, Long idUsuario)
    {
        if(matricula != null && !matricula.isEmpty())
        {
            Map map = new HashMap();
            map.put("criterioMatricula", matricula);
            if(idUsuario != null)
                map.put("criterioMatriculaId", idUsuario.toString());
            return readByCriteria(map);
        } else
        {
            return null;
        }
    }

    public void updateSincronizado(Long id, Boolean status)
    {
        DaoLocator.getUsuarioDAO().updateSincronizado(id, status);
    }

    public void updateAllSincronizadoFalse()
    {
        DaoLocator.getUsuarioDAO().updateAllSincronizadoFalse();
    }
    @Override
    public Map readList()
    {
        Map mapa = new LinkedHashMap();
        mapa.put("criterioAtivoFuncionario", "true");
        Map map = new LinkedHashMap();
        List lista = readByCriteria(mapa);
        Usuario usuario;
        String nome;
        for(Iterator i$ = lista.iterator(); i$.hasNext(); map.put(usuario.getId(), nome))
        {
            usuario = (Usuario)i$.next();
            nome = usuario.getUsuario();
            if(nome.length() > 30)
                nome = (new StringBuilder()).append(nome.substring(0, 30)).append(" ...").toString();
        }

        return map;
    }

}
