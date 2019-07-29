package data

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.BufferedReader

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 29, 2019                                                                                         *
 *******************************************************************************************************************/
class MaskData {
    val PATH = "..//ImageProcessor//in//mask//"

    fun load(maskName: String): Array<FloatArray>? {
        var mask: Array<FloatArray> = emptyArray()
        try {
            val inputStream: InputStream = File(PATH+maskName).inputStream()
            val bufferedReader: BufferedReader = inputStream.bufferedReader()
            val size = bufferedReader.readLine()

            //primeira linha = Largura X Altura
            val vSize = size.toUpperCase().split("X")
            mask =  Array(vSize[0].toInt()) { FloatArray ( vSize[1].toInt() ) }
            for (i in 0 until mask.size) {
                val line = bufferedReader.readLine()
                val vLine = line.split(" ")
                for (j in 0 until mask[0].size) {
                    mask[i][j] = vLine[j].toFloat()
                }
            }
        } catch (e: IOException) {
            println("Erro na leitura do arquivo ")
        }
        return mask
    }
}