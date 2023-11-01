import java.util.ArrayList;
import java.util.Collections;

public class Sentence {
    private ArrayList<TextElement> sentence;

    public Sentence(ArrayList<TextElement> text) {
        this.sentence = text;
    }

    public Sentence(TextElement[] textElements){
        this.sentence = new ArrayList<>();

        Collections.addAll(this.sentence, textElements);
    }

    @Override
    public String toString() {
        return sentence.toString();
    }

    public ArrayList<TextElement> getSentence() {
        return sentence;
    }

    public void setSentence(ArrayList<TextElement> sentence) {
        this.sentence = sentence;
    }
}
