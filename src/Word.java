import java.util.ArrayList;
import java.util.List;

public class Word extends TextElement{
    private ArrayList<WordCharacter> word;

    public Word(ArrayList<WordCharacter> word) {
        this.word = word;
    }

    public Word() {
        this.word = new ArrayList<>();
    }

    public Word(WordCharacter[] chars){
        this.word = new ArrayList<>(List.of(chars));
    }

    public Word(char[] chars){
        this.word = new ArrayList<>();
        for (char ch : chars) {
            this.word.add(new WordCharacter(ch));
        }

    }

    public ArrayList<WordCharacter> getWord() {
        return word;
    }

    public void setWord(ArrayList<WordCharacter> word) {
        this.word = word;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (WordCharacter ch: this.word) {
            builder.append(ch.getCharacter());
        }

        return builder.toString();
    }
}
