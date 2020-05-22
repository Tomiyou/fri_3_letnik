package prev.phase.regall;

import java.util.*;

class Vertex<T> {
    private HashSet<T> sosedi;

    public Vertex() {
        sosedi = new HashSet<>();
    }

    public boolean dodajSoseda(T sosed) {
        return sosedi.add(sosed);
    }

    public void odstraniSoseda(T sosed) {
        sosedi.remove(sosed);
    }

    public HashSet<T> vrniSosede() {
        return sosedi;
    }
}

public class Graph<T> {
    private Map<T, Vertex<T>> map = new HashMap<>();

    public void addVertex(T v) {
        if (map.get(v) == null)
            map.put(v, new Vertex<T>());
    }

    public void dodajSoseda(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        map.get(source).dodajSoseda(destination);
        map.get(destination).dodajSoseda(source);
    }

    public void odstraniVozlisce(T v) {
        Vertex<T> _v = map.get(v);
        for (T sosed : _v.vrniSosede()) {
            map.get(sosed).odstraniSoseda(v);
        }

        map.remove(v);
    }

    public void odstraniVozlisce(T v, Iterator<T> iter) {
        Vertex<T> _v = map.get(v);
        for (T sosed : _v.vrniSosede()) {
            map.get(sosed).odstraniSoseda(v);
        }

        iter.remove();
    }

    public T vrniNajvecjoStopnjo() {
        T max = null;
        int s = 0;

        for (T v : map.keySet()) {
            int stopnja = map.get(v).vrniSosede().size();

            if (max == null || s < stopnja) {
                max = v;
                s = stopnja;
            }
        }

        return max;
    }

    public Iterator<T> vrniIterator() {
        return map.keySet().iterator();
    }

    public HashSet<T> vrniSosede(T v) {
        return map.get(v).vrniSosede();
    }

    public void print() {
        for (T v : map.keySet()) {
            System.out.println(v);
        }
    }
}