import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
class Source {
    char way;
    DFAnode dn;
    public Source(char ch,DFAnode dn){
        way=ch;
        this.dn=dn;
    }
}

public class DFAnode {
    ArrayList<Source> sonnode;//可存在多个儿子
    ArrayList<Statement> statements;//一个节点内存的语句;

    public DFAnode() {
        sonnode = new ArrayList<>();
        statements = new ArrayList<>();
    }

    public void closure(Grammar grammar) {//节点内容的自我扩充,需要文法
        String trans = statements.get(0).transform;//第一个句子;
        int location = trans.indexOf("·");//返回点的位置;
        int size = trans.length();
        ArrayList<Statement> temp = grammar.Statements;
        if (location < size - 1) {//不是在末尾
            for (Statement s : temp) {
                if (s.Vn == trans.charAt(location + 1))//把以点之后的第一个节点开头的产生式加入
                    statements.add(s);
            }
        }
    }//自我填充完成。

    public void makeDFA(DFAnode start,Grammar grammar,ArrayList<DFAnode> arrdfa) {
       /* String s="!@3";
        s=s+'r'*/
       start.closure(grammar);//先补充完整
        arrdfa.add(start);
        //start.print();
        for (Statement s : start.statements){
            int loc = s.transform.indexOf("·");
            if (loc < s.transform.length() - 1) {//如果不是最后一个  手动把点前进一步
                DFAnode temp=new DFAnode();
                StringBuilder stringBuilder = new StringBuilder(s.transform);
                stringBuilder.replace(loc, loc + 2, s.transform.charAt(loc + 1) + "·");
                temp.statements.add(new Statement(s.Vn,stringBuilder.toString()));
                //System.out.println(loc+temp.statements.get(0).transform);
                boolean flag=true;
                for(DFAnode dn:arrdfa){
                    if(dn.statements.get(0).Vn==temp.statements.get(0).Vn&&dn.statements.get(0).transform.equals(temp.statements.get(0).transform)) {
                        start.sonnode.add(new Source(s.transform.charAt(loc+1), dn));
                        flag = false;
                    }
                }
                if(!flag){continue;}
                makeDFA(temp,grammar,arrdfa);//已存在的就不再new 而是增加儿子
                start.sonnode.add(new Source(s.transform.charAt(loc+1),temp));
                //System.out.println(s.transform.charAt(loc+1));
            }
        }
    }

    public void print() {
        for (Statement s:statements){
            System.out.print(s.Vn+"→");
            System.out.println(s.transform);
        }
        for(Source s:sonnode)
        System.out.println(s.way);
    }

}
