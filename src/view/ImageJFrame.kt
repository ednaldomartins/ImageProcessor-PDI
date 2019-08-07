package view

import java.awt.Graphics
import java.awt.Image
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
    var prop = 1f
    if (imagem.height > 600)
        prop = 600f/imagem.height

    val w = (imagem.width*prop).toInt()
    val h = (imagem.height*prop).toInt()
    frame.setSize(w, h + 24)
    val panel = object : JPanel() {
        override fun paintComponent(grafics: Graphics) {
            grafics.drawImage(imagem.getScaledInstance(w, h, Image.SCALE_DEFAULT), 0, 0, null)
            super.paintComponents(grafics)
        }
    }
    frame.contentPane = panel
    frame.isVisible = true
}