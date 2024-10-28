package com.onestop.EventDecor.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Arrays;
@Document(collection= "ItemList")
public class ItemList {
    private String _id;
    private int _amount;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }

    public ItemList() {
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "_id='" + _id + '\'' +
                ", _amount=" + _amount +
                '}';
    }
}

