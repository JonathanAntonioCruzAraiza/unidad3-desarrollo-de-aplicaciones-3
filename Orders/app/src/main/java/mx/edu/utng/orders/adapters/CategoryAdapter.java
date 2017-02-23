package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Category;

/**
 * Created by jony on 23/02/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter <CategoryAdapter.CategoryViewHolder> implements View.OnClickListener {


    List<Category> categories;
    View.OnClickListener listener;
    //Constructor
    public CategoryAdapter(List<Category> categories){
        this.categories = categories;
    }
    public View.OnClickListener getListener() {
        return listener;
    }
    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }



    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_category,parent,false);
        CategoryViewHolder holder=new CategoryViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {

        holder.tvCategoryName.setText(categories.get(position).getCategoryName());
        holder.tvListPosition.setText(categories.get(position).getListPosition());

        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }



    public static class CategoryViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cvCategory;
        TextView tvCategoryName;
        TextView tvListPosition;
        ImageView ivCategory;
        ImageButton btEditCategory;
        ImageButton btDeleteCategory;
        View.OnClickListener listener;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            cvCategory = (CardView) itemView.findViewById(R.id.cv_category);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tv_category_name);
            tvListPosition = (TextView) itemView.findViewById(R.id.tv_list_position);
            ivCategory = (ImageView) itemView.findViewById(R.id.iv_category);
            btDeleteCategory = (ImageButton) itemView.findViewById(R.id.bt_delete_category);
            btEditCategory = (ImageButton) itemView.findViewById(R.id.bt_edit_category);


            btDeleteCategory.setOnClickListener(this);
            btEditCategory.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }
        public void setListener(View.OnClickListener listener){
            this.listener=listener;
        }
    }
}
