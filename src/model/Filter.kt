package model

import java.awt.image.BufferedImage

interface Filter {
    //o metodo ja deve receber o pixelArray pronto, COM ou SEM extensao por zeros.
    fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel>>)
}