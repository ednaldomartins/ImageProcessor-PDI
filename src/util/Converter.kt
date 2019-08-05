package util

import model.Pixel

class Converter {
    companion object {
        fun RGBtoYIQ(pixelMatrix: Array<Array<Pixel?>>) {
            for (w in 0 until pixelMatrix.size) {
                for (h in 0 until pixelMatrix[0].size) {
                    val y: Float = 0.299f*pixelMatrix[w][h]!!.red + 0.587f*pixelMatrix[w][h]!!.green + 0.114f*pixelMatrix[w][h]!!.blue
                    val i: Float = 0.596f*pixelMatrix[w][h]!!.red - 0.274f*pixelMatrix[w][h]!!.green - 0.322f*pixelMatrix[w][h]!!.blue
                    val q: Float = 0.211f*pixelMatrix[w][h]!!.red - 0.523f*pixelMatrix[w][h]!!.green + 0.312f*pixelMatrix[w][h]!!.blue
                    //Pixel(rgba): red=y, green=i, blue=q, alpha=0
                    pixelMatrix[w][h] = Pixel(y, i, q)
                }
            }
        }

        fun YIQtoRGB(pixelMatrix: Array<Array<Pixel?>>) {
            for (w in 0 until pixelMatrix.size) {
                for (h in 0 until pixelMatrix[0].size) {
                    val r: Float = 1f*pixelMatrix[w][h]!!.red + 0.956f*pixelMatrix[w][h]!!.green + 0.621f*pixelMatrix[w][h]!!.blue
                    val g: Float = 1f*pixelMatrix[w][h]!!.red - 0.272f*pixelMatrix[w][h]!!.green - 0.647f*pixelMatrix[w][h]!!.blue
                    val b: Float = 1f*pixelMatrix[w][h]!!.red - 1.106f*pixelMatrix[w][h]!!.green + 1.703f*pixelMatrix[w][h]!!.blue
                    //Pixel(rgba): red=y, green=i, blue=q, alpha=0
                    pixelMatrix[w][h] = Pixel(r, g, b)
                }
            }
        }

    }
}