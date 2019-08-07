package control

import data.ImageData
import model.ExtensionFilter
import model.Filter
import util.Extension
import util.Matrix
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 21, 2019                                                                                         *
 *******************************************************************************************************************/
class ImageProcessorController {
    lateinit var bufferedImage: BufferedImage

    fun processImage (filter: Filter) {
        //tempo inicial
        val time = System.currentTimeMillis()

        filter.filtrate(bufferedImage)

        //tempo de execucao
        println("\n ${System.currentTimeMillis() - time} milissegundos.\n\n")
    }

    fun processImage (filter: ExtensionFilter, convolve: Boolean = false) {
        //tempo inicial
        val time = System.currentTimeMillis()
        val pixelMatrix = Extension().zeroExtension(bufferedImage, filter.mask)
        if (convolve) {
            Matrix.convolute(filter.mask)
            filter.filtrate(bufferedImage, pixelMatrix )
        } else
            filter.filtrate(bufferedImage, pixelMatrix)

        //tempo de execucao
        println("\n ${System.currentTimeMillis() - time} milissegundos.\n\n")
    }

    fun loadImage(path: String) {
        bufferedImage = ImageData().loadImage(path)
    }
}
