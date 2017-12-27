import java.util.ArrayList;

public class DFAnode {
    ArrayList<DFAnode> sonnode;//可存在多个儿子
    ArrayList<Statement> statements;//一个节点内存的语句;
    public void closure(){//自我扩充,需要文法
        String trans=statements.get(0).transform;//第一个句子;
        int location=trans.indexOf(".");//返回点的位置;
        int size=trans.length();
        if(location<size-1){//不是在末尾
            if(trans.charAt(location+1))
        }
    }
}
