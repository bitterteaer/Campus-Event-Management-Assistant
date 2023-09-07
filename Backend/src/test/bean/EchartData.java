package test.bean;

import java.util.Arrays;

/**
 * @author xzy
 * @create 2021/10/29 23:50
 */
public class EchartData {
    private int[] data;

    @Override
    public String toString() {
        return "Echart{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
