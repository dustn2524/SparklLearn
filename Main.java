import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // 사용자 정보 변수
    private static String nickname;
    private static String major;
    private static int grade;
    private static int points = 0;

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

        int numberOfQuestions = 5; // 기본값

        int score = 0;

        for (int i = 1; i <= numberOfQuestions; i++) {
            System.out.println(i + "번 문제: (임시 문제)");
            System.out.println("1) 보기1  2) 보기2  3) 보기3  4) 보기4");
            System.out.println("정답 번호를 입력하세요:");

            int answer = scanner.nextInt();
            scanner.nextLine();

            if (answer == 1) {  // 임시 정답은 1번으로 고정
                System.out.println("정답!");
                score += 10;
            } else {
                System.out.println("오답!");
            }
        }

        return score;
    }

    private static void showResult(int score) {
        System.out.println("퀴즈 종료! 점수: " + score);
    }

    private static void showRanking() {
        // 임시 랭킹 출력
        System.out.println(nickname + "님의 현재 랭킹은 학과 3등 입니다! (임시)");
    }

    private static void giveReward(int score) {
        int earnedPoints = score / 10;
        points += earnedPoints;
        System.out.println(earnedPoints + " 포인트를 획득하였습니다! 현재 보유 포인트: " + points);
    }
}
