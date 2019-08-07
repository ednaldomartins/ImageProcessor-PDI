package model

import data.MaskData
import java.awt.Color
import java.awt.image.BufferedImage

class BlackWhiteFilter: ExtensionFilter {
    override val mask: Array<FloatArray> = MaskData().loadEmptyMask("mascaraVazia3x3.txt")!!

    override fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel?>>) {
        val imgWriter = bufferedImage.raster
        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val mono = (0.2126f * pixelArray[i][j]!!.red + 0.7152f * pixelArray[i][j]!!.green + 0.0722f * pixelArray[i][j]!!.blue).toInt()
                val v = intArrayOf(mono, mono, mono, Color(bufferedImage.getRGB(i, j)).alpha)
                imgWriter.setPixel(i, j, v)
            }
        }
    }
}