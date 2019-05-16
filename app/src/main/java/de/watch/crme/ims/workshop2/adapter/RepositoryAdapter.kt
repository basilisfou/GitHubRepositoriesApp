package de.watch.crme.ims.workshop2.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.watch.crme.ims.workshop2.R
import de.watch.crme.ims.workshop2.model.Repository
import kotlinx.android.synthetic.main.repository_item.view.*
import android.view.LayoutInflater
import com.bumptech.glide.Glide


class RepositoryAdapter(private val repositoryList : List<Repository>, private val onClickListener: RepositoryOnClickListener) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositoryList[position]

        holder.apply {
            itemView.nameRepository.text = repository.name

            Glide
                .with(itemView.context)
                .load(repository.owner.avatar)
                .centerCrop()
                .into(itemView.imageView)

            itemView.nameOwner.text =  repository.owner.name

            itemView.description.text = repository.description

            itemView.setOnClickListener {
                onClickListener.onClickListener(repository.url)
            }
        }
    }

    class RepositoryViewHolder(view : View) : RecyclerView.ViewHolder(view)
}