package model

import java.awt.Color
import java.awt.image.BufferedImage


/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 21, 2019                                                                                         *
 *******************************************************************************************************************/
class GreenFilter: Filter {
    override fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel>>) {

        val imgWriter = bufferedImage.raster

        /*
         * TODO Implementar usando pixelArray
         * ainda sera implementado a parte que usaremos o pixelArray para editar a imagem.
         * O pixelArray sera usado para gerar uma matriz a partir da imagem do bufferedImage.
         * O pixelArray servira para fazer calculos de forma generica. Exemplo:
         * podemos usar a matriz pixelArray para criar uma matriz da imagem completando a borda com zeros ou com
         * repeticao de numeros para poder calcular os pixels que ficam na borda da verdadeira imagem.
         */

        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val v = intArrayOf(0, Color(bufferedImage.getRGB(i,j)).green, 0)
                imgWriter.setPixel(i, j, v)
            }
        }

    }
}