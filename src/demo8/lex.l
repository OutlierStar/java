%{
	#include<stdio.h>
	#include<stdlib.h>
	int line=1;
%}

digit [0-9]
letter [A-Za-z]
identifier {letter}({letter}|{digit})*
integer [1-9]{digit}*|0
operator [=|+|,|(|)]
mark ";"

%%
{integer} {printf("%s integer\n",yytext);}
{identifier} {printf("%s identifier\n",yytext);}
{operator} {printf("%s operator\n",yytext);}
(\*)(\*){0,1} {printf("%s operator\n",yytext);}
{mark} {printf("%s mark\n",yytext);}


%%
int main(){
	yyin=fopen("data.txt","r");
	yylex();
	return 0;
}
int yywrap(){
	return 1;
}

