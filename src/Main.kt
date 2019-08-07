import control.ImageProcessorController
import model.*
import view.showImage
import java.util.*
import kotlin.system.exitProcess

fun main () {
    val nomeArquivo = "gta.jpg"
    val imgController = ImageProcessorController()
    //nome do arquivo da imagem
    imgController.loadImage(nomeArquivo)
    //exibir imagem original
    showImage(imgController.bufferedImage)

    var x: Int
    val i = Scanner(System.`in`)

    while (true){
        println(
                "1. Filtro Vermelhor\n" +
                "2. Filtro Verde\n" +
                "3. Filtro Azul\n" +
                "4. Filtro Vermelho em preto e branco\n" +
                "5. Filtro Verde em preto e branco\n" +
                "6. Filtro Azul em preto e branco\n" +
                "7. Filtro Negativo\n" +
                "8. Filtro Negativo YIQ\n" +
                "9. Brilho RGB\n" +
                "10. Brilho YIQ\n" +
                "11. Filtro Preto e Branco\n" +
                "12. Media\n" +
                "13. Mediana\n" +
                "14. Sobel\n" +
                "15. Filtro Genérico\n" +
                "16. Recarregar Imagem\n" +
                "17. Sair\n"
        )
        x = i.nextInt()
        when (x) {

            1 -> imgController.processImage( RedFilter() )

            2 -> imgController.processImage( GreenFilter() )

            3 -> imgController.processImage( BlueFilter() )

            4 -> imgController.processImage( RedFilterBlackWhite() )

            5 -> imgController.processImage( GreenFilterBlackWhite() )

            6 -> imgController.processImage( BlueFilterBlackWhite() )

            7 -> imgController.processImage( NegativeFilter() )

            8 -> imgController.processImage( NegativeFilterYIQ() )

            9 -> {
                println("digite o valor de c ")
                val c = i.nextFloat()
                val bright = BrightRGBFilter()
                bright.c = c
                imgController.processImage( bright )
            }

            10 -> {
                println("digite o valor de c ")
                val c = i.nextFloat()
                val bright = BrightYIQFilter()
                bright.c = c
                imgController.processImage( bright )
            }

            11 -> imgController.processImage( BlackWhiteFilter() )

            12 -> {
                println("digite a largura da mascara")
                val w = i.nextInt()
                println("digite a altura da mascara")
                val h = i.nextInt()
                val averageFilter = AverageFilter(Array(w) { FloatArray (h) })
                println("aplicar Convolucao: 1 - Sim / n - Nao")
                val b = i.nextInt()
                //imgController.processImage(averageFilter)
                if (b == 1) imgController.processImage( averageFilter, true ) else imgController.processImage(averageFilter)
            }

            13 -> {
                println("digite a largura da mascara")
                val w = i.nextInt()
                println("digite a altura da mascara")
                val h = i.nextInt()
                val medianFilter = MedianFilter( Array(w) { FloatArray (h) } )
                imgController.processImage( medianFilter )
            }

            14 -> imgController.processImage( Sobel() )

            15 -> {
                val genericFilter = GenericFilter()
                println("aplicar Convolucao: 1 - Sim / n - Nao")
                val b = i.nextInt()
                if (b == 1) imgController.processImage( genericFilter, true ) else imgController.processImage(genericFilter)
            }

            16 -> imgController.loadImage(nomeArquivo)

            17 -> exitProcess(1)

        }
        showImage(imgController.bufferedImage)
    }
}