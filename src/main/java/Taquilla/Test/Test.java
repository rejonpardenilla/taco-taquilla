package Taquilla.Test;

import Taquilla.Controller.RegisterShowController;
import Taquilla.Views.RegisterShow;

/**
 * Para poder ejecutar la vista, es necesario crear el objeto vista, el objeto controlador, y asignarle al controlador
 * la vista correspondiente. Así que es necesario realizar estos pasos al momento que se desee acceder a la gui
 * de registro de obra. Se usa esta clase para probar la ejecución de estos módulos.
 */
public class Test {

    public static void main(String[] args) {
        RegisterShow viewRegisterShow = new RegisterShow();
        RegisterShowController controllerRegisterView = new RegisterShowController(viewRegisterShow);

    }

}
