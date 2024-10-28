package com.onestop.EventDecor.repository;

import com.onestop.EventDecor.model.ItemList;

import java.util.Optional;

public interface SearchRepository {

   Optional<ItemList> findById(String text);
}
