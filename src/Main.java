import Lab2.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Country> countries = new LinkedList<>();
        String buff = "";

        try (FileReader in = new FileReader("C:\\Users\\ASUS\\Desktop\\7сем\\тп\\lab2.txt"))
        {
            int val;
            while ((val = in.read()) != -1)
            {
                if ((char)val == ',')
                {
                    continue;
                }
                buff += (char)val;
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        String[] line = buff.split("\n");
        for (int i = 0; i < line.length; i++)
        {
            String[] cell = line[i].split("\t");
            if (cell.length != 4)
            {
                continue;
            }

            String name = cell[0];
            int num = Integer.parseInt(cell[1]);
            float total = Float.parseFloat(cell[2]);
            float national = Float.parseFloat(cell[3]);

            Country country = new Country(name, num, total, national);
            countries.add(country);
        }

        Collections.sort(countries);

        int val1 = 0;
        float val2 = 0;
        ArrayList<Integer> totalNum = new ArrayList<>(countries.size());

        System.out.printf("%-25s%-20s%-15s%-15s%n", "Название", "Число иммигрантов", "Общий процент", "Процент от населения");
        for (Country country : countries)
        {
            String name = country.getName();
            int num = country.getNum();
            float total = country.getTotal();
            float national = country.getNational();
            System.out.printf("%-25s%-20s%-15s%-15s%n", name, num, total, national);

            val1 += num;
            val2 += total;
            totalNum.add((int)Math.round(num * 100.0 / national));
        }

        System.out.println();
        System.out.println("Общее количество иммигрантов: " + Integer.toString(val1));
        System.out.println("Общий процент иммигрантов: " + Float.toString(val2));
        System.out.println("Страна с самым большим процентом иммигрантов: " + countries.get(0).getName());
        System.out.println("Страна с самым маленьким процентом иммигрантов: " + countries.get(countries.size()-1).getName());
        System.out.println("Общая численность населения:");
        for (int i = 0; i < countries.size(); i++)
        {
            System.out.println(countries.get(i).getName() + ": " + Integer.toString(totalNum.get(i)));
        }
    }
}
