package cr.ac.tec.proyecto1;

import cr.ac.tec.proyecto1.jsonFileHandling.JSONHandler;
import cr.ac.tec.proyecto1.linearStructures.SingleList;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class TestArray {


    public static void main(String[] args) {

        File audioFile = new File("C:\\Users\\Michael\\Documents\\TEC\\II Semestre 2020\\Algoritmos y Estructuras de Datos I\\Proyecto 1 - Monster TECG\\Proyecto-1-Monster-TECG\\src\\main\\resources\\gameSound.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        MediaPlayer audioPlayer = new MediaPlayer(audio);
        audioPlayer.setAutoPlay(true);


    }
}
