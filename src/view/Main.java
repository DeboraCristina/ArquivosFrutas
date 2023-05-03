package view;

import controll.FilesControl;

public class Main
{
    public static void main(String[] args)
    {
        FilesControl arquivosControll = new FilesControl();
        String path = "/home/debby/Testes/TEMP/";
        String file = "generic_food.csv";

        String result = "";

        boolean existe = arquivosControll.isValidPath(path);
        try {
            arquivosControll.showTable(3, "Fruits", file, path);
        } catch (Exception e) {
            System.err.println("Caminho Invalido!!");
        }

    }
}