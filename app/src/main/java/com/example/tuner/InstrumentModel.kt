package com.example.tuner

class InstrumentModel(id: Int?, name: String) {

    var id : Int = 0
    var name : String = ""

    init {
        if (id != null) {
            this.id = id
        }
        this.name = name
    }

    override fun toString(): String {
        return this.name
    }
}