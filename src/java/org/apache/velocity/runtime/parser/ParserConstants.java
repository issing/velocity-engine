/* Generated By:JJTree&JavaCC: Do not edit this line. ParserConstants.java */
package org.apache.velocity.runtime.parser;

public interface ParserConstants {

  int EOF = 0;
  int LBRACKET = 1;
  int RBRACKET = 2;
  int COMMA = 3;
  int LPAREN = 4;
  int RPAREN = 5;
  int REFMOD2_RPAREN = 6;
  int ESCAPE_DIRECTIVE = 7;
  int SET_DIRECTIVE = 8;
  int DOLLAR = 9;
  int DOLLARBANG = 10;
  int HASH = 14;
  int DOUBLE_ESCAPE = 15;
  int ESCAPE = 16;
  int TEXT = 17;
  int SINGLE_LINE_COMMENT = 18;
  int FORMAL_COMMENT = 19;
  int MULTI_LINE_COMMENT = 20;
  int WHITESPACE = 22;
  int STRING_LITERAL = 23;
  int TRUE = 24;
  int FALSE = 25;
  int NEWLINE = 26;
  int MINUS = 27;
  int PLUS = 28;
  int MULTIPLY = 29;
  int DIVIDE = 30;
  int MODULUS = 31;
  int LOGICAL_AND = 32;
  int LOGICAL_OR = 33;
  int LOGICAL_LT = 34;
  int LOGICAL_LE = 35;
  int LOGICAL_GT = 36;
  int LOGICAL_GE = 37;
  int LOGICAL_EQUALS = 38;
  int LOGICAL_NOT_EQUALS = 39;
  int LOGICAL_NOT = 40;
  int EQUALS = 41;
  int END = 42;
  int IF_DIRECTIVE = 43;
  int ELSEIF_DIRECTIVE = 44;
  int ELSE_DIRECTIVE = 45;
  int STOP_DIRECTIVE = 46;
  int DIGIT = 47;
  int NUMBER_LITERAL = 48;
  int LETTER = 49;
  int WORD = 50;
  int ALPHA_CHAR = 51;
  int ALPHANUM_CHAR = 52;
  int IDENTIFIER_CHAR = 53;
  int IDENTIFIER = 54;
  int DOT = 55;
  int LCURLY = 56;
  int RCURLY = 57;
  int REFERENCE_TERMINATOR = 58;
  int DIRECTIVE_TERMINATOR = 59;

  int DIRECTIVE = 0;
  int REFMODIFIER = 1;
  int REFMOD2 = 2;
  int DEFAULT = 3;
  int PRE_DIRECTIVE = 4;
  int REFERENCE = 5;
  int IN_MULTI_LINE_COMMENT = 6;
  int IN_FORMAL_COMMENT = 7;
  int IN_SINGLE_LINE_COMMENT = 8;

  String[] tokenImage = {
    "<EOF>",
    "\"[\"",
    "\"]\"",
    "\",\"",
    "\"(\"",
    "<RPAREN>",
    "\")\"",
    "<ESCAPE_DIRECTIVE>",
    "<SET_DIRECTIVE>",
    "<DOLLAR>",
    "<DOLLARBANG>",
    "\"##\"",
    "<token of kind 12>",
    "\"#*\"",
    "\"#\"",
    "\"\\\\\\\\\"",
    "\"\\\\\"",
    "<TEXT>",
    "<SINGLE_LINE_COMMENT>",
    "\"*#\"",
    "\"*#\"",
    "<token of kind 21>",
    "<WHITESPACE>",
    "<STRING_LITERAL>",
    "\"true\"",
    "\"false\"",
    "<NEWLINE>",
    "\"-\"",
    "\"+\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"&&\"",
    "\"||\"",
    "\"<\"",
    "\"<=\"",
    "\">\"",
    "\">=\"",
    "\"==\"",
    "\"!=\"",
    "\"!\"",
    "\"=\"",
    "<END>",
    "\"if\"",
    "\"elseif\"",
    "<ELSE_DIRECTIVE>",
    "\"stop\"",
    "<DIGIT>",
    "<NUMBER_LITERAL>",
    "<LETTER>",
    "<WORD>",
    "<ALPHA_CHAR>",
    "<ALPHANUM_CHAR>",
    "<IDENTIFIER_CHAR>",
    "<IDENTIFIER>",
    "<DOT>",
    "\"{\"",
    "\"}\"",
    "<REFERENCE_TERMINATOR>",
    "<DIRECTIVE_TERMINATOR>",
  };

}
