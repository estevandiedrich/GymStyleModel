// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModalidadeDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            PerfilAcessoDAO

public class ModalidadeDAO extends BaseDAO
{

    public ModalidadeDAO()
    {
    }
    @Override
    public void create(Connection conn, Object obj)
    {
    	Modalidade pojo = (Modalidade)obj;
        if(pojo.getPerfilAcesso() != null && pojo.getPerfilAcesso().getId() == null)
            DaoLocator.getPerfilAcessoDAO().create(conn, pojo.getPerfilAcesso());
        else
            DaoLocator.getPerfilAcessoDAO().update(conn, pojo.getPerfilAcesso());
        super.create(conn, pojo);
    }
    @Override
    public void update(Connection conn, Object obj)
    {
    	Modalidade pojo = (Modalidade)obj;
        if(pojo.getPerfilAcesso() != null && pojo.getPerfilAcesso().getId() == null)
            DaoLocator.getPerfilAcessoDAO().create(conn, pojo.getPerfilAcesso());
        else
            DaoLocator.getPerfilAcessoDAO().update(conn, pojo.getPerfilAcesso());
        super.update(conn, pojo);
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Modalidade pojo = (Modalidade)obj;
        addParametro("nome", pojo.getModalidade(), 12);
        addParametro("valor1", pojo.getValor1(), 8);
        addParametro("valor2", pojo.getValor2(), 8);
        addParametro("valor3", pojo.getValor3(), 8);
        addParametro("valor4", pojo.getValor4(), 8);
        addParametro("valor5", pojo.getValor5(), 8);
        addParametro("valor6", pojo.getValor6(), 8);
        addParametro("valor7", pojo.getValor7(), 8);
        int cont = 0;
        if(cont >= 7)
            cont = 7;
        else
        if(pojo.getValor6() != null && pojo.getValor6().doubleValue() > 0.0D)
            cont = 6;
        else
        if(pojo.getValor5() != null && pojo.getValor5().doubleValue() > 0.0D)
            cont = 5;
        else
        if(pojo.getValor4() != null && pojo.getValor4().doubleValue() > 0.0D)
            cont = 4;
        else
        if(pojo.getValor3() != null && pojo.getValor3().doubleValue() > 0.0D)
            cont = 3;
        else
        if(pojo.getValor2() != null && pojo.getValor2().doubleValue() > 0.0D)
            cont = 2;
        else
        if(pojo.getValor1() != null && pojo.getValor1().doubleValue() > 0.0D)
            cont = 1;
        addParametro("qtde_acessos", Integer.valueOf(cont), 4);
        addParametro("ativo", Boolean.valueOf(true), 16);
        if(pojo.getPerfilAcesso() != null && pojo.getPerfilAcesso().getId() != null)
            addParametro("id_perfis_acesso_fk", pojo.getPerfilAcesso().getId(), -1);
    }
    @Override
    public Modalidade extract(ResultSet rs)
        throws Exception
    {
        Modalidade pojo = null;
        if(rs != null)
        {
            pojo = new Modalidade();
            pojo.setId(rsGetId(rs));
            pojo.setModalidade(rs.getString("nome"));
            if(rs.getDouble("valor1") > 0.0D)
                pojo.setValor1(Double.valueOf(rs.getDouble("valor1")));
            if(rs.getDouble("valor2") > 0.0D)
                pojo.setValor2(Double.valueOf(rs.getDouble("valor2")));
            if(rs.getDouble("valor3") > 0.0D)
                pojo.setValor3(Double.valueOf(rs.getDouble("valor3")));
            if(rs.getDouble("valor4") > 0.0D)
                pojo.setValor4(Double.valueOf(rs.getDouble("valor4")));
            if(rs.getDouble("valor5") > 0.0D)
                pojo.setValor5(Double.valueOf(rs.getDouble("valor5")));
            if(rs.getDouble("valor6") > 0.0D)
                pojo.setValor6(Double.valueOf(rs.getDouble("valor6")));
            if(rs.getDouble("valor7") > 0.0D)
                pojo.setValor7(Double.valueOf(rs.getDouble("valor7")));
            pojo.setQtdeAcessos(Integer.valueOf(rs.getInt("qtde_acessos")));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
            pojo.setPerfilAcesso((PerfilAcesso)DaoLocator.getPerfilAcessoDAO().readById(Long.valueOf(rs.getLong("id_perfis_acesso_fk"))));
            pojo.setInstrutor(null);
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "modalidades";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        lista.add("ativo = true");
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        return lista;
    }

    public Set readDispositivosModalidadeById(Long idModalidade)
    {
        Set dispositivos = new HashSet();
        Connection conn = ConnectionManager.getInstance().getConnection();
        String sql = (new StringBuilder()).append("select D.* from dispositivos as D left join faixas_dispositivos as FD on FD.id_dispositivos_fk = D.id_dispositivos left join faixas as F on F.id_faixas = FD.id_faixas_fk left join perfis_acesso_faixas as PAF on PAF.id_faixas_fk = F.id_faixas left join perfis_acesso as PA on PA.id_perfis_acesso = PAF.id_perfis_acesso_fk left join  modalidades as M on M.id_perfis_acesso_fk = PA.id_perfis_acesso where true and id_modalidades = ").append(idModalidade).toString();
        sql = (new StringBuilder()).append(sql).append(" group by id_dispositivos").toString();
        Statement stm = null;
        try
        {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
            {
                Dispositivo dispositivo;
                for(; rs.next(); dispositivos.add(dispositivo))
                {
                    dispositivo = new Dispositivo();
                    dispositivo.setId(Long.valueOf(rs.getLong("id_dispositivos")));
                    dispositivo.setDispositivo(rs.getString("nome"));
                    dispositivo.setEnderecoIp(rs.getString("endereco_ip"));
                    dispositivo.setOnline(Boolean.valueOf(rs.getBoolean("online")));
                }

            }
            stm = null;
            conn = null;
        }
        catch(Exception ex)
        {
            Logger.getLogger(ModalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dispositivos;
    }
}
