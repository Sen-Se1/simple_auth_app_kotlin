package com.example.simple_auth_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_auth_app.data.Product
import com.example.simple_auth_app.ui.theme.Simple_auth_appTheme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Simple_auth_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val PREF_NAME = "MyPref"
    val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
//                prefs.edit().remove("State").apply()
                prefs.edit().putBoolean("State", false).apply()
                context.startActivity(Intent(context, MainActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Se deconnecter")
        }

        ListComposable()


    }
}

@Composable
fun ListComposable(modifier: Modifier = Modifier) {
    val list = remember {
        mutableListOf(
            Product("Product 1", 10.0, "Description 1", R.drawable.hyperx),
            Product("Product 2", 20.0, "Description 2", R.drawable.hyperx),
            Product("Product 3", 30.0, "Description 3", R.drawable.hyperx),
            Product("Product 4", 40.0, "Description 4", R.drawable.hyperx),
            Product("Product 5", 50.0, "Description 5", R.drawable.hyperx),
            Product("Product 6", 60.0, "Description 6", R.drawable.hyperx),
        )
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { prod ->
            ItemComposable(product = prod)
        }
    }

}

@Composable
fun ItemComposable(modifier: Modifier = Modifier, product: Product) {
    val context = LocalContext.current
    val Intent = Intent(context, ProductDescription::class.java)
    Card(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                Intent.putExtra("Prod_Name", product.name);
                Intent.putExtra("Prod_Price", product.price);
                Intent.putExtra("Prod_Desc", product.description);
                Intent.putExtra("Prod_Img", product.imageUrl);
                context.startActivity(Intent);
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = product.name,
                fontSize = 24.sp, fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Image(
                painter = painterResource(id = product.imageUrl), contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "$${product.price}",
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Simple_auth_appTheme {
        HomeScreen()
    }
}