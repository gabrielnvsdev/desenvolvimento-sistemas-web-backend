package com.example.desenvolvimento_web.validators;

public class CpfUtils {
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
            return false;

        try {
            int sum = 0, weight = 10;
            for (int i = 0; i < 9; i++)
                sum += Character.getNumericValue(cpf.charAt(i)) * weight--;

            int digit1 = 11 - (sum % 11);
            digit1 = (digit1 >= 10) ? 0 : digit1;

            sum = 0;
            weight = 11;
            for (int i = 0; i < 10; i++)
                sum += Character.getNumericValue(cpf.charAt(i)) * weight--;

            int digit2 = 11 - (sum % 11);
            digit2 = (digit2 >= 10) ? 0 : digit2;

            return digit1 == Character.getNumericValue(cpf.charAt(9))
                    && digit2 == Character.getNumericValue(cpf.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }
}
