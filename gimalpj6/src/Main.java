import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static String nickname;
    private static String major;
    private static int grade;
    private static int points = 0;

    // 쉬움 문제
    private static String[] easyQuestions = {
            "Java에서 클래스를 상속할 때 사용하는 키워드는?",
            "다음 중 정수형 자료형이 아닌 것은?",
            "for문에서 초기화, 조건식, 증감식을 구분하는 기호는?"
    };
    private static String[][] easyChoices = {
            {"1) extends", "2) implements", "3) inherit", "4) override"},
            {"1) int", "2) float", "3) short", "4) long"},
            {"1) :", "2) ;", "3) ,", "4) ()"}
    };
    private static int[] easyAnswers = {1, 2, 2};
    private static String[] easyExplanations = {
            "Java에서는 상속을 할 때 'extends' 키워드를 사용해요.",
            "'float'는 실수형 자료형이고, 정수형이 아니에요.",
            "';' 세미콜론은 for문의 각 부분을 구분하는 기호입니다."
    };

    // 보통 문제
    private static String[] normalQuestions = {
            "다음 중 Java에서 예외(Exception)를 처리하는 데 사용하는 키워드는?",
            "객체 지향 프로그래밍(OOP)의 4가지 특징이 아닌 것은?",
            "Java에서 인터페이스를 구현할 때 사용하는 키워드는?"
    };
    private static String[][] normalChoices = {
            {"1) try", "2) error", "3) catch", "4) finally"},
            {"1) 상속", "2) 추상화", "3) 다형성", "4) 포인터"},
            {"1) implements", "2) extends", "3) inherits", "4) overrides"}
    };
    private static int[] normalAnswers = {1, 4, 1};
    private static String[] normalExplanations = {
            "'try'는 예외를 처리하는 블록을 시작할 때 사용합니다.",
            "'포인터'는 Java의 객체 지향 특징 중 하나가 아닙니다.",
            "인터페이스는 'implements' 키워드로 구현합니다."
    };

    // 어려움 문제
    private static String[] hardQuestions = {
            "Java에서 제네릭(Generic)을 사용할 때 타입 매개변수를 지정하는 기호는?",
            "다음 중 Java에서 쓰레드 생성을 위해 구현하는 인터페이스는?",
            "Java의 가비지 컬렉션(Garbage Collection) 동작을 명시적으로 요청하는 메서드는?"
    };
    private static String[][] hardChoices = {
            {"1) <>", "2) []", "3) {}", "4) ()"},
            {"1) Runnable", "2) Serializable", "3) Comparable", "4) Cloneable"},
            {"1) System.gc()", "2) Runtime.run()", "3) Garbage.collect()", "4) Memory.clean()"}
    };
    private static int[] hardAnswers = {1, 1, 1};
    private static String[] hardExplanations = {
            "제네릭 타입 매개변수는 꺾쇠괄호 '<>'를 사용해 지정합니다.",
            "쓰레드 생성은 Runnable 인터페이스를 구현해서 합니다.",
            "가비지 컬렉션은 System.gc() 메서드 호출로 명시적으로 요청할 수 있습니다."
    };

    public static void main(String[] args) {
        System.out.println("=== 스파크 런(Sparklearn)에 오신 걸 환영합니다! ===");

        login();

        String mode = selectQuizMode();
        String subject = selectSubject();
        String difficulty = selectDifficulty();

        int score = startQuiz(difficulty);

        showResult(score);
        showRanking();
        giveReward(score);

        System.out.println("플랫폼 이용해 주셔서 감사합니다!");
    }

    private static void login() {
        System.out.println("닉네임을 입력하세요:");
        nickname = scanner.nextLine();

        System.out.println("학과를 입력하세요:");
        major = scanner.nextLine();

        System.out.println("학년을 숫자로 입력하세요:");
        grade = scanner.nextInt();
        scanner.nextLine();

        System.out.println(nickname + "님, 로그인 완료!");
    }

    private static String selectQuizMode() {
        System.out.println("퀴즈 모드를 선택하세요: 1) 개인전  2) 팀전");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return (choice == 1) ? "개인전" : "팀전";
    }

    private static String selectSubject() {
        System.out.println("과목을 선택하세요: 1) 전공과목  2) 교양과목");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return (choice == 1) ? "전공과목" : "교양과목";
    }

    private static String selectDifficulty() {
        System.out.println("난이도를 선택하세요: 1) 쉬움  2) 보통  3) 어려움");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: return "쉬움";
            case 2: return "보통";
            case 3: return "어려움";
            default: return "보통";
        }
    }

    private static int startQuiz(String difficulty) {
        String[] questions;
        String[][] choices;
        int[] answers;
        String[] explanations;

        switch (difficulty) {
            case "쉬움":
                questions = easyQuestions;
                choices = easyChoices;
                answers = easyAnswers;
                explanations = easyExplanations;
                break;
            case "보통":
                questions = normalQuestions;
                choices = normalChoices;
                answers = normalAnswers;
                explanations = normalExplanations;
                break;
            case "어려움":
                questions = hardQuestions;
                choices = hardChoices;
                answers = hardAnswers;
                explanations = hardExplanations;
                break;
            default:
                questions = normalQuestions;
                choices = normalChoices;
                answers = normalAnswers;
                explanations = normalExplanations;
        }

        System.out.println(difficulty + " 난이도 퀴즈를 시작합니다!");

        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\n문제 " + (i + 1) + ": " + questions[i]);
            for (String choice : choices[i]) {
                System.out.println(choice);
            }
            System.out.print("정답 번호를 입력하세요 (제한시간 10초): ");

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(() -> scanner.nextLine());

            int answer = -1;

            try {
                String input = future.get(10, TimeUnit.SECONDS).trim();

                if (input.matches("[1-4]")) {
                    answer = Integer.parseInt(input);
                } else {
                    System.out.println("⚠️ 1~4 사이 숫자만 입력해야 해요! 이 문제는 오답 처리됩니다.");
                }

            } catch (TimeoutException e) {
                System.out.println("\n⏰ 시간 초과! 오답 처리됩니다.");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("⚠️ 입력 처리 중 오류가 발생했어요. 오답 처리됩니다.");
            }

            executor.shutdown();

            if (answer == answers[i]) {
                System.out.println("✅ 정답!");
                score += 10;
            } else if (answer != -1) {
                System.out.println("❌ 오답! 정답은 " + answers[i] + "번입니다.");
                System.out.println("💡 해설: " + explanations[i]);
            }
        }

        return score;
    }

    private static void showResult(int score) {
        System.out.println("\n🎯 퀴즈 종료! 점수: " + score + "점");
    }

    private static void showRanking() {
        System.out.println("📊 " + nickname + "님의 현재 랭킹은 학과 3등입니다! (임시)");
    }

    private static void giveReward(int score) {
        int earnedPoints = score / 10;
        points += earnedPoints;
        System.out.println("🎁 " + earnedPoints + " 포인트를 획득하였습니다! 현재 보유 포인트: " + points);
    }
}
