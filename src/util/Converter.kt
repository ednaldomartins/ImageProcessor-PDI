package util

import model.Pixel

class Converter {
    companion object {
        fun RGBtoYIQ(pixelMatrix: Array<Array<Pixel>>) {
            for (w in 0 until pixelMatrix.size) {
                for (h in 0 until pixelMatrix[0].size) {
                    val y: Float = 0.299f*pixelMatrix[w][h].red + 0.587f*pixelMatrix[w][h].green + 0.114f*pixelMatrix[w][h].blue
                    val i: Float = 0.596f*pixelMatrix[w][h].red - 0.274f*pixelMatrix[w][h].green - 0.322f*pixelMatrix[w][h].blue
                    val q: Float = 0.211f*pixelMatrix[w][h].red - 0.523f*pixelMatrix[w][h].green + 0.312f*pixelMatrix[w][h].blue
                    //Pixel(rgba): red=y, green=i, blue=q, alpha=0
                    pixelMatrix[w][h] = Pixel(y, i, q)
                }
            }
        }

        fun createYIQfromRGB(pixelMatrix: Array<Array<Pixel>>): Array<Array<Pixel>> {
            val pixelMatrixYIQ = pixelMatrix.copyOf()
            RGBtoYIQ(pixelMatrixYIQ)
            return pixelMatrixYIQ
        }

    }
}