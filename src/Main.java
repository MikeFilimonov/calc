import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {

            String description = "Input the equation:";
            System.out.println(description);
            Scanner inputHandler = new Scanner(System.in);
            String userInput = inputHandler.nextLine();

            try {
                System.out.println(calc(userInput));
            } catch (Exception e) {
                System.out.println(String.format("Failed to calculate due to %s", e.toString()));
                break;
            }
        }

    }

    public static String calc(String input) throws Exception {

        String result = "";
        boolean operandsAreRoman = false;
        ArrayList inputData = new ArrayList();

        try {
            inputData = checkInput(input);
            operandsAreRoman = operandAreRoman(input);
        } catch (Exception e) {
            e.printStackTrace();
        }


        result = evaluateEquation(inputData, operandsAreRoman);


        return result;
    }

    private static boolean operandAreRoman(String input) {

        String[] splitData = input.split("[-+/*]");
        return isValidRomanNumber(splitData[0].trim()) && isValidRomanNumber(splitData[1].trim());


    }

    public static ArrayList checkInput(String userInput) throws Exception {


        String[] splitData = userInput.split("[-+/*]");
        String[] operations = {"+", "-", "*", "/"};

        int operatorPosition = 0;
        for (int i = 0; i < operations.length; i++) {

            if (userInput.indexOf(operations[i]) > 0) {
                operatorPosition = userInput.indexOf(operations[i]);
                break;
            }
        }

        if (operatorPosition == 0) {
            throw new Exception("Equation should contain operator between operands");
        }

        if (splitData.length != 2) {
            throw new Exception("The equation is wrong. It should be like Op0 operator Op1.");
        } else {

            String operand1 = splitData[0].trim();
            String operator = userInput.substring(operatorPosition, operatorPosition + 1);
            String operand2 = splitData[1].trim();

            if (!isValidOperator(operator)) {
                throw new Exception("The operator is faulty");
            }

            boolean operandAreValid = (isValidRomanNumber(operand1) && isValidRomanNumber(operand2)) ||
                    (isValidArabicNumber(operand1) && isValidArabicNumber(operand2));
            if (!operandAreValid) {
                throw new Exception("Check the operands up, they should be either both Arabic or Roman from 1 to 10");
            }

            if (isValidRomanNumber(operand1)) {
                operand1 = convertRomanToArabic(operand1);
                operand2 = convertRomanToArabic(operand2);
            }


            ArrayList<Object> result = new ArrayList<Object>();

            result.add(Integer.parseInt(operand1));
            result.add(operator);
            result.add(Integer.parseInt(operand2));
            return result;

        }

    }

    private static String convertRomanToArabic(String roman) {

        String result = "";
        roman.trim();

        switch (roman) {

            case "I":
                result = "1";
                break;
            case "II":
                result = "2";
                break;
            case "III":
                result = "3";
                break;
            case "IV":
                result = "4";
                break;
            case "V":
                result = "5";
                break;
            case "VI":
                result = "6";
                break;
            case "VII":
                result = "7";
                break;
            case "VIII":
                result = "8";
                break;
            case "IX":
                result = "9";
                break;
            case "X":
                result = "10";
                break;

        }
        ;

        return result;

    }

    public static boolean isValidArabicNumber(String input) {

        boolean result = false;
        try {
            int candidate = Integer.parseInt(input);
            result = (candidate > 0 && candidate <= 10);
        } catch (NumberFormatException e) {
            result = false;
        }

        return result;
    }

    public static boolean isValidRomanNumber(String input) {

        boolean result = false;
        input.toUpperCase();

        int arabicNumber = 0;

        switch (input) {
            case "I":
                arabicNumber = 1;
                break;
            case "II":
                arabicNumber = 2;
                break;
            case "III":
                arabicNumber = 3;
                break;
            case "IV":
                arabicNumber = 4;
                break;
            case "V":
                arabicNumber = 5;
                break;
            case "VI":
                arabicNumber = 6;
                break;
            case "VII":
                arabicNumber = 7;
                break;
            case "VIII":
                arabicNumber = 8;
                break;
            case "IX":
                arabicNumber = 9;
                break;
            case "X":
                arabicNumber = 10;
                break;
            default:
        }
        ;

        if (arabicNumber > 0) {
            result = isValidArabicNumber(String.valueOf(arabicNumber));
        }
        ;

        return result;
    }

    public static boolean isValidOperator(String input) {

        boolean result = false;

        switch (input) {

            case "+":
            case "-":
            case "*":
            case "/":
                result = true;

        }
        ;

        return result;
    }

    public static String evaluateEquation(ArrayList equationParts, boolean operandsAreRoman) throws Exception {

        String output = "";

        String operator = equationParts.get(1).toString();
        int op1 = (int) equationParts.get(0);
        int op2 = (int) equationParts.get(2);

        int result = 0;

        switch (operator) {
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                if (op2 == 0) {
                    throw new ArithmeticException();
                } else {
                    result = op1 / op2;
                }

        }
        ;

        if (operandsAreRoman) {

            if (result <= 0) {
                throw new Exception("The result is beyond the range for Roman figures");
            } else {
                output = romanizeInteger(result);
            }
        } else {

            output = String.valueOf(result);

        }
        return output;
    }

    private static String romanizeInteger(int numericalValue) {

        String result = "";

        if (numericalValue < 0 || 4000 <= numericalValue) {
            throw new IllegalArgumentException();
        }
        String[] nums = {"I", "V", "X", "L", "C", "D", "M"};
        int numCounter = 0;

        StringBuilder partialResult = new StringBuilder();
        int numeral = 0;

        String stringNumber = String.valueOf(numericalValue);

        for (int i = stringNumber.length() - 1; i >= 0; i--) {

            partialResult.delete(0, partialResult.length());
            numeral = Integer.parseInt(stringNumber.substring(i, i + 1));

            if (1 <= numeral && numeral < 4) {

                for (int j = 0; j < numeral; j++) {
                    partialResult.append(nums[numCounter]);
                }
                numCounter += 2;

            } else if (4 <= numeral && numeral < 9) {

                numCounter += 2;

                if (numeral == 4) {
                    partialResult.append(nums[numCounter - 2]);
                }
                partialResult.append(nums[numCounter - 1]);

                for (int j = 0; j < (numeral - 5); j++) {
                    partialResult.append(nums[numCounter - 2]);
                }

            } else if (numeral == 9) {

                numCounter += 2;
                partialResult.append(nums[numCounter - 2] + nums[numCounter]);

            } else if (numeral == 0) {
                numCounter += 2;
            }

            result = partialResult.append(result).toString();


        }

        return result;
    }
}