package ru.iliushenka.acci.parser.common;

/*

 Данный класс не используется для наследования дальше
 Он используется в вызове действия

 Пример (Возможно не актуально):
    player.sendMessage(texts=["Hello"], mode=2);

*/

/**
 * Капец я устал думать как все улучшить
 *
 * @author iliushenka
 */
public class Parameter extends NodeValue {

    private final String name;
    private boolean used = false;

    private final int size;
    private final String typeValue;

    private int tag = -1;
    private ParameterAction action = ParameterAction.ITEM;

    /**
     *
     * @param name Название параметра
     * @param size Сколько в себя берет максимум чего-то
     * @param typeValue Принимаемый тип, есть модифицированные
     *
     * @author iliushenka
     */
    public Parameter(String name, int size, String typeValue) {
        this.name = name;

        this.size = size;
        this.typeValue = typeValue;
    }

    /**
     *
     * Думаю как определять клик и предмет, чтобы все было с кайфом
     * Возможно добавлю переменную action, но не уверен
     * Action имеет теперь изначально ITEM значение
     *
     * @param name Название параметра
     * @param size Сколько в себя берет максимум чего-то
     * @param typeValue Принимаемый тип, есть модифицированные
     * @param tag Я предполагаю это место для обозначения слота
     *
     * @author iliushenka
     */
    public Parameter(String name, int size, String typeValue, int tag) {
        this.name = name;

        this.size = size;
        this.typeValue = typeValue;

        this.tag = tag;
    }

    /**
     *
     * Добавил тут типо action, надеюсь не бесполезность эаэааэ
     *
     * @param name Название параметра
     * @param size Сколько в себя берет максимум чего-то
     * @param typeValue Принимаемый тип, есть модифицированные
     * @param tag Я предполагаю это место для обозначения слота
     * @param action Короче место под определение предмет или клик
     *
     * @author iliushenka
     */
    public Parameter(String name, int size, String typeValue, int tag, ParameterAction action) {
        this.name = name;

        this.size = size;
        this.typeValue = typeValue;

        this.tag = tag;
        this.action = action;
    }

    public void setUsed() {
        this.used = true;
    }

    public String getName() {
        return name;
    }

    public boolean isUsed() {
        return used;
    }

    public int getSize() {
        return size;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public int getTag() {
        return tag;
    }

    public ParameterAction getAction() {
        return action;
    }
}
