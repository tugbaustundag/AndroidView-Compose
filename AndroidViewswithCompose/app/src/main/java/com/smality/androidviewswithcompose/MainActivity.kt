package com.smality.androidviewswithcompose

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import com.smality.androidviewswithcompose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ComposeView id'si üzerinden apply özelliğini çağırıyoruz
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
           //Compose oluşumu için setContent özelliğini çağırıyoruz
            setContent {
                Column(modifier = Modifier.fillMaxWidth()) {
                    //Text Compose oluşturuyoruz...
                    Text(
                        text = "Hello Compose Text!",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp
                    )
                    //16dp lık bir boşluk bırakalım
                    Spacer(modifier = Modifier.height(16.dp))
                    //Compose içindeyken TextView elementini ekleyelim
                    AndroidView(
                        modifier = Modifier.fillMaxWidth(),
                        factory = { context ->
                            TextView(context)
                        }) { textView ->
                        //TextView boyutlandırma özellikleri
                        textView.apply {
                            textView.layoutParams = ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )
                            text = "This is a TextView inside compose"
                            textSize = 25F
                            setTextColor(Color.BLACK)
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }
                    }
                }
            }
        }
    }
}