package com.lokalassignmentapp.common

// to show progress and message on screen, message which is also snack bar,
// Pair<Any,Boolean> in this boolean to check if to show or not and
// Any is used to show String or string resource so if Any is Int so its a resource else its string
data class ScreenState(
  val loading: Pair<Any,Boolean> = Pair("",false),
  val message: Pair<Any,Boolean> = Pair("",false),
)