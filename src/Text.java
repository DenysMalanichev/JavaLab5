import java.util.ArrayList;

public class Text {
    private ArrayList<TextElement> text;

    public Text(ArrayList<TextElement> text) {
        this.text = text;
    }

    public Text(String text){
        this.text = parseString(text);
    }

    public ArrayList<Word> getDistinctWords(int length){
        Text t = new Text(this.text);

        removeNonQuestionSentences(t);
        System.out.println(t);
        removeWordsOfNonGivenLengthAndPunctuation(t, length);

        return findUniqueWords(t);
    }

    private ArrayList<TextElement> parseString(String text) {
        ArrayList<TextElement> elements = new ArrayList<>();
        StringBuilder wordBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isWhitespace(currentChar)) {
                if (!wordBuilder.isEmpty()) {
                    elements.add(new Word(wordBuilder.toString().toCharArray()));
                    wordBuilder.setLength(0);
                }

                while (i + 1 < text.length() && Character.isWhitespace(text.charAt(i + 1))) {
                    i++;
                }
                continue;
            }

            if (Character.isAlphabetic(currentChar)) {
                wordBuilder.append(currentChar);
            } else {
                if (!wordBuilder.isEmpty()) {
                    elements.add(new Word(wordBuilder.toString().toCharArray()));
                    wordBuilder.setLength(0);
                }

                elements.add(new PunctuationMark(currentChar));
            }
        }

        if (!wordBuilder.isEmpty()) {
            elements.add(new Word(wordBuilder.toString().toCharArray()));
        }

        return elements;
    }

    private static void removeNonQuestionSentences(Text text)
    {
        int start = 0;
        for(int i = 0; i < text.getText().size(); i++)
        {
            TextElement element = text.getText().get(i);
            if(element instanceof PunctuationMark && (((PunctuationMark) element).getPunctuationMark() == '.'
                    || ((PunctuationMark) element).getPunctuationMark() == '!'))
            {
                text.getText().subList(start, i + 1).clear();
                i = start;
            }
            else if (element instanceof PunctuationMark && ((PunctuationMark) element).getPunctuationMark() == '?') {
                start = i;
            }
        }
    }

    private static void removeWordsOfNonGivenLengthAndPunctuation(Text text, int length)
    {
        for(int i = 0; i < text.getText().size(); i++)
        {
            if(text.getText().get(i) instanceof Word && ((Word) text.getText().get(i)).getWord().size() != length
                    || text.getText().get(i) instanceof PunctuationMark)
            {
                text.getText().remove(i--);
            }
        }
    }

    private static ArrayList<Word> findUniqueWords(Text text)
    {
        ArrayList<Word> distinctWords = new ArrayList<>();

        for(int i = 0; i < text.getText().size(); i++)
        {
            if(!containsInArray(distinctWords, ((Word) text.getText().get(i))))
            {
                distinctWords.add((Word)text.getText().get(i));
            }
        }

        return distinctWords;
    }

    private static boolean containsInArray(ArrayList<Word> array, Word word)
    {
        for (Word w : array) {
            if(isWordsEqual(w, word))
                return true;
        }

        return false;
    }

    private static boolean isWordsEqual(Word word1, Word word2)
    {
        if(word1.getWord().size() != word2.getWord().size())
        {
            return false;
        }

        for(int i = 0; i < word1.getWord().size(); i++)
        {
            if(word1.getWord().get(i).getCharacter() != word2.getWord().get(i).getCharacter())
            {
                return false;
            }
        }

        return true;
    }

    public ArrayList<TextElement> getText() {
        return text;
    }

    public void setText(ArrayList<TextElement> text) {
        this.text = text;
    }

    @Override
    public String toString() {
       /* StringBuilder builder = new StringBuilder();

        for (TextElement el: this.text) {
            builder.append(el);
        }

        return builder.toString();*/

        return this.text.toString();
    }
}
