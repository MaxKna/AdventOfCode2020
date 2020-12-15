package Day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Passport {

    public String ecl, pid, eyr, hcl, byr, iyr, cid, hgt;

    public boolean isValid1() {
        int numb = 0;
        numb += isNull(ecl);
        numb += isNull(pid);
        numb += isNull(eyr);
        numb += isNull(hcl);
        numb += isNull(byr);
        numb += isNull(iyr);
        numb += isNull(cid);
        numb += isNull(hgt);

        return (numb == 8 || (numb == 7 && cid == null));
    }

    public boolean isValid2() {
        try {
            //ECL
            if (!Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl)) {
                return false;
            }
            //PID
            if (pid.length() != 9) {
                return false;
            }
            //EYR
            if (eyr.length() != 4) {
                return false;
            }
            int expYear = Integer.parseInt(eyr);
            if (expYear > 2030 || expYear < 2020) {
                return false;
            }
            //HCL
            if (hcl.length() != 7 || hcl.charAt(0) != '#') {
                return false;
            }
            List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
            for (char c : hcl.substring(1).toCharArray()) {
                if (!chars.contains(c)) {
                    return false;
                }
            }
            //BYR
            int year = Integer.parseInt(byr);
            if (year < 1920 || year > 2002) {
                return false;
            }
            //IYR
            int issueYear = Integer.parseInt(iyr);
            if (issueYear < 2010 || issueYear > 2020) {
                return false;
            }


            //HGT
            String unit = hgt.substring(hgt.length() - 2);
            if (unit.equals("cm")) {
                if (hgt.length() != 5) {
                    return false;
                }
                int amount = Integer.parseInt(hgt.substring(0, 3));
                if (amount < 150 || amount > 193) {
                    return false;
                }
            } else if (unit.equals("in")) {
                if (hgt.length() != 4) {
                    return false;
                }
                int amount = Integer.parseInt(hgt.substring(0, 2));
                if (amount < 59 || amount > 76) {
                    return false;
                }
            }


            return true;
        } catch (NullPointerException | NumberFormatException npe) {
            return false;
        }
    }

    public int isNull(String s) {
        return s == null ? 0 : 1;
    }
}
