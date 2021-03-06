package com.example.arcore30

sealed class Model {
    abstract val degresPerSeconds:Float
    abstract val radius:Float
    abstract val height:Float
    abstract val rotateToDegrees:Float



    object Bee:Model(){
        override val degresPerSeconds: Float
            get() = 30f
        override val radius: Float
            get() = 5f
        override val height: Float
            get() = 0.7f
        override val rotateToDegrees: Float
            get() = 180f
    }


    object Fly:Model(){
        override val degresPerSeconds: Float
            get() = 30f
        override val radius: Float
            get() = 1.5f
        override val height: Float
            get() = 0.7f
        override val rotateToDegrees: Float
            get() = 180f
    }

    object Skeb:Model(){
        override val degresPerSeconds: Float
            get() = 30f
        override val radius: Float
            get() = 1.5f
        override val height: Float
            get() = 0.7f
        override val rotateToDegrees: Float
            get() = 180f
    }


}