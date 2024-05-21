package gamecontrol.main;

//Essa é a classe responsavel por sons no jogo, lembrando que em java precisamos de arquivos .wav em 16b para funcionar
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
    

    public static boolean isPlaying = false;



    Clip clip;

    URL soundURL[] = new URL[30];

    public Sound (){

        soundURL[0] = getClass().getResource("/res/musicas/MUSICA_TEMA_DARWIN_QUEST.wav");
        soundURL[1] = getClass().getResource("/res/musicas/MUSIQUINHA_TRANQUILINHA.wav");
        soundURL[2] = getClass().getResource("/res/musicas/MUSIQUINHA_MORTE.wav");
        soundURL[3] = getClass().getResource("/res/musicas/MUSIQUINHA_AÇÃO.wav");
    }
    
    public void setFile(int i) {

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){

        }
    }

    public void play() {

        clip.start();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){

        clip.stop();
    }
    
}
