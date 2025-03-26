package ru.iliushenka.acci.parser.common.statement;

import ru.iliushenka.acci.parser.common.NodeValue;
import ru.iliushenka.acci.parser.common.Parameter;
import ru.iliushenka.acci.parser.common.Value;
import ru.iliushenka.acci.parser.common.ValueParameter;
import ru.iliushenka.acci.utility.Manager;

public class StatementTypeEquals {

    /**
     * Сюда добавлять уникальные типы обработок
     *
     * @param value Значений с проверяемым типом данных
     * @param parameter Параметр с нужным типом данных
     * @return Совпадают ли типы данных
     */
    public static boolean checkType(NodeValue value, Parameter parameter) {
        String parameterType = parameter.getTypeValue();
        String valueType;

        if (value instanceof ValueParameter) {
            valueType = ((ValueParameter) value).getValue().getType();
        } else {
            valueType = value.getType();
        }

        if (value.getType().equals("VARIABLE") && value instanceof Value) {
            if (Manager.variablesSaved.contains(((Value) value).getValue())) {
                ((Value) value).setSave(true);
            }
        }

        if (value instanceof ValueParameter && ((ValueParameter) value).getValue().getType().equals("VARIABLE")) {
            Value updateValue = (Value)((ValueParameter) value).getValue();
            if (Manager.variablesSaved.contains(updateValue.getValue())) {
                updateValue.setSave(true);
            }
        }

        if (parameterType.equals(valueType)) {
            return true;
        }

        switch (parameterType) {
            case "MATH":
                return valueType.equals("VARIABLE") || valueType.equals("NUMBER");
            case "TEXT":
                return valueType.equals("STRING") || valueType.equals("NUMBER") || valueType.equals("VARIABLE");
            case "ALL":
                return true;
            case "POSITION":
                return valueType.equals("LOCATION") || valueType.equals("VARIABLE");
        }
        return false;
    }
}
