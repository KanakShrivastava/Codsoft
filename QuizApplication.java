import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String text;
    private List<String> options;
    private int correctOption;

    public Question(String text, List<String> options, int correctOption) {
        this.text = text;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private Timer timer;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getText());
            displayOptions(currentQuestion.getOptions());

            int userAnswer = getUserAnswer(currentQuestion.getOptions().size(), 10); // 10 seconds timer
            checkAnswer(currentQuestion, userAnswer);
        }

        displayResult();
    }

    private void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private int getUserAnswer(int numOptions, int timeLimit) {
        int userAnswer = -1;
        Scanner scanner = new Scanner(System.in);

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Time's up!");
                scanner.close();
                timer.cancel();
            }
        };

        timer = new Timer(true);
        timer.schedule(task, timeLimit * 1000);

        while (userAnswer == -1) {
            try {
                System.out.print("Enter your answer (1-" + numOptions + "): ");
                userAnswer = Integer.parseInt(scanner.nextLine());

                if (userAnswer < 1 || userAnswer > numOptions) {
                    System.out.println("Invalid input. Please enter a number between 1 and " + numOptions + ".");
                    userAnswer = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        timer.cancel();
        return userAnswer;
    }

    private void checkAnswer(Question question, int userAnswer) {
        if (userAnswer == question.getCorrectOption()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is option " + question.getCorrectOption());
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Light years is a unit of...", List.of("Light", "Time", "Distance", "intensity of light"), 3));
        questions.add(new Question("Which of the following is used in pencils?", List.of("Graphite", "Silicon", "Charcoal", "Phosphorus"), 1));
        questions.add(new Question("Plants receive their nutrients mainly from", List.of("Light", "Chlorophyll", "Atmosphere", "Soil"), 4));

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }

}
