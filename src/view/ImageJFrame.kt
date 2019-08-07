package view

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel

/********************************************************************************************************************
 * @author ednaldo martins                                                                                          *
 * created on july 20, 2019                                                                                         *
 *******************************************************************************************************************/
private var frame:JFrame = JFrame()

fun showImage(imagem: BufferedImage) {
    //+24 = barra de titulo
    frame.setSize(imagem.width, imagem.height+24)
    val panel = object : JPanel() {
        override fun paintComponent(grafics: Graphics) {
            grafics.drawImage(imagem, 0, 0, imagem.width, imagem.height, this)
            super.paintComponents(grafics)
        }
    }
    frame.contentPane = panel
    frame.isVisible = true
}