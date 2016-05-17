// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CalendarUtil.java

package br.com.rwtech.gymstylecore.model.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class CalendarUtil
{

    public CalendarUtil()
    {
    }

    public static Calendar getDateCurrent()
    {
        return Calendar.getInstance();
    }

    public static String getDataCurrente()
    {
        String data = "";
        Calendar dataAtual = Calendar.getInstance();
        int dia = dataAtual.get(5);
        int mes = dataAtual.get(2);
        mes++;
        int ano = dataAtual.get(1);
        if(dia < 10)
            data = (new StringBuilder()).append(data).append("0").toString();
        data = (new StringBuilder()).append(data).append(dia).append("/").toString();
        if(mes < 10)
            data = (new StringBuilder()).append(data).append("0").toString();
        data = (new StringBuilder()).append(data).append(mes).append("/").append(ano).toString();
        return data;
    }

    public static String getDataCurrente(int day)
    {
        String data = "";
        Calendar dataAtual = Calendar.getInstance();
        int dia = 1;
        int mes = dataAtual.get(2);
        mes++;
        int ano = dataAtual.get(1);
        if(dia < 10)
            data = (new StringBuilder()).append(data).append("0").toString();
        data = (new StringBuilder()).append(data).append(dia).append("/").toString();
        if(mes < 10)
            data = (new StringBuilder()).append(data).append("0").toString();
        data = (new StringBuilder()).append(data).append(mes).append("/").append(ano).toString();
        return data;
    }

    public static String getDataCurrente(String intervalo)
    {
        String data = "";
        Calendar dataAtual = Calendar.getInstance();
        int dia = dataAtual.get(5);
        int mes = dataAtual.get(2);
        mes++;
        int ano = dataAtual.get(1);
        if(dia < 10)
            data = (new StringBuilder()).append(data).append("0").toString();
        data = (new StringBuilder()).append(data).append(dia).append(intervalo).toString();
        if(mes < 10)
            data = (new StringBuilder()).append(data).append("0").toString();
        data = (new StringBuilder()).append(data).append(mes).append(intervalo).append(ano).toString();
        return data;
    }

    public static int getMinutoCurrente()
    {
        Calendar dataAtual = Calendar.getInstance();
        int minuto = dataAtual.get(12);
        return minuto;
    }

    public static int getHoraCurrente()
    {
        Calendar dataAtual = Calendar.getInstance();
        int minuto = dataAtual.get(11);
        return minuto;
    }

    public static int getSegundoCurrente()
    {
        Calendar dataAtual = Calendar.getInstance();
        int minuto = dataAtual.get(13);
        return minuto;
    }

    public static Calendar setDateCalendar(Date date)
    {
        Calendar calendar = null;
        if(date != null)
        {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    public static Calendar setTimesTamp(Timestamp times)
    {
        Calendar calendar = null;
        if(times != null)
        {
            calendar = Calendar.getInstance();
            calendar.setTime(times);
        }
        return calendar;
    }

    public static Calendar setDateCalendar(String date)
    {
        Calendar calendar = null;
        if(date != null && !date.isEmpty())
        {
            calendar = Calendar.getInstance();
            String val[] = date.split("/");
            int dia = Integer.valueOf(val[0]).intValue();
            int mes = Integer.valueOf(val[1]).intValue();
            int ano = Integer.valueOf(val[2]).intValue();
            calendar.set(ano, mes - 1, dia, 23, 59, 59);
        }
        return calendar;
    }

    public static Date setDateSqlCalendar(Calendar calendar)
    {
        Date data = null;
        if(calendar != null)
            data = new Date(calendar.getTime().getTime());
        return data;
    }

    public static Time setTimeSqlCalendar(Calendar calendar)
    {
        Time time = null;
        if(calendar != null)
            time = new Time(calendar.get(11), calendar.get(12), 0);
        return time;
    }

    public static Calendar setTimeCalendar(Time time)
    {
        Calendar calendar = null;
        if(time != null)
        {
            calendar = Calendar.getInstance();
            calendar.setTime(time);
        }
        return calendar;
    }

    public static Calendar setTimeCalendar(String time)
    {
        Calendar calendar = null;
        if(time != null && !time.isEmpty())
        {
            String tempo[] = time.split(":");
            calendar = Calendar.getInstance();
            int hora = Integer.parseInt(tempo[0]);
            int minuto = Integer.parseInt(tempo[1]);
            if(hora > 23)
                hora = 23;
            if(minuto > 59)
                minuto = 59;
            calendar.set(0, 0, 0, hora, minuto);
        }
        return calendar;
    }

    public static Calendar getDateByDay(int dia)
    {
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), dia);
        return calendar;
    }

    public static String getDateCalendar(Calendar calendar)
    {
        String data = "";
        if(calendar != null && calendar.getTime() != null)
        {
            String dia = "";
            if(calendar.get(5) < 10)
                dia = "0";
            dia = (new StringBuilder()).append(dia).append(calendar.get(5)).toString();
            String mes = "";
            if(calendar.get(2) + 1 < 10)
                mes = "0";
            mes = (new StringBuilder()).append(mes).append(calendar.get(2) + 1).toString();
            data = (new StringBuilder()).append(dia).append("/").append(mes).append("/").append(calendar.get(1)).toString();
        }
        return data;
    }

    public static String getTimeCalendar(Calendar calendar)
    {
        String time = "";
        if(calendar != null && calendar.getTime() != null)
        {
            String hora = "";
            if(calendar.get(11) < 10)
                hora = "0";
            hora = (new StringBuilder()).append(hora).append(calendar.get(11)).toString();
            String minuto = "";
            if(calendar.get(12) < 10)
                minuto = "0";
            minuto = (new StringBuilder()).append(minuto).append(calendar.get(12)).toString();
            time = (new StringBuilder()).append(hora).append(":").append(minuto).toString();
        }
        return time;
    }

    public static String getHorMinSegCalendar(Calendar calendar)
    {
        String time = "";
        if(calendar != null && calendar.getTime() != null)
        {
            String hora = "";
            if(calendar.get(11) < 10)
                hora = "0";
            hora = (new StringBuilder()).append(hora).append(calendar.get(11)).toString();
            String minuto = "";
            if(calendar.get(12) < 10)
                minuto = "0";
            minuto = (new StringBuilder()).append(minuto).append(calendar.get(12)).toString();
            time = (new StringBuilder()).append(hora).append(":").append(minuto).toString();
            String segundo = "";
            if(calendar.get(13) < 10)
                segundo = "0";
            segundo = (new StringBuilder()).append(segundo).append(calendar.get(13)).toString();
            time = (new StringBuilder()).append(time).append(":").append(segundo).toString();
        }
        return time;
    }

    public static String getHorMinSegCalendarHMS()
    {
        Calendar calendar = Calendar.getInstance();
        String time = "";
        if(calendar != null && calendar.getTime() != null)
        {
            String hora = "";
            if(calendar.get(11) < 10)
                hora = "0";
            hora = (new StringBuilder()).append(hora).append(calendar.get(11)).toString();
            String minuto = "";
            if(calendar.get(12) < 10)
                minuto = "0";
            minuto = (new StringBuilder()).append(minuto).append(calendar.get(12)).toString();
            time = (new StringBuilder()).append(hora).append("h").append(minuto).toString();
            String segundo = "";
            if(calendar.get(13) < 10)
                segundo = "0";
            segundo = (new StringBuilder()).append(segundo).append(calendar.get(13)).toString();
            time = (new StringBuilder()).append(time).append("m").append(segundo).append("s").toString();
        }
        return time;
    }
}
