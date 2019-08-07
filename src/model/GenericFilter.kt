package model

import data.MaskData
import java.awt.image.BufferedImage

class GenericFilter(override val mask: Array<FloatArray> = MaskData().loadMask("teste.txt")!!): ExtensionFilter {

    override fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel?>>) {
        //pegar o tamanho da borda adicional de cado lado da imagem
        val w: Int = mask.size/2
        val h: Int = mask[0].size/2
        //escritor da imagem
        val rasterImage = bufferedImage.raster
        //o loop comeca a edicao da imagem a partir do inicio da imagem original.
        //Para pegar a borda do pixelArray usamos (i-w+k, i-w+k, j-h+l, j-h+l)
        for (i in w until pixelArray.size-w) for (j in h until pixelArray[0].size-h) {
            /*  ! pixelArray[i-w+k][j-h+l]
             *      i: posicao atual do pixel editavel na imagem
             *      w: subtrai para corrigir a posicao do pixel na matriz que estamos calculando
             *      k: faz a matriz percorrer dentro da matriz selecionada atraves do tamanho da mascara
             */
            val pixel = Pixel()
            for (k in 0 until mask.size) for (l in 0 until mask[0].size) {
                pixel.red += pixelArray[i-w+k][j-h+l]!!.red * mask[k][l]
                pixel.green += pixelArray[i-w+k][j-h+l]!!.green * mask[k][l]
                pixel.blue += pixelArray[i-w+k][j-h+l]!!.blue * mask[k][l]
                pixel.alpha += pixelArray[i-w+k][j-h+l]!!.alpha * mask[k][l]
            }
            //corrigir valores fora do intervalo 0-255
            val vPixel = intArrayOf(0,0,0, pixelArray[i-w][j-h]!!.alpha.toInt())
            vPixel[0] = if (pixel.red > 255) 255 else if (pixel.red < 0) 0 else pixel.red.toInt()
            vPixel[1] = if (pixel.green > 255) 255 else if (pixel.green < 0) 0 else pixel.green.toInt()
            vPixel[2] = if (pixel.blue > 255) 255 else if (pixel.blue < 0) 0 else pixel.blue.toInt()
            //escrevendo o resultado na imagem
            rasterImage.setPixel( i-w, j-h, vPixel)
        }

    }
}