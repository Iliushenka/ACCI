package ru.iliushenka.acci.parser.common.statement;

/*

 Данный класс создает возможность
 обрабатывать события

 !!! КАЖДОМУ НОВОМУ КЛАССУ ДОБАВИТЬ CANCELLABLE,
 если не добавить, то будет автоматически выдано false

 */

import ru.iliushenka.acci.parser.common.*;
import ru.iliushenka.acci.parser.common.expression.Action;
import ru.iliushenka.acci.utility.Node;

import java.util.ArrayList;

public class EventStatement extends Statement {

    protected boolean cancellable = false;

    public void execute() {
        System.out.println(this.type);
        ArrayList<NodeValue> flatten = toFlatten();
//        for (NodeValue node : flatten)
//            System.out.println(node);
//        System.out.println();
        boolean flag_construction;
        for (NodeValue nodeValue : flatten) {
            Node node = (Node) nodeValue;

            Action action = node.getAction();
            ArrayList<Parameter> parameters = action.getParameters();

            ArrayList<NodeValue> values = node.getValues();

            ArrayList<OutputValue> result = new ArrayList<>();

            flag_construction = true;
            for (int index_parameter = 0; index_parameter < parameters.size(); index_parameter++) {
                Parameter parameter = parameters.get(index_parameter);
                for (int index_argument = 0; index_argument < values.size(); index_argument++) {
                    NodeValue value = values.get(index_argument);
                    if (value instanceof ValueParameter) {
                        flag_construction = false;
                        if (parameter.getName().equals(value.getType())) {
                            if (parameter.getSize() == 1) {
                                if (StatementTypeEquals.checkType(value, parameter)) {
                                    result.add(new OutputValue(parameter.getAction(), ((ValueParameter) value).getValue(), parameter.getSlot()));
                                    values.remove(index_argument);
                                    break;
                                }
                            }
                            System.out.println("1 Неверный аргумент");
                            System.exit(-1);
                        }
                    } else if (value instanceof ValueParameterArray) {
                        flag_construction = false;
                        if (parameter.getName().equals(value.getType())) {
                            if (parameter.getSize() >= ((ValueParameterArray) value).getLength()) {
                                if (checkArray((ValueParameterArray) value, parameter)) {
                                    result.add(new OutputValue(parameter.getAction(), ((ValueParameterArray) value).getValues(), parameter.getSlot()));
                                    values.remove(index_argument);
                                    break;
                                }
                            }
                            System.out.println("2 Неверный аргумент");
                            System.exit(-1);
                        }
                    } else if (value instanceof Value) {
                        if (flag_construction) {
                            if (parameter.getSize() == 1) {
                                if (StatementTypeEquals.checkType(value, parameter)) {
                                    result.add(new OutputValue(parameter.getAction(), value, parameter.getSlot()));
                                    values.remove(index_argument);
                                    break;
                                }
                            }
                            System.out.println("Данные не подходят");
                            System.exit(-1);
                        } else {
                            System.out.println("Требуется указать безымянные аргументы, перед именными");
                            System.exit(-1);
                        }
                    } else if (value instanceof ValueArray) {
                        if (flag_construction) {
                            if (parameter.getSize() >= ((ValueArray) value).getValues().size()) {
                                if (checkArray((ValueArray) value, parameter)) {
                                    result.add(new OutputValue(parameter.getAction(), ((ValueArray) value).getValues(), parameter.getSlot()));
                                    values.remove(index_argument);
                                    break;
                                }
                            }
                            System.out.println("Данные не подходят");
                            System.exit(-1);
                        } else {
                            System.out.println("Требуется указать безымянные аргументы, перед именными");
                            System.exit(-1);
                        }
                    }
                }
            }
            if (!action.getType().equals("NULL")) {
                System.out.println("    " + action.getType() + ", not=" + action.not + ", miniSelect=" + action.selected);
                for (OutputValue value : result) {
                    System.out.println("        " + value);
                }
                System.out.println();
            }
        }
    }

    protected boolean checkArray(ValueParameterArray array, Parameter parameter) {
        for (NodeValue value : array.getValues()) {
            if (StatementTypeEquals.checkType(value, parameter)) {
                continue;
            }
            System.out.println("В массиве не соответсвующий тип аргумента");
            System.exit(-1);
        }
        return true;
    }

    protected boolean checkArray(ValueArray array, Parameter parameter) {
        for (NodeValue value : array.getValues()) {
            if (StatementTypeEquals.checkType(value, parameter)) {
                continue;
            }
            System.out.println("В массиве не соответсвующий тип аргумента");
            System.exit(-1);
        }
        return true;
    }
}
