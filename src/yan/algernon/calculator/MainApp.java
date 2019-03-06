
package yan.algernon.calculator;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yan.algernon.calculator.view.MainViewController;

public class MainApp extends Application {
    private  Stage primaryStage;
    private AnchorPane mainRoot;
    
    @Override
    public void start(Stage primaryStage) {        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Кальякулятор");
        primaryStage.setResizable(false);
        
        initMainView(); 
        
        
    }
    
    public void initMainView (){        
          try {            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainView.fxml"));
            mainRoot = (AnchorPane) loader.load(); 
            
            Scene scene = new Scene(mainRoot);
            primaryStage.setScene(scene);
                        
            
            MainViewController controller = loader.getController();
            
            
            primaryStage.show();
            
        }  catch (IOException e) {
            e.printStackTrace();
        }           
    
    }
    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
