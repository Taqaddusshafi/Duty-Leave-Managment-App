import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dutyleavemanagementapp.databinding.UseritemdltBinding

import com.google.firebase.firestore.FirebaseFirestore

import com.squareup.picasso.Picasso
class imageadapter2(private var mList: MutableList<String>) :
    RecyclerView.Adapter<imageadapter2.ImagesViewHolder>() {

    inner class ImagesViewHolder(var binding: UseritemdltBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mRemoveButton = binding.mRemoveButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = UseritemdltBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImagesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding){
            val item = mList[position]
            val parts = item.split("|")
            val imageUrl = parts[0]
            val imageLink = parts[1]
            Picasso.get().load(imageUrl).into(imageView)
            holder.mRemoveButton.setOnClickListener {
                // Remove the item on remove/button click
                mList.removeAt(position)
                notifyDataSetChanged()

                // Delete the corresponding document from Firebase
                val firebaseFirestore = FirebaseFirestore.getInstance()
                firebaseFirestore.collection("images")
                    .whereEqualTo("pic", imageUrl)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            document.reference.delete()
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("TAG", "Error getting documents: ", exception)
                    }

                // Update the positions of the remaining items in Firebase
                for (i in position until mList.size) {
                    val item = mList[i]
                    val parts = item.split("|")
                    val imageUrl = parts[0]
                    val imageLink = parts[1]
                    firebaseFirestore.collection("images")
                        .whereEqualTo("pic", imageUrl)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                document.reference.update("pic", imageUrl, "text", imageLink)
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d("TAG", "Error getting documents: ", exception)
                        }
                }
            }
            imageView.setOnClickListener {
                // Open the link in a web browser
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(imageLink)
                root.context.startActivity(intent)
            }
        }
    }



    override fun getItemCount(): Int {
        return mList.size
    }
}
