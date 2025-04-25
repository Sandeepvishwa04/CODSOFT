import java.util.*;

class Question {
    String question;
    String[] options;
    char correctAnswer;

    public Question(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = Character.toUpperCase(correctAnswer);
    }

    public boolean isCorrect(char userAnswer) {
        return Character.toUpperCase(userAnswer) == correctAnswer;
    }
}

public class QuizApp {
    private static final int TIME_LIMIT = 15; 
    private List<Question> questions;
    private Map<Integer, Boolean> results;
    private int score;

    public QuizApp() {
        questions = new ArrayList<>();
        results = new HashMap<>();
        score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("Which language runs in a web browser?", new String[]{"A. Java", "B. C", "C. Python", "D. JavaScript"}, 'D'));
        questions.add(new Question("What does HTML stand for?", new String[]{"A. Hyper Text Markup Language", "B. Home Tool Markup Language", "C. Hyperlinks and Text Markup Language", "D. High Text Machine Language"}, 'A'));
        questions.add(new Question("Who is the founder of Microsoft?", new String[]{"A. Steve Jobs", "B. Elon Musk", "C. Bill Gates", "D. Mark Zuckerberg"}, 'C'));
        questions.add(new Question("Which year was Java released?", new String[]{"A. 1991", "B. 1995", "C. 2000", "D. 1985"}, 'B'));
        questions.add(new Question("What is the capital of France?", new String[]{"A. Rome", "B. Paris", "C. Madrid", "D. Berlin"}, 'B'));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Welcome to the Java Quiz!");
        System.out.println(" You have " + TIME_LIMIT + " seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.question);
            for (String option : q.options) {
                System.out.println(option);
            }

            System.out.print("Your answer (A/B/C/D): ");
            long startTime = System.currentTimeMillis();

            String input = "";
            while (System.currentTimeMillis() - startTime < TIME_LIMIT * 1000 && input.isEmpty()) {
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine().trim().toUpperCase();
                }
            }

            if (input.isEmpty()) {
                System.out.println("⏰ Time's up! No answer given.");
                results.put(i, false);
            } else if (q.isCorrect(input.charAt(0))) {
                System.out.println("✅ Correct!");
                score++;
                results.put(i, true);
            } else {
                System.out.println("❌ Incorrect. Correct answer: " + q.correctAnswer);
                results.put(i, false);
            }

            System.out.println("---------------------------");
        }

        showResult();
        scanner.close();
    }

    private void showResult() {
        System.out.println("\n📊 Quiz Over!");
        System.out.println("Score: " + score + " / " + questions.size());

        System.out.println("\n📋 Summary:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + (results.get(i) ? "✅ Correct" : "❌ Incorrect (Ans: " + q.correctAnswer + ")"));
        }
    }

    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();
        quiz.startQuiz();
    }
}
