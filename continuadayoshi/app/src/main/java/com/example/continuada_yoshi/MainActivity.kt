package com.example.continuada_yoshi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var valido = false

    fun validar(validar:View){
        val nome = nome_usuario.text.toString()
        val salario = salario.text.toString().toDouble()
        val vt = vt.text.toString()

        if(nome == ""){
            tv_valido.setTextColor(Color.GREEN)
            tv_valido.text = "O nome não pode estar em branco" }

        else if(salario < 1045.00){
            tv_valido.setTextColor(Color.BLUE)
            tv_valido.text = "O salário tem que ser maior que R$1045.00, o atual salário mínimo" }

        else if(vt == ""){
            tv_valido.setTextColor(Color.CYAN)
            tv_valido.text = "O valor do vale transporte deve ser declarado" }

        else {
            tv_valido.setTextColor(Color.BLACK)
            tv_valido.text = "Todos os dados são válidos"
            valido = true; }

        tv_valido.visibility = View.VISIBLE
    }

    fun mensagem(mensagem:View){

        if(valido) {

            val nome = nome_usuario.text.toString()
            val salario = salario.text.toString().toDouble()
            val vtDeclarado = vt.text.toString().toDouble()

            val ir = calcularIr(salario)
            val vt = calcularDescontoVT(salario, vtDeclarado)


            tv_resultado.text = "Olá $nome, com base no seu salário o valor descontado de IR deverá ser $ir " +
                        "e o valor do seu Vale Transporte será $vt :)" }

        else{
            tv_resultado.setTextColor(Color.RED)
            tv_resultado.text = "Os campos devem estar válidos para realizar a operação" }

        tv_resultado.visibility = View.VISIBLE }


    fun calcularIr(salario:Double):Double{
        if(salario <= 1903.98){
            return 0.0
        }
        else if (salario in 1903.98..2826.65){
            return salario*0.075
        }
        else if(salario in 2826.66..3751.05){
            return  salario*0.15
        }
        else if (salario in 3751.06..4664.67){
            return  salario*0.225
        }
        else{
            return salario*0.275
        }
    }

    fun calcularDescontoVT(salario:Double, vt:Double):Double{
        val vtMaximo = salario*0.06

        if(vt <= vtMaximo){ return vt }

        else{ return vtMaximo}
    }
}
