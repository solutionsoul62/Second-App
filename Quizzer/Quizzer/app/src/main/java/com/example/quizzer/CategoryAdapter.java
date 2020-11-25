package com.example.quizzer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList)
    {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.setData(categoryModelList.get(position).getUrl(),categoryModelList.get(position).getSets() ,categoryModelList.get(position).getName());

    }

    @Override
    public int getItemCount()
    {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CircleImageView imageView;
        private TextView title;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_view);
            title=itemView.findViewById(R.id.title_text);

        }
        private void setData(String url, final int sets, final String title)
        {
            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.title.setText(title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Intent setintent=new Intent(itemView.getContext(), SetsActivity.class);

                    setintent.putExtra("title",title);
                    setintent.putExtra("sets",sets);
                    itemView.getContext().startActivity(setintent);

                }
            });


        }

    }
}
