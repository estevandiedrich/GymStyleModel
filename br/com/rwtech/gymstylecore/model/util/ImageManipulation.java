// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageManipulation.java

package br.com.rwtech.gymstylecore.model.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageManipulation
{

    public ImageManipulation()
    {
        nome = "123123123123";
    }

    public static byte[] diminuiResolucaoImagem(int lar, int alt, byte foto[])
    {
        Image imagem = null;
        try
        {
            java.io.InputStream in = null;
            in = new ByteArrayInputStream(foto);
            BufferedImage imagemMenor = ImageIO.read(in);
            imagem = imagemMenor.getScaledInstance(lar, alt, 1);
        }
        catch(IOException ex)
        {
            Logger.getLogger(ImageManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(imagem != null)
            foto = imageToByte(imagem);
        return foto;
    }

    public static byte[] imageToByte(Image image)
    {
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), 1);
        Graphics bg = bi.getGraphics();
        bg.drawImage(image, 0, 0, null);
        bg.dispose();
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(bi, "JPG", buff);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return buff.toByteArray();
    }

    String nome;
}
