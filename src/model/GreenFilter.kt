package model

import java.awt.Color
import java.awt.image.BufferedImage


/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 21, 2019                                                                                         *
 *******************************************************************************************************************/
class GreenFilter: Filter {
    override fun filtrate(bufferedImage: BufferedImage) {
        val imgWriter = bufferedImage.raster

        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val v = intArrayOf(0, Color(bufferedImage.getRGB(i,j)).green, 0)
                imgWriter.setPixel(i, j, v)
            }
        }
    }
}