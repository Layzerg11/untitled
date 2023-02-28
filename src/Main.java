import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
     public static String name;
     public static char plus = '+';
     public static char minus = '-';
     public static char multiply = '*';
     public static char divide = '/';
     public static String action;
     public static String oneValue;
     public static String twoValue;
     public static int endRezult;
     public static String numberSystemOne = "Arabic";
     public static String numberSystemTwo = "Arabic";
    public static String endRomRezult;
    public static void main(String[] args) throws ScannerException {

        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        calc(name);

    }

    public static String calc(String input) throws ScannerException{

        int arabic[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,
                41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
                61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,
                81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};
        String roman[] = {"I","II","III","IV","V","VI","VII","VIII","IX","X",
                "XI","XII","XIII","IXV","XV","XVI","XVII","XVIII","XIX","XX",
                "XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX",
                "XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL",
                "XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L",
                "LI","LII","LIII","LIV","LV","VLI","LVII","LVIII","LIX","LX",
                "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
                "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX",
                "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
                "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"};

        name = name.replaceAll("\\s", "");

        for(int i=0; i<name.length(); i++){
            if(name.charAt(i) == plus){
                action = "\\+";
                break;
            } else if (name.charAt(i) == minus) {
                action = "-";
                break;
            }else if (name.charAt(i) == multiply) {
                action = "\\*";
                break;
            }else if (name.charAt(i) == divide) {
                action = "/";
                break;
            }
        }

        if(action == null){
            System.out.println("Отсутствует арифметический знак");
            throw new ScannerException();
        }
           String parts[] = name.split(action);
        if (parts.length > 2){
            System.out.println("Вы ввели больше двух переменных");
            throw new ScannerException();
        }
        if (parts.length < 2){
            System.out.println("Строка не является математической операцией");
            throw new ScannerException();
        }
        if (parts[0].equals("0") || parts[1].equals("0")){
            System.out.println("Введённые числа должны быть от 1 до 10 включительно");
            throw new ScannerException();
        }

        int result[] = new int[2];

        for(int i=0; i<10; i++){
            try {
                oneValue = parts[0];
                twoValue = parts[1];
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Вы ввели некорректные данные");
                throw new ScannerException();
            }
            if(oneValue.equals(roman[i]))
            {
                RomanNumeral romanNumeral = RomanNumeral.valueOf(parts[0]); // Перевод в стринг
                parts[0] = romanNumeral.getConvert();
                numberSystemOne = "Roman";
            }
            if(twoValue.equals(roman[i]))
            {
                RomanNumeral romanNumeral = RomanNumeral.valueOf(parts[1]); // Перевод в стринг
                parts[1] = romanNumeral.getConvert();
                numberSystemTwo = "Roman";
            }
        }

        if(!numberSystemOne.equals(numberSystemTwo)){
            System.out.println("Введённые числа должны быть в одноЙ системе исчисления");
            throw new ScannerException();
        }

        for (int i=0; i<2; i++) {
            try {
                result[i] = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели некорректные данные");
                throw new ScannerException();
            }
            if(result[i] > 10){
                System.out.println("Вы ввели число > 10");
                throw new ScannerException();
            }
        }

        if (action == "\\+"){
            if (numberSystemOne.equals("Roman")) {
                endRezult = result[0] + result[1];
                for (int i = 0; i < arabic.length; i++) {
                    if (endRezult == arabic[i]) {
                        endRomRezult = roman[i];
                        System.out.println(endRomRezult);
                        break;
                    }
                }
            }else System.out.println(result[0] + result[1]);

        }else if (action == "-") {
            if (numberSystemOne.equals("Roman")) {
                endRezult = result[0] - result[1];
                if(endRezult<1){
                    System.out.println("Результат меньше 1");
                    throw new ScannerException();
                }
                for (int i = 0; i < arabic.length; i++) {
                    if (endRezult == arabic[i]) {
                        endRomRezult = roman[i];
                        System.out.println(endRomRezult);
                        break;
                    }
                }
            }else System.out.println(result[0] - result[1]);

        }else if (action == "\\*") {
            if (numberSystemOne.equals("Roman")) {
                endRezult = result[0] * result[1];
                for (int i = 0; i < arabic.length; i++) {
                    if (endRezult == arabic[i]) {
                        endRomRezult = roman[i];
                        System.out.println(endRomRezult);
                        break;
                    }
                }
            }else System.out.println(result[0] * result[1]);

        }else if (action == "/") {
            if (numberSystemOne.equals("Roman")) {
                endRezult = result[0] / result[1];
                for (int i = 0; i < arabic.length; i++) {
                    if (endRezult == arabic[i]) {
                        endRomRezult = roman[i];
                        System.out.println(endRomRezult);
                        break;
                    }
                }
            }else System.out.println(result[0] / result[1]);
        }
        return endRomRezult;
    }

}




