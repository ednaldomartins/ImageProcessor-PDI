package model;

import data.MaskData;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Sobel implements ExtensionFilter{
    float[][] mask = new MaskData().loadEmptyMask("mascaraVazia3x3.txt");
    @Override
    public void filtrate( BufferedImage bufferedImage, Pixel[][] pixelArray)  {
        WritableRaster imgWrite = bufferedImage.getRaster();
        //cores da borda (usaremos edgeColors para nao alterar o pixelArray durante o processo)
        int[][] edgeColors = new int[pixelArray.length][pixelArray[0].length];
        //gradiene maximo (comecamos com -1 para testar no if a primeira vez sem dar nullPointer)
        int maxGradient = -1;
        //percorrer todos os pixels da imagem
        for (int i = 1; i < pixelArray.length - 1; i++) {
            for (int j = 1; j < pixelArray[0].length - 1; j++) {
                //temp: guarda os valores convertidos para nivel de cinza, da regiao aonde as duas mascaras serao aplicadas
                float[] temp = new float[mask.length*mask[0].length];
                for (int k = 0, n = 0; k < mask.length; k++) {
                    for (int l = 0; l < mask[0].length; l++, n++) {
                        //https://en.wikipedia.org/wiki/Grayscale, calculando a luminancia
                        temp[n] = (0.2126f * pixelArray[i-1+k][j-1+l].getRed() + 0.7152f * pixelArray[i-1+k][j-1+l].getGreen() + 0.0722f * pixelArray[i-1+k][j-1+l].getBlue());
                    }
                }
                //sobel vertical * regiao aplicada da imagem
                int gX = (int) ((-1 * temp[0]) + (-2 * temp[3]) + (-1 * temp[6]) + (1 * temp[2])  + (2 * temp[5])  + (1 * temp[8]));
                //sobel horizontal * regiao aplicada da imagem
                int gY = (int) ((-1 * temp[0]) + (-2 * temp[1]) + (-1 * temp[2]) + (1 * temp[6])  + (2 * temp[7])  + (1 * temp[8]));
                //resultado do gradiente
                int gradient = (int) Math.sqrt((gX * gX) + (gY * gY));
                //se o novo valor do gradiente for maior do que o maior valor ate o momento, entao o maxGradient assume esse valor
                if( gradient > maxGradient ) maxGradient = gradient;

                edgeColors[i][j] = gradient;
            }
        }

        double scale = 255.0 / maxGradient;
        for (int i = 1; i < pixelArray.length - 1; i++) {
            for (int j = 1; j < pixelArray[0].length - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
                int[] imagePixel = new int[3];
                imagePixel[0] = (edgeColor >> 16) & 0xff;
                imagePixel[1] = (edgeColor >> 8) & 0xff;
                imagePixel[2] = (edgeColor) & 0xff;

                imgWrite.setPixel( i-1,j-1, imagePixel);
            }
        }

    }//fim do filtrate

    @Override
    public float[][] getMask() {
        return mask;
    }

}
