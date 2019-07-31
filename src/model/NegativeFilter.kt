package model

import java.awt.Color
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author Danillo Medeiros                                                                                         *
 * created on july 30, 2019                                                                                         *
 *******************************************************************************************************************/

//filtro negativo, obtem os valores RBG da imagem carregada e faz a subtraçao por 255 ,gerando assim uma imagem negativa
class NegativeFilter: Filter {
    override fun filtrate(bufferedImage: BufferedImage) {
        val imgWriter = bufferedImage.raster

        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val v = intArrayOf(Color(255-bufferedImage.getRGB(i,j)).red, 255 - Color(bufferedImage.getRGB(i,j)).green, 255 - Color(bufferedImage.getRGB(i,j)).blue)
                imgWriter.setPixel(i, j, v)
            }
        }
    }
}