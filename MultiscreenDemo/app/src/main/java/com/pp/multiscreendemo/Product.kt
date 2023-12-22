package com.pp.multiscreendemo

import java.io.Serializable

class Product(
    var productName : String,
    var quantity : Int,
    var price : Double
) : Serializable {

    var total : Double = 0.0
    var tax : Double = 0.0
    var finalPrice : Double = 0.0

    // fun calculateTotal()
    init {
        this.total = this.quantity * this.price
        this.tax = this.total * 0.13
        this.finalPrice = this.total + this.tax
    }

    override fun toString(): String {
        return "Product(productName='$productName'," +
                " quantity=$quantity," +
                " price=$price," +
                " total=$total," +
                " tax=$tax," +
                " finalPrice=$finalPrice)"
    }
}