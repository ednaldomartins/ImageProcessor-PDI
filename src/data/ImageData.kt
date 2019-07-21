package data

import java.io.File
import javax.imageio.ImageIO

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 20, 2019                                                                                         *
 *******************************************************************************************************************/
class ImageData {
    fun loadImage(path: String) = ImageIO.read(File(path))
}
