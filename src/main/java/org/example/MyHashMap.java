package org.example;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyHashMap <K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable
{
    private final int initSize = 16;
    private final int CutRate = 4;
    private int pointer = 0;
    private int defaultPointer = 0;
    private Object[] arrayKey = new Object[initSize];
    private Object[] arrayValue = new Object[initSize];

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return Set.of();
    }

    private Object getArrayKey(int Index)
    {
        return arrayKey[Index];
    }
    private Object getArrayValue(int Index)
    {
        return arrayValue[Index];
    }
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

    private void Resize(int newLength)
    {
        Object[] newArrayKey = new Object[newLength];
        Object[] newArrayValue = new Object[newLength];
        System.arraycopy(arrayKey, 0, newArrayKey, 0, pointer);
        System.arraycopy(arrayValue, 0, newArrayValue, 0, pointer);
        arrayKey = newArrayKey;
        arrayValue = newArrayValue;
    }

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
    @Override
    public int size() {
        return pointer;
    }

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
               if (arrayValue[i] == value)
               {
                   return true;
               }
           }
        }
        return false;
    }

    public boolean containsKey(Object value)
    {
        if (value == null)
        {
            return false;
        }
        else
        {
            for (int i = 0; i<= pointer; i++)
            {
                if (arrayKey[i] == value)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<K> keySet()
    {
        Set<K> resultSet= new HashSet<K>();
        for (int i = 0; i<= pointer;i++)
        {
            resultSet.add((K)this.getArrayKey(i));
        }
        return resultSet;
    }

    public Set<V> setValue(V value)
    {
        Set<V> resultSet= new HashSet<V>();
        for (int i = 0; i<= pointer;i++)
        {
            resultSet.add((V)this.getArrayValue(i));
        }
        return resultSet;
    }
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
    @Override
    public void clear()
    {
        arrayKey = new Object[initSize];
        arrayValue = new Object[initSize];
        pointer = defaultPointer;
    }
}
