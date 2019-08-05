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

        int linha = pixelMatrix.length;//numero de linha
        int coluna = pixelMatrix[0].length;//numero de coluna
        //assumindo que o .length retorne o tamanho,por exemplo uma matrix 5x5 retorna 5 entao a matriz vai de 0 a 4,
        // por isso o -1 nas logicas para se chegar no 4 e alterar naquele spot

        //diagonais nunca variam
        pixelMatrix[0][0] = pixelMatrix[1][1];//esq sup
        pixelMatrix[0][coluna - 1] = pixelMatrix[1][coluna - 2];//dir sup
        pixelMatrix[linha- 1][0] = pixelMatrix[linha - 2][1];//esq inf
        pixelMatrix[linha - 1][coluna - 1] = pixelMatrix[linha - 2][coluna - 2];//dir inf

        int i=0,j=0;

        // assumindo que linha eh o mermo numero de coluna entao tanta faz usar a variavel linha ou coluna nos for
        for( i = 1;i<linha-1;i++)
            pixelMatrix[0][i] = pixelMatrix[1][i];
        
        for( i = 1;i<linha-1;i++)
            pixelMatrix[linha - 1][i] = pixelMatrix[linha - 2][i];

        for(i = 1;i<linha-1;i++)
            pixelMatrix[i][0] = pixelMatrix[i][1];

        for(i = 1;i<linha-1;i++)
            pixelMatrix[i][coluna - 1] = pixelMatrix[i][coluna - 2];


        return pixelMatrix;
    }

}
