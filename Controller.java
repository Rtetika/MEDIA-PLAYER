package sample;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller implements Initializable {
    public static ArrayList<String> mlist = new ArrayList<String>();
    public int num =0;


    //changing and opening scenes
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

   public static void music(){
        String path3="H:\\Intellij projects\\Project media player\\media player\\src\\sample\\1stscn.wav";
        Media media3 = new Media(new File(path3).toURI().toString());
        MediaPlayer mediaP2= new MediaPlayer(media3);
        mediaP2.play();

    }




    @FXML
    void nextScene(ActionEvent event) {
        String path2 ="H:\\Intellij projects\\new1\\src\\sample\\2ndscn.wav";
        Media media2 = new Media(new File(path2).toURI().toString());
        MediaPlayer mediaP= new MediaPlayer(media2);
        mediaP.play();

        Main.stage.show();
        Main.window.close();
    }
    @FXML
    private Button close;
    @FXML
    void close1(ActionEvent event) {
        Main.window.close();

    }

    @FXML
    void close2(ActionEvent event) {

        Main.stage.close();
    }

    @FXML
    void OpenMp4(ActionEvent event) {

        Main.stage2.show();
        Main.stage.close();

    }
    @FXML
    void OpenMp3(ActionEvent event) {
        Main.stage3.show();
        Main.stage.close();

    }

    //MP4 PLAYER

    @FXML
    private Button play;
    @FXML
    private Button next;
    @FXML
    private Button normal;
    @FXML
    private Button fast;
    @FXML
    private Button stop;
    @FXML
    private Button slow;
    @FXML
    private Button openfile;
    @FXML
    private Button prev;
    @FXML
    private Button pause;
    @FXML
    private Button close3;
    @FXML
    private Button fullscreen;
    @FXML
    private Button minimize;
    @FXML
    private Button list;
    @FXML
    private Slider volSlider;
    @FXML
    private Button vol;
    @FXML
    private Button mute;
    @FXML
    private Slider slider;
    @FXML
    private Button ask;
    @FXML
    private Tooltip tnormal;
    @FXML
    private Tooltip tfast;
    @FXML
    private Tooltip tclose3;
    @FXML
    private Tooltip tpause;
    @FXML
    private Tooltip tback;
    @FXML
    private Tooltip topenfile;
    @FXML
    private Tooltip tnotmute;
    @FXML
    private Tooltip tAsk;
    @FXML
    private Tooltip tfull;
    @FXML
    private Tooltip tmute;
    @FXML
    private Tooltip tmin;
    @FXML
    private Tooltip tslow;
    @FXML
    private Tooltip tstop;
    @FXML
    private Tooltip tfront;
    @FXML
    private Tooltip tfilelist;
    @FXML
    private Tooltip tplay;


    @FXML
    private MediaView mediaView;

    private String filepath;
    private MediaPlayer mediaPlayer;

    @FXML
    void selectingFile(ActionEvent event) {
        try{
            openfile.setTooltip(topenfile);

            FileChooser fileChooser= new FileChooser();
            FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("select a File(*.mp4)","*.mp4");

            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(null);
            filepath = file.toURI().toString();

            if(filepath!=null){
                Media media= new Media(filepath);
                mediaPlayer= new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                DoubleProperty width = mediaView.fitWidthProperty();
                DoubleProperty height = mediaView.fitHeightProperty();
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {

                        slider.setValue(newValue.toMinutes());
                    }
                });
                slider.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mediaPlayer.seek(Duration.minutes(slider.getValue()));
                    }
                });
                slider.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mediaPlayer.seek(Duration.minutes(slider.getValue()));
                    }
                });
                mediaPlayer.setOnReady(new Runnable() {
                    @Override
                    public void run() {
                        Duration totalTime= media.getDuration();
                        slider.setMax(totalTime.toMinutes());
                    }
                });
                volSlider.setValue(mediaPlayer.getVolume()*100);
                volSlider.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        mediaPlayer.setVolume(volSlider.getValue()/100);
                    }
                });


                mediaPlayer.play();
            }

        }catch (NullPointerException e){


            System.out.println(e.getMessage());
            System.out.println("You did not select any file.That's why the ans is NULL. Please select a file.");

        }



    }
    @FXML
    void play(ActionEvent event) {
        play.setTooltip(tplay);
        mediaPlayer.play();
        mediaPlayer.setRate(1);

    }

    @FXML
    void pause(ActionEvent event) {
        pause.setTooltip(tpause);
        mediaPlayer.pause();
    }

    @FXML
    void stop(ActionEvent event) {
        stop.setTooltip(tstop);
        mediaPlayer.stop();

    }

    @FXML
    void mute(ActionEvent event) {
        mute.setTooltip(tmute);
        mediaPlayer.setMute(true);
    }
    @FXML
    void notMute(ActionEvent event) {
        vol.setTooltip(tnotmute);
        mediaPlayer.setMute(false);
    }

    @FXML
    void fast(ActionEvent event) {
        fast.setTooltip(tfast);
        mediaPlayer.setRate(1.5);

    }

    @FXML
    void normalspeed(ActionEvent event) {
        normal.setTooltip(tnormal);
        mediaPlayer.setRate(1);
    }
    @FXML
    void slow(ActionEvent event) {
        slow.setTooltip(tslow);
        mediaPlayer.setRate(0.75);

    }
    @FXML
    void forward10sec(ActionEvent event) {
        next.setTooltip(tfront);
        double d= mediaPlayer.getCurrentTime().toSeconds();
        d=d+10;
        mediaPlayer.seek(new Duration(d*1000));

    }
    @FXML
    void back10sec(ActionEvent event) {
        prev.setTooltip(tback);
        double d= mediaPlayer.getCurrentTime().toSeconds();
        d=d-10;
        mediaPlayer.seek(new Duration(d*1000));

    }

    @FXML
    void close3(ActionEvent event) {
        close3.setTooltip(tclose3);
        Main.stage2.close();

    }
    @FXML
    void fullscreen(ActionEvent event) {
        fullscreen.setTooltip(tfull);
        Main.stage2.setFullScreen(true);
    }
    @FXML
    void minimize(MouseEvent event) {
        minimize.setTooltip(tmin);
        Main.stage2.setIconified(true);
    }
    @FXML
    void mp4list(ActionEvent event) {
        list.setTooltip(tfilelist);
        Filelist list2 = new call();

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lname2 = name.toLowerCase();
                return lname2.endsWith(".mp4");
            }
        };

        File mediafile2[]= list2.getfile().listFiles(filenameFilter);
        for (File a :mediafile2) {
            mlist.add(a.toURI().toString());

            System.out.println(a);
            /*mediaPlayer= new MediaPlayer(new Media(mlist.get(num)));
            mediaPlayer.play();*/



        }

    }

    @FXML
    void askUs(ActionEvent event) throws FileNotFoundException{
        ask.setTooltip(tAsk);
        char askMoree;
        do{
            File askFile= new File("H:/Intellij projects/new1/src/sample/AboutTheMp4Player.txt");
            File askFile2= new File("H:/Intellij projects/new1/src/sample/AboutTheMp3Player.txt");
            File askFile3= new File("H:/Intellij projects/new1/src/sample/AboutTheGroup.txt");
            Scanner input= new Scanner(System.in);
            System.out.println("Please choose from the options--->");
            System.out.println("1.About the MP4 Player.");
            System.out.println("2.About the MP3 Player.");
            System.out.println("3.About the Group.");
            System.out.println("Enter Your Choice Here: ");
            int choice = input.nextInt();
            if (choice==1){
                Scanner read1 =new Scanner(askFile);
                while(read1.hasNext()){
                    String mp4= read1.nextLine();
                    System.out.println(mp4);
                }

                read1.close();


            }
            else if(choice==2){

                Scanner read2 =new Scanner(askFile2);
                while(read2.hasNext()){
                    String mp3= read2.nextLine();
                    System.out.println(mp3);
                }

                read2.close();

            }
            else if(choice==3){

                Scanner read3 =new Scanner(askFile3);
                while(read3.hasNext()){
                    String group= read3.nextLine();
                    System.out.println(group);
                }

                read3.close();

            }
            else{
                System.out.println("Opsss!!! :( :(. Invalid input,try again!!");
            }

            System.out.println("\nWant to continue asking?Then press -> 'a' : " +"\nOR"+
                    "\nFinished asking?Then press--> Any Key");

            askMoree =input.next().charAt(0);
        }while(askMoree=='a');



    }


    //MP3 PLAYER
    @FXML
    private Button prev2;
    @FXML
    private Button play2;
    @FXML
    private Button next2;
    @FXML
    private Button openfile2;
    @FXML
    private Button close4;
    @FXML
    private Button pause2;
    @FXML
    private Button slow2;
    @FXML
    private Button stop2;
    @FXML
    private Button normal2;
    @FXML
    private Button fast2;
    @FXML
    private Slider volSlider2;
    @FXML
    private Button vol2;
    @FXML
    private Button mute2;
    @FXML
    private Slider slider2;
    @FXML
    private Button minimize2;
    @FXML
    private Button ask2;
    @FXML
    private Button list2;
    @FXML
    private Tooltip tstop2;
    @FXML
    private Tooltip tmin2;
    @FXML
    private Tooltip tplay2;
    @FXML
    private Tooltip tfront2;
    @FXML
    private Tooltip tclose4;
    @FXML
    private Tooltip tnotmute2;
    @FXML
    private Tooltip topen2;
    @FXML
    private Tooltip tback2;
    @FXML
    private Tooltip tmute2;
    @FXML
    private Tooltip tnormal2;
    @FXML
    private Tooltip tslow2;
    @FXML
    private Tooltip tfilelist2;
    @FXML
    private Tooltip tfast2;
    @FXML
    private Tooltip task2;
    @FXML
    private Tooltip tpause2;


    @FXML
    void selectingFile2(ActionEvent event) {

        try{
            FileChooser fileChooser= new FileChooser();
            FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("select a File(*.mp3/*.wav)","*.mp3","*.wav");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(null);
            filepath = file.toURI().toString();

            openfile2.setTooltip(topen2);

            if(filepath!=null){

                Media media= new Media(filepath);
                mediaPlayer= new MediaPlayer(media);

                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue2, Duration newValue2) {

                        slider2.setValue(newValue2.toMinutes());

                    }
                });
                slider2.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mediaPlayer.seek(Duration.minutes(slider2.getValue()));

                    }
                });
                slider2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mediaPlayer.seek(Duration.minutes(slider2.getValue()));

                    }
                });
                mediaPlayer.setOnReady(new Runnable() {
                    @Override
                    public void run() {

                        Duration totalTime= media.getDuration();
                        slider2.setMax(totalTime.toMinutes());

                    }
                });
                volSlider2.setValue(mediaPlayer.getVolume()*100);
                volSlider2.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        mediaPlayer.setVolume(volSlider2.getValue()/100);
                    }
                });

                mediaPlayer.play();
            }

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            System.out.println("You did not select any file.That's why the ans is NULL. Please select a file");

        }



    }

    @FXML
    void play2(ActionEvent event) {


        play2.setTooltip(tplay2);
        mediaPlayer.play();
        mediaPlayer.setRate(1);

    }



    @FXML
    void pause2(ActionEvent event) {
        pause2.setTooltip(tpause2);
        mediaPlayer.pause();



    }

    @FXML
    void stop2(ActionEvent event) {
        stop2.setTooltip(tstop2);
        mediaPlayer.stop();


    }

    @FXML
    void notMute2(ActionEvent event) {
        vol2.setTooltip(tnotmute2);
        mediaPlayer.setMute(false);

    }

    @FXML
    void mute2(ActionEvent event) {
        mute2.setTooltip(tmute2);
        mediaPlayer.setMute(true);

    }

    @FXML
    void normalspeed2(ActionEvent event) {
        normal2.setTooltip(tnormal2);
        mediaPlayer.setRate(1);

    }
    @FXML
    void slow2(ActionEvent event) {
        slow2.setTooltip(tslow2);
        mediaPlayer.setRate(0.75);



    }

    @FXML
    void fast2(ActionEvent event) {
        fast2.setTooltip(tfast2);
        mediaPlayer.setRate(1.5);

    }
    @FXML
    void forward10sec2(ActionEvent event) {
        next2.setTooltip(tfront2);
        double d= mediaPlayer.getCurrentTime().toSeconds();
        d=d+10;
        mediaPlayer.seek(new Duration(d*1000));

    }
    @FXML
    void back10sec2(ActionEvent event) {
        prev2.setTooltip(tback2);
        double d= mediaPlayer.getCurrentTime().toSeconds();
        d=d-10;
        mediaPlayer.seek(new Duration(d*1000));

    }

    @FXML
    void minimize2(MouseEvent event) {
        minimize2.setTooltip(tmin2);
        Main.stage3.setIconified(true);

    }

    @FXML
    void close4(ActionEvent event) {
        close4.setTooltip(tclose4);
        Main.stage3.close();

    }

    @FXML
    void file(ActionEvent event) {

        list2.setTooltip(tfilelist2);


        Filelist list = new call();

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lname = name.toLowerCase();
                return lname.endsWith(".mp3");
            }
        };

         File mediafile[]= list.getfile().listFiles(filenameFilter);
        for (File a :mediafile) {
            mlist.add(a.toURI().toString());
            System.out.println(a);


        }
    }
    @FXML
    void askUs2(ActionEvent event) throws FileNotFoundException{
        ask2.setTooltip(task2);
        char askMore;
        do{
            File askFile= new File("H:/Intellij projects/new1/src/sample/AboutTheMp4Player.txt");
            File askFile2= new File("H:/Intellij projects/new1/src/sample/AboutTheMp3Player.txt");
            File askFile3= new File("H:/Intellij projects/new1/src/sample/AboutTheGroup.txt");
            Scanner input= new Scanner(System.in);
            System.out.println("Please choose from the options--->");
            System.out.println("1.About the MP4 Player.");
            System.out.println("2.About the MP3 Player.");
            System.out.println("3.About the Group.");
            System.out.println("Enter Your Choice Here: ");
            int choice = input.nextInt();
            if (choice==1){
                Scanner read1 =new Scanner(askFile);
                while(read1.hasNext()){
                    String mp4= read1.nextLine();
                    System.out.println(mp4);
                }

                read1.close();



            }
            else if(choice==2){

                Scanner read2 =new Scanner(askFile2);
                while(read2.hasNext()){
                    String mp3= read2.nextLine();
                    System.out.println(mp3);
                }

                read2.close();


            }
            else if(choice==3){

                Scanner read3 =new Scanner(askFile3);
                while(read3.hasNext()){
                    String group= read3.nextLine();
                    System.out.println(group);
                }

                read3.close();


            }
            else{
                System.out.println("Opsss!!! :( :(. Invalid input,try again!!");

            }
            System.out.println("\nWant to continue asking?Then press -> 'a' : " +"\nOR"+
                    "\nFinished asking?Then press--> Any Key");

             askMore =input.next().charAt(0);
        }while(askMore=='a');



    }




@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {






    }
}
