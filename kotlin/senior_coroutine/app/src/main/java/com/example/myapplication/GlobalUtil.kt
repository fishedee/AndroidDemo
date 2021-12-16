package com.example.myapplication

import java.time.Instant.now

fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")
