import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Main.calc(sc.nextLine()));
    }


    private static String calc(String input) {

//        if (!isCorrectInput(input)) {
//            throw new IllegalArgumentException("Некорректный ввод. " +
//                    "\nЗначения могут быть арабской системы счисления в диапазоне 0-10 " +
//                    "\nили римской системы счисления в диапазоне I-X");
//        }
        char[] inputToCharArr = input.toCharArray();
        int value1 = 0;
        int value2 = 0;
        char operation = ' ';
        int rez = 0;


        for (char c : inputToCharArr) {
            if (c == '+') {
                operation = '+';
            }
            if (c == '-') {
                operation = '-';
            }
            if (c == '*') {
                operation = '*';
            }
            if (c == '/') {
                operation = '/';
            }
        }

        /* (isRomaValue(parts[0]) && isRomaValue(parts[1])) {
            value1 = romanToInt(parts[0]);
            value2 = romanToInt(parts[1]);
            rez = operation(value1, value2, operation);
            if (rez < 0) {
                throw new IllegalArgumentException("Результат операции ниже нуля.");
            }
            return intToRoman(rez);  */

        //(?<=\\D)(?=\\d)"
        //[+-/*]
        String[] parts = input.replaceAll(" ", "").toUpperCase().split("[^\\d\\sA-Z]");
        if (parts.length > 2) {
            throw new IllegalArgumentException("Проверьте корректность данных. " +
                    "Возможно указана неверная операция над значениями");
        }


        if (isRomaValue(parts[0]) && isRomaValue(parts[1])) {
            value1 = romanToInt(parts[0]);
            value2 = romanToInt(parts[1]);
            rez = operation(value1, value2, operation);
            if (rez < 0) {
                throw new IllegalArgumentException("Результат операции ниже нуля.");
            }
            return intToRoman(rez);
        } else if (isInteger(parts[0]) && isInteger(parts[1])) {
            value1 = Integer.parseInt(parts[0]);
            value2 = Integer.parseInt(parts[1]);
            return String.valueOf(rez = operation(value1, value2, operation));
        } else {
            throw new IllegalArgumentException("Операции допустимы только " +
                    "со значениями одинаковой системой счисления и в диапазоне 0-10 либо I-X");
        }
    }


    private static final int[] arabic = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

//    private static boolean isCorrectInput(String input) {
//        String[] partsOfInput = input.split("[+-/*]");
//        if (isRomaValue(partsOfInput[0]) || (isRomaValue(partsOfInput[1]))) {
//            return true;
//        } else if (isInteger(partsOfInput[0])  || isInteger(partsOfInput[1])) {
//            boolean p1 = Integer.parseInt(partsOfInput[0]) > 0;
//            boolean p2 = Integer.parseInt(partsOfInput[1]) <= 10;
//        }
//        return false;
//    }


//    private static boolean isInteger(Object partOfInput) {
//        return partOfInput instanceof Integer;
//    }
    private static boolean isInteger(String partOfInput) {
        try {
            int isInt = Integer.parseInt(partOfInput);
            if (isInt < 0 || isInt >= 10) {
                throw new IllegalArgumentException("Значение '" + partOfInput + "' вне диапазона 0-10");
            }
            return true;
        }
         catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
            return false;
    }

//    private static boolean isRomaValueInRange(String romaVal) {
//        String[] romaNumRange = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
//        for (String num : romaNumRange) {
//            if (romaVal.equals(num)) {
//
//            }
//        }
//    }


    private static boolean isRomaValue(String partOfInput) {
        //TODO: сделать красиво, придумать как кинуть исключение если римское число вне диапазона
        String[] romaNumRange = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String keys : romaNumRange) {
            if (partOfInput.equals(keys)) {
                return true;
            }
        } return false;

    }


    private static int operation(int value1, int value2, char operation) {
        return switch (operation) {
            case '+' -> value1 + value2;
            case '-' -> value1 - value2;
            case '*' -> value1 * value2;
            case '/' -> value1 % value2;
            default -> throw new IllegalArgumentException("Неверный знак операции над числами");
        };
    }

    private static int romanToInt(String romanNum) {
        int result = 0;
        for (int i = 0; i < arabic.length; i++ ) {
            while (romanNum.indexOf(roman[i]) == 0) {
                result += arabic[i];
                romanNum = romanNum.substring(roman[i].length());
            }
        }
        return result;
    }

    private static String intToRoman(int value) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < roman.length; i++) {
            while (value >= arabic[i]) {
                sb.append(roman[i]);
                value -= arabic[i];
            }
        }
        return sb.toString();
    }
}

