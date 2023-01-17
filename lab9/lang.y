%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int yylex();
int yyerror(char *s);

#define YYDEBUG 1 
%}

%token FUNC
%token INT
%token STRING
%token BOOL
%token CHAR
%token IF
%token ELIF
%token ELSE
%token LET
%token VAR
%token RET
%token TRUE
%token FALSE
%token READ
%token PRINT
%token LOOP
%token GO
%token STOP

%token ID
%token INT_CONST
%token STRING_CONST

%token AT_SIGN
%token HASHTAG
%token SEMI_COLON
%token APOSTROPHE
%token OPEN_CURLY_BRACKET
%token CLOSED_CURLY_BRACKET
%token OPEN_ROUND_BRACKET
%token CLOSED_ROUND_BRACKET
%token OPEN_STRAIGHT_BRACKET
%token CLOSED_STRAIGHT_BRACKET
%token PLUS
%token MINUS
%token MUL
%token DIV
%token LESS
%token GREATER
%token LESS_EQ
%token GREATER_EQ
%token DIFF
%token EQUAL
%token ATRIBUTION
%token NEGATION
%token TERNARY_OP
%token MOD

%left '+' '-' '*' '/'

%start program 

%%

program : GO tempDecl STOP ;
tempDecl : /*Empty*/ | tempDecl declList | tempDecl stmtList ;
declList : declaration | declaration declList ;
declaration : variableDeclaration | constDeclaration ;
variableDeclaration : VAR ID AT_SIGN type ATRIBUTION expression SEMI_COLON | VAR ID AT_SIGN type SEMI_COLON ;
constDeclaration : LET ID AT_SIGN type ATRIBUTION expression SEMI_COLON ;
type1 : BOOL | INT | CHAR | STRING ;
arrayDecl : OPEN_STRAIGHT_BRACKET type1 CLOSED_STRAIGHT_BRACKET ;
type : type1 | arrayDecl ;
stmtList : stmt | stmt stmtList ;
stmt : simplStmt | structStmt ;
simplStmt : assignStmt | ioStmt ;
assignStmt : ID ATRIBUTION expression SEMI_COLON;
expression : expression PLUS term | expression MINUS term | term | BOOL ;
term : term MUL factor | term DIV factor | term MOD factor | factor ;
factor : OPEN_ROUND_BRACKET expression CLOSED_ROUND_BRACKET | ID | INT_CONST ;
ioStmt : READ OPEN_ROUND_BRACKET ID CLOSED_ROUND_BRACKET SEMI_COLON | PRINT OPEN_ROUND_BRACKET stringExp CLOSED_ROUND_BRACKET SEMI_COLON ;
stringExp : STRING_CONST | ID ;
structStmt : ifStmt | whileStmt ;
ifStmt : IF condition OPEN_CURLY_BRACKET stmtList CLOSED_CURLY_BRACKET tempElifStmt | IF condition OPEN_CURLY_BRACKET stmtList CLOSED_CURLY_BRACKET tempElifStmt elseStmt ;
tempElifStmt : /*Empty*/ | tempElifStmt elifStmt ;
elseStmt : ELSE OPEN_CURLY_BRACKET stmtList CLOSED_CURLY_BRACKET ;
elifStmt : ELIF condition OPEN_CURLY_BRACKET stmtList CLOSED_CURLY_BRACKET ;
whileStmt : LOOP condition OPEN_CURLY_BRACKET stmtList CLOSED_CURLY_BRACKET ;
condition : expression relation expression ;
relation : LESS | LESS_EQ | EQUAL | GREATER_EQ | GREATER | DIFF ;

%%
int yyerror(char *s) {
    printf("Error: %s", s);
}

extern FILE *yyin;

int main(int argc, char** argv) {
    if (argc > 1) 
        yyin = fopen(argv[1], "r");
    if (!yyparse()) 
        fprintf(stderr, "\tOK\n");
}
