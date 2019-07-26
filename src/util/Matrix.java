package util;

import model.Pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Matrix {

    /****************************************************************************************************************
     * O metodo deve retornar uma matriz de pixels que sera manipulada pelos filtros implementados no projeto       *
     *                                                                                                              *
     * @param bufferedImage imagem original carregada do arquivo                                                    *
     * @param mask          mascara que sera aplicada na imagem                                                     *
     * @return pixelMatrix                                                                                          *
     ***************************************************************************************************************/
    public Pixel[][] zeroExtension(BufferedImage bufferedImage, float[][] mask) {
        //w: largura da imagem, h: altura da imagem
        int w = bufferedImage.getWidth(), h = bufferedImage.getHeight();
        //m: borda adicionada a largura, n: borda adicionada a altura
        int m = (mask.length/2),  n = (mask[0].length/2);
        //Kotlin => Array(w+m) { arrayOfNulls<Pixel>(h+n)}
        Pixel [][] pixelMatrix = new Pixel[w+(m*2)][h+(n*2)];
        for (int i = 0; i < pixelMatrix.length; i++) {
            for (int j = 0; j < pixelMatrix[0].length; j++) {
                pixelMatrix[i][j] = new Pixel(0,0,0,0);
            }
        }
        //copiar a imagem ignorando as bordas.
        copyImageToMatrix(bufferedImage, pixelMatrix, w, h, m, n);

        return pixelMatrix;
    }

    public Pixel[][] edgeExtension(BufferedImage bufferedImage, float[][] mask) {
        //reusar o codigo de criacao de matriz com extensao com zeros
        Pixel [][] pixelMatrix = zeroExtension(bufferedImage, mask);
        // ! adicionar borda recheada e deliciosa
        // TODO

        return pixelMatrix;
    }

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
    private void copyImageToMatrix(BufferedImage bufferedImage, Pixel[][] pixelMatrix, int imageWidth, int imageHeight, int borderWidth, int borderHeight) {
        //copiar no fim da borda a esqueda ate o inicio da borda a direita (pixelMatrix.size - (mask.size))
        for (int i = 0, w = borderWidth; i < imageWidth; i++, w++) {
            //copiar no fim da borda superior ate inicio da borda inferior (pixelMatrix[0].size - (mask.size))
            for (int j = 0, h = borderHeight; j < imageHeight; j++, h++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                pixelMatrix[w][h] = new Pixel(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
        }
    }

}
