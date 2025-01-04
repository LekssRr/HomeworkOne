package org.example;

import javax.management.ObjectName;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyHashMap <K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable
{
    //Начальный размер массива
    private final int initSize = 16;
    //Длинна массива
    private int pointer = 0;
    //Начальная длинна массива
    private int defaultPointer = 0;
    //Массив ключей
    private Object[] arrayKey = new Object[initSize];
    //Массив значений
    private Object[] arrayValue = new Object[initSize];

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return Set.of();
    }
    //вспомогательная функция для поиска ключей
    private Object getArrayKey(int Index)
    {
        return arrayKey[Index];
    }
    //вспомогательная функция для поиска значений
    private Object getArrayValue(int Index)
    {
        return arrayValue[Index];
    }
    /*
    Запускаем цикл и перебираем все ключи если находим нужный ключ
    возвращаем значение под этим индексом
    */
    @Override
    public V get(Object K)
    {
        for (int i = 0; i<= pointer -1; i++)
        {
            if (K.equals(this.getArrayKey(i)))
            {
                return (V) this.getArrayValue(i);
            }
        }
        return null;
    }
    /*
    Вспомогательный метод для увеличения размеров массива
    */
    private void Resize(int newLength)
    {
        Object[] newArrayKey = new Object[newLength];
        Object[] newArrayValue = new Object[newLength];
        System.arraycopy(arrayKey, 0, newArrayKey, 0, pointer);
        System.arraycopy(arrayValue, 0, newArrayValue, 0, pointer);
        arrayKey = newArrayKey;
        arrayValue = newArrayValue;
    }
    /*
    Вспомогательный метод для добовления объектов и ключей в массивы
    */
    private void addToArrays(K key, V value)
    {
        if(pointer == arrayKey.length-1)
        {
            Resize(arrayKey.length*2);
        }
        int myPointer = pointer++;
        arrayKey[myPointer] = key;
        arrayValue[myPointer] = value;
    }
    /*
    Запускаем цикл и проверяем есть ли данный ключ в массиве ключей
    если нет, то добовляем и ключ и значение в массивы
    */
    @Override
    public V put(K key, V value)
    {
        for(int i = 0; i<=pointer; i++)
        {
          if (key.equals(arrayKey[i]))
          {
              return null;
          }
        }
        addToArrays(key, value);
        return (V) this.getArrayKey(pointer);
    }
    //Возвращает размер массивов
    @Override
    public int size() {
        return pointer;
    }
    /*
    Проверяем что бы value != null
    Проверяем что присутствует ли данное значение в массиве
    */
    @Override
    public boolean containsValue(Object value)
    {
        if (value == null)
        {
            return false;
        }
        else
        {
           for (int i = 0; i<= pointer; i++)
           {
               if (arrayValue[i].equals(value))
               {
                   return true;
               }
           }
        }
        return false;
    }
    /*
    Проверяем что бы key != null
    Проверяем что присутствует ли данное значение в массиве
    */
    @Override
    public boolean containsKey(Object key)
    {
        if (key == null)
        {
            return false;
        }
        else
        {
            for (int i = 0; i<= pointer; i++)
            {
                if (arrayKey[i].equals(key))
                {
                    return true;
                }
            }
        }
        return false;
    }
    /*
    Создаем HashSet в цикле присваиваем HashSet значения из массива ArrayKey
    */
    @Override
    public Set<K> keySet()
    {
        Set<K> resultSet= new HashSet<K>();
        for (int i = 0; i<= pointer;i++)
        {
            resultSet.add((K)this.getArrayKey(i));
        }
        return resultSet;
    }
    /*
        Создаем HashSet в цикле присваиваем HashSet значения из массива ArrayValue
        */
    public Set<V> setValue(V value)
    {
        Set<V> resultSet= new HashSet<V>();
        for (int i = 0; i<= pointer;i++)
        {
            resultSet.add((V)this.getArrayValue(i));
        }
        return resultSet;
    }
    /*
    Перебираем массив arrayValue
    */
    public boolean isEmpty()
    {
        for (int i = 0; i<=pointer; i++)
        {
            if(arrayValue[i] != null)
            {
                return true;
            }
        }
        return false;
    }

    /*
    Создаем новые массивы и приравниваем
    arrayKey и arrayValue к новым массивам
    так pointer приравниваем к defaultPointer
    */
    @Override
    public void clear()
    {
        arrayKey = new Object[initSize];
        arrayValue = new Object[initSize];
        pointer = defaultPointer;
    }
    /*
    Перебирает массив arrayKey если находим такой же ключ в масиве то приравниваем ему значение null
    */
    @Override
    public V remove(Object key)
    {
        V result = null;
        for(int i = 0; i<= pointer-1; i++)
        {
            if(arrayKey[i].equals(key))
            {
                result =  (V)arrayValue[i];
                arrayValue[i] = null;
            }
        }
        return result;
    }
}
