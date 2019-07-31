package model

import java.awt.Color
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author Danillo Medeiros                                                                                         *
 * created on july 30, 2019                                                                                         *
 ******************************************************************************************************************/

class BlueFilter: Filter {
    override fun filtrate(bufferedImage: BufferedImage) {
        val imgWriter = bufferedImage.raster

        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val v = intArrayOf(0, 0, Color(bufferedImage.getRGB(i,j)).blue)
                imgWriter.setPixel(i, j, v)
            }
        }
    }
}