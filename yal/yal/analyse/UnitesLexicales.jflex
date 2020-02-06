package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+
csteB = "vrai" | "faux"

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaire = [/][/].*

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"entier"               { return symbol(CodesLexicaux.TYPE); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"lire"                 { return symbol(CodesLexicaux.LIRE); }

"si"                   { return symbol(CodesLexicaux.SI); }
"alors"                { return symbol(CodesLexicaux.ALORS); }
"sinon"                { return symbol(CodesLexicaux.SINON); }
"finsi"                { return symbol(CodesLexicaux.FINSI); }

"tantque"              { return symbol(CodesLexicaux.TANTQUE); }
"repeter"              { return symbol(CodesLexicaux.REPETER); }
"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }

"="                    { return symbol(CodesLexicaux.EGAL); }
"+"                    { return symbol(CodesLexicaux.PLUS); }
"-"                    { return symbol(CodesLexicaux.MOINS); }
"*"                    { return symbol(CodesLexicaux.MULTIPLICATION); }
"/"                    { return symbol(CodesLexicaux.DIVISION); }

"=="                   { return symbol(CodesLexicaux.EGALITE); }
"<"                    { return symbol(CodesLexicaux.INFERIEUR); }
">"                    { return symbol(CodesLexicaux.SUPERIEUR); }
"!="                   { return symbol(CodesLexicaux.DIFFERENCE); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

"et"                   { return symbol(CodesLexicaux.ET); }
"ou"                   { return symbol(CodesLexicaux.OU): }
"non"                  { return symbol(CodesLexicaux.NON); }

"("                    { return symbol(CodesLexicaux.PAROUVR); }
")"                    { return symbol(CodesLexicaux.PARFER); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }
{commentaire}          { }
{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }
{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

