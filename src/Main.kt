import control.ImageProcessorController
import model.GreenFilter
import view.showImage

fun main () {
    val imgController = ImageProcessorController()
    imgController.loadImage("..//ImageProcessor//imagens//lena256color.jpg")
    //testando filtro verde
    imgController.processImage( GreenFilter() )
    showImage(imgController.bufferedImage)
}