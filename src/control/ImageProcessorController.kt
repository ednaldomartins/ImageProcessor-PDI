package control

import data.ImageData
import model.Filter
import util.Matriz
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 21, 2019                                                                                         *
 *******************************************************************************************************************/
class ImageProcessorController {
    lateinit var bufferedImage: BufferedImage

    fun processImage (filter: Filter) {
        filter.filtrate(bufferedImage, Matriz().criarMatrizSemExtensaoPorZeros(bufferedImage.width, bufferedImage.height))
    }

    fun loadImage(path: String) {
        bufferedImage = ImageData().loadImage(path)
    }
}