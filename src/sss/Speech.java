package sss;



import javax.sound.sampled.AudioFileFormat.Type;

import com.sun.speech.freetts.*;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

class Voice
{
    private String name;
    
    private com.sun.speech.freetts.Voice systemVoice;
    AudioPlayer audio;

    public Voice(String name)
    {
        this.name = name;        
        this.systemVoice = VoiceManager.getInstance().getVoice(this.name);
        this.systemVoice.allocate();
    }

    public void say(String[] thingsToSay)
    {
        for (int i = 0; i < thingsToSay.length; i++)
        {
            this.say( thingsToSay[i] );
        }
    }

    public void say(String thingToSay)
    {
        
        //audio = new SingleFileAudioPlayer("str",Type.WAVE);
        //this.systemVoice.setAudioPlayer(audio);
        this.systemVoice.speak(thingToSay);
        //audio.close();
        
    }
    
    public void saveAudio(String filename, String thingToSay){
    	audio = new SingleFileAudioPlayer(filename,Type.WAVE);
        this.systemVoice.setAudioPlayer(audio);
        this.systemVoice.speak(thingToSay);
        audio.close();
    }
    
   
    public void dispose()
    {
        this.systemVoice.deallocate();
    }
}