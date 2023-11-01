import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class PunctuationMark extends TextElement {
    private char punctuationMark;

    public PunctuationMark(char punctuationMark) {
        if(isPunctuationMark(punctuationMark)){
            this.punctuationMark = punctuationMark;
        }
        else{
            throw new InputMismatchException("WordCharacter cannot non-alphabetic symbol.");
        }
    }

    public char getPunctuationMark() {
        return punctuationMark;
    }

    public void setPunctuationMark(char punctuationMark) {
        if(isPunctuationMark(punctuationMark)){
            this.punctuationMark = punctuationMark;
        }
        else{
            throw new InputMismatchException("WordCharacter cannot non-alphabetic symbol.");
        }
    }

    private boolean isPunctuationMark(char ch){
        return ch == '!' || ch == ',' || ch == ';' || ch == '.' || ch == '?' || ch == '-' ||
                ch == '\'' || ch == '\"' || ch == ':';
    }

    @Override
    public String toString() {
        return punctuationMark+"";
    }
}
