package com.chainspower.model;

import androidx.databinding.*;
import com.chainspower.mytab.BR;

/**
 * Description:
 * Date：2019/6/10-11:50
 * Author: cwh
 */
public class Goods extends BaseObservable {
    @Bindable
    public float price;

    @Bindable
    public String goodsName;

    public String details;

    public  ObservableField<String> date;

    public ObservableInt nums;

    public ObservableBoolean isHave;

    public Goods(float price, String goodsName, String details, String date, int nums, boolean isHave) {
        this.price = price;
        this.goodsName = goodsName;
        this.details = details;
        this.date = new ObservableField<>(date);
        this.nums = new ObservableInt(nums);
        this.isHave = new ObservableBoolean(isHave);
    }


    public ObservableField<String> getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = new ObservableField<>(date);
    }

    public ObservableInt getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = new ObservableInt(nums);
    }

    public ObservableBoolean getIsHave() {
        return isHave;
    }

    public void setIsHave(boolean isHave) {
        this.isHave = new ObservableBoolean(isHave);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        //只对@Bindable的属性进行修改，需要声明修改的属性
        notifyPropertyChanged(BR.price);
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        //修改所有的属性
        notifyChange();
    }
}
