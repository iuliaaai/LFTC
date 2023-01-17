/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     FUNC = 258,
     INT = 259,
     STRING = 260,
     BOOL = 261,
     CHAR = 262,
     IF = 263,
     ELIF = 264,
     ELSE = 265,
     LET = 266,
     VAR = 267,
     RET = 268,
     TRUE = 269,
     FALSE = 270,
     READ = 271,
     PRINT = 272,
     LOOP = 273,
     GO = 274,
     STOP = 275,
     ID = 276,
     INT_CONST = 277,
     STRING_CONST = 278,
     AT_SIGN = 279,
     HASHTAG = 280,
     SEMI_COLON = 281,
     APOSTROPHE = 282,
     OPEN_CURLY_BRACKET = 283,
     CLOSED_CURLY_BRACKET = 284,
     OPEN_ROUND_BRACKET = 285,
     CLOSED_ROUND_BRACKET = 286,
     OPEN_STRAIGHT_BRACKET = 287,
     CLOSED_STRAIGHT_BRACKET = 288,
     PLUS = 289,
     MINUS = 290,
     MUL = 291,
     DIV = 292,
     LESS = 293,
     GREATER = 294,
     LESS_EQ = 295,
     GREATER_EQ = 296,
     DIFF = 297,
     EQUAL = 298,
     ATRIBUTION = 299,
     NEGATION = 300,
     TERNARY_OP = 301,
     MOD = 302
   };
#endif
/* Tokens.  */
#define FUNC 258
#define INT 259
#define STRING 260
#define BOOL 261
#define CHAR 262
#define IF 263
#define ELIF 264
#define ELSE 265
#define LET 266
#define VAR 267
#define RET 268
#define TRUE 269
#define FALSE 270
#define READ 271
#define PRINT 272
#define LOOP 273
#define GO 274
#define STOP 275
#define ID 276
#define INT_CONST 277
#define STRING_CONST 278
#define AT_SIGN 279
#define HASHTAG 280
#define SEMI_COLON 281
#define APOSTROPHE 282
#define OPEN_CURLY_BRACKET 283
#define CLOSED_CURLY_BRACKET 284
#define OPEN_ROUND_BRACKET 285
#define CLOSED_ROUND_BRACKET 286
#define OPEN_STRAIGHT_BRACKET 287
#define CLOSED_STRAIGHT_BRACKET 288
#define PLUS 289
#define MINUS 290
#define MUL 291
#define DIV 292
#define LESS 293
#define GREATER 294
#define LESS_EQ 295
#define GREATER_EQ 296
#define DIFF 297
#define EQUAL 298
#define ATRIBUTION 299
#define NEGATION 300
#define TERNARY_OP 301
#define MOD 302




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

