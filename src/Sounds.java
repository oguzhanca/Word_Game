import java.applet.Applet;

import java.applet.AudioClip;
import java.net.URL;

import javax.swing.JApplet;

/*
 * OÐUZHAN;		BU CLASS HAKKINDA PEK FÝKRÝM YOK ( yorumu yazan ali:D )
 */

public class Sounds extends JApplet {
	private AudioClip audioClip;
	public static String MusicName = "sounds/aaaaa.wav";
	URL a = getClass().getResource(MusicName);

	public void Sound(String MusicName, boolean SoundOn) {

		if (SoundOn) {

			this.MusicName = MusicName;
			a = getClass().getResource(MusicName);
			audioClip = Applet.newAudioClip(a);

			audioClip.play();
		}
	}

	public void start(boolean SoundOn) {
		if (audioClip != null && SoundOn)
			audioClip.loop();
	}

	public void stop() {
		if (audioClip != null)
			audioClip.stop();

	}
}
