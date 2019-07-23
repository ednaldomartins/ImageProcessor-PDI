package model

import java.awt.image.BufferedImage

interface Filter {
    fun filtrate(bufferedImage: BufferedImage)
}