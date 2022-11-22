package com.example.biaspediav4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class B_RecyclerViewAdapter extends RecyclerView.Adapter<B_RecyclerViewAdapter.MyViewHolder> implements Filterable {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<BiasModel> biasModels;
    ArrayList<BiasModel> biasModelFull; //$

    public B_RecyclerViewAdapter(Context context, ArrayList<BiasModel> biasModels,
                                 RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.biasModels = biasModels;
        this.biasModelFull = new ArrayList<>(biasModels); //$
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public B_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This method inflates the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new B_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull B_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigns value to each rows as they come back to screen
        //based on position of recycler view
        holder.tvName.setText(biasModels.get(position).getBiasName());
        holder.imageView.setImageResource(biasModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return biasModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing views from recycler_view_row layout file

        ImageView imageView;
        TextView tvName; //update when more fields

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    @Override // Following is new
    public Filter getFilter() {
        return biasFilter;
    }

    private Filter biasFilter = new Filter() { //following is new

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<BiasModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(biasModelFull);
            } else {
                String biasPattern = charSequence.toString().toLowerCase().trim();

                for (BiasModel item : biasModelFull) {
                    if (item.getBiasName().toLowerCase().contains(biasPattern)) { //fix
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            biasModels.clear();
            biasModels.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

}
