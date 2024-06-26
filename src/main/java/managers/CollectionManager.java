package managers;

import data.City;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.max;


public class CollectionManager {
    @Getter
    private static ArrayDeque<City> collection = new ArrayDeque<>();
    @Getter
    private java.util.Date lastInitTime;
    @Getter
    private LocalDateTime lastSaveTime;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
    }

    public void loadCollection(FileManager fileManager) {
        collection = fileManager.readCollection();
        lastInitTime = new Date();
    }

    public City getById(int id) {
        for (City city : collection) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }



    public boolean isContain(City city) {
        if (city == null || getCollectionById(city.getId()) == null) return false;
        return collection.contains(city);
    }

    public void add(City city) {
        collection.add(city);
    }

    public boolean addById(Integer id, City city){
        if (!isEmpty()){
            if(isContain(city)) {return false;}
            if (getCollectionById(id) != null) {return false;}}
        city.setId(id);
        collection.addLast(city);
        return true;
    }


    public void remove(int id) {
        var a = getById(id);
        if (a == null) return;
        collection.remove(a);
    }

    public void clear(){
        collection.clear();
    }

    public boolean saveCollection(FileManager fileManager) {
        fileManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
        return true;
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        StringBuilder info = new StringBuilder();
        for (var city : collection) {
            info.append(city).append("\n\n");
        }
        return info.toString().trim();
    }
    public String collectionType(){
        return collection.getClass().getName();
    }
    public int collectionSize(){
        return collection.size();
    }

    public void removeFirst(){ collection.poll(); }

    public void printCollection(ConsoleManager console){
        for (City el : collection){
            console.println(el);
        }
    }
    public boolean isEmpty(){
        return collection.isEmpty();
    }
    public ArrayDeque<City> getCollectionById(Integer id){
        if (isEmpty()) return null;
        for (City el: collection){
            if (el.getId().equals(id)) return collection;
        }
        return null;
    }

    public static Integer getMaxId(){
        int maxId = 0;
        for (City el : collection){
            maxId = max(el.getId(), maxId);
        }
        return maxId;
    }
}
