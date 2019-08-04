package model

import data.MaskData
import java.awt.image.BufferedImage

class MedianFilter : ExtensionFilter {
    override val mask: Array<FloatArray> = MaskData().loadEmptyMask("mascaraVazia3x3.txt")!!

    override fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel?>>) {
        //pegar o tamanho da borda adicional de cado lado da imagem
        val w: Int = mask.size/2
        val h: Int = mask[0].size/2
        //O loop comeca a edicao da imagem a partir do inicio da imagem original.
        //Para pegar a borda do pixelArray usamos (i-w+k, i-w+k, j-h+l, j-h+l)
        for (i in w until pixelArray.size-w) for (j in h until pixelArray[0].size-h) {
            /*  calular o valor de red, green, blue e alpha
             *  Usaremos um vetor para melhor a velocidade e facilitar o entendimento. Para nao precisar fazer mais
             *  calculos como, k*mask.size + l para achar a posicao de atribuir no vetor, vamos usar o 'n' para tambem
             *  facilitar a leitura e evitar calculos. Apos isso o vetor deve ser ordenado e pegaremos o valor central.
             *  ! pixelArray[i-w+k][j-h+l]
             *      i: posicao atual do pixel editavel na imagem
             *      w: subtrai para corrigir a posicao do pixel na matriz que estamos calculando
             *      k: faz a matriz percorrer dentro da matriz selecionada atraves do tamanho da mascara
             */
            val redMask = IntArray(mask.size*mask[0].size)
            val greenMask = IntArray(mask.size*mask[0].size)
            val blueMask = IntArray(mask.size*mask[0].size)
            var n = 0
            for (k in 0 until mask.size) for (l in 0 until mask[0].size) {
                redMask[n] = pixelArray[i - w + k][j - h + l]!!.red.toInt()
                greenMask[n] = pixelArray[i - w + k][j - h + l]!!.green.toInt()
                blueMask[n] = pixelArray[i - w + k][j - h + l]!!.blue.toInt()
                n++
            }
            redMask.sort()
            greenMask.sort()
            blueMask.sort()
            //criado apenas para facilitar a leitura
            val imagePixel = intArrayOf(redMask[redMask.size/2], greenMask[redMask.size/2], blueMask[redMask.size/2], 255)
            //escrevendo o resultado na imagem
            val rasterImage = bufferedImage.raster
            rasterImage.setPixel( i-w, j-h, imagePixel)
        }

    }
}