package yan.algernon.calculator.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import yan.algernon.calculator.model.Calculator;

public class MainViewController {

    @FXML
    private TextField formulaField;

    @FXML
    private void initialize() {

    }

    @FXML
    private void calculate() {
        Calculator calc = new Calculator();
        calc.setStrFromField(formulaField.getText());
        formulaField.clear();
        try {
            calc.setAnswer(calc.calculate(calc.makeReversePolishNotation(calc.getStrFromField())));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Некорректный ввод");
        alert.setContentText("Формула должна содержать только цифры, точку, скобки и операторы ( +, -, *, / )");

        alert.showAndWait();

        }
        calc.setStrFromField(calc.getStrFromField()+ " = "+calc.getAnswer());
        formulaField.setText(calc.getStrFromField());

    }

    @FXML
    private void closeWindow(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

}
