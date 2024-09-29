package jump;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {
    private Clip clip;
    public Sound(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                this.clip = AudioSystem.getClip();
                clip.open(sound);

            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void stop() {
        clip.stop();
    }
    public void nextsong(int song) {
        // AudioInputStream sound = AudioSystem.getAudioInputStream("file.file");
            // load the sound into memory (a Clip)
        //    clip = AudioSystem.getClip();
        //    clip.open(sound);
    }

}