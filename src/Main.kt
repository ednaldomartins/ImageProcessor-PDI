import control.ImageProcessorController
import model.*
import util.Matrix
import view.showImage

fun main () {
    val imgController = ImageProcessorController()
    //nome do arquivo da imagem
    imgController.loadImage("lena256color.jpg")
    //exibir imagem original
    showImage(imgController.bufferedImage)
    //tempo inicial
    val time = System.currentTimeMillis()

    //testando filtro verde
    //imgController.processImage( GreenFilter() )
    //testando filtro negativo
    //imgController.processImage( NegativeFilter() )
    //testando mudança de brilho
    //imgController.processImage( Bright() )
    //testando brilho YIQ
    //imgController.processImage( BrightYIQ() )
    //testando filtro azul
    //imgController.processImage( BlueFilter() )
    //testando filtro azul preto e branco
    //imgController.processImage( BlueFilterBlackWhite() )
    //testando filtro vermelho em preto e branco
    //imgController.processImage( RedFilterBlackWhite() )
    //testando filtro da media
    //imgController.processImage(AverageFilter())
    //testando filtro mediana
    imgController.processImage(MedianFilter(), true)
    //testando filtro de sobel
    //imgController.processImage(Sobel())

    //exibir resultado do filtro aplicado na imagem
    showImage(imgController.bufferedImage)

    //tempo de execucao
    println( "${System.currentTimeMillis() - time} milissegundos ")
}