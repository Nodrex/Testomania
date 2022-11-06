package com.earth.testomania.common.model

data class SelectedAnswer(
    var selectedKey: String = "", //i think here should be char from Quiz model
    var userSelected: Boolean = false,
)
