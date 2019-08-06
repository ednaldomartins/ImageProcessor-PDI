package model

import util.Converter
import util.Matrix.copyImageToMatrix
import java.awt.Color
import java.awt.image.BufferedImage

class BrightYIQ: Filter {

    override fun filtrate(bufferedImage: BufferedImage) {

        var c: Float = 0.5f
        //var c: Float = 1.5f
        //var c: Float = 3.0f

        //cria como nao nulo e envia para uma classe java
        val pixelMatrix: Array<Array<Pixel?>> = Array(bufferedImage.width) { arrayOfNulls<Pixel>(bufferedImage.height)}
        //copia pixels da imagem para matriz sem extensao
        copyImageToMatrix(bufferedImage, pixelMatrix)
        //converte de RGB para YIQ para poder fazer o negativo nesse sistema de cores
        Converter.RGBtoYIQ(pixelMatrix)
        //aplica o mesmo conceito do RGB no sistema YIQ, onde Y = luma. //OBS.: AQUI O 'RED' = Y
        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                pixelMatrix[i][j]!!.red = c * pixelMatrix[i][j]!!.red
            }
        }

        //tras de volta para o RGB
        Converter.YIQtoRGB(pixelMatrix)
        //cria o escritor da imagem
        val imgWriter = bufferedImage.raster
        val vPixel = intArrayOf(0,0,0,255)
        //apos trazer do YIQ para RGB, devemos verificar se os valores estao fora dos limites permitidos no RGB(255)
        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                vPixel[0] = if (pixelMatrix[i][j]!!.red > 255) 255 else if (pixelMatrix[i][j]!!.red < 0) 0 else pixelMatrix[i][j]!!.red.toInt()
                vPixel[1] = if (pixelMatrix[i][j]!!.green > 255) 255 else if (pixelMatrix[i][j]!!.green < 0) 0 else pixelMatrix[i][j]!!.green.toInt()
                vPixel[2] = if (pixelMatrix[i][j]!!.blue > 255) 255 else if (pixelMatrix[i][j]!!.blue < 0) 0 else pixelMatrix[i][j]!!.blue.toInt()
                vPixel[4] = Color(bufferedImage.getRGB(i, j)).alpha
                imgWriter.setPixel(i, j, vPixel)
            }
        }

    }


}