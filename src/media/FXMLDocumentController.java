/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//This file has all the functionality
package media;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Surabhi Maheshwari
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    
    private Label label;
    private MediaPlayer mediaPlayer;
    private String filePath;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider seekSlider;
    
    @FXML
    private Slider slider;//object slider
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("Select a file(*.mp4)", "*.mp4"); //choosing a mp4 file
       fileChooser.getExtensionFilters().add(filter);//already added filter object and passing extension to filter object
       File file= fileChooser.showOpenDialog(null);//to choose a file  
       filePath= file.toURI().toString(); //added the string variable filePath to file class object so filePath is our media path
       if(filePath!=null){
       Media media= new Media(filePath); //import media     
        //adding media to mediaplayer
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);//set mediaview to mediaplayer 
           DoubleProperty width= mediaView.fitWidthProperty();
           DoubleProperty height= mediaView.fitHeightProperty();
           width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width")); //binds the video to screen size
           height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
           
           slider.setValue(mediaPlayer.getVolume()*100);
           slider.valueProperty().addListener(new InvalidationListener(){
            @Override
           public void invalidated(Observable observable) {
                mediaPlayer.setVolume(slider.getValue()/100);           }
          });
           
           mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
           @Override
           public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
           seekSlider.setValue(newValue.toSeconds());//moves slider  by seconds
           }            
           });
           seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
           {
           @Override
           public void handle(MouseEvent event) {
               mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
           }
           });
              
        
        mediaPlayer.play();//used to play the media
      
       }
    }
    @FXML 
    private void pauseVideo(ActionEvent event)
    {
        mediaPlayer.pause();
    }
    @FXML 
    private void playVideo(ActionEvent event)
    { mediaPlayer.play();
    mediaPlayer.setRate(1);
    }
    @FXML 
    private void stopVideo(ActionEvent event)
    {mediaPlayer.stop();
        }
    @FXML 
    private void fastVideo(ActionEvent event)
    {
        mediaPlayer.setRate(1.5);
    }
    @FXML 
    private void fasterVideo(ActionEvent event)
    {mediaPlayer.setRate(2);
    }
    @FXML 
    private void slowVideo(ActionEvent event)
    {
        mediaPlayer.setRate(.75);
    }
    @FXML 
    private void slowerVideo(ActionEvent event)
    {mediaPlayer.setRate(0.5);
    }
    @FXML 
    private void exitVideo(ActionEvent event)
    {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
