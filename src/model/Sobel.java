package model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Sobel implements Filter{
    @Override
    public void filtrate( BufferedImage bufferedImage)  {
        WritableRaster image = bufferedImage.getRaster();

        int x = image.getWidth();
        int y = image.getHeight();

        int maxGval = 0;//valor maximo de G
        int[][] edgeColors = new int[x][y];//cores da borda
        int maxGradient = -1;//gradiene maximo

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                int val00 = getGrayScale(new Color(bufferedImage.getRGB(i - 1, j - 1)).getRed(),new Color(bufferedImage.getRGB(i - 1, j - 1)).getGreen(),new Color(bufferedImage.getRGB(i - 1, j - 1)).getBlue());
                int val01 = getGrayScale(new Color(bufferedImage.getRGB(i - 1, j)).getRed(),new Color(bufferedImage.getRGB(i - 1, j)).getGreen(),new Color(bufferedImage.getRGB(i - 1, j)).getBlue());
                int val02 = getGrayScale(new Color(bufferedImage.getRGB(i - 1, j + 1)).getRed(),new Color(bufferedImage.getRGB(i - 1, j + 1)).getGreen(),new Color(bufferedImage.getRGB(i - 1, j + 1)).getBlue());

                int val10 = getGrayScale(new Color(bufferedImage.getRGB(i, j - 1)).getRed(),new Color(bufferedImage.getRGB(i, j - 1)).getGreen(),new Color(bufferedImage.getRGB(i, j - 1)).getBlue());
                int val11 = getGrayScale(new Color(bufferedImage.getRGB(i, j)).getRed(),new Color(bufferedImage.getRGB(i, j)).getGreen(),new Color(bufferedImage.getRGB(i, j)).getBlue());
                int val12 = getGrayScale(new Color(bufferedImage.getRGB(i, j + 1)).getRed(),new Color(bufferedImage.getRGB(i, j + 1)).getGreen(),new Color(bufferedImage.getRGB(i, j + 1)).getBlue());

                int val20 = getGrayScale(new Color(bufferedImage.getRGB(i + 1, j - 1)).getRed(),new Color(bufferedImage.getRGB(i + 1, j - 1)).getGreen(),new Color(bufferedImage.getRGB(i + 1, j - 1)).getBlue());
                int val21 = getGrayScale(new Color(bufferedImage.getRGB(i + 1, j)).getRed(),new Color(bufferedImage.getRGB(i + 1, j)).getGreen(),new Color(bufferedImage.getRGB(i + 1, j)).getBlue());
                int val22 = getGrayScale(new Color(bufferedImage.getRGB(i + 1, j + 1)).getRed(),new Color(bufferedImage.getRGB(i + 1, j + 1)).getGreen(),new Color(bufferedImage.getRGB(i + 1, j + 1 - 1)).getBlue());

                int gx =  ((-1 * val00) + (0 * val01) + (1 * val02))
                        + ((-2 * val10) + (0 * val11) + (2 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));

                int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (2 * val21) + (1 * val22));

                double gval = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gval;

                if(maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
        }

        double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
                int[] v = new int[3];
                v[0] = (edgeColor >> 16) & 0xff;
                v[1] = (edgeColor >> 8) & 0xff;
                v[2] = (edgeColor) & 0xff;

                image.setPixel(i,j,v);
            }
        }

    }//fim do filtrate

    public static int  getGrayScale(int r,int g,int b) {//escala do gradiente

        //https://en.wikipedia.org/wiki/Grayscale, calculando a luminancia
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        //int gray = (r + g + b) / 3;

        return gray;
    }
}
