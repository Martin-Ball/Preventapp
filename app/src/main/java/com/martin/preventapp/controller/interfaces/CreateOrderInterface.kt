package com.martin.preventapp.controller.interfaces

interface CreateOrderInterface {
    interface View {

    }

    interface Controller {
        fun onProductSelected(item:String)
    }

    interface Model {

    }
}