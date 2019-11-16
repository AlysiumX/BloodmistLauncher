package main_shell;

import events.FileDragAndDrop;
import events.FileDropListener;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainShell extends Scene implements FileDropListener
{
	//Settings -- TODO : Make a settings screen you lazy bum!
	private static final String EngineLocation = "D:\\Programs\\Doom\\gzdoom.exe";
	public MainShell(Parent root, double width, double height)
	{
		super(root, width, height);

		var fileDragAndDrop = new FileDragAndDrop();
		fileDragAndDrop.Setup(this );
		fileDragAndDrop.addListener(this::fileDropped);
	}

	@Override
	public void fileDropped(String droppedFiles)
	{
		var process = new ProcessBuilder(EngineLocation, droppedFiles);
		try
		{
			process.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
