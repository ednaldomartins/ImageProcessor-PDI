import control.ImageProcessorController
import model.AverageFilter
import view.showImage

fun main () {
    val imgController = ImageProcessorController()
    //local da imagem
    imgController.loadImage("..//ImageProcessor//imagens//lena256color.jpg")

    val time = System.currentTimeMillis()

    //testando filtro verde
    //imgController.processImage( GreenFilter() )
    //teste de filtro vermelho em preto e branco
    //imgController.processImage( RedFilterBlackWhite() )

    imgController.processImage(AverageFilter())
    //mostrar resultado da imagem
    showImage(imgController.bufferedImage)

    println( "${System.currentTimeMillis() - time} milissegundos ")
}