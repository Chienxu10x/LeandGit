package com.example.appcc.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R
import com.fansipan.text.repeater10k.util.TextStylish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AdapterStyle(private val context: Context,private val itemList: List<String> ) :
    RecyclerView.Adapter<AdapterStyle.ViewHolder>() {


    private var string:String=""

    fun UpdateString(string: CharSequence?) {
            this.string = string.toString()
            notifyDataSetChanged()

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.text_style)
        val imageViewCopy: ImageView = itemView.findViewById(R.id.image_copy)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_style, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = itemList[position]
//        val typeface: Typeface = Typeface.createFromAsset(context.assets, item)

//        holder.itemText.typeface = typeface
//        holder.itemText.text = string
        if (string.equals("")){
            applyFont(position, "WELCOM MY APP"){result->
                holder.itemText.text = result
            }
        }else{
            applyFont(position, string){result->
                holder.itemText.text = result
            }
        }

        holder.imageViewCopy.setOnClickListener{
            applyFont(position,holder.itemText.text.toString()){result->
                val textToCopy = result
                val clipboardManager = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Copied Text", textToCopy)
                clipboardManager.setPrimaryClip(clipData)
                val toast = Toast(context)
                toast.duration = Toast.LENGTH_SHORT

                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val layout = inflater.inflate(R.layout.custom_toast, null)

// Customize the content of the Toast layout here if needed
                val toastText = layout.findViewById<TextView>(R.id.toast_text)
                toastText.text = "Finish Copy"

                toast.view = layout
                toast.setGravity(Gravity.CENTER, 0, 0) // Set gravity to center

                toast.show()
//                CustomToast.makeText(context, "Finish Copy", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun getItemCount(): Int {
        return 20
    }
    fun applyFont(position:Int,str: String,onDone:(rs:String)-> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result=when(position){
                0-> TextStylish.font1(str)
                1->TextStylish.font2(str)
                2->TextStylish.font3(str)
                3->TextStylish.font4(str)
                4->TextStylish.font5(str)
                5->TextStylish.font6(str)
                6->TextStylish.font7(str)
                7->TextStylish.font8(str)
                8->TextStylish.font9(str)
                9->TextStylish.font10(str)
                10->TextStylish.font11(str)
                11->TextStylish.font12(str)
                12->TextStylish.font13(str)
                13->TextStylish.font14(str)
                14->TextStylish.font15(str)
                15->TextStylish.font16(str)
                16->TextStylish.font17(str)
                17->TextStylish.font18(str)
                18->TextStylish.font19(str)
                19->TextStylish.font20(str)
                else -> {str}
            }
            withContext(Dispatchers.Main){
                onDone.invoke(result)
            }
        }


    }
}
