package ru.iliushenka.acci.parser.common;

/*

 Данный класс не используется для наследования дальше
 Он используется в вызове действия

 Пример (Возможно не актуально):
    player.sendMessage(texts=["Hello"], mode=2);

*/

public class Parameter extends NodeValue {

    private final String name;

    private final int size;
    private final String typeValue;

    public Parameter(String name, int size, String typeValue) {
        this.name = name;

        this.size = size;
        this.typeValue = typeValue;
    }

}
