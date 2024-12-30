package org.example;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<T> extends AbstractList<T>
{
    //ArrayList
    private final int initSize = 16;
    private final int CutRate = 4;
    private Object[] array = new Object[initSize];
    private int pointer = 0;
    private int defaultPointer = 0;

    /*
     Проверяем новый элемнт поместится в массив если он занимает
     последнюю ячейку то вызываем метод Resize который увеличивает размер массива в 2 раза
     */
    public void Add(T item)
    {
        if(pointer == array.length-1)
        {
            Resize(array.length*2);
        }
        array[pointer++] = item;
    }
    /*
    Создает новый массив и записывает его в array
    pointer приравнивает к defaultPointer
     */
    public void Clear()
    {
        array = new Object[initSize];
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
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
    //Сортируем массив
    public void sort(Comparator<? super T> c)
    {
        Arrays.sort((T[]) array, 0, pointer, c);
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
        return (T) array[index];
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

}
