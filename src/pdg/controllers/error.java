package pdg.controllers;

public class ErrorPopupComponent{
  public static void show(String message){
    show(message,title:"Error")
  }
	public static void  show(String message,String title){
	 Alert alert = new Alert(Alert.AlertType.ERROR);
	 alert.setTitle(title);
	 alert.setHeaderText(title);

	 Label label =new Label(message);
	 label.setWrapText(true);
	 label.setMaxWidth(400);
	 StackPane pane = new StackPane(label);
	 alert.getDialogPane().setContent(pane);

	 alert.showAndWait();
	}
}