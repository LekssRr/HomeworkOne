package org.example;

import java.lang.foreign.SymbolLookup;
import java.util.*;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MyArrayList<T> extends AbstractList<T> implements Comparable<T> {

    private final int initSize = 16;
    private final int CutRate = 4;
    private Comparable[] array = new Comparable[initSize];
    private int pointer = 0;
    private int defaultPointer = 0;

    /*
     Проверяем новый элемнт поместится в массив если он занимает
     последнюю ячейку то вызываем метод Resize который увеличивает размер массива в 2 раза
     */
    public void Add(Comparable item)
    {
        if(pointer == array.length-1)
        {
            Resize(array.length*2);
        }
        array[pointer++] = item;
    }
    public void addToIndex(Comparable item, int Index)
    {
        if(pointer <= Index)
        {
            array[Index] = item;
        }
    }
    /*
    Создает новый массив и записывает его в array
    pointer приравнивает к defaultPointer
     */
    public void Clear()
    {
        array = (Comparable[])new Object[initSize];
        pointer = defaultPointer;
    }
    /*
        Отчищаем массив
     */
    public void Remove(int index)
    {
        if (index <= array.length - 1)
        {
            array[index] = null;
        }
        for (int i = index +1; i< array.length; i++)
        {
            array[i -1] = array[i];
        }
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
    private void swap(Object[] array, int ind1, int ind2)
    {
        Object tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
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
    public void quickSort(MyArrayList<T> sortingList)
    {
        sortingList.Algoritm(sortingList.toArray(), 0, sortingList.size()-1);
    }

    private void Algoritm(Comparable[] arr, int low, int high)
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
    public Comparable getLow() {

        return  array[0];
    }
    public Comparable getHight() {
        return array[pointer];
    }
    public Comparable elementData(int index) {
        return array[index];
    }
    public Comparable[] toArray()
    {
        return array;
    }

    //возвращает длинну массива
    @Override
    public int size()
    {
        return this.pointer;
    }
    //Возвращает элемент массива по индексу
    @Override
    public T get(int index) {

        return (T)array[index];
    }


    @Override
    public Spliterator<T> spliterator() {
        return super.spliterator();
    }

    @Override
    public void addFirst(T t) {
        super.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        super.addLast(t);
    }

    @Override
    public T getFirst() {
        return super.getFirst();
    }

    @Override
    public T getLast() {
        return super.getLast();
    }

    @Override
    public T removeFirst() {
        return super.removeFirst();
    }

    @Override
    public T removeLast() {
        return super.removeLast();
    }

    @Override
    public MyArrayList<T> reversed() {
        return null;
    }



    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        super.replaceAll(operator);
    }


    @Override
    public int compareTo(Object o)
    {
        //Comparable result = (Comparable) o;

        return 1;
    }
}
