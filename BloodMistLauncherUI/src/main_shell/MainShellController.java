package main_shell;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainShellController implements Initializable
{
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{

	}

	//TODO: Setup close listener
	public void closeButton()
	{
		System.exit(1);
	}
}
