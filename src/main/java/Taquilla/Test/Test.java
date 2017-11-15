package Taquilla.Test;

import Taquilla.Controller.EditPlayController;
import Taquilla.Controller.RegisterPlayController;
import Taquilla.Model.EditPlayModel;
import Taquilla.Model.RegisterPlayModel;
import Taquilla.Views.EditPlayView;
import Taquilla.Views.RegisterPlayView;

/**
 * Para poder ejecutar la vista, es necesario crear el objeto vista, el objeto controlador, y asignarle al controlador
 * la vista correspondiente. Así que es necesario realizar estos pasos al momento que se desee acceder a la gui
 * de registro de obra. Se usa esta clase para probar la ejecución de estos módulos.
 */
public class Test {

    public static void main(String[] args) {
        RegisterPlayController controlador = new RegisterPlayController(new RegisterPlayView(), new RegisterPlayModel());
        //EditPlayController controlador = new EditPlayController(new EditPlayView() , new EditPlayModel());

    }

}
