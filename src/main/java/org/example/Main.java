package org.example;


import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
        public static <T> void main(String[] args)
        {
                MyArrayList<Integer> mylist = new MyArrayList<Integer>();
                mylist.Add(8);
                mylist.Add(10);
                mylist.Add(9);
                mylist.Add(54);
                mylist.Add(23);
                mylist.Add(78);
                mylist.Add(69);
                mylist.Add(12);
                mylist.Add(98);
                mylist.Add(73);
                mylist.Add(2);

                for (int i = 0; i<=mylist.size()-1;i++)
                {
                        System.out.print(mylist.get(i) + "    ");
                }
                mylist.quickSort(mylist);

                System.out.print("  -      ");

                for (int i = 0; i<=mylist.size()-1;i++)
                {
                        System.out.print(mylist.get(i) + "    ");
                }

        }
}