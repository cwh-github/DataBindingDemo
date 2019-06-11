package com.chainspower.model;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * Description:
 * Date：2019/6/11-11:18
 * Author: cwh
 */
public class ImageInfo {
    public ObservableField<String> url;

    public ObservableInt size;

    public ImageInfo(String url,int size) {
        this.url = new ObservableField<>(url);
        this.size = new ObservableInt(size);
    }

    public ObservableField<String> getUrl() {
        return url;
    }

    public void setUrl(ObservableField<String> url) {
        this.url = url;
    }

    public ObservableInt getSize() {
        return size;
    }

    public void setSize(ObservableInt size) {
        this.size = size;
    }


}
