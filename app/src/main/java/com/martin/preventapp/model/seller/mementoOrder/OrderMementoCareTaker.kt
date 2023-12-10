package com.martin.preventapp.model.seller.mementoOrder

class OrderMementoCareTaker {
    private val mementos: MutableList<OrderMementoEntity> = mutableListOf()

    fun saveState(memento: OrderMementoEntity) {
        mementos.add(memento)
    }

    fun restoreState(index: Int): OrderMementoEntity {
        return mementos[index]
    }

    fun getMementoCount(): Int {
        return mementos.size
    }
}