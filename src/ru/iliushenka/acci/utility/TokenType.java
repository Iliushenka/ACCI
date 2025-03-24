package ru.iliushenka.acci.utility;

public enum TokenType {

    NULL,

    STRING,
    STRING_COMPONENT,
    VARIABLE,
    NUMBER,

    ASSIGN,

    PLUS_ASSIGN,
    MINUS_ASSIGN,

    PLUS,
    MINUS,
    MULTIPLY,
    DIVISION,

    DOT,
    COMMA,

    OPEN_PARENT,
    CLOSE_PARENT,

    OPEN_BRACKET,
    CLOSE_BRACKET,

    OPEN_SQUARE_BRACKET,
    CLOSE_SQUARE_BRACKET,

    OPEN_BRACE,
    CLOSE_BRACE,

    SEMICOLON, // ';'
    COLON, // ':'

    EOF
}
