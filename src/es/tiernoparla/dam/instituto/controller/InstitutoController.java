package es.tiernoparla.dam.instituto.controller;

import java.io.IOException;
import java.util.List;

import es.tiernoparla.dam.instituto.App;
import es.tiernoparla.dam.instituto.model.Alumno;
import es.tiernoparla.dam.instituto.model.DAOFactory;
import es.tiernoparla.dam.instituto.model.InstitutoDAO;
import es.tiernoparla.dam.instituto.view.FormularioViewController;
import es.tiernoparla.dam.instituto.view.IViewFile;
import es.tiernoparla.dam.instituto.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstitutoController extends Application {
   
    private InstitutoDAO dao;
    private static Stage currentStage;

    public InstitutoController(){
        /**
         * Llamo a la factoría para inicializar el dao que me dará acceso a los datos
         */
        dao = DAOFactory.getDAO(DAOFactory.MODO_TEST);

    }


    /// Cargar pantalla inicial
    @Override
    public void start(Stage stage) throws Exception {
        
/**        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/FormularioView.fxml"));
        Parent root = (Parent)fxmlLoader.load();  

        //Obtengo el controlador de la vista para pasarle una referencia al controlador principal:
        FormularioViewController viewController = fxmlLoader.<FormularioViewController>getController();
        viewController.setInstitutoController(this);
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
*/        

        //Inicialmente cargo la pantalla principal, uso la interfaz IViewFile para mantener los nombres de los ficheros

        currentStage = stage;
        FormularioViewController viewController = (FormularioViewController) cargarVista(IViewFile.VIEW_PRINCIPAL);

        //Incializo la tabla con los datos del modelo.
        viewController.cargaInicialTabla(obtenerAlumnos());
    }

    /** 
     * Cargar una vista desde el fichero y devuelve el controlador de la vista cargada (desde su clase padre ViewController)
     */
    public ViewController cargarVista(String ficheroView) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(ficheroView));
        Parent root = (Parent)fxmlLoader.load();  

        //Obtengo el controlador de la vista para pasarle una referencia al controlador principal:
        ViewController viewController = fxmlLoader.<ViewController>getController();
        viewController.setInstitutoController(this);
        Scene scene = new Scene(root); 
        currentStage.close();

        //Cargo la nueva pantalla
        currentStage.setScene(scene);
        currentStage.show();

        return viewController;
    }

    // Metodos de acceso a los datos

    public List<Alumno> add(Alumno a) throws Exception{
        dao.add(a);
        return dao.obtenerAlumnos();
    }

    public List<Alumno> obtenerAlumnos() throws Exception{
        return dao.obtenerAlumnos();
    }

}
