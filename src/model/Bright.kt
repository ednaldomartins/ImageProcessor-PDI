package model

import java.awt.Color
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author Danillo Medeiros                                                                                         *
 * created on august 02, 2019                                                                                         *
 ******************************************************************************************************************/

/*O Brilho eh feito a partir da formula : S = R . C, onde R sao as cores da imagem e C eh um numero real
C < 1 a imagem escurece; C > 1 a imagem fica mais clara; Foi especificado para respeitar os limites do RGB, ou seja,
S nao pode passar de 0 e 255*/

//valores de teste 0.5; 1.5 e 3; assumindo que seriam valores passados pelo usuario.

class Bright: Filter {

    override fun filtrate(bufferedImage: BufferedImage) {
        //var c: Double = 0.5
        //var c: Double = 1.5
        var c: Double = 3.0

        val imgWriter = bufferedImage.raster

        for (i in 0 until bufferedImage.width) {
            for (j in 0 until bufferedImage.height) {
                val v = intArrayOf((Color(bufferedImage.getRGB(i,j)).red * c).toInt(),
                    (Color(bufferedImage.getRGB(i,j)).green * c).toInt(), (Color(bufferedImage.getRGB(i,j)).blue * c).toInt()
                    , Color(bufferedImage.getRGB(i, j)).alpha
                )
                if(v[0] > 255){
                    v[0] = 255
                }
                if(v[1] > 255){
                    v[1] = 255
                }
                if(v[2] > 255){
                    v[2] = 255
                }
                imgWriter.setPixel(i, j, v)

            }
        }
    }
}