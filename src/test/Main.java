package test;

import java.net.InterfaceAddress;
import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(),k = input.nextInt();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = input.nextInt();
            if(map.get(Integer.valueOf(value))==null){
                map.put(value,1);
            }else{
                map.put(value,map.get(Integer.valueOf(value))+1);
            }
        }
        Set<Integer> keySet = map.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        int sum = 0;
        while (iterator.hasNext()){
            int temp = map.get(Integer.valueOf(iterator.next()));
            sum+=temp/2;
            if(temp%2==1)sum++;
        }
        System.out.println(sum);
    }
}
/**

 */