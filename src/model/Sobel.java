package model;

import data.MaskData;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Sobel implements ExtensionFilter{
    private float[][] mask = new MaskData().loadEmptyMask("mascaraVazia3x3.txt");
    @Override
    public void filtrate( BufferedImage bufferedImage, Pixel[][] pixelArray)  {
        int w = bufferedImage.getWidth(), h = bufferedImage.getHeight();
        //cores da borda (usaremos edgeColors para nao alterar o pixelArray durante o processo)
        int[][] edgeColors = new int[pixelArray.length][pixelArray[0].length];
        //gradiene maximo (comecamos com -1 para testar no if a primeira vez sem dar nullPointer)
        int maxGradient = -1;
        //percorrer todos os pixels da imagem
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                //temp: guarda os valores convertidos para nivel de cinza, da regiao aonde as duas mascaras serao aplicadas
                float[] temp = new float[mask.length*mask[0].length];
                for (int k = 0, n = 0; k < mask.length; k++) {
                    for (int l = 0; l < mask[0].length; l++, n++) {
                        //https://en.wikipedia.org/wiki/Grayscale, calculando a luminancia
                        temp[n] = (0.2126f * pixelArray[i+k][j+l].getRed() + 0.7152f * pixelArray[i+k][j+l].getGreen() + 0.0722f * pixelArray[i+k][j+l].getBlue());
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

        WritableRaster imgWrite = bufferedImage.getRaster();
        //Divide 255 pelo maior gradiente para encontrar um valor para ponderar todos os outros pixels na escala 0-255.
        float scale = 255.0f / maxGradient;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int edgeColor = (int)(edgeColors[i+1][j+1] * scale);
                int[] imagePixel = {edgeColor, edgeColor, edgeColor};
                imgWrite.setPixel( i,j, imagePixel);
            }
        }

    }//fim do filtrate

    @Override
    public float[][] getMask() {
        return mask;
    }

}
