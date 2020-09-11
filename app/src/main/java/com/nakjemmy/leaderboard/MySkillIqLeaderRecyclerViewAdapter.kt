package com.nakjemmy.leaderboard

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nakjemmy.leaderboard.data.SkillIqLeader

import com.nakjemmy.leaderboard.dummy.DummyContent.DummyItem
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MySkillIqLeaderRecyclerViewAdapter(
    private val values: List<SkillIqLeader>
) : RecyclerView.Adapter<MySkillIqLeaderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_leader_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Picasso.get()
            .load(item.badgeUrl)
            .into(holder.badge)
        holder.title.text = item.name
        holder.subtitle.text = "${item.score} skill IQ Score, ${item.country}"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val badge: ImageView = view.findViewById(R.id.image_card_badge)
        val title: TextView = view.findViewById(R.id.text_card_title)
        val subtitle: TextView = view.findViewById(R.id.text_card_subtitle)

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }
}