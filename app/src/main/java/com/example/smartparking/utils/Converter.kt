package com.example.smartparking.utils

interface Converter <in FROM, out TO> {
    fun convert(f: FROM) : TO
}