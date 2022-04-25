package com.swe.whatscooking.entity.KrogerAPI;

import java.util.List;

public class KrogerAPIResponse {
    private List<KrogerProduct> data;

    public KrogerAPIResponse(List<KrogerProduct> data) {
        this.data = data;
    }

    public List<KrogerProduct> getData() {
        return data;
    }

    public void setData(List<KrogerProduct> data) {
        this.data = data;
    }
}
