import control.ImageProcessorController
import model.RedFilterBlackWhite
import view.showImage

fun main () {
    val imgController = ImageProcessorController()
    //local da imagem
    imgController.loadImage("..//ImageProcessor//imagens//lena256color.jpg")

    //testando filtro verde
    //imgController.processImage( GreenFilter() )
    //teste de filtro vermelho em preto e branco
    imgController.processImage( RedFilterBlackWhite() )

    //mostrar resultado da imagem
    showImage(imgController.bufferedImage)
}