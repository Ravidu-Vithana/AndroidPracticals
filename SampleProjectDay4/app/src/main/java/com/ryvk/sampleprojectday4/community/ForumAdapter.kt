package com.ryvk.sampleprojectday4.community

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ryvk.sampleprojectday4.R

class ForumAdapter(private val forumList: List<ForumCard>) : RecyclerView.Adapter<ForumAdapter.forumViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): forumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_forum_card,parent,false)
        context = parent.context
        return forumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forumList.size
    }

    override fun onBindViewHolder(holder: forumViewHolder, position: Int) {
        val forumCard: ForumCard = forumList.get(position)
        var descriptionCropped: String = forumCard.description

        if(forumCard.description.length > 140){
            descriptionCropped = forumCard.description.substring(0,140)+"..."
            Log.d("description cropped", "onBindViewHolder: $descriptionCropped")
        }

        holder.title.text = forumCard.title
        holder.description.text = descriptionCropped
        holder.joinForumButton.setOnClickListener{
            Toast.makeText(context,"Joining forum ${forumCard.title} via link ${forumCard.link}",Toast.LENGTH_LONG).show()
        }

    }

    class forumViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title: TextView = itemView.findViewById(R.id.textView8)
        var description: TextView = itemView.findViewById(R.id.textView9)
        var joinForumButton: Button = itemView.findViewById(R.id.button3)
    }
}

data class ForumCard(var title: String, var description: String, var link: String){}