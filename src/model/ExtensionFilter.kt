package model

import java.awt.image.BufferedImage

interface ExtensionFilter {
    val mask: Array<FloatArray>
    //o metodo ja deve receber o pixelArray pronto, com extensao por zeros ou extensão por bordas.
    fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel?>>)
}
