package utilities;

import managers.Ask;

public interface Executable {
    boolean execute(String argument) throws Ask.AskBreak;
}
