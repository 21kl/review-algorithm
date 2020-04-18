package thread;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;

/**
 * 使用wait和notify来演示下载图片和显示图片
 */
public class DownloadAndShowPic {

    public static void main(String[] args) {
        Object object = new Object();
        Object jj = new Object();
        Thread show = new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        //阻塞该线程直到图片下载的线程完成
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("开始展示图片");
                System.out.println("图片展示完毕");
                synchronized (jj) {
                    jj.notify();
                }
            }
        };

        Thread download = new Thread() {
            @Override
            public void run() {
                System.out.println("开始下载图片");
                for (int i = 0; i < 101; i += 10) {
                    System.out.println("download:" + i + "%");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("图片下载成功");

                //唤醒展示图片的线程
                synchronized (object) {
                    object.notify();
                }
                synchronized (jj) {
                    try {
                        jj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("开始下载附件");
                for (int i = 0; i < 101; i += 10) {
                    System.out.println("download:" + i + "%");
                }
                System.out.println("图片附件下载完成");
            }
        };
//        download.start();
        show.start();
        download.start();
    }


}
