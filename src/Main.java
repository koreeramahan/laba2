import Lab2.*;

import java.io.FileReader; //для считывания из файла
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections; //для сортировки
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Country> countries = new LinkedList<>();
        String buff = "";
        //считываем информацию из текстового файла
        try (FileReader in = new FileReader("C:\\Users\\ASUS\\Desktop\\7сем\\тп\\lab2.txt"))
        {
            int r;
            while ((r = in.read())!=-1)
            {
                if ((char)r==',') continue;
                buff+=(char)r;
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        String[] line = buff.split("\n");
        //заполняем таблицу
        for (int i = 0; i < line.length; i++)
        {
            String[] str = line[i].split("\t");
            if (str.length != 4) //4 колонки для таблицы
            {
                continue;
            }
            String name = str[0]; //название страны
            int num = Integer.parseInt(str[1]); //число иммигрантов
            float total = Float.parseFloat(str[2]); //процент в мире
            float national = Float.parseFloat(str[3]); //процент от населения
            Country country = new Country(name, num, total, national);
            countries.add(country);
        }
        Collections.sort(countries); //отсортировать по кол-ву в процентах от населения
        int a = 0;
        float b = 0;
        ArrayList<Integer> totalNum = new ArrayList<>(countries.size());
        //выводим заголовки таблицы
        System.out.printf("%-22s%-12s%-15s%-15s%n", "Country", "Immigrants", "% world total", "% population");
        //считаем и выводим данные таблицы
        for (Country country : countries)
        {
            String name = country.getName(); //название страны
            int num = country.getNum(); //число иммигрантов
            float total = country.getTotal(); //процент в мире
            float national = country.getNational(); //процент от населения
            System.out.printf("%-22s%-12s%-15s%-15s%n",name,num,total,national); //выводим в таблицу
            a += num;
            b += total;
            totalNum.add((int)Math.round(num*100.0/national)); //считаем total population для последнего пункта
        }
        System.out.println();
        System.out.println("Total immigrants: " + Integer.toString(a));
        System.out.println("Total percentage of world's immigrants: " + Float.toString(b));
        System.out.println("Country with least immigration: " + countries.get(0).getName());
        System.out.println("Country with greatest immigration: " + countries.get(countries.size()-1).getName());
        System.out.println("Total estimated population of all countries:");
        for (int i = 0; i < countries.size(); i++)
        {
            System.out.println(countries.get(i).getName() + ": " + Integer.toString(totalNum.get(i)));
        }
    }
}
