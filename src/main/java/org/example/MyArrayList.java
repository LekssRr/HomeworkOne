package org.example;

import java.lang.foreign.SymbolLookup;
import java.util.*;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MyArrayList<T> extends AbstractList<T> implements Comparable<T>
{
    //ArrayList
    //Начальный размер массива
    private final int initSize = 16;
    //Массив значений
    private Comparable[] array = new Comparable[initSize];
    //Длинна массива
    private int pointer = 0;
    //Начальная длинна массива
    private int defaultPointer = 0;

    /*
     Проверяем новый элемнт поместится в массив если он занимает
     последнюю ячейку то вызываем метод Resize который увеличивает размер массива в 2 раза
     */
    @Override
    public boolean add(T e) {
        if(pointer == array.length-1)
        {
            Resize(array.length*2);
        }
        array[pointer++] = (Comparable) e;
        return true;
    }
    // Проверяем индекс входит в длиину массива если да то записываем элемент в ячейку
    @Override
    public void add(int index, T element)
    {
        if(pointer >= index)
        {
            array[index] = (Comparable) element;
        }
    }

    /*
    Создает новый массив и записывает его в array
    pointer приравнивает к defaultPointer
    */
    @Override
    public void clear()
    {
        array = new Comparable[initSize];
        pointer = defaultPointer;
    }
    /*
    проверяем присутствует ли данный индекс в массиве
    если да то отчищаем, перемещаем ячейку массива стоящего после данной ячейки на ее место
    */
    @Override
    public T remove(int index)
    {
        T result = null;
        if (index <= pointer - 1)
        {
            result = (T)array[index];
            array[index] = null;
        }
        for (int i = index +1; i< pointer; i++)
        {
            array[i -1] = array[i];
        }
        this.array[pointer-1] = null;
        pointer = pointer-1;
        return result;
    }
    /*
    Создаем новый массив увеличивая его вместимость на newLength
    и записываем его в array
    */
    private void Resize(int newLength)
    {
        Comparable[] newArray = (Comparable[])new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    /*
    Принимаем объект типа компоратор
    создаем булевую переменную needIteration и присваеваем ей значение true
    запускаем цикл while и передаем в него needIteration
    сразу же меняем значение переменной в false
    и запускаем цикл for который перебирает весь массив array
    и каждый элемент массива приводит к типу comporator
    после чего сравнивает объект i и i -1
    и вызвает функцию swap переводя переменную needIteration в значениие true
     */
    public void sort(Comparator<? super T> c)
    {
        boolean needIteration = true;
        while (needIteration)
        {
            needIteration = false;
            for (int i = 1; i <= pointer - 1; i++)
            {
                if (c.compare((T) this.get(i), (T) this.get(i - 1)) > 0)
                {
                        swap(array, i, i - 1);
                        needIteration = true;
                }
            }
        }
    }
    private void swap(Object[] array, int ind1, int ind2)
    {
        Object tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
    /*
    Передаем в функцию MyArrayList вызываем функцию Algoritm передаем в нее
    массив используя функцию toArray()
     */
    public static <T> void quickSort(MyArrayList<T> sortingList)
    {
        MyArrayList.Algoritm(sortingList.toArray(), 0, sortingList.size()-1);
    }
    /*
    Проверяем low меньше чем high.
    Создаем локальные переменные
    int i передаем в нее значение low;
    int j передаем в нее значение high;
    Comparable x равен среднему значению всех элементов массива
    Запускаем цикл do while(i <= j) в цикле запускаем 2 цикла
    while (arr[i].compareTo(x) < 0) который перебирает все значения от low до x
    while (x.compareTo(arr[j]) < 0) который перебирает все значения от high до x
    if ( i <= j) при саблюдении данного усовия объекты меняются местами
    Algoritm(arr, low, j) рекрусивно запускаем алгоритм заново передовая в него новое значение hight
    Algoritm(arr, i, high) рекурсивно запускаем алгоритм заново передовая в него новое значение low
    */
    private static void Algoritm(Comparable[] arr, int low, int high)
    {
        if (low < high)
        {
            int i = low;
            int j = high;
            Comparable x = arr[(i + j) / 2];

            do {
                while (arr[i].compareTo(x) < 0)
                {
                    i++;
                }
                while (x.compareTo(arr[j]) < 0)
                {
                    j--;
                }

                if ( i <= j) {
                    Comparable tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }

            } while (i <= j);
            Algoritm(arr, low, j);
            Algoritm(arr, i, high);
        }
    }
    /*
    возвращает массив
    */
    @Override
    public Comparable[] toArray()
    {
        return this.array;
    }
    /*
    Возвращает длинну массива
    */
    @Override
    public int size()
    {
        return this.pointer;
    }
    //Возвращает элемент массива по индексу
    @Override
    public T get(int index)
    {
        return (T)array[index];
    }


    @Override
    public Spliterator<T> spliterator() {
        return super.spliterator();
    }
    /*
    Добовляет элемент в начало массива
     */
    @Override
    public void addFirst(T t) {
        this.add( 0, t);
    }
    /*
    Добовляет элемент в конец массива
     */
    @Override
    public void addLast(T t) {
        this.add(pointer-1, t);
    }
    /*
    Возвращает первый элемент
    */
    @Override
    public T getFirst() {
        return this.get(0);
    }
    /*
    Возвращает последний элемент
    */
    @Override
    public T getLast()
    {
        return this.get(pointer-1);
    }

    /*
    Создаем массив newArray c длинной равной pointer
    записываем в newArray элементы массива array с индекса 1
    меняем значение pointer на pointer-1
    приравниваем array к newArray
     */
    @Override
    public T removeFirst()
    {
        Comparable[] newArray = new Comparable[pointer];
        for (int i =1; i<=pointer-1;i++)
        {
            newArray[i-1] = array[i];
        }
        pointer = pointer-1;
        array = newArray;
        return null;
    }

    /*
    Создаем массив newArray c длинной равной pointer
    записываем в newArray элементы массива array без последнего элемента
    меняем значение pointer на pointer-1
    приравниваем array к newArray
     */
    @Override
    public T removeLast() {
        Comparable[] newArray = new Comparable[pointer];
        for(int i = 0; i<= pointer-1;i++)
        {
            newArray[i] = array[i];
        }
        pointer = pointer-1;
        array = newArray;
        return null;
    }

    /*
    Создаем reversList и добавляем в него элементы из array в обратном порядке
    возвращаем новый reversList
     */
    @Override
    public MyArrayList<T> reversed()
    {
        MyArrayList<T> reversList = new MyArrayList<T>();
        for(int i = pointer-1; i >= 0; i--)
        {
            reversList.add((T)array[i]);
        }
        return reversList;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        super.replaceAll(operator);
    }


    @Override
    public int compareTo(Object o)
    {
        return 1;
    }
}
