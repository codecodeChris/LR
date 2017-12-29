import java.util.ArrayList;
import java.util.HashSet;

public class Grammar {
    HashSet<Character>Vn;
    HashSet<Character>Vt;
    ArrayList<Statement>Statements;
    char startVn;
    public Grammar(String allsm){
        Vn=new HashSet<>();
        Vt=new HashSet<>();
        Statements=new ArrayList<>();
        startVn=allsm.charAt(0);
        String []tempS=allsm.split(" ");
        for(int i=0;i<tempS.length;i++){
            Statement tempSt=new Statement(tempS[i].charAt(0),tempS[i].substring(2));
            Statements.add(tempSt);
            Vn.add(tempS[i].charAt(0));//非终结符
            for(int j=2;j<tempS[i].length();j++) {
                Vt.add(tempS[i].charAt(j));
            }
        }
        for(Character ch:Vn){
            Vt.remove(ch);
        }
    }
    public void print(){
        for(Character ch:Vn)
            System.out.print(ch);
        System.out.println();
        for(Character ch:Vt)
            System.out.print(ch);
        System.out.println();
        System.out.println(startVn);
    }
    public void altergrammar(){
        for(Statement s:Statements){
            if(s.transform.equals("ε"))
                s.transform="";
            s.transform="·"+s.transform;
        }
        for(Statement s:Statements){
            System.out.println(s.transform);
        }
    }
}
