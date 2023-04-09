import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] your = new int[10][10];
        int[][] bot = new int[10][10];
        Random random = new Random();
        boolean b = false;
        System.out.println("Це гра морський бій, номерація полів від 0 до 9, якщо ваш корабель поцілили він відобраться цифрою 2. Цілі кораблі 1");
        do {
            b = fillTheShip(bot, 4, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 3, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 3, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 2, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 2, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 2, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 1, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 1, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 1, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        do {
            b = fillTheShip(bot, 1, random.nextBoolean(), random.nextInt(10), random.nextInt(10));
        } while (!b);
        init(your);
        startGame(your,bot);
        checkWinner(your);
    }

    private static void startGame(int[][] player, int[][] bot) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        while (!checkWin(player, bot)) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            while (player[y][x] == 2) {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            player[y][x] = 2;
            System.out.println("Введіть Х");
            x = scanner.nextInt();
            System.out.println("Введіть Y");
            y = scanner.nextInt();
            while (x > 9 || x <0 || y > 9 || y < 0){
                System.out.println("Недопустимі значення");
                System.out.println("Введіть Х");
                x = scanner.nextInt();
                System.out.println("Введіть Y");
                y = scanner.nextInt();
            }
            while (bot[y][x] == 2){
                System.out.println("Ви сюди вже стріляли");
                System.out.println("Введіть Х");
                x = scanner.nextInt();
                System.out.println("Введіть Y");
                y = scanner.nextInt();
            }
            if (bot[y][x] == 1){
                System.out.println("Є влучення");
            } else {
                System.out.println("Повз корабель");
            }
            bot[y][x] = 2;
            for (int i = 0; i < player.length; i++) {
                for (int j = 0; j < player[i].length; j++) {
                    System.out.print(player[i][j] + "  ");
                }
                System.out.println();
            }
        }

    }

    private static boolean checkWin(int[][] player, int[][] bot) {
        for (int i = 0; i < bot.length; i++)
            for (int j = 0; j < bot[i].length; j++)
                if (bot[i][j] == 1){
                    return false;
                }
        for (int i = 0; i < player.length; i++)
            for (int j = 0; j < player[i].length; j++)
                if (player[i][j] == 1){
                    return false;
                }
        return true;
    }
    private static void checkWinner(int[][] player) {
        for (int i = 0; i < player.length; i++)
            for (int j = 0; j < player[i].length; j++) {
                if (player[i][j] == 1){
                    System.out.println("Гравець переміг");
                    return;
                }
            }
        System.out.println("Бот переміг");

    }

    public static boolean fillTheShip(int[][] field, int shipLength, boolean verical, int x, int y) {
        if (verical) {
            if (x + shipLength > 9)
                return false;
            for (int i = (y - 1 < 0 ? y : y - 1); i <= (y + 1 > 9 ? y : y + 1); i++) {
                for (int j = (x - 1 < 0 ? x : x - 1); j <= (x + shipLength + 1 > 9 ? x + shipLength : x + shipLength + 1); j++) {
                    if (field[i][j] != 0)
                        return false;
                }
            }
            for (int i = x; i < x + shipLength; i++)
                field[y][i] = 1;
            return true;
        } else {
            if (y + shipLength > 9)
                return false;
            for (int i = (y - 1 < 0 ? y : y - 1); i <= (y + shipLength + 1 > 9 ? y + shipLength : y + 1 + shipLength); i++) {
                for (int j = (x - 1 < 0 ? x : x - 1); j <= (x + 1 > 9 ? x : x + 1); j++) {
                    if (field[i][j] != 0)
                        return false;
                }
            }
            for (int i = y; i < y + shipLength; i++)
                field[i][x] = 1;
            return true;
        }
    }

    public static void init(int[][] field) {
        boolean vertical;
        int x;
        int y;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть корабель на 4: ");
        System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
        if (sc.nextInt() == 1) vertical = true;
        else vertical = false;
        System.out.println("Введіть x: ");
        x = sc.nextInt();
        System.out.println("Введіть y: ");
        y = sc.nextInt();
        while (!fillTheShip(field, 4, vertical, x, y)) {
            System.out.println("Ваші дані невірні введіть ще раз");
            System.out.println("Введіть корабель на 4: ");
            System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
            if (sc.nextInt() == 1) vertical = true;
            else vertical = false;
            System.out.println("Введіть x: ");
            x = sc.nextInt();
            System.out.println("Введіть y: ");
            y = sc.nextInt();
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("Введіть корабель на 2: ");
            System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
            if (sc.nextInt() == 1) vertical = true;
            else vertical = false;
            System.out.println("Введіть x: ");
            x = sc.nextInt();
            System.out.println("Введіть y: ");
            y = sc.nextInt();
            while (!fillTheShip(field, 2, vertical, x, y)) {
                System.out.println("Ваші дані невірні введіть ще раз");
                System.out.println("Введіть корабель на 2: ");
                System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
                if (sc.nextInt() == 1) vertical = true;
                else vertical = false;
                System.out.println("Введіть x: ");
                x = sc.nextInt();
                System.out.println("Введіть y: ");
                y = sc.nextInt();
            }
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("Введіть корабель на 3: ");
            System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
            if (sc.nextInt() == 1) vertical = true;
            else vertical = false;
            System.out.println("Введіть x: ");
            x = sc.nextInt();
            System.out.println("Введіть y: ");
            y = sc.nextInt();
            while (!fillTheShip(field, 3, vertical, x, y)) {
                System.out.println("Ваші дані невірні введіть ще раз");
                System.out.println("Введіть корабель на 3: ");
                System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
                if (sc.nextInt() == 1) vertical = true;
                else vertical = false;
                System.out.println("Введіть x: ");
                x = sc.nextInt();
                System.out.println("Введіть y: ");
                y = sc.nextInt();
            }
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("Введіть корабель на 1: ");
            System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
            if (sc.nextInt() == 1) vertical = true;
            else vertical = false;
            System.out.println("Введіть x: ");
            x = sc.nextInt();
            System.out.println("Введіть y: ");
            y = sc.nextInt();
            while (!fillTheShip(field, 1, vertical, x, y)) {
                System.out.println("Ваші дані невірні введіть ще раз");
                System.out.println("Введіть корабель на 1: ");
                System.out.println("Якщо вертикальний введіть 1, якщо горизонтальний 2: ");
                if (sc.nextInt() == 1) vertical = true;
                else vertical = false;
                System.out.println("Введіть x: ");
                x = sc.nextInt();
                System.out.println("Введіть y: ");
                y = sc.nextInt();
            }
            for (int k = 0; k < field.length; k++) {
                for (int j = 0; j < field[k].length; j++) {
                    System.out.print(field[k][j] + "  ");
                }
                System.out.println();
            }
        }

    }
}