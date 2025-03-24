package ru.iliushenka.acci.parser.common;

public class MiniSelector {

    /**
     * Есть такие типы как: default, all, selected, random, killer, damager, shooter, victim
     *
     * @param select Тип внутр. выборки.
     * @return true если такая есть, false если нет.
     * @author iliushenka
     */
    public static boolean checkSelected(String select) {
        return switch (select) {
            case "default", "all", "selected", "random", "killer", "damager", "shooter", "victim" -> true;
            default -> false;
        };
    }
}
