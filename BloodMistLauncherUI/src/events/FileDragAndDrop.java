package events;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileDragAndDrop
{
	private List<FileDropListener> listeners = new ArrayList<FileDropListener>();
	private List<String> DroppedFiles = new ArrayList<String>();

	public void addListener(FileDropListener dropListener)
	{
		listeners.add(dropListener);
	}

	public void Setup(Scene scene )
	{
		scene.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasFiles()) {
					event.acceptTransferModes(TransferMode.COPY);
				} else {
					event.consume();
				}
			}
		});

		DroppedFiles.clear();
		scene.setOnDragDropped(new EventHandler<DragEvent>()
		{
			@Override
			public void handle(DragEvent event)
			{
				Dragboard db = event.getDragboard();
				var success = false;
				DroppedFiles.clear();
				if(db.hasFiles()){
					for( File file:db.getFiles() )
					{
						success = true;
						DroppedFiles.add( "\"" + file.getAbsolutePath() + "\"" );
					}
				}

				var fileToLoadString = String.join(" ", DroppedFiles);

				for(FileDropListener dropListener : listeners)
					dropListener.fileDropped(fileToLoadString);

				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

}
