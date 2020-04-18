import interfacetest.InterfaceTest;
import interfacetest.InterfaceTestImpl1;
import interfacetest.InterfaceTestImpl2;
import list.ListBase;
import reactor.Server;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Main {

    public static String disp(){
        System.out.println("hello");
        return "hello";
    }
    public static void main(String[] args) throws Exception{
        Function<String, Integer> stringToInteger = Integer::parseInt;
        System.out.println(stringToInteger.apply("432"));
        BiPredicate<List<String>,String> contains = List::contains;
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        System.out.println(contains.test(list,"aaaa"));
    }





    public static void copyTxt(String src,String dist) throws Exception{
        FileInputStream inputStream = new FileInputStream(src);
        FileChannel inf = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream(dist);
        FileChannel outf = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            //将输入通道中的数据读取到缓冲区中
            int r = inf.read(buffer);
            //如果读取完了就退出
            if(r==-1)break;
            //读写反转
            buffer.flip();
            //将缓冲中的数据写入输出通道
            outf.write(buffer);
            //清空缓冲
            buffer.clear();
        }
    }
}
