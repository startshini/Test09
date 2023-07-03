package com.example.dfs

import java.time.LocalDate

class Calculation {

    val Date: LocalDate = LocalDate.now()
    val CurrentDate = Date.toString()
    val current = CurrentDate.split("-")
    var age: Int = 0

    //年齢計算
    fun ageCalc(birthDate: String): Int {
        val birth = birthDate.split("/")
        if (birth[0].toInt() > current[0].toInt() || birth[0].toInt() < 1900 || birth[1].toInt() < 1 || birth[1].toInt() > 12 || birth[2].toInt() < 1 || birth[2].toInt() > 31) {
            return -1
        } else {
            age = current[0].toInt() - birth[0].toInt()
            if (current[1].toInt() > birth[1].toInt()) {
                age -= 1
            } else {
                if (current[2].toInt() > birth[2].toInt()) {
                    age -= 1
                }
            }
        }
        return age
    }

    //一日の上限カロリーを計算
    fun limitCalorieCalc(weight: Double, height: Double, age: Int, gender: Int): Int {

        var limitCalorie: Int = -1

        if (gender == 0) {
            limitCalorie = ((13.397 * weight + 4.799 * height - 5.677 * age + 88.362) + (3 * weight * 1.05)).toInt()
        } else if (gender == 1) {
            limitCalorie = ((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) + (3 * weight * 1.05)).toInt()
        }

        return limitCalorie
    }

    fun difCalc(limit: Int, Intake: Int, Burned: Int):Int{
        val number = limit - Intake + Burned
        return number
    }
}