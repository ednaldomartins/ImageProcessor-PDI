package util;

import model.Pixel;

import java.awt.image.BufferedImage;

public class Extension {
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
                pixelMatrix[i][j] = new Pixel();
            }
        }
        //copiar a imagem ignorando as bordas.
        Matrix.copyImageToMatrixWithExtension(bufferedImage, pixelMatrix, w, h, m, n);

        return pixelMatrix;
    }

    public Pixel[][] edgeExtension(BufferedImage bufferedImage, float[][] mask) {
        //reusar o codigo de criacao de matriz com extensao com zeros
        Pixel [][] pixelMatrix = zeroExtension(bufferedImage, mask);
        // ! adicionar borda recheada e deliciosa
        // TODO

        return pixelMatrix;
    }

}
