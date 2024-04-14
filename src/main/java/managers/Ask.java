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
            var line = console.readln().trim();
            if (line.equals("exit")) throw new AskBreak();
            if (Integer.parseInt(line) > 0) {
                try { area = Integer.parseInt(line); break; }catch(NumberFormatException e) { }
            }
        }
        return area;
    }

    public Long askPopulation() throws AskBreak {
        long population;
        while (true){
            console.print("population: ");
            var line = console.readln().trim();
            if (line.equals("exit")) throw new AskBreak();
            if (!line.isEmpty() | Long.parseLong(line) > 0) {
                try { population = Long.parseLong(line); break; }catch(NumberFormatException e) { }
            }
        } return population;
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
            var line = console.readln().trim();
            if (Integer.parseInt(line) > 0 || Integer.parseInt(line) < 1000) {
                try { carCode = Integer.parseInt(line); break; }catch(NumberFormatException e) { }
            }
            if (line.equals("exit")) throw new AskBreak();
        } return carCode;
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
            long age;
            while (true) {
                console.print("Enter age");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (Long.parseLong(line)> 0) {
                    try { age = Integer.parseInt(line); break; }catch(NumberFormatException e) { }
                }
        }  return age;
    }
}