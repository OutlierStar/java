package demo15;

public class main {
    public static void main(String[] args) {
        String[][] opTable = {
            {">", ">", "<", "<", ">", ">"},  // + 
            {">", ">", "<", "<", ">", ">"},  // (
            {">", " ", "<", "<", ">", ">"},  // )
            {">", ">", " ", " ", ">", ">"},  // i
            {"<", "<", "<", "<", "=", " "},  // #
    };
    
    String[][] precedenceTable = {
        //        +     *    ^    i    (    )    #
        /* + */ {">", "<", "<", "<", "<", ">", ">"},
        /* * */ {">", ">", "<", "<", "<", ">", ">"},
        /* ^ */ {">", ">", "<", "<", "<", ">", ">"},
        /* i */ {">", ">", ">", " ", " ", ">", ">"},
        /* ( */ {"<", "<", "<", "<", "<", "=", " "},
        /* ) */ {">", ">", ">", " ", " ", ">", ">"},
        /* # */ {"<", "<", "<", "<", "<", " ", "="}
    };
        OPGParser1 parser = new OPGParser1(precedenceTable);
        System.out.println("分析 i+i# ：");
        parser.parse("i+i#");
    
        parser = new OPGParser1(opTable);
        System.out.println("分析 i+(i)# ：");
        parser.parse("i+(i)#");


    }



}
