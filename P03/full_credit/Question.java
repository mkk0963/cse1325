import java.util.ArrayList;

public class Question
{
    private static int nextQuestionNumber = 1;
    private final int questionNumber;
    private String question;
    private ArrayList<String> answers;
    private int rightAnswer;

    public Question(String question, ArrayList<String> answers, int rightAnswer)
    {
        this.questionNumber = nextQuestionNumber++;
        this.question =  question;
        this.answers =  answers; // this.answers = new ArrayList<String>();
        this.rightAnswer = rightAnswer;

        if(rightAnswer < 1 || rightAnswer > questionNumber)
        {
            throw new IllegalArgumentException("A question must have at least one right answer but not more than the number of answers");
        }
    }

    public boolean checkAnswer(int answer)
    {
        if(answer == rightAnswer)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return questionNumber + "." + question + "%s\n" + answers;
    }
}