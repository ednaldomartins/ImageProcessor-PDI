package util;

import model.Pixel;

public class Matriz {

    //ixj tamanho da mascara
    public Pixel[][] criarMatrizComExtensaoPorZeros (int width, int height, int i, int j) {
        return new Pixel[width+(i-1)][height+(j-1)];
    }

    public Pixel[][] criarMatrizSemExtensaoPorZeros (int width, int height) {
        return new Pixel[width][height];
    }

}
