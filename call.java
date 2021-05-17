package sample;

import javafx.stage.DirectoryChooser;

import java.io.File;
public class call extends Filelist{

    public File getfile() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File media = directoryChooser.showDialog(null);

        return media;
    }
}
