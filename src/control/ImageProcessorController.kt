package control

import data.ImageData
import model.ExtensionFilter
import model.Filter
import util.Matrix
import java.awt.image.BufferedImage

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 21, 2019                                                                                         *
 *******************************************************************************************************************/
class ImageProcessorController {
    lateinit var bufferedImage: BufferedImage

    fun processImage (filter: Filter) {
        filter.filtrate(bufferedImage)
    }

    fun processImage (filter: ExtensionFilter) {
        filter.filtrate(bufferedImage, Matrix().zeroExtension(bufferedImage, filter.mask) )
    }

    fun loadImage(path: String) {
        bufferedImage = ImageData().loadImage(path)
    }
}
