import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String args[]){
        /*StringBuilder stringBuilder = new StringBuilder("1234");E→TA A→+E A→ε T→FB B→T B→ε F→PC C→*C C→ε P→(E) P→a P→b P→^
        stringBuilder.replace(2,  4, "." + "·");
        System.out.println(stringBuilder);*/
        String temostatements=" E→aA E→bB A→cA A→d B→cB B→d";
        Grammar grammar=new Grammar("Z→"+temostatements.charAt(1)+temostatements);
        HashMap<Character,Statement>hcs=new HashMap<>();
        char tempc='a';
        for(Statement s:grammar.Statements){
            Statement temp=new Statement(s.Vn,s.transform+"·");
            hcs.put(tempc++,temp);
        }
        grammar.altergrammar();

        char table[][]=new char[100][100];
        int count=0;
        for(Character ch:grammar.Vt)//终结点
            table[0][++count]=ch;
        table[0][++count]='#';
        for(Character ch:grammar.Vn)
            table[0][++count]=ch;
        for(int i=0;i<100;i++)
            table[i][0]= (char) (i+48);
        //表格设计完成
//        for(Statement s:hcs){
//
//        }
        //grammar.print();
        //ArrayList<DFAnode> arrDFA=new ArrayList<>();//
        DFAnode temp=new DFAnode();
        temp.statements.add(grammar.Statements.get(0));
       // temp.closure(grammar);
        ArrayList<DFAnode>anodes=new ArrayList<>();
        temp.makeDFA(temp,grammar,anodes);
        char ccount='1';
        for(DFAnode dn:anodes){
            dn.num= ccount++;
           dn.print();
        }
        for(Map.Entry<Character,Statement> entry:hcs.entrySet()){
           System.out.println(entry.getKey()+"---"+entry.getValue().Vn+"→"+entry.getValue().transform);
        }
        for(DFAnode dn:anodes){
            System.out.println(dn.statements.get(0).Vn+"→"+dn.statements.get(0).transform);
        }
        //
        for(DFAnode dn:anodes){
            for (Map.Entry<Character,Statement> entry : hcs.entrySet()){
                if(entry.getValue().Vn==dn.statements.get(0).Vn&&entry.getValue().transform.equals(dn.statements.get(0).transform))
                    table[dn.num-48][grammar.Vt.size()+1]=entry.getKey();
            }
                for(Source source:dn.sonnode){
               for(int k=1;k<100;k++)
                   if(table[0][k]==source.way)
                       table[dn.num-48][k]=source.dn.num;//目标
           }

        }
        for(int i=0;i<anodes.size()+1;i++) {
            System.out.println("————————————————————————————————————————————————————————————————————————————————————————————————————————————");
            for (int j = 0; j < grammar.Vt.size() + grammar.Vn.size() + 2;j++)
                if(table[i][j]<64&&table[i][j]>57)
                System.out.print(String.format("%1$10s", (int)(table[i][j]-48))+"|");
            else
                System.out.print(String.format("%1$10s",table[i][j])+"|");
            System.out.println();
        }
        //temp.print();
        //System.out.println();


    }
}
