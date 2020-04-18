package sort;

/**
 * 几大排序算法
 */
public class SortAlgorithm {

    /**
     * 一个打印函数
     *
     * @param array
     */
    public static void displayArray(int[] array) {
        if (array == null) return;
        int len = array.length;
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * 交换数组中i，j两个位置的元素
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 冒泡排序,升序排序
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array == null) return null;

        int len = array.length;
        for (int i = len; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
        }
        return array;
    }

    /**
     * 选择排序
     *
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array) {
        if (array == null) return null;
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
        return array;
    }

    /**
     * 插入排序，其实就是默认左边的数组已经是有序的数组，右边一次用一个元素来与左边的有序数组来做比较，然后插入到有序数组的对应位置
     *
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array) {
        if (array == null) return null;
        int len = array.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return array;
    }

    /**
     * 对插入算法的优化,就是采用覆盖而不是直接交换
     * @param array
     * @return
     */
    public static void testInsertSort(int[] array){
        int len = array.length;
        for(int i = 1;i< len;i++){
            int temp = array[i],j=i;
            for(;j>0;j--){
                if(temp<array[j-1]){
                    array[j] = array[j-1];
                }else{
                    break;
                }
            }
            array[j] = temp;
        }
    }

    /**
     * 快速排序就是指定一个基准值，然后将小于基准值的元素都放到基准值的左边，将大于基准值的元素都放到基准值的右边，一直递归，直到完成排序
     *
     * @param array
     * @return
     */
    public static void quickSort(int[] array, int first, int last) {
        if (array == null) return;
        int i = first, j = last, temp = array[first];
        while (i < j) {
            while (i < j && temp <= array[j]) j--;
            array[i] = array[j];
            while (i < j && temp >= array[i]) i++;
            array[j] = array[i];
        }
        array[i] = temp;
        if (first < i - 1) quickSort(array, first, i - 1);
        if (last > i + 1) quickSort(array, i + 1, last);
    }

    /**
     * 归并排序
     *
     * @param array
     */
    public static void mergeSort(int[] array) {
        mergeSortFunc(array, 0, array.length - 1);
    }

    /**
     * 真正使用归并法的函数
     *
     * @param array
     * @param left
     * @param right
     */
    private static void mergeSortFunc(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //前一步是分
            mergeSortFunc(array, left, mid);
            mergeSortFunc(array, mid + 1, right);
            //后一步是合
            merge(array, left, mid, right, right - left + 1);
        }
    }

    /**
     * 合并两个有序数组
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     * @param len
     */
    private static void merge(int[] array, int left, int mid, int right, int len) {
        int i = left, j = mid + 1, k = 0;
        int[] temp = new int[len];
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (int m = left, n = 0; m <= right; m++, n++) {
            array[m] = temp[n];
        }
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        int len = array.length;
        //第一次需要建立大顶堆，len/2-1其实就是最左边的子节点的父节点，比如数组有五个元素，那么，就是下标为1的那个节点，注意，数组的下标是从0开始的
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, len);
        }
        //然后我们每次都将最大的元素与最后一个元素进行交换，再重新使之成为大顶堆
        for (int i = len - 1; i >= 0; i--) {
            swap(array, i, 0);
            adjustHeap(array, 0, i);
        }
    }

    /**
     * 调整数组使之成为大顶堆
     *
     * @param array
     * @param i
     * @param len
     */
    private static void adjustHeap(int[] array, int i, int len) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < len; k = k * 2 + 1) {
            //k+1其实就是下标为i的节点的右边孩子
            if (k + 1 < len && array[k] < array[k + 1]) {
                k++;
            }
            if (temp < array[k]) {
                //如果父节点的值小于左边或者右边孩子的值，那么我们需要交换他们的位置
                swap(array, i, k);
                //还需要看看他对于子节点的影响，如果是让子节点维持不了大顶堆的话，就需要继续交换调整
                i = k;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 8, 2, 3, 3};
//        SortAlgorithm.bubbleSort(array);
//        SortAlgorithm.selectSort(array);
//        SortAlgorithm.insertSort(array);
        SortAlgorithm.testInsertSort(array);
//        SortAlgorithm.quickSort(array,0,array.length-1);
//        SortAlgorithm.mergeSort(array);
//        SortAlgorithm.heapSort(array);
//        swap(array,0,4);
        SortAlgorithm.displayArray(array);
    }
}
