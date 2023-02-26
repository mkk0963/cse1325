import java.util.ArrayList;

public class Quiz
{
    private ArrayList<Question> questions;

    public Quiz()
    {
        loadQuiz();
    }

    public double takeQuiz()
    {

    }

    private void loadQuiz()
    {

        ArrayList<Question> questions = new ArrayList<Question>(2);
        questions.add(new Question("The capital of Texas is", , 1));
    }

}