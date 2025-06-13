import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // 사용자 정보 변수
    private static String nickname;
    private static String major;
    private static int grade;
    private static int points = 0;

    // 문제 데이터 (문제, 보기들, 정답 인덱스)
    private static String[] questions = {
            "Java에서 클래스를 상속할 때 사용하는 키워드는?",
            "다음 중 정수형 자료형이 아닌 것은?",
            "for문에서 초기화, 조건식, 증감식을 구분하는 기호는?",
            "다음 중 Java에서 예외(Exception)를 처리하는 데 사용하는 키워드는?",
            "객체 지향 프로그래밍(OOP)의 4가지 특징이 아닌 것은?"
    };

    private static String[][] choices = {
            {"1) extends", "2) implements", "3) inherit", "4) override"},
            {"1) int", "2) float", "3) short", "4) long"},
            {"1) :", "2) ;", "3) ,", "4) ()"},
            {"1) try", "2) error", "3) catch", "4) finally"},
            {"1) 상속", "2) 추상화", "3) 다형성", "4) 포인터"}
    };

    private static int[] correctAnswers = {1, 2, 3, 1, 4}; // 각 문제의 정답 인덱스 (1부터 시작)

    public static void main(String[] args) {
        System.out.println("=== 스파크 런(Sparklearn)에 오신 걸 환영합니다! ===");

        login();

        String mode = selectQuizMode();
        String subject = selectSubject();
        String difficulty = selectDifficulty();

        int score = startQuiz(subject, difficulty);

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
        scanner.nextLine(); // 버퍼 비우기

        System.out.println(nickname + "님, 로그인 완료!");
    }

    private static String selectQuizMode() {
        System.out.println("퀴즈 모드를 선택하세요: 1) 개인전  2) 팀전");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
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

    private static int startQuiz(String subject, String difficulty) {
        System.out.println(subject + " (" + difficulty + ") 퀴즈를 시작합니다!");

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
                    System.out.println("⚠️ 1~4 사이 숫자만 입력해야 해요!");
                }

            } catch (TimeoutException e) {
                System.out.println("\n⏰ 시간 초과! 오답 처리됩니다.");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("⚠️ 입력 처리 중 오류가 발생했어요.");
            }

            executor.shutdown();

            if (answer == correctAnswers[i]) {
                System.out.println("✅ 정답!");
                score += 10;
            } else if (answer != -1) {
                System.out.println("❌ 오답! 정답은 " + correctAnswers[i] + "번이었어요.");
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
