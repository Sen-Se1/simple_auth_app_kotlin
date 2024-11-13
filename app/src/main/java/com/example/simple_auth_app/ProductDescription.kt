package com.example.simple_auth_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_auth_app.ui.theme.Simple_auth_appTheme

class ProductDescription : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val Prod_Name = intent.getStringExtra("Prod_Name") ?: ""
        val Prod_Price = intent.getDoubleExtra("Prod_Price",0.0)
        val Prod_Desc = intent.getStringExtra("Prod_Desc") ?: ""
        val Prod_Img = intent.getIntExtra("Prod_Img",0)

        enableEdgeToEdge()
        setContent {
            Simple_auth_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = Prod_Name,
                        price = Prod_Price,
                        description = Prod_Desc,
                        img = Prod_Img,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String , price: Double , description: String, img : Int, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = 24.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Image(
            painter = painterResource(id = img), contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = "$${price}",
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = description,
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
    }
}