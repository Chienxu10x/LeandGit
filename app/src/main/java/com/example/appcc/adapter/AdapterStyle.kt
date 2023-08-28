package com.example.appcc.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R



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
        val item = itemList[position]
        val typeface: Typeface = Typeface.createFromAsset(context.assets, item)

        holder.itemText.typeface = typeface
        holder.itemText.text = string
        holder.imageViewCopy.setOnClickListener{
            val textToCopy = string
            val clipboardManager = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Copied Text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "Finish Copy", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

//
//class AdapterStyle(private val context: Context,private val data: List<String> ) : BaseAdapter() {
//    private var string:String=""
//
//    fun UpdateString(string: CharSequence?){
//        if (string == null){
//            this.string = "Well"
//        }else{
//            this.string = string.toString()
//            notifyDataSetChanged()
//        }
//
//    }
//    override fun getCount(): Int {
//        return data.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return data[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//    @SuppressLint("ViewHolder")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//
//        val view : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val convertView = view.inflate(R.layout.item_style, null)
//
//        val item = getItem(position) as String
//        val typeface: Typeface = Typeface.createFromAsset(context.assets, item)
//        val textView : TextView = convertView.findViewById(R.id.text_style)
//        textView.typeface = typeface
//        textView.text = string
//
//        return convertView;
//    }
//
//}