package com.example.ktorclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktorclient.data.remote.PostService
import com.example.ktorclient.data.remote.dto.PostResponse
import com.example.ktorclient.ui.theme.KtorClientTheme

class MainActivity : ComponentActivity() {
    private val service = PostService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val post =  produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                  value =  service.getPost()
                }
            )

            KtorClientTheme {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(post.value.size){
                        page->


                        Column {
                            Text(text = post.value[page].title, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = post.value[page].body, fontSize = 10.sp)
                        }

                    }
                }

            }
        }
    }
}
