import java.util.ArrayList;

public class TextParser {
    public static Text findWordsOfGivenLengthInQuestionSentences(Text text, int length)
    {
        Text currentTextState = text;

        findQuestionSentences(text);

        findWordsOfGivenLength(text, length);

        findUniqueWords(text);

        return text;
    }

    private static void findQuestionSentences(Text text)
    {
        int start = 0;
        for(int i = 0; i < text.getText().size(); i++)
        {
            if(text.getText().get(i) instanceof PunctuationMark && (((PunctuationMark) text.getText().get(i)).getPunctuationMark() == '.'
                    || ((PunctuationMark) text.getText().get(i)).getPunctuationMark() == '!'))
            {
                text.getText().subList(start, i + 1).clear();
                i = start;
            }
            else if(text.getText().get(i) instanceof PunctuationMark && (((PunctuationMark) text.getText().get(i)).getPunctuationMark() == '?')){
                text.getText().remove(i);
            }
        }
    }

    private static void findWordsOfGivenLength(Text text, int length)
    {
        for(int i = 0; i < text.getText().size(); i++)
        {
            if(text.getText().get(i) instanceof Word && ((Word) text.getText().get(i)).getWord().size() != length
                || text.getText().get(i) instanceof PunctuationMark)
            {
                text.getText().remove(i);
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
}