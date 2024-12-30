package org.example;

import java.util.Comparator;

public class Auto
{
    private int year;

    Auto(int newYear)
    {
        year = newYear;
        String str = new String();
        str = Integer.toString(newYear);
        System.out.printf(str);
    }
    public int getYear()
    {
        return year;
    }
}
