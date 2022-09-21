package com.ldelaiglesia.tempedia.ui.temtemlist

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.model.api.Temtem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_temtem_search.view.*
import java.util.stream.Collectors

class TemtemListAdapter(val temtemClick: (Int) -> Unit) :
    RecyclerView.Adapter<TemtemListAdapter.SearchViewHolder>() {
    private var temtemList: MutableList<Temtem> = emptyList<Temtem>().toMutableList()
    private var originalList: MutableList<Temtem> = emptyList<Temtem>().toMutableList()

    fun setData(list: List<Temtem>) {
        temtemList.addAll(list)
        originalList.addAll(temtemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_temtem_search, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return temtemList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val temtem = temtemList[position]
        holder.itemView.temtemText.text = "#${temtem.number} - ${temtem.name}"
        Picasso.get().load(temtem.portraitList).into(holder.itemView.portraitList)
        holder.itemView.setOnClickListener { temtemClick(temtem.number) }
    }

    fun searchInput(query: String) {
        val length: Int = query.length
        if (length == 0) {
            temtemList.clear()
            temtemList.addAll(originalList)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val collection = temtemList.stream().filter { i ->
                    i.name.lowercase().contains(query.lowercase())
                }.collect(Collectors.toList())
                temtemList.clear()
                temtemList.addAll(collection)
            } else {
                for (temtem in originalList) {
                    if (temtem.name.lowercase().contains(query.lowercase())) {
                        temtemList.add(temtem)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}