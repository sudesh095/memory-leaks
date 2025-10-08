package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.codexorbit.oom_memoryleaks.utils.AppConstants

@Composable
fun HomeScreen(
    onClick: (String) -> Unit,
    onLaunchActivity: (String, Boolean) -> Unit = { _, _ -> }
) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Context Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_CONTEXT, true) })
            MyButton(text = "Safe Context Passing", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_CONTEXT, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "View Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_VIEW, true) })
            MyButton(text = "Safe View Removing", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_VIEW, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Handler Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_HANDLER, true) })
            MyButton(text = "Safe Handler Unregistered", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_HANDLER, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Coroutine Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_COROUTINE_ACTIVITY, true) })
            MyButton(text = "Safe Coroutine Scope", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_COROUTINE_ACTIVITY, false
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Receiver Leak",
                onClick = { onLaunchActivity(AppConstants.LEAKY_RECEIVER, true) })
            MyButton(text = "Safe Receiver Unregister", color = Color.Green, onClick = {
                onLaunchActivity(
                    AppConstants.LEAKY_RECEIVER, false
                )
            })
        }

        Text("Below In Compose ")

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Leaky Observer",
                onClick = { onClick(AppConstants.LEAKY_OBSERVER) })

            MyButton(text = "Fixed Observer", color = Color.Green, onClick = {
                onClick(
                    AppConstants.FIXED_OBSERVER
                )
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyButton(
                text = "Leaky Coroutine",
                onClick = { onClick(AppConstants.LEAKY_COROUTINE) })

            MyButton(text = "Fixed Coroutine", color = Color.Green, onClick = {
                onClick(
                    AppConstants.FIXED_COROUTINE
                )
            })
        }


        Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Leaked Adapter",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "class LeakAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {\n" +
                        "    //Keeps Activity in memory if not cleared\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Safe Adapter",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "class SafeAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {\n" +
                        "    private val appContext = context.applicationContext // Safe\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Large Collection Unlimited Data",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "object CacheLeak {\n" +
                        "    val list = mutableListOf<Bitmap>()\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Limit Collection Size",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "object SafeCache {\n" +
                        "    val cache = LruCache<String, Bitmap>(10 * 1024 * 1024) // Bounded cache with limited size\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }


        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Drawable or Bitmap Leak",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "override fun onDestroy() {\n" +
                        "    super.onDestroy()\n" +
                        "    imageView.setImageBitmap(largeBitmap) // Holds ref\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Fixed Bitmap Leak",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "override fun onDestroy() {\n" +
                        "    super.onDestroy()\n" +
                        "    imageView.setImageDrawable(null) // Releases drawable memory\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }


        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Fragment Leak (View not released)",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "class LeakFragment : Fragment() {\n" +
                        "    private var binding: FragmentLeakBinding? = null\n" +
                        "\n" +
                        "    override fun onDestroy() {\n" +
                        "        super.onDestroy()\n" +
                        "        // forgot to nullify binding \n" +
                        "    }\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Release Binding",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "override fun onDestroyView() {\n" +
                        "    super.onDestroyView()\n" +
                        "    binding = null //  Releases views\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

    }


}

@Composable
fun MyButton(text: String, color: Color = Color.Red, onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(vertical = 10.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.7f),
            contentColor = Color.White
        )
    ) {
        Text(text)
    }
}