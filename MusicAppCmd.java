/* Matt Nicol
 * 16/11/18
 * MidiPlayer
 *  Eclipse v4.9.0
 */


package MidiPlayer;


import javax.sound.midi.*;

public class MusicAppCmd {

    public static void main(String[] args) {
        MusicAppCmd mini = new MusicAppCmd();
        if (args.length < 2) { //Checks if arguments have been passed into the main 'args' string

            System.out.println("Don't forget the instrument and note args");
        } else {

            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument, note);
//            System.out.println(args[0]); //Test to ensure command line argument pass was successful
//            System.out.println(args[1]); //Test to ensure command line argument pass was successful
        }
    }

    public void play(int instrument, int note) {

        try {

            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,instrument,0);
            MidiEvent changeInstrument = new MidiEvent(first,1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            a.setMessage(128,1,note,100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);
            player.setSequence(seq);
            player.start();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}