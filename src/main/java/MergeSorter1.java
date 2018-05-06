public class MergeSorter1 implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {
        int[] auxArray = new int[sourceArray.length];
        return mergeSort(sourceArray, auxArray, 0, sourceArray.length - 1);
    }

    /**
     * 基于分治思想，执行归并排序
     *
     * @param low  待排序数组下标下界
     * @param high 待排序数组下标上界
     */
    private int[] mergeSort(int[] array, int[] auxArray, int low, int high) {
        // 分治位置索引变量
        int dividedIndex = 0;
        if (low < high) {
            // 计算分治位置(采用简单的二分思想)
            dividedIndex = (low + high) / 2;
            // 左侧递归归并排序
            mergeSort(array, auxArray, low, dividedIndex);
            // 右侧递归归并排序
            mergeSort(array, auxArray, dividedIndex + 1, high);
            // 合并分治结果
            merge(array, auxArray, low, dividedIndex, high);
        }
        return array;
    }

    private void merge(int[] array, int[] auxArray, int low, int dividedIndex, int high) {
        // 指向左半分区数组的指针
        int i = low;
        // 指向右半分区数组的指针
        int j = dividedIndex + 1;
        // 指向辅助区数组的指针
        int auxPtr = 0;
        // 合并两个有序数组：array[low..dividedIndex]与array[dividedIndex+1..high]。
        while (i <= dividedIndex && j <= high) {
            // 将两个有序的数组合并，排序到辅助数组auxArray中
            // 左侧数组array[low..dividedIndex]中的array[i]
            // 大于右侧数组array[dividedIndex+1..high]中的array[j]
            if (array[i] > array[j]) {
                auxArray[auxPtr++] = array[j++];
            } else {
                auxArray[auxPtr++] = array[i++];
            }
        }
        // 如果array[low..dividedIndex].length>array[dividedIndex+1..high].length，经过上面合并
        // array[low..dividedIndex]没有合并完，则直接将array[low..dividedIndex]中没有合并的元素复制到辅助数组auxArray中去
        while (i <= dividedIndex) {
            auxArray[auxPtr++] = array[i++];
        }
        // 如果array[low..dividedIndex].length<array[dividedIndex+1..high].length，经过上面合并
        // array[dividedIndex+1..high]没有合并完，则直接将array[dividedIndex+1..high]中没有合并的元素复制到辅助数组auxArray中去
        while (j <= high) {
            auxArray[auxPtr++] = array[j++];
        }
        // 最后把辅助数组auxArray的元素复制到原来的数组中去，归并排序结束
        for (auxPtr = 0, i = low; i <= high; i++, auxPtr++) {
            array[i] = auxArray[auxPtr];
        }
    }
}
