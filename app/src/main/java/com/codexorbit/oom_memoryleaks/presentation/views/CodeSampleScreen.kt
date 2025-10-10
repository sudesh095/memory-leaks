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
import com.codexorbit.oom_memoryleaks.utils.AppConstants.BACK

@Composable
fun CodeSampleScreen(
    onClick:(String) -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        CommonToolbar("Code Samples", onBackClick = {onClick(BACK)})

        /******************************************************************************************************************************************************************/

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
        /******************************************************************************************************************************************************************/

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
        /******************************************************************************************************************************************************************/


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
        /******************************************************************************************************************************************************************/


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
        /******************************************************************************************************************************************************************/



        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Bitmap Too Large",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "val bitmap = BitmapFactory.decodeResource(resources, R.drawable.large_image)\n" +
                        "imageView.setImageBitmap(bitmap) // may OOM",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Down Sample Bitmap",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "val options = BitmapFactory.Options().apply { inSampleSize = 4 }\n" +
                        "val bitmap = BitmapFactory.decodeResource(resources, R.drawable.large_image, options)\n" +
                        "imageView.setImageBitmap(bitmap)\n" +
                        "Note: inSampleSize=4 means the image is scaled down by 4×.",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/


        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Large List in Memory",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "val bigList = MutableList(10_000_000) { it } // ~80MB+\n" +
                        "Log.d(\"OOM\", \"List size: bigList.size\")",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Use paging, streaming, or database cursor",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "val flow = flow {\n" +
                        "    for (i in 0..100_000) emit(i) // Stream items\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/



        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Forgot to Close Resources",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "val inputStream = FileInputStream(file)\n" +
                        "val text = inputStream.readBytes() //  Not closed",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Always use .use {} block to auto-close.",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "FileInputStream(file).use { input ->\n" +
                        "    val text = input.readBytes()\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/


        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Animated Drawable with Many Frames",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "imageView.setBackgroundResource(R.drawable.large_frame_animation)\n" +
                        "val animation = imageView.background as AnimationDrawable\n" +
                        "animation.start() //  All frames in memory",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Use AnimatedImageDrawable (API 28+)",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "imageView.setBackgroundResource(R.drawable.large_frame_animation)\\n\" +\n" +
                        "                        \"val animation = imageView.background as AnimatedImageDrawable\\n\" +\n" +
                        "                        \"animation.start()\n" +
                        "// Or Lottie animation (optimized vector JSON)",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/


        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Fragment Retained Improperly",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "val fragment = MyFragment()\n" +
                        "fragment.retainInstance = true // Old approach leaks memory",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Use ViewModel",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "// Don’t retain manually; use ViewModel to keep data\n" +
                        "class MyViewModel : ViewModel() {\n" +
                        "    val name = MutableLiveData<String>()\n" +
                        "}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Huge JSON Parsing",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "val jsonString = assets.open(\"bigdata.json\").bufferedReader().use { it.readText() }\n" +
                        "val jsonArray = JSONArray(jsonString) //  Loads full file in memory",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Reads Stream Wise",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "val reader = JsonReader(InputStreamReader(assets.open(\"bigdata.json\")))\n" +
                        "reader.beginArray()\n" +
                        "while (reader.hasNext()) {\n" +
                        "    val obj = reader.nextString() // Reads stream-wise\n" +
                        "}\n" +
                        "reader.endArray()\n" +
                        "reader.close()",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Glide Misuse / Cache Overflow",style = TextStyle(
                color = Color.Red
            ))
            Text(
                "Glide.with(this)\n" +
                        "    .load(url)\n" +
                        "    .submit() // Keeps bitmap without clearing",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }

        Column(Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Black, shape = RoundedCornerShape(12.dp)).padding(10.dp), horizontalAlignment = Alignment.Start) {
            Text("Limit Cache Size,  Scope Loading",style = TextStyle(
                color = Color.Green
            ))
            Text(
                "Glide.with(this)\n" +
                        "    .load(url)\n" +
                        "    .into(imageView)\n\n\n" +
                        "//Also limit cache:" +
                        "Glide.get(this).setMemoryCategory(MemoryCategory.LOW)",
                style = TextStyle(
                    color = Color.Gray
                )
            )
        }
        /******************************************************************************************************************************************************************/


    }


}