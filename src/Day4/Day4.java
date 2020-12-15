package Day4;

import util.Day;
import util.Logger;

import java.util.List;

public class Day4 extends Day {

    public Day4(){
        super(4);
        this.registerTestPartOne("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929\n" +
                "\n" +
                "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm\n" +
                "\n" +
                "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in");
        this.registerTestPartTwo("eyr:1972 cid:100\n" +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
                "\n" +
                "iyr:2019\n" +
                "hcl:#602927 eyr:1967 hgt:170cm\n" +
                "ecl:grn pid:012533040 byr:1946\n" +
                "\n" +
                "hcl:dab227 iyr:2012\n" +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" +
                "\n" +
                "hgt:59cm ecl:zzz\n" +
                "eyr:2038 hcl:74454a iyr:2023\n" +
                "pid:3556412378 byr:2007");
        this.registerTestPartTwo("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" +
                "\n" +
                "hcl:#888785\n" +
                "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                "pid:545766238 ecl:hzl\n" +
                "eyr:2022\n" +
                "\n" +
                "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719");
    }

    @Override
    public void part1(List<String> inputs, List<Object> params) {
        inputs.add("");
        Passport pass = null;
        int validPorts = 0;
        int numberOfPorts = 0;
        int numberOfPortsChecked = 0;
        for(String line : inputs){
            if(line.length() == 0 && pass != null){
                if(pass.isValid1()){
                    validPorts++;
                }
                numberOfPortsChecked++;
                pass = null;
                continue;
            }
            if(pass == null){
                pass = new Passport();
                numberOfPorts++;
            }
            processPass(pass,line);
        }
        Logger.debug("Number of Ports checked: "+numberOfPortsChecked);
        Logger.result("Number of Ports:" + numberOfPorts + ", valid:" + validPorts);
    }

    private void processPass(Passport pass, String line){
        String[] comps = line.split(" +");
        for(String comp : comps){
            String[] parts = comp.split(":");
            switch (parts[0]){
                case "ecl":
                    pass.ecl = parts[1];
                    break;
                case "pid":
                    pass.pid = parts[1];
                    break;
                case "eyr":
                    pass.eyr = parts[1];
                    break;
                case "hcl":
                    pass.hcl = parts[1];
                    break;
                case "byr":
                    pass.byr = parts[1];
                    break;
                case "iyr":
                    pass.iyr = parts[1];
                    break;
                case "cid":
                    pass.cid = parts[1];
                    break;
                case "hgt":
                    pass.hgt = parts[1];
                    break;
            }
        }
    }

    @Override
    public void part2(List<String> inputs, List<Object> params) {
        inputs.add("");
        Passport pass = null;
        int validPorts = 0;
        int numberOfPorts = 0;
        int numberOfPortsChecked = 0;
        for(String line : inputs){
            if(line.length() == 0 && pass != null){
                if(pass.isValid2()){
                    validPorts++;
                }
                numberOfPortsChecked++;
                pass = null;
                continue;
            }
            if(pass == null){
                pass = new Passport();
                numberOfPorts++;
            }
            processPass(pass,line);
        }
        Logger.debug("Number of Ports checked: "+numberOfPortsChecked);
        Logger.result("Number of Ports:"+numberOfPorts+", valid:" +validPorts);
    }
}
