package model

import data.MaskData
import java.awt.image.BufferedImage

class AverageFilter(override val mask: Array<FloatArray>): ExtensionFilter {
    //mask = arrayOf(floatArrayOf())
    //override val mask: Array<FloatArray> = MaskData().loadEmptyMask("mascaraVazia3x1.txt")!!
    override fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel?>>) {
        //pegar o tamanho da borda adicional de cado lado da imagem
        val w: Int = mask.size/2
        val h: Int = mask[0].size/2
        val media: Float = (1f/(mask.size*mask[0].size))
        //preencher a mascara com a media obtida
        for (i in 0 until mask.size) for (j in 0 until mask[0].size) mask[i][j] = media
        //escritor da imagem
        val rasterImage = bufferedImage.raster
        //o loop comeca a edicao da imagem a partir do inicio da imagem original.
        //Para pegar a borda do pixelArray usamos (i-w+k, i-w+k, j-h+l, j-h+l)
        for (i in w until pixelArray.size-w) for (j in h until pixelArray[0].size-h) {
                /*  calular o valor de red, green, blue e alpha
                 *  Poderiamos substiruir cada multiplicacao do mask[x][y] por apenas um filterMatriz[0][0] ao
                 *  final da soma, o colocando em evidencia. Mas para fins teoricos vamos manter o filterMatriz[x][y]
                 *  multiplicando cada um dos pixelArray's de forma separada.
                 *  ! pixelArray[i-w+k][j-h+l]
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
                //criado apenas para facilitar a leitura
                val imagePixel = intArrayOf(pixel.red.toInt(), pixel.green.toInt(), pixel.blue.toInt(), pixel.alpha.toInt())
                //escrevendo o resultado na imagem
                rasterImage.setPixel( i-w, j-h, imagePixel)
        }

    }

}