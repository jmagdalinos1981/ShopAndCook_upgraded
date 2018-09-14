package com.johnmagdalinos.android.shopandcook2.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.johnmagdalinos.android.shopandcook2.data.Repository
import com.johnmagdalinos.android.shopandcook2.data.ShoppingEntry

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private var shoppingList: LiveData<List<ShoppingEntry>>? = null

    /** Initiate the list shopping list */
    fun init(sortPair: Pair<String, Boolean>) {
//        shoppingList = when (sortPair.first) {
//            Constants.PREFS_METHOD_BY_NAME -> repository.getShoppingList(sortPair.second)
//            else -> repository.getShoppingListColor(sortPair.second)
//        }
        shoppingList = repository.getShoppingList(sortPair)
    }

    fun getShoppingList(): LiveData<List<ShoppingEntry>>? {
      return shoppingList
    }

    fun insertShoppingEntry(entry: ShoppingEntry){
        repository.insertRandomTestEntry()
    }

    fun updateShoppingEntry(entry: ShoppingEntry) {
        repository.updateShoppingEntry(entry)
    }

    fun deleteAllShopping() {
        repository.deleteAllShopping()
    }

    fun deleteShoppingEntry(entry: ShoppingEntry) {
        repository.deleteShoppingEntry(entry)
    }

    fun addTestEntries() {
        repository.insertRandomTestEntry()
    }

    fun addAllTestEntries() {
        repository.insertAllTestEntries()
    }
}