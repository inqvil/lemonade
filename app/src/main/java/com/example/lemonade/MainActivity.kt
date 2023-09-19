package com.example.lemonade


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()

                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview(showBackground = true)
    fun LemonadeApp() {




        var currentStep by remember { mutableStateOf(1) }
        var count by remember { mutableStateOf(1) }

        when (currentStep) {
            1 -> LemonAndTextImage(
                textResourceId = R.string.lemon_tree,
                imageResourceId = R.drawable.lemon_tree,
                onImageClick = {
                    currentStep = 2
                    count = (2..4).random()
                })
            2 -> LemonAndTextImage(
                textResourceId = R.string.lemon,
                imageResourceId = R.drawable.lemon_squeeze,
                onImageClick = {
                    count--
                    if (count == 0) {
                        currentStep = 3
                    }})
            3 -> LemonAndTextImage(
                textResourceId = R.string.glass_of_lemonade,
                imageResourceId = R.drawable.lemon_drink,
                onImageClick = { currentStep = 4 })
            4 -> LemonAndTextImage(
                textResourceId = R.string.empty_glass,
                imageResourceId = R.drawable.lemon_restart,
                onImageClick = { currentStep = 1 })


        }
    }


    @Composable
    fun LemonAndTextImage(
        textResourceId: Int,
        imageResourceId: Int,
        onImageClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(id = textResourceId), fontSize =18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = stringResource(id = textResourceId),
                modifier = Modifier
                    .clickable(onClick = onImageClick)
                    .border(
                        BorderStroke(2.dp, Color(900, 200, 200)),
                        shape = RoundedCornerShape(44.dp),
                    )
                    .padding(16.dp)
            )
        }
    }

}
