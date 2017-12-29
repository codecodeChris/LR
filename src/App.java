import java.util.ArrayList;

public class App {
    public static void main(String args[]){
        /*StringBuilder stringBuilder = new StringBuilder("1234");
        stringBuilder.replace(2,  4, "." + "·");
        System.out.println(stringBuilder);*/
        Grammar grammar=new Grammar("E→TA A→+E A→ε T→FB B→T B→ε F→PC C→*C C→ε P→(E) P→a P→b P→^");
        grammar.altergrammar();
        //grammar.print();
        //ArrayList<DFAnode> arrDFA=new ArrayList<>();//
        DFAnode temp=new DFAnode();
        temp.statements.add(grammar.Statements.get(0));
       // temp.closure(grammar);
        ArrayList<DFAnode>anodes=new ArrayList<>();
        temp.makeDFA(temp,grammar,anodes);
        for(DFAnode dn:anodes){
           dn.print();
        }
        //temp.print();
        //System.out.println();


    }
}
