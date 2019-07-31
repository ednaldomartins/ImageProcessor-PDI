import control.ImageProcessorController
import model.AverageFilter
import model.BlueFilter
import model.BlueFilterBlackWhite
import model.NegativeFilter
import view.showImage

fun main () {
    val imgController = ImageProcessorController()
    //local da imagem
    imgController.loadImage("lena256color.jpg")
    //exibir imagem original
    showImage(imgController.bufferedImage)
    //tempo inicial
    val time = System.currentTimeMillis()

    //testando filtro verde
    //imgController.processImage( GreenFilter() )
    //filtro negativo
    imgController.processImage( NegativeFilter() )
    //filtro azul
    //imgController.processImage( BlueFilter() )
    //filtro azul preto e branco
    //imgController.processImage( BlueFilterBlackWhite() )
    //testando filtro vermelho em preto e branco
    //imgController.processImage( RedFilterBlackWhite() )
    //testando filtro da media
    //imgController.processImage(AverageFilter())

    //exibir resultado do filtro aplicado na imagem
    showImage(imgController.bufferedImage)
    //tempo
    println( "${System.currentTimeMillis() - time} milissegundos ")
}