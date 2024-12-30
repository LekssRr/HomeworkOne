package org.example;


import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
        public static void main(String[] args)
        {
                MyArrayList A= new MyArrayList();
                A.Add(new Auto(2));
                A.Add(new Auto(1));
                A.Add(new Auto(5));
                A.Add(new Auto(0));
                A.Add(new Auto(13));
                A.Add(new Auto(8));

                Comparator<Auto> com = new Comparator<Auto>()
                {
                        @Override
                        public int compare(Auto o1, Auto o2)
                        {
                                if (o1.equals(o2))
                                {
                                        return -1;
                                }
                                if (o1.getYear() > o2.getYear())
                                {
                                        return 1;
                                }
                                else
                                {
                                        return -1;
                                }
                        }
                };
                //Collections.sort(A, com);
                A.sort(com);
                String str1 = new String();
                System.out.printf(str1);
                System.out.printf("  ");
                for (int i = 0; i<= A.size() -1; i++)
                {
                        Auto g = (Auto) A.get(i);
                        str1 = Integer.toString(g.getYear());
                        System.out.printf(str1);
                }

        }
}