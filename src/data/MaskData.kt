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

    /**
     * O metodo deve retornar uma mascara Preenchida com valores encontrados dentro do arquivo escolhido.
     * @param maskName nome do arquivo de leitura para cirar mascara
     * @return mascara preenchida
     */
    fun loadMask(maskName: String): Array<FloatArray>? {
        try {
            val inputStream: InputStream = File(PATH+maskName).inputStream()
            val bufferedReader: BufferedReader = inputStream.bufferedReader()
            //primeira linha = Largura X Altura
            val vSize = bufferedReader.readLine().toUpperCase().split('X')
            val mask =  Array(vSize[1].toInt()) { FloatArray (  vSize[0].toInt() ) }
            //linha a linha pegar os valores da matriz
            for (i in 0 until mask.size) {
                val vLine = bufferedReader.readLine().split(' ')
                for (j in 0 until mask[0].size) {
                    mask[i][j] = vLine[j].toFloat()
                }
            }
            return mask
        } catch (e: IOException) {
            println("Erro na leitura do arquivo ")
            return Array(0) {FloatArray(0)}
        }
    }

    /**
     * O metodo deve retornar apenas uma mascara vazia. Essa mascara pode ser usada para o filtro media e mediana.
     * @param maskName nome do arquivo de leitura para cirar mascara
     * @return mascara vazia
     */
    fun loadEmptyMask(maskName: String): Array<FloatArray>? = try {
        val inputStream: InputStream = File(PATH+maskName).inputStream()
        val bufferedReader: BufferedReader = inputStream.bufferedReader()
        //primeira linha = Largura X Altura
        val vSize = bufferedReader.readLine().toUpperCase().split('X')
        Array(vSize[0].toInt()) { FloatArray ( vSize[1].toInt() ) } //return
    } catch (e: IOException) {
        println("Erro na leitura do arquivo ")
        Array(0) { FloatArray (0) } //return
    }

}