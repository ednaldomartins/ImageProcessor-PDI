package util;

import model.Pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Matrix {

    /****************************************************************************************************************
     *  O metodo deve copiar a imagem dentro da matriz ignorando as bordas. A imagem sera copiada apenas na parte   *
     *  central da matriz. Essa matriz serve para calcular novos valores para imagem sem precisar alterar a imagem  *
     *  diretamente no bufferedImage.                                                                               *
     *                                                                                                              *
     * @param bufferedImage imagem original carregada do arquivo                                                    *
     * @param pixelMatrix   matriz criada com bordas, para realizar calculos e retornar para a imagem               *
     * @param imageWidth    largura da imagem original                                                              *
     * @param imageHeight   altura da imagem original                                                               *
     * @param borderWidth   tamanho das bordas laterais                                                             *
     * @param borderHeight  tamanho das bordas superior e inferior                                                  *
     ***************************************************************************************************************/
    public static void copyImageToMatrixWithExtension(BufferedImage bufferedImage, Pixel[][] pixelMatrix, int imageWidth, int imageHeight, int borderWidth, int borderHeight) {
        //copiar no fim da borda a esqueda ate o inicio da borda a direita (pixelMatrix.size - (mask.size))
        for (int i = 0, w = borderWidth; i < imageWidth; i++, w++) {
            //copiar no fim da borda superior ate inicio da borda inferior (pixelMatrix[0].size - (mask.size))
            for (int j = 0, h = borderHeight; j < imageHeight; j++, h++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                pixelMatrix[w][h] = new Pixel(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
        }
    }

    public static void copyImageToMatrix(BufferedImage bufferedImage, Pixel[][] pixelMatrix) {
        for (int i = 0; i < pixelMatrix.length; i++) {
            for (int j = 0; j < pixelMatrix[0].length; j++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                pixelMatrix[i][j] = new Pixel(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
        }
    }

    public static void convolution(float[][] floatMatrix) {
        final int w = floatMatrix.length, h = floatMatrix[0].length;
        float[][] copyMatrix = copyMatrix(floatMatrix);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                floatMatrix[w-i-1][h-j-1] = copyMatrix[i][j];
            }
        }
    }

    public static float[][] copyMatrix(float[][] matrix) {
        final int w = matrix.length, h = matrix[0].length;
        float [][] copy = new float[w][h];
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                copy[i][j] = matrix[i][j];

        return copy;
    }

}