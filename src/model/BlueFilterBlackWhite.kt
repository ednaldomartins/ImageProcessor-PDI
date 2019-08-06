package model

import java.awt.Color
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author Danillo Medeiros                                                                                         *
 * created on july 30, 2019                                                                                         *
 ******************************************************************************************************************/

class BlueFilterBlackWhite: Filter {
    override fun filtrate(bufferedImage: BufferedImage) {
        val imgWriter = bufferedImage.raster

        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val v = intArrayOf(Color(bufferedImage.getRGB(i, j)).blue, Color(bufferedImage.getRGB(i, j)).blue, Color(bufferedImage.getRGB(i, j)).blue, Color(bufferedImage.getRGB(i, j)).alpha)
                imgWriter.setPixel(i, j, v)
            }
        }
    }
}