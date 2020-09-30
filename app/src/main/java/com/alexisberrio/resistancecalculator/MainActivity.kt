package com.alexisberrio.resistancecalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object {
        private const val ohm = "Ω"
        private const val Null = ""
        private const val punto = "."
        private const val kilo = "K"
        private const val cero = "0"
        private const val mega = "M"
        private const val giga = "G"
    }

    private var banda1 = 1
    private var banda2 = 0
    private var multiplicador = 0
    private var tolerancia = 1
    private var sumabandas = Null
    private var resultado = "10Ω ±1%"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make adapterView
        val a1 =
            ArrayAdapter.createFromResource(this, R.array.coloresbanda1, R.layout.spinner_size)
        a1.setDropDownViewResource(R.layout.spinner_size)
        with(banda1_spinner)
        {
            adapter = a1
            setSelection(0)
            onItemSelectedListener = this@MainActivity
        }

        val a2 =
            ArrayAdapter.createFromResource(this, R.array.coloresbanda2, R.layout.spinner_size)
        a2.setDropDownViewResource(R.layout.spinner_size)
        with(banda2_spinner)
        {
            adapter = a2
            setSelection(0)
            onItemSelectedListener = this@MainActivity
        }
        with(multiplicador_spinner)
        {
            adapter = a2
            setSelection(0)
            onItemSelectedListener = this@MainActivity
        }

        val a3 =
            ArrayAdapter.createFromResource(this, R.array.colorestolerancia, R.layout.spinner_size)
        a3.setDropDownViewResource(R.layout.spinner_size)
        with(tolerancia_spinner)
        {
            adapter = a3
            setSelection(0)
            onItemSelectedListener = this@MainActivity
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        banda1 = 1 + banda1_spinner.selectedItemPosition
        banda2 = banda2_spinner.selectedItemPosition
        multiplicador = multiplicador_spinner.selectedItemPosition
        tolerancia = tolerancia_spinner.selectedItemPosition

       // sumabandas = banda1.toString() + banda2.toString()

        when(multiplicador){
            0 ->{
                resultado = banda1.toString() + banda2.toString() + ohm
            }
            1 -> {
                resultado = banda1.toString() + banda2.toString() + cero + ohm
            }
            2 -> {
                resultado = banda1.toString() + punto + banda2.toString() + kilo + ohm
            }
            3 -> {
                resultado = banda1.toString() + banda2.toString() + kilo + ohm
            }
            4 -> {
                resultado = banda1.toString()  + banda2.toString() + cero + kilo + ohm
            }
            5 -> {
                resultado = banda1.toString() + punto + banda2.toString() + mega + ohm
            }
            6 -> {
                resultado = banda1.toString() + banda2.toString() + mega + ohm
            }
            7 -> {
                resultado = banda1.toString() + banda2.toString() + cero + mega + ohm
            }
            8 -> {
                resultado = banda1.toString() + punto + banda2.toString() + giga + ohm
            }
            9 -> {
                resultado = banda1.toString() + banda2.toString() + giga + ohm
            }
        }
        when(tolerancia){
            0 -> resultado += " ±1%"
            1 -> resultado += " ±2%"
            2 -> resultado += " ±5%"
            3 -> resultado += " ±10%"

        }
        banda1 = 0
        banda2 = 0
        sumabandas = Null
        resultado_textView.text = resultado
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit
}