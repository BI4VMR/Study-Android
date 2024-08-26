package net.bi4vmr.study.transform;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/**
 * Name        : MyViewModel
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 14:51
 * <p>
 * Description : ViewModel：数据转换测试。
 */
public class MyViewModel2 extends ViewModel {

    private int num = 0;
    public final MutableLiveData<Integer> numberData = new MutableLiveData<>(num);

    // 自"numberData"转换而来的LiveData，每当"numberData"数值改变时，它的值变为"numberData"的平方值。
    public final LiveData<Integer> squaredData = Transformations.map(numberData, new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer input) {
            // 计算平方值并返回结果
            return squared(input);
        }
    });

    // 聚合LiveData，当上文的两个LiveData其中之一数值改变时，都将他们的数值叠加到自身。
    public final MediatorLiveData<Integer> mediatorData = new MediatorLiveData<>();

    {
        // 设置初始值为0
        mediatorData.setValue(0);
        // 注册事件源，每当"numberData"的值改变时触发。
        mediatorData.addSource(numberData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                // 此处为了演示方便已经初始化MediatorLiveData，实际使用时需要添加空值判断逻辑。
                assert mediatorData.getValue() != null;
                int current = mediatorData.getValue();
                // 当前数值加上"numberData"的数值。
                mediatorData.setValue(current + integer);
            }
        });

        // 注册事件源，每当"squaredData"的值改变时触发。
        mediatorData.addSource(squaredData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                assert mediatorData.getValue() != null;
                int current = mediatorData.getValue();
                // 当前数值加上"squaredData"的数值。
                mediatorData.setValue(current + integer);
            }
        });
    }

    // 数值增加
    public void plus() {
        // 改变数值
        num += 10;
        // 通知观察者数值发生变化
        numberData.setValue(num);
    }

    // 数值减少
    public void minus() {
        // 改变数值
        num -= 10;
        // 通知观察者数值发生变化
        numberData.setValue(num);
    }

    // 计算平方值
    private int squared(int raw) {
        return raw * raw;
    }
}
