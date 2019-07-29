package data

import java.io.File
import javax.imageio.ImageIO

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 20, 2019                                                                                         *
 *******************************************************************************************************************/
class ImageData {
    val PATH = "..//ImageProcessor//in//images//"
    //@return BufferedImage
    fun loadImage(imageName: String) = ImageIO.read(File(PATH+imageName))
}
