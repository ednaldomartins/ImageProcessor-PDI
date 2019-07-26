package model

import java.awt.image.BufferedImage

class AverageFilter: ExtensionFilter {
    override val mask = arrayOf(
        floatArrayOf(0.1111f, 0.1111f, 0.1111f),
        floatArrayOf(0.1111f, 0.1111f, 0.1111f),
        floatArrayOf(0.1111f, 0.1111f, 0.1111f)
    )

    override fun filtrate(bufferedImage: BufferedImage, pixelArray: Array<Array<Pixel?>>) {
        //pegar o tamanho da borda adicional de cado lado da imagem
        val w: Int = mask.size/2
        val h: Int = mask[0].size/2
        //escritor da imagem
        val imgWriter = bufferedImage.raster
        //o loop comeca a edicao da imagem a partir do inicio da imagem original.
        //Para pegar a borda do pixelArray usamos (i-1, i+1, j-1, j+1)
        for (i in w until pixelArray.size-w) {
            for (j in h until pixelArray[0].size-h) {
                /*  calular o valor de red, green, blue e alpha
                 *  Poderiamos substiruir cada multiplicacao do mask[x][y] por apenas um filterMatriz[0][0] ao
                 *  final da soma, o colocando em evidencia. Mas para fins teoricos vamos manter o filterMatriz[x][y]
                 *  multiplicando cada um dos pixelArray's de forma separada.
                 */
                val red: Float = ( pixelArray[i-1][j-1]!!.red*mask[0][0] + pixelArray[i-1][j]!!.red*mask[0][1] + pixelArray[i-1][j+1]!!.red*mask[0][2] +
                        pixelArray[i][j-1]!!.red*mask[1][0] + pixelArray[i][j]!!.red*mask[1][1] + pixelArray[i][j+1]!!.red*mask[1][2] +
                        pixelArray[i+1][j-1]!!.red*mask[2][0] + pixelArray[i+1][j]!!.red*mask[2][1] + pixelArray[i+1][j+1]!!.red*mask[2][2] )

                val green: Float = ( pixelArray[i-1][j-1]!!.green*mask[0][0] + pixelArray[i-1][j]!!.green*mask[0][1] + pixelArray[i-1][j+1]!!.green*mask[0][2] +
                        pixelArray[i][j-1]!!.green*mask[1][0] + pixelArray[i][j]!!.green*mask[1][1] + pixelArray[i][j+1]!!.green*mask[1][2] +
                        pixelArray[i+1][j-1]!!.green*mask[2][0] + pixelArray[i+1][j]!!.green*mask[2][1] + pixelArray[i+1][j+1]!!.green*mask[2][2] )

                val blue: Float = ( pixelArray[i-1][j-1]!!.blue*mask[0][0] + pixelArray[i-1][j]!!.blue*mask[0][1] + pixelArray[i-1][j+1]!!.blue*mask[0][2] +
                        pixelArray[i][j-1]!!.blue*mask[1][0] + pixelArray[i][j]!!.blue*mask[1][1] + pixelArray[i][j+1]!!.blue*mask[1][2] +
                        pixelArray[i+1][j-1]!!.blue*mask[2][0] + pixelArray[i+1][j]!!.blue*mask[2][1] + pixelArray[i+1][j+1]!!.blue*mask[2][2] )

                val alpha: Float = ( pixelArray[i-1][j-1]!!.alpha*mask[0][0] + pixelArray[i-1][j]!!.alpha*mask[0][1] + pixelArray[i-1][j+1]!!.alpha*mask[0][2] +
                        pixelArray[i][j-1]!!.alpha*mask[1][0] + pixelArray[i][j]!!.alpha*mask[1][1] + pixelArray[i][j+1]!!.alpha*mask[1][2] +
                        pixelArray[i+1][j-1]!!.alpha*mask[2][0] + pixelArray[i+1][j]!!.alpha*mask[2][1] + pixelArray[i+1][j+1]!!.alpha*mask[2][2] )

                //substituimos o valor atual pelos calculados usando uma nova instancia. Devemos manter o valor Float
                pixelArray[i][j] = Pixel(red, green, blue, alpha)
                //criado apenas para facilitar a leitura
                val pixel = intArrayOf(red.toInt(), green.toInt(), blue.toInt(), alpha.toInt())
                //escrevendo o resultado na imagem
                imgWriter.setPixel( i-w, j-h, pixel)
            }
        }
    }
}