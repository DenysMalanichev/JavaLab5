import java.util.ArrayList;

public class Text {
    private ArrayList<Sentence> text;

    public Text(ArrayList<Sentence> text) {
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

    private ArrayList<Sentence> parseString(String text) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        ArrayList<TextElement> currentSentence = new ArrayList<>();
        StringBuilder wordBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isWhitespace(currentChar)) {
                if (!wordBuilder.isEmpty()) {
                    currentSentence.add(new Word(wordBuilder.toString().toCharArray()));
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
                    currentSentence.add(new Word(wordBuilder.toString().toCharArray()));
                    wordBuilder.setLength(0);
                }

                currentSentence.add(new PunctuationMark(currentChar));
                if(currentChar == '.' || currentChar == '!' || currentChar == '?'){
                    sentences.add(new Sentence(currentSentence));
                    currentSentence = new ArrayList<>();
                }
            }
        }

        if (!wordBuilder.isEmpty()) {
            currentSentence.add(new Word(wordBuilder.toString().toCharArray()));
            sentences.add(new Sentence(currentSentence));
        }

        return sentences;
    }

    private static void removeNonQuestionSentences(Text text)
    {
        for(int i = 0; i < text.getText().size(); i++)
        {
            Sentence currentSentence = text.getText().get(i);
            if((currentSentence.getSentence().get(currentSentence.getSentence().size() - 1) instanceof Word)
                    || ((PunctuationMark)currentSentence.getSentence().get(currentSentence.getSentence().size() - 1)).getPunctuationMark() != '?')
            {
                text.getText().remove(i--);
            }
        }
    }

    private static void removeWordsOfNonGivenLengthAndPunctuation(Text text, int length)
    {
        for(int i = 0; i < text.getText().size(); i++)
        {
            Sentence currentSentence = text.getText().get(i);
            for(int j = 0; j < currentSentence.getSentence().size(); j++){
                TextElement el = currentSentence.getSentence().get(j);
                if(el instanceof Word && ((Word) el).getWord().size() != length
                        || el instanceof PunctuationMark)
                {
                    text.getText().get(i).getSentence().remove(j--);
                }
            }
        }
    }

    private static ArrayList<Word> findUniqueWords(Text text)
    {
        ArrayList<Word> distinctWords = new ArrayList<>();

        for(int i = 0; i < text.getText().size(); i++)
        {
            Sentence currentSentence = text.getText().get(i);
            for(int j = 0; j < currentSentence.getSentence().size(); j++) {
                Word word = (Word) currentSentence.getSentence().get(j);
                if (!containsInArray(distinctWords, word)) {
                    distinctWords.add(word);
                }
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

    public ArrayList<Sentence> getText() {
        return text;
    }

    public void setText(ArrayList<Sentence> text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text.toString();
    }
}
