package com.example.simplearithmetic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplearithmetic.ui.theme.SimpleArithmeticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleArithmeticTheme {
                CalculationScreen()
            }
        }
    }
}

fun add(a:Float, b:Float): Float{
    return a + b
}
fun subtract(a:Float, b:Float): Float{
    return a - b
}
fun multiply(a:Float, b:Float): Float{
    return a * b
}
fun divide(a:Float, b:Float): Float{
    return a/b
}
fun modulus(a:Float, b:Float): Float{
    return a%b
}
@Composable
fun CalculationScreen(){
    var a by remember { mutableStateOf("")}
    var b by remember { mutableStateOf("")}
    var c by remember { mutableStateOf("")}
    var result by remember { mutableStateOf("0")}
    val operations = setOf('+','-','*','/','%')
    fun onTextChange1(newText: String){
        if(newText.isEmpty() || newText.matches("^\\d*\\.?\\d*\$".toRegex())){
            a = newText
        }
    }
    fun onTextChange2(newText: String){
        if(newText.isEmpty() || newText.matches("^\\d*\\.?\\d*\$".toRegex())){
            b = newText
        }
    }
    fun operationSelection(newText: String){
        if(newText.isEmpty() || newText.all{it in operations}){
            c = newText
        }
    }
    Column(modifier = Modifier.padding(top = 100.dp)) {

        OutlinedTextField(
            value = a,
            onValueChange = { newText -> onTextChange1(newText) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {Text(text = "Type in a number")}

        )
        OutlinedTextField(
            value = b,
            onValueChange = { newVal -> onTextChange2(newVal)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {Text(text = "Type in a number")}
        )
        OutlinedTextField(
            value = c,
            onValueChange = {newOp -> operationSelection(newOp)},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {Text(text = "Type one of the following operations: + - * / %")}
        )
        Row() {
            Button(onClick = {
                result =
                    when (c) {
                        "+" -> add(a.toFloat(), b.toFloat()).toString()
                        "-" -> subtract(a.toFloat(), b.toFloat()).toString()
                        "*" -> multiply(a.toFloat(), b.toFloat()).toString()
                        "/" -> (if (b.toFloat() == 0f) "Cannot divide by 0! I don't want to explode!" else divide(
                            a.toFloat(),
                            b.toFloat()
                        ).toString())

                        "%" -> modulus(a.toFloat(), b.toFloat()).toString()
                        else -> "Invalid operation"
                    }
            }) {
                Text(text = "Calculate")
            }
            if(result == "Cannot divide by 0"){
                Text(text = result, color = Color.Red, fontFamily = FontFamily.Monospace, modifier = Modifier.padding(start = 10.dp, top = 12.dp))
            }else {
                Text(text = result, fontFamily = FontFamily.Monospace, modifier = Modifier.padding(start = 10.dp, top = 12.dp))
            }
        }

    }


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleArithmeticTheme {
    }
}