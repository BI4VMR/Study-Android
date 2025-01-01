package net.bi4vmr.aidl;

interface IExceptions {

    // 计算除法
    int divide(int a, int b);

    // 计算除法（已传递原始异常）
    int divide2(int a, int b);
}
