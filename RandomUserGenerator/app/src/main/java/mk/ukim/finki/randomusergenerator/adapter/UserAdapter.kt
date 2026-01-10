package mk.ukim.finki.randomusergenerator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mk.ukim.finki.randomusergenerator.R
import mk.ukim.finki.randomusergenerator.data.remote.dto.RandomUserDto

class UserAdapter(private val users: ArrayList<RandomUserDto> = ArrayList<RandomUserDto>())
    :RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var imageView: ImageView = view.findViewById(R.id.avatarIv)
        private var titleText: TextView = view.findViewById(R.id.emailTv)

        fun bind(user: RandomUserDto){

            Glide.with(imageView)
                .load(user.picture.large)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)

            titleText.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_random_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
       return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun updateUsers(newUsers: List<RandomUserDto>){
        users.clear()
        users.addAll(newUsers)
    }

}