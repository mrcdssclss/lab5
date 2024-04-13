package managers;

import data.*;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Getter
public class Ask {
    private CollectionManager collectionManager;
    Scanner userScanner;
    private ConsoleManager console;
    public static class AskBreak extends Exception {}
    public Ask(CollectionManager collectionManager, Scanner userScanner, ConsoleManager console) {
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
        this.console = console;

    }


    public int setId() {
        return CollectionManager.getMaxId();
    }



    public String askName() throws AskBreak {
    String name;
        while (true){
            console.print("name: ");
            name=console.readln().trim();
            if(name.equals("exit"))throw new AskBreak();
            if(!name.isEmpty())break;
        }
        return name;
    }
    public Coordinates askCoordinates() throws AskBreak {
        try {
            float x;
            while (true) {
                console.print("coordinates.x: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try { x = Float.parseFloat(line); break; }catch(NumberFormatException e) { }
                }
            }
            float y;
            while (true) {
                console.print("coordinates.y: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try { y = Float.parseFloat(line); break; }catch(NumberFormatException e) { }
                }
            }
            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            throw new AskBreak();
        }
    }

    public LocalDateTime askCreationDate(){
        return LocalDateTime.now();
    }
    public int askArea() throws AskBreak {
        int area;
        while (true) {
            console.print("area: ");
            area = Integer.parseInt(console.readln());
            if (area < 0) {
                console.printError("число должно быть больше 0");
                throw new AskBreak();
            }
            if (String.valueOf(area).equals("exit")) throw new AskBreak();
            return area;
        }
    }

    public Long askPopulation() throws AskBreak {
        long population;
        while (true){
            console.print("population: ");
            population = Long.parseLong(console.readln());
            if (String.valueOf(population).isEmpty() | population < 0) {
                console.printError("число должно быть большe 0 и не null ");
                throw new AskBreak();
            }
            if (String.valueOf(population).equals("exit")) throw new AskBreak();
            return population;
        }
    }

    public double askMetersAboveSeaLevel() throws AskBreak{
        double metersAboveSeaLevel;
        console.print("Meters Above Sea Level: ");
        metersAboveSeaLevel = Double.parseDouble(console.readln());
        if (String.valueOf(metersAboveSeaLevel).equals("exit")) throw new AskBreak();
        return metersAboveSeaLevel;
    }

    public int askCarCode()throws AskBreak{
        int carCode;
        while (true) {
            console.print("Car Code: ");
            carCode = Integer.parseInt(console.readln());
            if (carCode < 0 || carCode > 1000) {
                console.printError("число должно быть больше 0 и меньше тысячи ");
                throw new AskBreak();
            }
            if (String.valueOf(carCode).equals("exit")) throw new AskBreak();
            return carCode;
        }
    }

    public Government askGovernment()throws AskBreak{
        try{
            Government r;
            while (true){
                console.print("Government ("+ Arrays.toString(Government.names()));
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try {
                        r = Government.valueOf(line); break;
                    } catch (NullPointerException | IllegalArgumentException ignored) { }
                } else return null;
            }
            return r;
            } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public StandardOfLiving askStandardOfLiving()throws AskBreak{
        try{
            StandardOfLiving r;
            while (true){
                console.print("Standard of Living ("+ Arrays.toString(StandardOfLiving.names()));
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try {
                        r = StandardOfLiving.valueOf(line); break;
                    } catch (NullPointerException e) { }
                } else return null;
            }
            return r;
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public long askHuman() throws AskBreak{
        try{
            long age;
            while (true) {
                console.print("Enter age");
                age = Long.parseLong(console.readln().trim());
                if (String.valueOf(age).equals("exit")) throw new AskBreak();
                if (age < 0) {
                    console.printError("возраст должен быть больше 0");
                    throw new AskBreak();
                }
                return age;
            }
        } catch (AskBreak e) {
            throw new RuntimeException(e);
        }
    }
}
