package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp(){
    var currentStep by remember { mutableStateOf(1) }
    var squeezzeCount by remember { mutableStateOf(0) }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        when(currentStep){
            1->{
                LemonTextAndImage(
                    textLabelResourceId=R.string.lemon_select,
                    drawableResourceId=R.drawable.lemon_tree,
                    contentDescriptionResourceId=R.string.lemon_tree_content_description,
                    onImageClick={
                        currentStep=2
                        squeezzeCount=(2..4).random()
                    }
                )
            }
            2->{
                LemonTextAndImage(
                    textLabelResourceId=R.string.lemon_squeeze,
                    drawableResourceId=R.drawable.lemon_squeeze,
                    contentDescriptionResourceId=R.string.lemon_content_description,
                    onImageClick={
                        squeezzeCount--
                        if(squeezzeCount==0){
                            currentStep=3
                        }
                    }
                )
            }
            3->{
                LemonTextAndImage(
                    textLabelResourceId=R.string.lemon_drink,
                    drawableResourceId=R.drawable.lemon_drink,
                    contentDescriptionResourceId=R.string.lemonade_content_description,
                    onImageClick={
                        currentStep=4
                    }
                )
            }
            4->{
                LemonTextAndImage(
                    textLabelResourceId=R.string.lemon_empty_glass,
                    drawableResourceId=R.drawable.lemon_restart,
                    contentDescriptionResourceId=R.string.empty_glass_content_description,
                    onImageClick={
                        currentStep=1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId:Int,
    drawableResourceId:Int,
    contentDescriptionResourceId:Int,
    onImageClick:()->Unit,
    modifier: Modifier=Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResourceId) ,
            contentDescription = stringResource(id = contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105,205,216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonApp()
    }
}