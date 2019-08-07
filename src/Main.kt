import control.ImageProcessorController
import model.*
import util.Matrix
import view.showImage
import java.util.*

fun main () {
    val imgController = ImageProcessorController()
    //nome do arquivo da imagem
    imgController.loadImage("gta.jpg")
    //exibir imagem original
    showImage(imgController.bufferedImage)
    //tempo inicial
    val time = System.currentTimeMillis()

    var x: Int
    val i = Scanner(System.`in`)

    loop@ while (true){

    println(
        "1> Filtro Verde\n2> Filtro Azul\n3> Filtro Vermelho\n4> Filtro Verde preto e branco\n" +
                "5> Filtro Negativo\n6> Filtro Negativo YIQ\n7> Brilho\n8> Brilho YIQ\n9> Media\n" +
                "10> Mediana\n11> Sobel\n12> Sair\n"
    )
        x = i.nextInt()

    when (x) {

        1 -> imgController.processImage( GreenFilter() )

        2 -> imgController.processImage( BlueFilter() )

        3 -> imgController.processImage( RedFilter() )

        4 -> imgController.processImage( GreenFilterBlackWhite() )

        5 -> imgController.processImage( NegativeFilter() )

        6 -> imgController.processImage( NegativeFilterYIQ() )

        7 -> imgController.processImage( Bright() )

        8 -> imgController.processImage( BrightYIQFilter() )

        9 -> imgController.processImage(AverageFilter())

        10 -> imgController.processImage(MedianFilter(), true)

        11 -> imgController.processImage(Sobel())

        12 -> break@loop

    }
        //exibir resultado do filtro aplicado na imagem
        showImage(imgController.bufferedImage)
}
    //tempo de execucao
    println( "${System.currentTimeMillis() - time} milissegundos ")
}