package kpk.dev.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kpk.dev.presentation.R

@BindingAdapter("imageUrl")
fun bindImageView(
    view: ImageView,
    imageUrl: String
) {
    if (imageUrl.isEmpty()) {
        view.setImageResource(R.drawable.ic_avatar)
    } else {
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_avatar).into(view)
    }

}
