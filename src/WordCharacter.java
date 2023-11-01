import java.util.InputMismatchException;

public class WordCharacter {
    private char character;

    public WordCharacter(char character) {
        if(Character.isAlphabetic(character)){
            this.character = character;
        }
        else{
            throw new InputMismatchException("WordCharacter cannot non-alphabetic symbol.");
        }
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        if(Character.isAlphabetic(character)){
            this.character = character;
        }
        else{
            throw new InputMismatchException("WordCharacter cannot non-alphabetic symbol.");
        }
    }
}
