package com.cng.carfelchemsafety

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform